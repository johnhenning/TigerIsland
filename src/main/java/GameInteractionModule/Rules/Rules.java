package GameInteractionModule.Rules;


import GameStateModule.Coordinate;
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
        if(gameboard[hex.getx()][hex.gety()] == null) { return true; }
        else { return false; }
    }


    public static boolean CheckEndGameConditions(GameState gameState) {
        return true;
    }

    public static boolean HexesAreAdjacent(Hex hex0, Hex hex1) {
        
        return true;
    }

    public static Hex downRight(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y += 1;
            return gameboard[x][y];
        }
        else {  //odd
            x += 1;
            y += 1;
            return  gameboard[x][y];
        }
    }
    public static Hex downLeft(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y += 1;
            return gameboard[x][y];
        }
        else {  //odd
            y += 1;
            return  gameboard[x][y];
        }
    }
    public static Hex topRight(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y -= 1;
            return gameboard[x][y];
        }
        else {  //odd
            x += 1;
            y -= 1;
            return  gameboard[x][y];
        }
    }
    public static Hex topLeft(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y -= 1;
            return gameboard[x][y];
        }
        else {  //odd
            y -= 1;
            return  gameboard[x][y];
        }
    }

    public static Hex leftOfHex(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x -= 1;

        return  gameboard[x][y];
    }

    public static Hex rightOfHex(Hex[][] gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x += 1;

        return  gameboard[x][y];
    }
}
