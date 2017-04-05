package GameStateModuleTests;

import GameStateModule.Coordinate;
import GameStateModule.Player;
import GameStateModule.Settlement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


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
        settlement.expandSettlement(settlementCoordinates);
    }

    @Test
    public void coordinateAdjacency() throws Exception{
        settlement = new Settlement(settlementCoordinates, player1);
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.add(new Coordinate(3,3));
        newCoordinates.add(new Coordinate(1,4));
        newCoordinates.add(new Coordinate(6,5));
        assert settlement.areCoordinatesAdjacent(newCoordinates);
    }

    @Test
    public void coordinateNonAdjacency() throws Exception{
        settlement = new Settlement(settlementCoordinates, player1);
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        newCoordinates.add(new Coordinate(3,4));
        newCoordinates.add(new Coordinate(1,4));
        newCoordinates.add(new Coordinate(6,5));
        assert !settlement.areCoordinatesAdjacent(newCoordinates);
    }

    @Test
    public void getOwnerTest() throws Exception{
        settlement = new Settlement(settlementCoordinates, player1);
        assert settlement.getOwner() == player1;
    }

    @Test
    public void getSizeTest() throws Exception{
        settlement = new Settlement(settlementCoordinates, player1);
        assert settlement.getSize() == 2;
    }

    @Test
    public void getSettlementCoordinateTest() throws Exception{
        settlement = new Settlement(settlementCoordinates, player1);

        ArrayList<Coordinate> settlementCoord;

        settlementCoord = settlement.getSettlementCoordinates();

        assert settlementCoord == settlementCoordinates;
    }

}
