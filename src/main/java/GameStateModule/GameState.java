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


    public void foundSettlement(Coordinate coordinate, Player player, boolean shaman) {
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if (SettlementFoundationRules.isValidFoundation(h, player)) {
            player.addScore(ScoringRules.settlementFounded());
            Settlement settlement = new Settlement(coordinate,player, ++settlementIDCount);
            if (shaman) {
                player.removeShaman();
                placeShaman(coordinate);
                settlement.setShaman(true);
            } else {
                player.removeMeeple();
                placeMeeple(coordinate);
            }
            mergeSettlements(settlement);
        } else {
            throw new AssertionError();
        }
}

    private void placeShaman(Coordinate coordinate) {
        Hex hex = getHex(coordinate);
        hex.setShaman(true);
    }

    public void expandSettlement(Coordinate coordinate, Player player, TerrainType terrainType) {
        Hex hex = getHex(coordinate);
        ArrayList<Coordinate> hexesToPlaceMeeplesOn = new ArrayList<>();
        if (SettlementExpansionRules.expansionIsValid(hex)) {
            Settlement settlement = getSettlementByID(hex.getSettlementID());
            ArrayList<Coordinate> beforeExpansionCoordinates = getCoordinatesofSettlement(settlement);
            Settlement beforeExpansion = new Settlement(beforeExpansionCoordinates, player, settlement.getSettlementID());
            hexesToPlaceMeeplesOn = expansionDFS(gameboard, terrainType, beforeExpansion);
            if(BuildRules.checkPlayerHasEnoughMeeples(player, SettlementExpansionRules.getMeeplesRequiredExpansion(this, hexesToPlaceMeeplesOn))){
                expansionPlaceMeeples(hexesToPlaceMeeplesOn, player);
                settlement = beforeExpansion;
            }
            else{
                System.out.println("Something happened in expandSettlement");
                throw new AssertionError();
            }
            mergeSettlements(settlement);
        }
        else{
            System.out.println("Something happened in expandSettlement");
            throw new AssertionError();
        }

    }
    public ArrayList<Coordinate> expansionDFS(Grid gameboard, TerrainType terrain, Settlement settlement){
        ArrayList<Coordinate> hexesEncountered = settlement.getSettlementCoordinates();
        ArrayList<Coordinate> newHexesAdded = new ArrayList<>();
        Stack<Coordinate> coords = new Stack();
        coords.addAll(hexesEncountered);

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoordinates = findAdjacentCoords(gameboard, terrain, currentAdjacentCoordinate);
            for(int i=0; i<neighboringCoordinates.size(); i++){
                if(SettlementExpansionRules.contains(hexesEncountered, neighboringCoordinates.get(i))== false){
                    newHexesAdded.add(neighboringCoordinates.get(i));
                    hexesEncountered.add(neighboringCoordinates.get(i));
                    coords.push(neighboringCoordinates.get(i));
                }
            }
        }
        return newHexesAdded;
    }
    public ArrayList<Coordinate> findAdjacentCoords(Grid gameboard, TerrainType terrain, Coordinate coordinate){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        //we only want to add the coordinates, if they have the same terrain type, and they are unoccupied
        //if the match occurs we need to add the coordinates of that match to the array list
        Hex hex;
        hex = downLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = downRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = leftOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = rightOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && BuildRules.isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());

        return adjacentCoordinates;
    }
    public Hex downRight(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y += 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            x += 1;
            y += 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex downLeft(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y += 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            y += 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex topRight(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            x += 1;
            y -= 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex topLeft(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
    }

    public Hex leftOfHex(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x -= 1;

        return gameboard.getGameboard()[x][y];
    }

    public Hex rightOfHex(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x += 1;
        return gameboard.getGameboard()[x][y];
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
            Settlement settlement = getSettlementByID(hex.getSettlementID());
            if (settlement.HasShaman()) {
                player.addScore(2 * ScoringRules.settlementExpanded(hex));
            } else {
                player.addScore(ScoringRules.settlementExpanded(hex));
            }
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
            System.out.println("Something happened in placeTotoro");
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
            System.out.println("Something happened in placeTiger");
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
        combinedSettlements.setShaman(newSettlement.HasShaman());
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
        if(settlement.getSettlementCoordinates().size() <= 0){
            return splitSettlements;
        }
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
        ArrayList<Settlement> affectedSettlements = findAffectedSettlements(settlementList, tile);
        ArrayList<Settlement> dividedSettlments = new ArrayList<>();

        for(Settlement s : affectedSettlements){
            dividedSettlments.addAll(divideSettlement(gameBoard, s, settlementID));
        }
        settlementList.addAll(dividedSettlments);
    }
    public ArrayList<Settlement> findAffectedSettlements(ArrayList<Settlement> settlements, Tile tile) {
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
    private int coordinateIndex(ArrayList<Coordinate> settlementCoords, Coordinate nukedCoord){
        for(Coordinate c : settlementCoords){
            if(c.getX() == nukedCoord.getX() && c.getY() == nukedCoord.getY()){
                return settlementCoords.indexOf(c);
            }
        }
        return -1;
    }

}

