package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.Player;
import GameStateModule.Settlement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/23/17.
 */
public class SettlementTests {
    private Settlement settlement;
    private Player player1;
    private ArrayList<Coordinate> settlementCoordinates;

    @Before
    public void setup() throws Exception {
        player1 = new Player();
        settlementCoordinates = new ArrayList<Coordinate>();
        settlementCoordinates.add(new Coordinate(2,2));
        settlementCoordinates.add(new Coordinate(1,2));
    }



    @Test
    public void settlementCreation() throws Exception {
        settlement = new Settlement(settlementCoordinates,player1);
    }

    @Test
    public void settlementExpansion() throws Exception {
        settlement = new Settlement(settlementCoordinates,player1);
        settlementCoordinates = new ArrayList<Coordinate>();
        settlementCoordinates.add(new Coordinate(3,2));
        settlement.ExpandSettlement(settlementCoordinates);
    }
}
