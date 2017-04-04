package ServerModuleTests;

import ServerModule.KnockKnockClient;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by carlos on 4/3/2017.
 */
public class KnockKnockClientTests {

    private KnockKnockClient client;
    private String testString;

    @Before
    public void setup()throws Exception{
        client = new KnockKnockClient();
        testString = "WAIT FOR THE TOURNAMENT TO BEGIN 2";
    }

    @Test
    public void parseStringFromServerTest(){
        client.parseStringFromServer(testString);
        assert client.getPid() == 2;

        testString = "NEW CHALLENGE 28 YOU WILL PLAY 8 MATCHES";
        client.parseStringFromServer(testString);
        assert client.getCid() == 28;
        assert client.getNumRounds() == 8;

        testString = "BEGIN ROUND 1 OF 2";
        client.parseStringFromServer(testString);
        assert client.getRid() == 1;

        testString = "NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 234";
        client.parseStringFromServer(testString);
        assert client.getOid() == 234;

        testString = "MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECONDS: MOVE 5 PLACE LAKE+ROCK";
        client.parseStringFromServer(testString);
        assert client.getGid() == 2;
        assert client.getMoveNum() == 5;
        assert client.getTileTypeOne().equals("LAKE");
        assert client.getTileTypeTwo().equals("ROCK");

        testString = "GAME 2 MOVE 8 PLAYER 222 PLACED JUNGLE+GRASS AT -1 -1 -2 1 FOUNDED SETTLEMENT AT -1 -2 3";
        client.parseStringFromServer(testString);
        assert client.getGid() == 2;
        assert client.getMoveNum()== 8;
        assert client.getPid()== 222;
        assert client.getTileTypeOne().equals("JUNGLE");
        assert client.getTileTypeTwo().equals("GRASS");
        assert client.getxPlaced() == -1;
        assert client.getyPlaced() == -1;
        assert client.getzPlaced() == -2;
        assert client.getOrientation() == 1;
        assert client.getxBuilt()== -1;
        assert client.getyBuilt()== -2;
        assert client.getzBuilt()== 3;
        assert client.isFounded();

        testString = "GAME 1 MOVE 10 PLAYER 223 PLACED GRASS+LAKE AT -3 -3 3 2 EXPANDED SETTLEMENT AT -4 -4 4 LAKE";
        client.parseStringFromServer(testString);
        assert client.getGid() == 1;
        assert client.getMoveNum()== 10;
        assert client.getPid()== 223;
        assert client.getTileTypeOne().equals("GRASS");
        assert client.getTileTypeTwo().equals("LAKE");
        assert client.getxPlaced() == -3;
        assert client.getyPlaced() == -3;
        assert client.getzPlaced() == 3;
        assert client.getOrientation() == 2;
        assert client.getxBuilt()== -4;
        assert client.getyBuilt()== -4;
        assert client.getzBuilt()== 4;
        assert client.getTerrainType().equals("LAKE");
        assert client.isExpdanded();

        testString = "GAME 1 MOVE 20 PLAYER 222 PLACED JUNGLE+JUNGLE AT -1 -1 -1 5 BUILT TOTORO SANCTUARY AT -5 -5 -5";
        client.parseStringFromServer(testString);
        assert client.getGid() == 1;
        assert client.getMoveNum()== 20;
        assert client.getPid()== 222;
        assert client.getTileTypeOne().equals("JUNGLE");
        assert client.getTileTypeTwo().equals("JUNGLE");
        assert client.getxPlaced() == -1;
        assert client.getyPlaced() == -1;
        assert client.getzPlaced() == -1;
        assert client.getOrientation() == 5;
        assert client.getxBuilt()== -5;
        assert client.getyBuilt()== -5;
        assert client.getzBuilt()== -5;
        assert client.isTotoro();

        testString = "GAME 2 MOVE 22 PLAYER 223 PLACED GRASS+GRASS AT -1 -1 -5 4 BUILT TIGER PLAYGROUND AT -6 -6 -6";
        client.parseStringFromServer(testString);
        assert client.getGid() == 2;
        assert client.getMoveNum()== 22;
        assert client.getPid()== 223;
        assert client.getTileTypeOne().equals("GRASS");
        assert client.getTileTypeTwo().equals("GRASS");
        assert client.getxPlaced() == -1;
        assert client.getyPlaced() == -1;
        assert client.getzPlaced() == -5;
        assert client.getOrientation() == 4;
        assert client.getxBuilt()== -6;
        assert client.getyBuilt()== -6;
        assert client.getzBuilt()== -6;
        assert client.isTiger();

        testString = "GAME 2 OVER PLAYER 1 200 PLAYER 2 205";
        client.parseStringFromServer(testString);
        assert client.getGid() == 2;
        assert client.getPid() == 1;
        assert client.getP1Score()== 200;
        assert client.getOid() == 2;
        assert client.getP2Score()== 205;

        testString = "END OF ROUND 1 OF 2";
        client.parseStringFromServer(testString);
        assert client.getRid() == 1;
        assert client.getNumRounds() == 2;



    }
}
