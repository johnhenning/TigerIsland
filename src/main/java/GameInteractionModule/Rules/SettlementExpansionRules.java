package GameInteractionModule.Rules;

import GameStateModule.Coordinate;
import GameStateModule.Hex;
import GameStateModule.Settlement;
import GameStateModule.TerrainType;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by johnhenning on 3/22/17.
 */
public class SettlementExpansionRules extends BuildRules{
    public static ArrayList<Coordinate> expansionDFS(Hex[][] gameboard, TerrainType terrain, Settlement settlement){
        ArrayList<Coordinate> returnValue = settlement.getSettlementCoordinates(); //is this right?

        Stack<Coordinate> coords = new Stack();
        coords.addAll(returnValue);

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoordinates = findAdjacentCoords(gameboard, terrain, currentAdjacentCoordinate);
        }

        return returnValue;
    }

    public static ArrayList<Coordinate> findAdjacentCoords(Hex[][] gameboard, TerrainType terrain, Coordinate coordinate){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        //we only want to add the coordinates, if they have the same terrain type, and they are unoccupied
        //if the match occurs we need to add the coordinates of that match to the array list
        Hex hex;
        hex = downLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());
        hex = downRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());
        hex = topRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());
        hex = topLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());
        hex = leftOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());
        hex = rightOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoords());

        return adjacentCoordinates;
    }
}

