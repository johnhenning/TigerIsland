package GameStateModule;
import GameInteractionModule.Rules.*;

import java.util.ArrayList;

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
      //TODO: Add Victory Points
        Hex h = gameboard.getHexFromCoordinate(coordinate);

        if (SettlementFoundationRules.isValidFoundation(h)) {
            player.removeMeeple();
            placeMeeple(coordinate);
            Settlement settlement = new Settlement(coordinate,player,settlementIDCount);
            settlementIDCount++;
            mergeSettlements(settlement);
        } else {
            throw new AssertionError();
        }
}

    public void expandSettlement(Coordinate coordinate, Player player, TerrainType terrainType) {
        Hex hex = getHex(coordinate);
        if (hex == null) {
            throw new AssertionError();
        }
        Settlement settlement = getSettlementByID(hex.getSettlementID());
        SettlementExpansionRules.expansionDFS(gameboard, terrainType, settlement);
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
        } catch (Exception e) {
            return false;
        }
    }

    public boolean levelTile(Tile tile) {
        TileNukeRules.bigDivideSettlements(gameboard, settlementList, tile, getSettlementIDCount());
        cleanSettlements();
        gameboard.levelTile(tile);
        return true;
    }
    public void cleanSettlements(){
        for(int i = 0; i < settlementList.size(); i++){
            if(settlementList.get(i).getSettlementCoordinates().size() == 0) {
                settlementList.remove(i--);
            }
        }
    }
    
    public void placeMeeple(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        int level = hex.getLevel();
        hex.addMeeple(level);
    }

    public void placeTotoro(Coordinate coordinate) {
        //TODO: fixplz
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        assert TotoroBuildRules.isValidTotoroLocation(hex,currentPlayer,this);
        hex.addTotoro();
    }

    public void placeTiger(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        assert TigerBuildRules.canPlaceTiger(hex, this);
        hex.addTiger();
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

    public void printSummary() {

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

        for(Settlement s: playersSettlements){
            if(s.areCoordinatesAdjacent(adjacentCoordinates)) {
                adjacentCoordinates.addAll(s.getSettlementCoordinates());
                settlementList.remove(s);
            }
        }
        Settlement combinedSettlements = new Settlement(adjacentCoordinates, newSettlement.getOwner(), newSettlement.getSettlementID());
        settlementList.add(combinedSettlements);
    }
}
