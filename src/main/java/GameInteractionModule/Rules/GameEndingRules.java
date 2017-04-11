package GameInteractionModule.Rules;

import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by Kyle on 4/8/2017.
 */
public class GameEndingRules {

    public static boolean playerCanCompleteBuildAction(Player player,  GameState gameState){
        Coordinate coordinate = new Coordinate(-1,-1);
        if(player.getNumMeeples() == 0){
            if (playerCanPlaceTiger(player, gameState).equals(coordinate) &&
                    playerCanPlaceTotoro(player,gameState).equals(coordinate))
                return false;
        }
        return true;
    }

    public static Coordinate playerCanPlaceTotoro(Player player, GameState gameState){
        ArrayList<Settlement> playerSettlements = TotoroBuildRules.playerHasSizeFiveSettlement(player, gameState);
        if (playerSettlements.size()==0)
            return new Coordinate(-1,-1);
        for(Settlement s : playerSettlements){
            if(TotoroBuildRules.settlementNotContainTotoros(s, gameState)) {
                for (Coordinate c : s.getSettlementCoordinates()) {
                    Hex h;
                    h = BuildRules.downRight(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                    h = BuildRules.downLeft(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                    h = BuildRules.topLeft(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                    h = BuildRules.topRight(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                    h = BuildRules.leftOfHex(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                    h = BuildRules.rightOfHex(gameState.getGameboard(), c);
                    if(h != null && TotoroBuildRules.isValidTotoroLocation(gameState.getHex(c), player, gameState))
                        return c;
                }
            }
        }
        return new Coordinate(-1,-1);
    }

    public static Coordinate playerCanPlaceTiger(Player player, GameState gameState){
        ArrayList<Settlement> playerSettlements = BuildRules.settlementsOfPlayer(gameState.getSettlementList(), player);
        for(Settlement s : playerSettlements){
            for(Coordinate c : s.getSettlementCoordinates()){
                Hex h;
                h = BuildRules.downRight(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
                h = BuildRules.downLeft(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
                h = BuildRules.topLeft(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
                h = BuildRules.topRight(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
                h = BuildRules.leftOfHex(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
                h = BuildRules.rightOfHex(gameState.getGameboard(), c);
                if(h != null && TigerBuildRules.canPlaceTiger(gameState.getHex(c), player, gameState))
                    return c;
            }
        }
        return new Coordinate(-1,-1);
    }


}
