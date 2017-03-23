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

    public void foundSettlement(Coordinate coordinate, Player player){
        //TODO: Work on Player Rules
        //TODO: Add Victory Points
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if(SettlementFoundationRules.isUnnocupied(h)){
            player.removeMeeple();
            h.addMeeple(1);
            Settlement settlement = new Settlement(coordinate,player);
            settlementList.add(settlement);
        }
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

    public Grid getGameboard(){
        return gameboard;
    }

}
