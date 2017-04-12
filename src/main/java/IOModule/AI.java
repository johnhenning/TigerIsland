package IOModule;

import GameInteractionModule.Rules.BuildRules;
import GameInteractionModule.Rules.SettlementFoundationRules;
import GameInteractionModule.Rules.TilePlacementRules;
import GameInteractionModule.Turn;
import GameStateModule.*;
import ServerModule.Adapter;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by johnhenning on 4/4/17.
 */
public class AI implements Player {
    @Override
    public void completeTurn(Message message, GameState gameState) {
        //Calculate Tile Placement
        ArrayList<Coordinate> tilePlacement = calculateTilePlacement(gameState);
        Tile tile = message.tile;
        tile.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile, gameState);

        BuildMove buildMove = new BuildMove(null, null, null);
        //Calculate Build Move
        if(gameState.getCurrentPlayer().getNumMeeples() > 0) {
            buildMove = calculateBuildMove(tile, gameState);
            message.buildMove = buildMove;
            Turn.makeBuildMove(buildMove, gameState);
        }
        else{
            message.buildMove = new BuildMove(BuildMoveType.UNABLE_TO_BUILD, null ,null);

        }

        //Send updated serverMessage back to server
        sendMessageToServer(message);
    }

    private BuildMove calculateBuildMove(Tile tile, GameState gameState) {
        Coordinate coordinate = new Coordinate(-1, -1);
        for(Hex h: tile.getHexes()){
            if(h.getTerrain() != TerrainType.VOLCANO){
                coordinate = h.getCoordinate();
            }
        }
        return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, coordinate, null);
    }

    private ArrayList<Coordinate> calculateTilePlacement(GameState gameState) {
        ArrayList<Tile> placedTiles = gameState.getGameboard().getPlacedTiles();
        Hex h = calculateBottomRightMostHex(placedTiles);
        Coordinate c = Adapter.downRight(h.getCoordinate());
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(c);
        coords.add(Adapter.downRight(c));
        coords.add(Adapter.downLeft(c));

        return coords;
    }

    public Hex calculateBottomRightMostHex(ArrayList<Tile> placedTiles){
        int max = 0;
        Tile bottomRightTile;
        Hex bottomRightHex = new Hex(null, null);
        for(Tile t: placedTiles){
            for(Hex h : t.getHexes()){
                int coord_val = h.getCoordinate().getX() + h.getCoordinate().getY();
                if(coord_val > max) {
                    max = coord_val;
                    bottomRightHex = h;
                }
            }
        }
        return bottomRightHex;
    }

    private void sendMessageToServer(Message message) {

    }


    public boolean isNewSettlementLarger(Settlement previous, Settlement current){
        if(previous == null){
            return true;
        }

        return previous.getSettlementCoordinates().size() < current.getSettlementCoordinates().size();

    }

    public Hex getBestHexForFoundation(GameState gameState){
        ArrayList<Settlement> lessThanSizeFivePlayerSettlements = getPlayerSettlementsLessThanFive(gameState);
        Settlement bestSettlment = null;
        Hex bestHex = null;
        for(Settlement s: lessThanSizeFivePlayerSettlements){
            ArrayList<Hex> adjacentHexes = getHexesAdjacentToSettlementLessThanFive(s, gameState);
            if((adjacentHexes != null) && isNewSettlementLarger(bestSettlment, s)) {
                bestSettlment = s;
                bestHex = adjacentHexes.get(0);
            }
        }
        return bestHex;
    }

    public ArrayList<Settlement> getPlayerSettlementsLessThanFive(GameState gameState){

        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        ArrayList<Settlement> playerSettlements = new ArrayList<>();
        ArrayList<Settlement> playerSettlementsLessThanFive = new ArrayList<>();
        for(Settlement s: settlementList){
            if(s.getOwner().equals(gameState.getCurrentPlayer())){
                playerSettlements.add(s);
            }
        }

        for(Settlement s: playerSettlements){
            if(s.getSize() <= 4){
                playerSettlementsLessThanFive.add(s);
            }
        }
        return playerSettlementsLessThanFive;
    }

    public ArrayList<Hex> getHexesAdjacentToSettlementLessThanFive(Settlement playerSettlement, GameState gameState){

        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();


        for(Coordinate c: playerSettlement.getSettlementCoordinates()){
            Hex hex = gameboard.getHexFromCoordinate(c);
            for(Hex h : TilePlacementRules.getAdjacentHexes(hex, gameboard)){
                if(!containsHex(adjacentHexes, h)){
                    adjacentHexes.add(h);
                }
            }
        }


        for(int i = 0; i < adjacentHexes.size(); i++){
            if(!SettlementFoundationRules.isValidFoundation(adjacentHexes.get(i), gameState.getCurrentPlayer())){
                adjacentHexes.remove(i--);
            }
        }

        return adjacentHexes;
    }

    public static boolean containsHex(ArrayList<Hex> hexes, Hex hex){
        for(Hex h : hexes){
            if(h.equals(hex)){
                return true;
            }
        }
        return false;
    }


    public ArrayList<Hex> getHexesAdjacentToSettlementsLessThanFive(ArrayList<Settlement> playerSettlements, GameState gameState) {

        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();

        for (Settlement s : playerSettlements) {
            for (Coordinate c : s.getSettlementCoordinates()) {
                Hex hex = gameboard.getHexFromCoordinate(c);
                for (Hex h : TilePlacementRules.getAdjacentHexes(hex, gameboard)) {
                    if (!containsHex(adjacentHexes, h)) {
                        adjacentHexes.add(h);
                    }
                }
            }
        }

        for(int i = 0; i < adjacentHexes.size(); i++){
            Hex hex = adjacentHexes.get(i);
            if(!SettlementFoundationRules.isValidFoundation(hex, gameState.getCurrentPlayer())){
                adjacentHexes.remove(i--);
            }
        }
        return adjacentHexes;
    }
}
