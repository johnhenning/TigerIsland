package GameStateModule;
import GameInteractionModule.Rules.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import static GameStateModule.Coordinate.removeDuplicates;

/**
 * Created by johnhenning on 3/19/17.
 */
public class GameState {
    private Grid gameboard;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private ArrayList<Settlement> settlementList;
    private int settlementIDCount;

    public GameState(){
        gameboard = new Grid(200);
        player1 = new Player();
        player2 = new Player();
        settlementList = new ArrayList<Settlement>();

        settlementIDCount = 0;
        currentPlayer = player1;
        placeTile(Tile.getInitialTile());
    }


    public void foundSettlement(Coordinate coordinate, Player player) {
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if (SettlementFoundationRules.isValidFoundation(h, player)) {
            player.removeMeeple();
            placeMeeple(coordinate);
            player.addScore(ScoringRules.settlementFounded());
            Settlement settlement = new Settlement(coordinate,player, ++settlementIDCount);
            mergeSettlements(settlement);
        } else {
            throw new AssertionError();
        }
}

    public void expandSettlement(Coordinate coordinate, Player player, TerrainType terrainType) {
        Hex hex = getHex(coordinate);
        ArrayList<Coordinate> hexesToPlaceMeeplesOn = new ArrayList<>();
        if (SettlementExpansionRules.expansionIsValid(hex)) {
            Settlement settlement = getSettlementByID(hex.getSettlementID());
            ArrayList<Coordinate> beforeExpansionCoordinates = getCoordinatesofSettlement(settlement);
            Settlement beforeExpansion = new Settlement(beforeExpansionCoordinates, player, settlement.getSettlementID());
            hexesToPlaceMeeplesOn = SettlementExpansionRules.expansionDFS(gameboard, terrainType, beforeExpansion);
            if(BuildRules.checkPlayerHasEnoughMeeples(player, SettlementExpansionRules.getMeeplesRequiredExpansion(this, hexesToPlaceMeeplesOn))){
                expansionPlaceMeeples(hexesToPlaceMeeplesOn, player);
                settlement = beforeExpansion;
            }
            else{
                throw new AssertionError();
            }
            mergeSettlements(settlement);
        }
        else{
            throw new AssertionError();
        }

    }

    public Settlement getSettlementByID(int settlementID) {
        for (Settlement settlement : settlementList) {
            if ( settlement.getSettlementID() == settlementID ) {
                return settlement;
            }
        }
        return null;
    }

    public boolean placeTile(Tile tile) {
        try {
            gameboard.placeTile(tile);
            return true;
        } catch (AssertionError e) {
            System.out.println("Invalid tile placement/nuke");
            return false;
        }
    }

    public boolean levelTile(Tile tile) {
        try {
            TileNukeRules.isValidNuke(tile, gameboard.getGameboard(), this);
            bigDivideSettlements(gameboard, settlementList, tile, settlementIDCount);
            cleanSettlements();
            gameboard.levelTile(tile);
            return true;
        } catch (AssertionError e) {
            System.out.println("Somethings really messed up");
            return false;
        }

    }
    public void cleanSettlements(){
        for(int i = 0; i < settlementList.size(); i++){
            if(settlementList.get(i).getSettlementCoordinates().size() == 0) {
                settlementList.remove(i--);
            }
        }
    }
    public ArrayList<Coordinate> getCoordinatesofSettlement(Settlement settlement){
        ArrayList<Coordinate> copyOfCoordinates= new ArrayList<Coordinate>();
        for(Coordinate c : settlement.getSettlementCoordinates()){
            copyOfCoordinates.add(c);
        }
        return copyOfCoordinates;
    }
    public  void expansionPlaceMeeples(ArrayList<Coordinate> coordinates, Player player){
        for(Coordinate c : coordinates){
            Hex hex = gameboard.getHexFromCoordinate(c);
            player.addScore(ScoringRules.settlementExpanded(hex));
            int meeplesPlaced = placeMeeple(c);
            player.removeMeeple(meeplesPlaced);
        }
    }
    
    public int placeMeeple(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        int level = hex.getLevel();
        hex.addMeeple(level);
        return level;
    }

    public void placeTotoro(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        if(TotoroBuildRules.isValidTotoroLocation(hex, currentPlayer,this)) {
            hex.addTotoro();
            currentPlayer.removeTotoro();
            currentPlayer.addScore(ScoringRules.totoroSanctuaryBuilt());
            Settlement settlement = new Settlement(coordinate, currentPlayer, ++settlementIDCount);
            mergeSettlements(settlement);
        }
        else{
            throw new AssertionError();
        }

    }

    public void placeTiger(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        if(TigerBuildRules.canPlaceTiger(hex,currentPlayer, this)){
            hex.addTiger();
            currentPlayer.removeTiger();
            currentPlayer.addScore(ScoringRules.tigerPlaygroundBuilt());
            Settlement settlement = new Settlement(coordinate, currentPlayer, ++settlementIDCount);
            mergeSettlements(settlement);
        }
        else{
            throw new AssertionError();
        }
    }

    public ArrayList<Settlement> getSettlementList() {
        return settlementList; 
    }

    public Hex getHex(Coordinate coordinate) { 
        return gameboard.getHexFromCoordinate(coordinate); 
    }

    public Grid getGameboard() {
        return gameboard;
    }



    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getSettlementIDCount() {
        return settlementIDCount;
    }

    private void mergeSettlements(Settlement newSettlement) {
        ArrayList<Coordinate> adjacentCoordinates = newSettlement.getSettlementCoordinates();
        ArrayList<Settlement> playersSettlements = BuildRules.settlementsOfPlayer(settlementList, newSettlement.getOwner());
        playersSettlements.remove(newSettlement);
        settlementList.remove(newSettlement);
        for(Settlement s: playersSettlements){
            int newSettlmentID = newSettlement.getSettlementID();
            if(s.areCoordinatesAdjacent(adjacentCoordinates) && (newSettlmentID != 0 || newSettlmentID != s.getSettlementID())) {
                adjacentCoordinates.addAll(s.getSettlementCoordinates());
                settlementList.remove(s);
            }
        }
        Settlement combinedSettlements = new Settlement(adjacentCoordinates, newSettlement.getOwner(), newSettlement.getSettlementID());
        for(Coordinate c : combinedSettlements.getSettlementCoordinates()){
            Hex h = getHex(c);
            h.setSettlementID(newSettlement.getSettlementID());
        }
        ArrayList<Coordinate> duplicates = combinedSettlements.getSettlementCoordinates();
        duplicates = removeDuplicates(duplicates);
        settlementList.add(combinedSettlements);
    }


    public void printHexSummary(int x, int y) {
        //printing tiles placed with coords
        gameboard.getHexFromCoordinate(new Coordinate(x, y)).printHex();
    }
    public void printPlacedTiles(){
        gameboard.printLog();
    }

    public void printSettlementSummary(){
        for(Settlement s: settlementList){
            s.printSettlementInfo();
            System.out.println();
        }
    }
    public ArrayList<Settlement> divideSettlement(Grid gameBoard, Settlement settlement, int settlementID){
        ArrayList<Coordinate> hexesEncountered = new ArrayList<>();
        ArrayList<Settlement> splitSettlements = new ArrayList<>();
        Stack<Coordinate> coords = new Stack();
        coords.add(settlement.getSettlementCoordinates().get(0));
        hexesEncountered.add(settlement.getSettlementCoordinates().get(0));

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoords =
                    TileNukeRules.findAdjacentSettlementCoords(gameBoard, currentAdjacentCoordinate, settlement);
            for(int i = 0; i < neighboringCoords.size(); i++){
                if(!SettlementExpansionRules.contains(hexesEncountered, neighboringCoords.get(i))){
                    hexesEncountered.add(neighboringCoords.get(i));
                    coords.push(neighboringCoords.get(i));
                }
            }
        }

        removeCoordsFromSettlement(hexesEncountered, settlement);
        Settlement newSettlement = new Settlement(hexesEncountered, settlement.getOwner(), settlement.getSettlementID());
        splitSettlements.add(newSettlement);

        return splitSettlements;
    }
    public void removeCoordsFromSettlement(ArrayList<Coordinate> coords, Settlement s){

        Iterator<Coordinate> coordinateIterator = coords.iterator();
        Iterator<Coordinate> settlementCoordinateIterator = s.getSettlementCoordinates().iterator();
        while(coordinateIterator.hasNext()){
            Coordinate coord = coordinateIterator.next();
            while(settlementCoordinateIterator.hasNext()){
                Coordinate settlementCoord = settlementCoordinateIterator.next();
                if(coord.equals(settlementCoord)){
                    settlementCoordinateIterator.remove();
                }
            }
            settlementCoordinateIterator = s.getSettlementCoordinates().iterator();


        }

    }


    public void bigDivideSettlements (Grid gameBoard, ArrayList<Settlement> settlementList, Tile tile, int settlementID){
        ArrayList<Settlement> affectedSettlements = TileNukeRules.findAffectedSettlements(settlementList, tile);
        ArrayList<Settlement> dividedSettlments = new ArrayList<>();

        for(Settlement s : affectedSettlements){
            dividedSettlments.addAll(divideSettlement(gameBoard, s, settlementID));
        }
        settlementList.addAll(dividedSettlments);
    }
}

