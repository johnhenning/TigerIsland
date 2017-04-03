package GameInteractionModule.Rules;


import GameStateModule.Coordinate;
import GameStateModule.Grid;

import GameStateModule.GameState;

import GameStateModule.Hex;

import java.util.ArrayList;

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
    public static boolean CheckIfHexEmpty(Hex hex, Hex[][] gameboard){
        return gameboard[hex.getx()][hex.gety()] == null;
    }


    public static boolean CheckEndGameConditions(GameState gameState) {
        return true;
    }

    public static boolean HexesAreAdjacent(Hex hex0, Hex hex1) {
        Coordinate[][] directions = { { new Coordinate(1, 0), new Coordinate( 0, -1),
                new Coordinate(-1, -1), new Coordinate(-1, 0), new Coordinate(-1, 1),
                new Coordinate( 0, 1) }, { new Coordinate(1, 0), new Coordinate(1, -1),
                new Coordinate( 0, -1), new Coordinate(-1, 0),
                new Coordinate( 0, 1), new Coordinate(1, 1) } };


        int parity = hex0.gety() & 1;
        for ( int i = 0; i < directions[parity].length; i++) {
            if (Coordinate.add(directions[parity][i],hex0.getCoordinate()).equals(hex1.getCoordinate())) {
                return true;
            }
        }
        return false;
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
