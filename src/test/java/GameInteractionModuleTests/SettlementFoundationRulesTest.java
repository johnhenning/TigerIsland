package GameInteractionModuleTests;

import GameInteractionModule.Rules.SettlementFoundationRules;
import GameStateModule.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by KWong on 3/28/2017.
 */
public class SettlementFoundationRulesTest {

    private Hex hex;
    private Hex hex2;
    private Hex hex3;
    private Player player;

    @Before
    public void setup(){
        player = new Player();
        hex = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
        hex2 = new Hex(new Coordinate(101, 101), TerrainType.LAKE);
        hex3 = new Hex(new Coordinate(101, 101), TerrainType.LAKE);
        hex.setLevel(1);
        hex2.setLevel(1);
        hex3.setLevel(1);

        hex3.addMeeple(1);
    }

    @Test
    public void hexIsLevelOne(){
        assert SettlementFoundationRules.hexIsLevelOne(hex) == true;
    }

    @Test
    public void isValidFoundation(){
        assert SettlementFoundationRules.isValidFoundation(hex, player) == false;
        assert SettlementFoundationRules.isValidFoundation(hex2, player) == true;
        assert SettlementFoundationRules.isValidFoundation(hex3, player) == false;
    }
}
