package GameInteraction;

import GameState.Grid;
import GameState.Hex;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Rules {
    static public boolean GameStarted(Grid grid){
        if(grid.TurnNumber() == 0)
            return false;
        else
            return true;

    }
    static public boolean CheckIfHexEmpty(Hex hex, Hex[][] gameboard){
        if(gameboard[hex.getx()][hex.gety()] == null)
            return true;
        else
            return false;
    }

}
