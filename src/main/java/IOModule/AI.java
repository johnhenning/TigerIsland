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

    private BuildMove calculateBuildMove(GameState gameState) {
        return new BuildMove(BuildMoveType.EXPANDSETTLEMENT,new Coordinate(0,0), TerrainType.GRASSLAND);
    }

    private ArrayList<Coordinate> calculateTilePlacement(Tile tile, GameState gameState) {

        return new ArrayList<Coordinate>();
    }

    private void sendMessageToServer(Message message) {
    }
}
