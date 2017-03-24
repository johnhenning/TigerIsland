package GameState;

import GameInteraction.SettlementFoundationRules;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class GameState {
    private Grid gameboard;
    private Player player1;
    private Player player2;
    private ArrayList<Settlement> settlementList;
    public GameState(){
        gameboard = new Grid(200);
        player1 = new Player();
        player2 = new Player();
        settlementList = new ArrayList<Settlement>();
    }

    public boolean foundSettlement(Coordinate coordinate, Player player) throws Exception{
        //TODO: Work on Player Rules
        //TODO: Add Victory Points
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if(SettlementFoundationRules.isValidFoundation(h)){
            player.removeMeeple();
            placeMeeple(coordinate);
            Settlement settlement = new Settlement(coordinate,player);
            settlementList.add(settlement);
            return true;
        }
        return false;
    }

    public void placeTile(Tile tile) {
        gameboard.PlaceTile(tile);
    }
    
    public void placeMeeple(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        int tileIndex = hex.getTileIndex();
        int level = gameboard.getPlacedTile(tileIndex).getLevel();
        hex.addMeeple(level);
    }

    public void placeTotoro(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        hex.addTotoro();
    }

    public ArrayList<Settlement> getSettlementList() { return settlementList; }

    public Hex getHex(Coordinate coordinate) { return gameboard.getHexFromCoordinate(coordinate); }

}
