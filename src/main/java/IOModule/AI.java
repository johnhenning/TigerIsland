package IOModule;

import GameInteractionModule.Rules.BuildRules;
import GameInteractionModule.Rules.SettlementFoundationRules;
import GameInteractionModule.Rules.TilePlacementRules;
import GameInteractionModule.Rules.TotoroBuildRules;
import GameInteractionModule.Turn;
import GameStateModule.*;
import ServerModule.Adapter;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import static GameInteractionModule.Rules.BuildRules.isUnnocupied;
import static GameInteractionModule.Rules.Rules.*;

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
        if (gameState.getCurrentPlayer().getNumMeeples() > 0) {
            buildMove = calculateBuildMove(tile, gameState);
            message.buildMove = buildMove;
            Turn.makeBuildMove(buildMove, gameState);
        } else {
            message.buildMove = new BuildMove(BuildMoveType.UNABLE_TO_BUILD, null, null);

        }

        //Send updated serverMessage back to server
        sendMessageToServer(message);
    }

    public BuildMove calculateBuildMove(Tile tile, GameState gameState) {

        ArrayList<Hex> totoroHex = new ArrayList<>();
        Hex settlementHex = null;
        totoroHex = canPlaceTotoro(gameState);
        settlementHex = getBestHexForFoundation(gameState);


        if (totoroHex.size() != 0) {
            return new BuildMove(BuildMoveType.PLACETOTORO, totoroHex.get(0).getCoordinate(), null);
        } else if (settlementHex != null) {
            return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, settlementHex.getCoordinate(), null);
        } else {
            Coordinate coordinate = new Coordinate(-1, -1);
            for (Hex h : tile.getHexes()) {
                if (h.getTerrain() != TerrainType.VOLCANO) {
                    coordinate = h.getCoordinate();
                }
            }
            return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, coordinate, null);
        }

    }

    public ArrayList<Coordinate> calculateTilePlacement(GameState gameState) {
        ArrayList<Tile> placedTiles = gameState.getGameboard().getPlacedTiles();
        Hex h = calculateBottomRightMostHex(placedTiles);
        Coordinate c = Adapter.downRight(h.getCoordinate());
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(Adapter.downLeft(c));
        coords.add(c);
        coords.add(Adapter.downRight(c));

        return coords;
    }

    public Hex calculateBottomRightMostHex(ArrayList<Tile> placedTiles) {
        int max = 0;
        Tile bottomRightTile;
        Hex bottomRightHex = new Hex(null, null);
        for (Tile t : placedTiles) {
            for (Hex h : t.getHexes()) {
                int coord_val = h.getCoordinate().getX() + h.getCoordinate().getY();
                if (coord_val > max) {
                    max = coord_val;
                    bottomRightHex = h;
                }
            }
        }
        return bottomRightHex;
    }

    private void sendMessageToServer(Message message) {

    }

    public ArrayList<Settlement> getPlayerSettlementsLessThanFive(GameState gameState) {

        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        ArrayList<Settlement> playerSettlements = new ArrayList<>();
        ArrayList<Settlement> playerSettlementsLessThanFive = new ArrayList<>();
        for (Settlement s : settlementList) {
            if (s.getOwner().equals(gameState.getCurrentPlayer())) {
                playerSettlements.add(s);
            }
        }

        for (Settlement s : playerSettlements) {
            if (s.getSize() <= 4) {
                playerSettlementsLessThanFive.add(s);
            }
        }
        return playerSettlementsLessThanFive;
    }

    public boolean isNewSettlementLarger(Settlement previous, Settlement current) {
        if (previous == null) {
            return true;
        }

        return previous.getSettlementCoordinates().size() < current.getSettlementCoordinates().size();

    }

    public Hex getBestHexForFoundation(GameState gameState) {
        ArrayList<Settlement> lessThanSizeFivePlayerSettlements = getPlayerSettlementsLessThanFive(gameState);
        Settlement bestSettlment = null;
        Hex bestHex = null;
        for (Settlement s : lessThanSizeFivePlayerSettlements) {
            ArrayList<Hex> adjacentHexes = getHexesAdjacentToSettlementLessThanFive(s, gameState);
            if ((adjacentHexes != null) && isNewSettlementLarger(bestSettlment, s) && (adjacentHexes.size() >0)) {
                bestSettlment = s;
                bestHex = adjacentHexes.get(0);
            }
        }
        return bestHex;
    }

    public ArrayList<Hex> getHexesAdjacentToSettlements(ArrayList<Settlement> playerSettlements, GameState gameState) {

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

        for (int i = 0; i < adjacentHexes.size(); i++) {
            Hex hex = adjacentHexes.get(i);
            if (!SettlementFoundationRules.isValidFoundation(hex, gameState.getCurrentPlayer())) {
                adjacentHexes.remove(i--);
            }
        }
        return adjacentHexes;
    }

    public ArrayList<Hex> getHexesAdjacentToSettlementLessThanFive(Settlement playerSettlement, GameState gameState) {
        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();


        for (Coordinate c : playerSettlement.getSettlementCoordinates()) {
            Hex hex = gameboard.getHexFromCoordinate(c);
            for (Hex h : TilePlacementRules.getAdjacentHexes(hex, gameboard)) {
                if (!containsHex(adjacentHexes, h)) {
                    adjacentHexes.add(h);
                }
            }
        }


        for (int i = 0; i < adjacentHexes.size(); i++) {
            if (!SettlementFoundationRules.isValidFoundation(adjacentHexes.get(i), gameState.getCurrentPlayer())) {
                adjacentHexes.remove(i--);
            }
        }

        return adjacentHexes;
    }

    public static boolean containsHex(ArrayList<Hex> hexes, Hex hex) {
        for (Hex h : hexes) {
            if (h.equals(hex)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Hex> canPlaceTotoro(GameState gameState) {
        ArrayList<Settlement> playerSettlement = new ArrayList<>();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();
        playerSettlement = TotoroBuildRules.playerHasSizeFiveSettlement(gameState);
        if (playerSettlement.size() != 0) {
            for (Settlement s : playerSettlement) {
                if (TotoroBuildRules.settlementNotContainTotoros(s, gameState)) {
                    adjacentHexes = getHexesAdjacentToSettlementLessThanFive(s, gameState);
                    return adjacentHexes;
                }
            }
        }
        return adjacentHexes;
    }

    public ArrayList<Coordinate> expandHex(GameState gameState, Settlement settlement, TerrainType terrain){
        ArrayList<Coordinate> hexesEncountered = settlement.getSettlementCoordinates();
        ArrayList<Coordinate> newHexesAdded = new ArrayList<>();
        Stack<Coordinate> coords = new Stack();
        coords.addAll(hexesEncountered);

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoordinates = findAdjacentCoords(gameState.getGameboard(), terrain, currentAdjacentCoordinate);
            for(int i=0; i<neighboringCoordinates.size(); i++){
                if(contains(hexesEncountered, neighboringCoordinates.get(i))== false){
                    newHexesAdded.add(neighboringCoordinates.get(i));
                    hexesEncountered.add(neighboringCoordinates.get(i));
                    coords.push(neighboringCoordinates.get(i));
                }
            }
        }
        return newHexesAdded;
    }

    public void calculateExpansion(GameState gameState){


    }
    public static boolean contains(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Coordinate> findAdjacentCoords(Grid gameboard, TerrainType terrain, Coordinate coordinate){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        //we only want to add the coordinates, if they have the same terrain type, and they are unoccupied
        //if the match occurs we need to add the coordinates of that match to the array list
        Hex hex;
        hex = downLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = downRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = leftOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = rightOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());

        return adjacentCoordinates;
    }
}

