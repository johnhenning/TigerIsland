package GameInteractionModule;

import GameStateModule.*;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Turn {
    public static boolean makeTileMove(Tile tile, GameState gameState) {
        if (gameState.placeTile(tile)) {
            return true;
        } else if (gameState.levelTile(tile)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean makeBuildMove(BuildMove buildMove, GameState gameState) {
        switch(buildMove.buildMoveType) {
            case FOUNDSETTLEMENT:
                try {
                    gameState.foundSettlement(buildMove.coordinate,gameState.getCurrentPlayer());
                } catch (AssertionError e) {
                    return false;
                }
                break;
            case EXPANDSETTLEMENT:
                try {
                    gameState.expandSettlement(buildMove.coordinate, gameState.getCurrentPlayer(), buildMove.terrainType);
                } catch (AssertionError e) {
                    return false;
                }
                break;
            case PLACETIGER:
                try {
                    gameState.placeTiger(buildMove.coordinate);
                } catch (AssertionError e){
                    return false;
                }
                break;
            case PLACETOTORO:
                try {
                    gameState.placeTotoro(buildMove.coordinate);
                } catch (AssertionError e){
                    return false;
                }
                break;
            default: return false;
        }
        gameState.switchPlayer();

        return true;
    }
}
