package GameStateModule;
import GameInteractionModule.Rules.BuildRules;
import GameInteractionModule.Rules.SettlementFoundationRules;
import GameInteractionModule.Rules.TileNukeRules;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class GameState {
    private Grid gameboard;
    private Player player1;
    private Player player2;
    private static ArrayList<Settlement> settlementList;
  
    public GameState(){
        gameboard = new Grid(200);
        player1 = new Player();
        player2 = new Player();
        settlementList = new ArrayList<Settlement>();
        placeTile(Tile.getInitialTile());
    }

    public void foundSettlement(Coordinate coordinate, Player player) {
      //TODO: Add Victory Points
        Hex h = gameboard.getHexFromCoordinate(coordinate);
        if(!SettlementFoundationRules.isValidFoundation(h)) throw new AssertionError();

        if(SettlementFoundationRules.isValidFoundation(h)){
            player.removeMeeple();
            placeMeeple(coordinate);
            Settlement settlement = new Settlement(coordinate,player);
            mergeSettlements(settlement);
        }
    }

    public void placeTile(Tile tile) {
        gameboard.placeTile(tile);
    }

    public void levelTile(Tile tile) {
        TileNukeRules.bigDivideSettlements(gameboard.getGameboard(), settlementList, tile);
        gameboard.levelTile(tile);
    }
    
    public void placeMeeple(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        int level = hex.getLevel();
        hex.addMeeple(level);
    }

    public void placeTotoro(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        hex.addTotoro();
    }

    public static ArrayList<Settlement> getSettlementList() {
        return settlementList; 
    }

    public Hex getHex(Coordinate coordinate) { 
        return gameboard.getHexFromCoordinate(coordinate); 
    }

    public Hex[][] getGameboard() {
        return gameboard.getGameboard();
    }

    private void mergeSettlements(Settlement newSettlement){
        ArrayList<Coordinate> adjacentCoordiantes = newSettlement.getSettlementCoordinates();
        ArrayList<Settlement> playersSettlements = BuildRules.settlementsOfPlayer(settlementList, newSettlement.getOwner());

        for(Settlement s: playersSettlements){
            if(s.areCoordinatesAdjacent(adjacentCoordiantes)){
                adjacentCoordiantes.addAll(s.getSettlementCoordinates());
                settlementList.remove(s);
            }
        }

        Settlement combinedSettlements = new Settlement(adjacentCoordiantes, newSettlement.getOwner());
        settlementList.add(combinedSettlements);
    }

}
