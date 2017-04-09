package IOModule;

import GameInteractionModule.Rules.BuildRules;
import GameInteractionModule.Turn;
import GameStateModule.*;
import ServerModule.Adapter;

import java.util.ArrayList;

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

        //Calculate Build Move
        if(gameState.getCurrentPlayer().getNumMeeples() > 0) {
            BuildMove buildMove = calculateBuildMove(tile, gameState);
            message.buildMove = buildMove;
            Turn.makeBuildMove(buildMove, gameState);
        }
        else{
            message.buildMove.buildMoveType = BuildMoveType.UNABLE_TO_BUILD;
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
}
