package IOModule;

import GameInteractionModule.Turn;
import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by johnhenning on 4/4/17.
 */
public class AI implements Player {
    @Override
    public void completeTurn(Message message, GameState gameState) {
        //Calculate Tile Placement
        calculateRightMostCoordinate(gameState);
        ArrayList<Coordinate> tilePlacement = calculateTilePlacement(message.tile, gameState);
        Tile tile = message.tile;
        tile.setCoordinates(tilePlacement);
        Turn.makeTileMove(tile, gameState);

        //Calculate Build Move
        BuildMove buildMove = calculateBuildMove(gameState);
        message.buildMove = buildMove;
        Turn.makeBuildMove(buildMove, gameState);

        //Send updated message back to server
        sendMessageToServer(message);
    }

    private ArrayList<Coordinate> calculateTilePlacement(Tile tile, GameState gameState) {
        Coordinate rightMostCoordinate = gameState.getRightMostCoordinate();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        if (rightMostCoordinate.getY() % 2 == 0) {
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 1, rightMostCoordinate.getY()));
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 2, rightMostCoordinate.getY()));
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 1, rightMostCoordinate.getY() - 1));

        } else {
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 1, rightMostCoordinate.getY()));
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 2, rightMostCoordinate.getY()));
            coordinates.add(new Coordinate(rightMostCoordinate.getX() + 2, rightMostCoordinate.getY() - 1));
        }
        return coordinates;
    }

    private BuildMove calculateBuildMove(GameState gameState) {
        ArrayList<Tile> placedTiles = gameState.getGameboard().getPlacedTiles();
        Tile mostRecentTile = placedTiles.get(placedTiles.size() - 1);
        Coordinate coordinate = new Coordinate(0,0);
        for (Hex hex : mostRecentTile.getHexes()) {
            if (hex.getTerrain() != TerrainType.VOLCANO) {
                coordinate = hex.getCoordinate();
                break;
            }
        }

        return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, coordinate, null);
    }

    private void sendMessageToServer(Message message) {

    }

    private void calculateRightMostCoordinate(GameState gameState) {
        Coordinate rightMostCoordinate = gameState.getRightMostCoordinate();
        ArrayList<Tile> placedTiles = gameState.getGameboard().getPlacedTiles();
        Tile mostRecentTile = placedTiles.get(placedTiles.size() - 1);
        for (Hex hex : mostRecentTile.getHexes()) {
            boolean largerRowAndColumn = hex.getCoordinate().getX() > rightMostCoordinate.getX()
                    && hex.getCoordinate().getY() > rightMostCoordinate.getY();
            boolean evenRowSameColumn = hex.getCoordinate().getX() == rightMostCoordinate.getX()
                    && (rightMostCoordinate.getY() % 2 == 0);
            if (largerRowAndColumn || evenRowSameColumn) {
                rightMostCoordinate = hex.getCoordinate();
            }
        }
    }

}
