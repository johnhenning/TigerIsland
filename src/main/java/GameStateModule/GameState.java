package GameStateModule;
import GameInteractionModule.Rules.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
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
        placeTile(getInitialTile());
    }

    public Tile getInitialTile(){
        ArrayList<Hex> initialTile = new ArrayList<>();
        initialTile.add(new Hex(new Coordinate(100,100), TerrainType.VOLCANO));
        initialTile.add(new Hex(new Coordinate(100,99), TerrainType.LAKE));
        initialTile.add(new Hex(new Coordinate(99,99), TerrainType.JUNGLE));
        initialTile.add(new Hex(new Coordinate(99,101), TerrainType.ROCK));
        initialTile.add(new Hex(new Coordinate(100,101), TerrainType.GRASS));
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(new Coordinate(100,100));
        coords.add(new Coordinate(100,99));
        coords.add(new Coordinate(99,99));
        coords.add(new Coordinate(99,101));
        coords.add(new Coordinate(100,101));
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.JUNGLE);
        terrains.add(TerrainType.ROCK);
        terrains.add(TerrainType.GRASS);


        return new Tile(coords, terrains);
    }

    public void foundSettlement(Coordinate coordinate, Player player) {
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if (isValidFoundation(h, player)) {
            player.removeMeeple();
            placeMeeple(coordinate);
            player.addScore(ScoringRules.settlementFounded());
            Settlement settlement = new Settlement(coordinate,player, ++settlementIDCount);
            mergeSettlements(settlement);
        } else {
            throw new AssertionError();
        }
    }

    public  boolean isValidFoundation(Hex hex, Player player){
        return hex != null && hex.getLevel()==1 && isUnnocupied(hex) && hex.getTerrain()!=TerrainType.VOLCANO && BuildRules.checkPlayerHasEnoughMeeples(player, 1);
    }

    public void expandSettlement(Coordinate coordinate, Player player, TerrainType terrainType) {
        Hex hex = getHex(coordinate);
        ArrayList<Coordinate> hexesToPlaceMeeplesOn = new ArrayList<>();
        if (expansionIsValid(hex)) {
            Settlement settlement = getSettlementByID(hex.getSettlementID());
            ArrayList<Coordinate> beforeExpansionCoordinates = getCoordinatesofSettlement(settlement);
            Settlement beforeExpansion = new Settlement(beforeExpansionCoordinates, player, settlement.getSettlementID());
            hexesToPlaceMeeplesOn = expansionDFS(gameboard, terrainType, beforeExpansion);
            if(checkPlayerHasEnoughMeeples(player, getMeeplesRequiredExpansion(this, hexesToPlaceMeeplesOn))){
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
    public boolean expansionIsValid(Hex hex){
        if(hex != null && hex.getSettlementID() != 0 && hex.getTerrain()!=TerrainType.VOLCANO){
            return true;
        }
        else{
            throw new AssertionError("This is not a proper settlement expansion");
        }
    }
    public boolean checkPlayerHasEnoughMeeples(Player player, int numMeeples){
        return player.getNumMeeples() >= numMeeples;
    }

    public int getMeeplesRequiredExpansion(GameState gameState, ArrayList<Coordinate> coordinates){
        int meeplesRequired = 0;
        for(Coordinate c: coordinates){
            Hex h = gameState.getHex(c);
            meeplesRequired += h.getLevel();
        }
        return meeplesRequired;
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
                if(contains(hexesEncountered, neighboringCoordinates.get(i))== false){
                    newHexesAdded.add(neighboringCoordinates.get(i));
                    hexesEncountered.add(neighboringCoordinates.get(i));
                    coords.push(neighboringCoordinates.get(i));
                }
            }
        }
        return newHexesAdded;
    }

    public boolean contains(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Coordinate> findAdjacentCoords(Grid gameboard, TerrainType terrain, Coordinate coordinate){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        //we only want to add the coordinates, if they have the same terrain type, and they are unoccupied
        //if the match occurs we need to add the coordinates of that match to the array list
        Hex hex;
        hex = downLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = downRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = leftOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = rightOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());

        return adjacentCoordinates;
    }
    public boolean isUnnocupied(Hex hex){
        return hex.getMeepleCount()==0 && !hex.hasTiger() && !hex.hasTotoro();
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
        Iterator<Settlement> settlementIterator = settlementList.iterator();
        while (settlementIterator.hasNext()) {
           Settlement settlement = settlementIterator.next();
            if(settlement.getSettlementCoordinates().size()==0){
                settlementIterator.remove();
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
        ArrayList<Settlement> playersSettlements = settlementsOfPlayer(settlementList, newSettlement.getOwner());
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

    public ArrayList<Settlement> settlementsOfPlayer(ArrayList<Settlement> settlements, Player player){
        ArrayList<Settlement> playerSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getOwner().equals(player))
                playerSettlements.add(s);
        }
        return playerSettlements;
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
        if(settlement == null || settlement.getSettlementCoordinates().size() <= 0){
            return splitSettlements;
        }
        coords.add(settlement.getSettlementCoordinates().get(0));
        hexesEncountered.add(settlement.getSettlementCoordinates().get(0));

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoords =
                    findAdjacentSettlementCoords(gameBoard, currentAdjacentCoordinate, settlement);
            for(int i = 0; i < neighboringCoords.size(); i++){
                if(!contains(hexesEncountered, neighboringCoords.get(i))){
                    hexesEncountered.add(neighboringCoords.get(i));
                    coords.push(neighboringCoords.get(i));
                }
            }
        }

        removeCoordsFromSettlement(hexesEncountered, settlement);
        Settlement newSettlement = new Settlement(hexesEncountered, settlement.getOwner(), ++settlementIDCount);
        splitSettlements.add(newSettlement);
        mergeSettlements(newSettlement);

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
    public void removeSettlementByID(int settlementID){
        Iterator<Settlement> settlementIterator = settlementList.iterator();
        while (settlementIterator.hasNext()) {
            Settlement settlement = settlementIterator.next();
            if (settlement.getSettlementID() == settlementID) {
                settlementIterator.remove();
            }
        }
    }
    public ArrayList<Settlement> findAffectedSettlements(ArrayList<Settlement> settlements, Tile tile) {
        ArrayList<Settlement> affectedSettlements = new ArrayList<>();
        ArrayList<Coordinate> nukedCoords = tile.getCoords();
        for(Coordinate c: nukedCoords){
            Hex h = getHex(c);
            if(h.getSettlementID() != 0) {
                if(getSettlementByID(h.getSettlementID()) != null) {
                    int i = coordinateIndex(getSettlementByID(h.getSettlementID()).getSettlementCoordinates(), c);
                    getSettlementByID(h.getSettlementID()).getSettlementCoordinates().remove(i);
                    affectedSettlements.add(getSettlementByID(h.getSettlementID()));
//                    removeSettlementByID(h.getSettlementID());
                }
            }
        }
        Iterator<Settlement> settlementIterator = affectedSettlements.iterator();
        while (settlementIterator.hasNext()) {
            Settlement settlement = settlementIterator.next();
            if (settlement != null && settlement.getSettlementCoordinates().size() == 0) {
                settlementIterator.remove();
            }
        }

//        for (Settlement s : settlements) {
//            boolean found = false;
//            for (Coordinate c : nukedCoords) {
//                int i;
//                i = coordinateIndex(s.getSettlementCoordinates(), c);
//                if (i >= 0) {
//                    s.getSettlementCoordinates().remove(i);
//                    found = true;
//                }
//            }
//            if(found)
//                affectedSettlements.add(s);
//        }

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

    public ArrayList<Coordinate> findAdjacentSettlementCoords(Grid gameBoard, Coordinate coordinate, Settlement settlement){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        Hex hex;

        hex = downLeft(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = downRight(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topRight(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topLeft(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = leftOfHex(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = rightOfHex(gameBoard, coordinate);
        if(hex != null && settlmentContainsCoordinate(settlement, hex.getCoordinate()))
            adjacentCoordinates.add(hex.getCoordinate());
        return adjacentCoordinates;
    }

    public  boolean settlmentContainsCoordinate(Settlement settlement, Coordinate coordinate){
        for(Coordinate c : settlement.getSettlementCoordinates()){
            if(c.equals(coordinate)){
                return true;
            }
        }
        return false;
    }

}

