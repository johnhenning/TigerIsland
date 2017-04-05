package GameInteractionModule.Rules;

import GameStateModule.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TileNukeRules extends Rules {



    public static void isValidNuke(Tile tile, Hex[][] gameboard){
        CheckLowerHexesAreSameLevel(tile,gameboard);
        CheckHexesSpanMultipleTiles(tile, gameboard);
        CheckVolcanoesLineUp(tile,gameboard);
        CheckTileNotContainTotoro(tile, gameboard);
        CheckTileNotContainTiger(tile, gameboard);
    }

    public static int getNewTileLevel(Tile tile, Hex[][] gameboard){
        int lowerLevel = CheckLowerHexesAreSameLevel(tile, gameboard);
        return lowerLevel+1;
    }
    public static int CheckLowerHexesAreSameLevel(Tile tile, Hex[][] gameboard) {
        Hex hex0 = tile.getHexes()[0];
        Hex hex1 = tile.getHexes()[1];
        Hex hex2 = tile.getHexes()[2];

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) { return -1; }

        int lowerLevel0 = lower_hex0.getLevel();
        int lowerLevel1 = lower_hex1.getLevel();
        int lowerLevel2 = lower_hex2.getLevel();

        if (lowerLevel0 == lowerLevel1 && lowerLevel1 == lowerLevel2) {
            return lowerLevel0;
        } else {
            throw new AssertionError();
        }
    }

    public static boolean CheckHexesSpanMultipleTiles(Tile tile, Hex[][] gameboard) {
        Hex hex0 = tile.getHexes()[0];
        Hex hex1 = tile.getHexes()[1];
        Hex hex2 = tile.getHexes()[2];

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

        if (hex_zero == null || hex_one == null || hex_two == null) { return false; }

        int tileIndex0 = hex_zero.getTurnPlaced();
        int tileIndex1 = hex_one.getTurnPlaced();
        int tileIndex2 = hex_two.getTurnPlaced();

        if(tileIndex0 == tileIndex1 && tileIndex1 == tileIndex2){
            throw new AssertionError();
        }

        return true;

    }

    public static boolean CheckVolcanoesLineUp(Tile tile, Hex[][] gameboard) {

        for (Hex hex : tile.getHexes()) {
            if (hex.getTerrain() == TerrainType.VOLCANO) {
                if (gameboard[hex.getx()][hex.gety()].getTerrain() == TerrainType.VOLCANO) { return true; }
            }
        }
        throw new AssertionError();
    }

    public static boolean CheckTileNotContainTotoro(Tile tile, Hex[][]gameboard){
        Hex hex0 = tile.getHexes()[0];
        Hex hex1 = tile.getHexes()[1];
        Hex hex2 = tile.getHexes()[2];

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

        if (hex_zero.hasTotoro() || hex_one.hasTotoro() || hex_two.hasTotoro()){
            throw new AssertionError();
        }
        return true;

    }

    public static boolean CheckTileNotContainTiger(Tile tile, Hex[][]gameboard){
        Hex hex0 = tile.getHexes()[0];
        Hex hex1 = tile.getHexes()[1];
        Hex hex2 = tile.getHexes()[2];

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

       if(hex_zero.hasTiger() || hex_one.hasTiger() || hex_two.hasTiger()){
           throw new AssertionError();
       }
       return true;

    }
    //TODO: needs to be finished
    public static boolean CheckNukeDoesNotWipeoutSettlement(Tile tile, ArrayList<Settlement> settlementList){
        ArrayList<Coordinate> tileCoords = tile.getCoords();
        ArrayList<Settlement> settlementsInDanger = getSettlementsThatCouldBeWipedOut(settlementList);
        for(Settlement s: settlementsInDanger){
            s.getSettlementCoordinates();
            //see if all of s->coords is contained in tile->coords
        }
        return true;

    }

    public static boolean doCoordinatesOverlap(ArrayList<Coordinate> tile, ArrayList<Coordinate> settlementCoords){

        Coordinate t1 = tile.get(0);
        Coordinate t2 = tile.get(1);
        Coordinate t3 = tile.get(2);

        Coordinate s1 = settlementCoords.get(0);
        Coordinate s2 = settlementCoords.get(1);
        Coordinate s3 = settlementCoords.get(2);



        if((t1.getX()==s1.getX())&&(t2.getX()==s2.getX())&&(t3.getX()==s3.getX()) && (t1.getY() == s1.getY())
                && (t2.getY()== s2.getY()) && (t3.getY()==s3.getY())){
            return true;
        }
        else{
            return false;
        }

    }

    private static ArrayList<Settlement> getSettlementsThatCouldBeWipedOut(ArrayList<Settlement> settlementList){
        ArrayList<Settlement> smallSettlements = new ArrayList<>();
        for(Settlement s: settlementList){
            if(s.getSettlementCoordinates().size() < 3)
                smallSettlements.add(s);
        }
        return smallSettlements;
    }
    private static int coordinateIndex(ArrayList<Coordinate> settlementCoords, Coordinate nukedCoord){
        for(Coordinate c : settlementCoords){
            if(c.getX() == nukedCoord.getX() && c.getY() == nukedCoord.getY()){
                return settlementCoords.indexOf(c);
            }
        }
        return -1;
    }

    public static ArrayList<Settlement> findAffectedSettlements(ArrayList<Settlement> settlements, Tile tile) {
        ArrayList<Settlement> affectedSettlements = new ArrayList<>();
        ArrayList<Coordinate> nukedCoords = tile.getCoords();

        for (Settlement s : settlements) {
            boolean found = false;

            for (Coordinate c : nukedCoords) {
                int i;
                i = coordinateIndex(s.getSettlementCoordinates(), c);
                if (i >= 0) {
                    s.getSettlementCoordinates().remove(i);
                    found = true;
                }

            }
            if(found)
                affectedSettlements.add(s);
        }

        return  affectedSettlements;
    }

    public static boolean settlmentContainsCoordinate(Settlement settlement, Coordinate coordinate){
        for(Coordinate c : settlement.getSettlementCoordinates()){
            if(c.equals(coordinate)){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<Coordinate> findAdjacentSettlmentCoords(Hex[][] gameBoard, Coordinate coordinate, Settlement settlement){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        Hex hex;

        hex = downLeft(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        hex = downRight(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        hex = topRight(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        hex = topLeft(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        hex = leftOfHex(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        hex = rightOfHex(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoords());
        return adjacentCoordinates;
    }

    public static void removeCoordsFromSettlement(ArrayList<Coordinate> coords, Settlement s){

        for(int i = 0; i < s.getSettlementCoordinates().size(); i++){
            for(int j = 0; j < coords.size(); j++){
                if(s.getSettlementCoordinates().get(i).equals(coords.get(j)))
                    s.getSettlementCoordinates().remove(i);
            }
        }

    }

    public static ArrayList<Settlement> divideSettlement(Hex[][] gameBoard, Settlement settlement){
        ArrayList<Coordinate> hexesEncountered = new ArrayList<>();
        ArrayList<Settlement> splitSettlements = new ArrayList<>();
        Stack<Coordinate> coords = new Stack();
        coords.add(settlement.getSettlementCoordinates().get(0));
        hexesEncountered.add(settlement.getSettlementCoordinates().get(0));

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoords =
                    findAdjacentSettlmentCoords(gameBoard, currentAdjacentCoordinate, settlement);
            for(int i = 0; i < neighboringCoords.size(); i++){
                if(!SettlementExpansionRules.contains(hexesEncountered, neighboringCoords.get(i))){
                    hexesEncountered.add(neighboringCoords.get(i));
                    coords.push(neighboringCoords.get(i));
                }
            }
        }

        removeCoordsFromSettlement(hexesEncountered, settlement);
        Settlement newSettlement = new Settlement(hexesEncountered, settlement.getOwner());

        splitSettlements.add(newSettlement);

        return splitSettlements;
    }

    public static void bigDivideSettlements (Hex[][] gameBoard, ArrayList<Settlement> settlementList, Tile tile){
        ArrayList<Settlement> affectedSettlements = findAffectedSettlements(settlementList, tile);
        ArrayList<Settlement> dividedSettlments = new ArrayList<>();

        for(Settlement s : affectedSettlements){
            dividedSettlments.addAll(divideSettlement(gameBoard, s));
        }
        settlementList.addAll(dividedSettlments);
    }

}
