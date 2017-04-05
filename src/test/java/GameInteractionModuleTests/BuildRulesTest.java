package GameInteractionModuleTests;

import GameInteractionModule.Rules.BuildRules;
import GameStateModule.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by KWong on 3/28/2017.
 */
public class BuildRulesTest {

    private Hex hex;
    private Hex hexToBuildOn;
    private Player player1;
    private Player player2;
    private ArrayList<Settlement> Settlement;
    private ArrayList<Settlement> Settlement2;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> secondSettlementCoords;
    private ArrayList<Settlement> allSettlements;

    private ArrayList<TerrainType> terrains;

    @Before
    public void setup() throws Exception{
        hex = new Hex(new Coordinate(100, 100), TerrainType.VOLCANO);
        hexToBuildOn = new Hex(new Coordinate(100, 101), TerrainType.GRASSLAND);

        player1 = new Player();
        player2 = new Player();

        Settlement = new ArrayList<>();
        Settlement2 = new ArrayList<>();
        coordinates = new ArrayList<>();
        allSettlements = new ArrayList<>();
        secondSettlementCoords = new ArrayList<>();
        terrains = new ArrayList<>();

        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.JUNGLE);

        Settlement.add(new Settlement(new Coordinate(100,100), player1, 0));

        secondSettlementCoords.add(new Coordinate(102, 102));
        secondSettlementCoords.add(new Coordinate(103, 103));
        secondSettlementCoords.add(new Coordinate(102, 103));

        Settlement2.add(new Settlement(new Coordinate(102,102), player2, 0));
        allSettlements.addAll(Settlement);
        allSettlements.addAll(Settlement2);
    }

    @Test
    public void isUnoccupiedHexTest(){
        assert BuildRules.isUnnocupied(hex) == true;
    }

    @Test
    public void isNotVolcanoTest(){
        assert BuildRules.isNotVolcano(hex) == false;
    }

    @Test
    public void settlementOfPlayerTest(){
        ArrayList<Settlement> settlementOfPlayer2 = new ArrayList<>();
        settlementOfPlayer2.addAll(BuildRules.settlementsOfPlayer(allSettlements, player2));
        assert Settlement2.equals(settlementOfPlayer2);
    }

    @Test
    public void isValidBuildTest(){
        //TODO: Finish this test once checkEnoughEntities() is complete
        //BuildRules.isValidBuild(hexToBuildOn, player1);
    }

}
