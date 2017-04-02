package GameInteractionModule.Rules;
import GameStateModule.*;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TotoroBuildRules extends BuildRules {


    public static boolean isHexAdjacentToSettlement(Hex hex)
    {
        ArrayList<Settlement> settlementList = GameState.getSettlementList();
        Hex[][] gameboard = Grid.getGameboard();
        ArrayList<Hex> adjacentHexes = TilePlacementRules.getAdjacentHexes(hex, gameboard);
        for(Settlement s:settlementList)
            for(Hex h: adjacentHexes)
                if(CoordinateIsPartOfSettlement(s.getSettlementCoordinates(), h.getCoords()))
                    return true;
        return false;
    }

    public static boolean CoordinateIsPartOfSettlement(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }

    public static boolean playerHasSizeFiveSettlement(Player player)
    {
        ArrayList<Settlement> settlementList = GameState.getSettlementList();
        ArrayList<Settlement> settlementListOfPlayer = settlementsOfPlayer(settlementList, player);
        settlementListOfPlayer = SettlementsGreaterThanFive(settlementListOfPlayer);
        return settlementListOfPlayer.size() > 0;
    }

    public static ArrayList<Settlement> SettlementsGreaterThanFive(ArrayList<Settlement> settlements){
        ArrayList<Settlement> sizeFiveSettlements = new ArrayList<Settlement>();
        for(Settlement s: settlements){
            if(s.getSize() >= 5)
                sizeFiveSettlements.add(s);
        }
        return sizeFiveSettlements;
    }

    public static boolean isValidTotoroLocation(Hex hex, Player player){
        return playerHasSizeFiveSettlement(player) && isHexAdjacentToSettlement(hex) && isValidBuild(hex,player);
    }

}
