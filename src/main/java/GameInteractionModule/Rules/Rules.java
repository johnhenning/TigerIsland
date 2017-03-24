package GameInteractionModule.Rules;


import GameStateModule.Grid;

import GameStateModule.GameState;

import GameStateModule.Hex;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Rules {

     public static boolean GameStarted(Grid grid){
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


    public static boolean CheckEndGameConditions(GameState gameState) {
        return true;
    }

    public static boolean HexesAreAdjacent(Hex hex0, Hex hex1) {
        
        return true;
    }


}
