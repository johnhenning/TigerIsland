package ServerModuleTests;

import ServerModule.Adapter;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by carlos on 4/3/2017.
 */
public class AdapterTests {

    private Adapter adapter;
    private String testString;
    private String authenticationString;

    @Before
    public void setup()throws Exception{
        adapter = new Adapter();
        testString = "WAIT FOR THE TOURNAMENT TO BEGIN 2";
        authenticationString = "WELCOME TO ANOTHER EDITION OF THUNDERDOME!";

    }


    @Test
    public void parseStringFromServerTest(){
        adapter.parseStringFromServer(testString);
        assert adapter.pid == 2;

        testString = "NEW CHALLENGE 28 YOU WILL PLAY 8 MATCHES";
        adapter.parseStringFromServer(testString);
        assert adapter.cid == 28;
        assert adapter.numRounds == 8;

        testString = "BEGIN ROUND 1 OF 2";
        adapter.parseStringFromServer(testString);
        assert adapter.rid == 1;

        testString = "NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 234";
        adapter.parseStringFromServer(testString);
        assert adapter.oid == 234;

        testString = "MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECONDS: MOVE 5 PLACE LAKE+ROCK";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 2;
        assert adapter.moveNum == 5;
        assert adapter.tileTypeOne.equals("LAKE");
        assert adapter.tileTypeTwo.equals("ROCK");

        testString = "GAME 2 MOVE 8 PLAYER 222 PLACED JUNGLE+GRASS AT -1 -1 -2 1 FOUNDED SETTLEMENT AT -1 -2 3";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 2;
        assert adapter.moveNum == 8;
        assert adapter.pid == 222;
        assert adapter.tileTypeOne.equals("JUNGLE");
        assert adapter.tileTypeTwo.equals("GRASS");
        assert adapter.xPlaced == -1;
        assert adapter.yPlaced == -1;
        assert adapter.zPlaced == -2;
        assert adapter.orientation == 1;
        assert adapter.xBuilt== -1;
        assert adapter.yBuilt== -2;
        assert adapter.zBuilt== 3;
        assert adapter.founded;

        testString = "GAME 1 MOVE 10 PLAYER 223 PLACED GRASS+LAKE AT -3 -3 3 2 EXPANDED SETTLEMENT AT -4 -4 4 LAKE";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 1;
        assert adapter.moveNum == 10;
        assert adapter.pid == 223;
        assert adapter.tileTypeOne.equals("GRASS");
        assert adapter.tileTypeTwo.equals("LAKE");
        assert adapter.xPlaced == -3;
        assert adapter.yPlaced == -3;
        assert adapter.zPlaced == 3;
        assert adapter.orientation == 2;
        assert adapter.xBuilt == -4;
        assert adapter.yBuilt == -4;
        assert adapter.zBuilt == 4;
        assert adapter.terrainType.equals("LAKE");
        assert adapter.expdanded;

        testString = "GAME 1 MOVE 20 PLAYER 222 PLACED JUNGLE+JUNGLE AT -1 -1 -1 5 BUILT TOTORO SANCTUARY AT -5 -5 -5";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 1;
        assert adapter.moveNum== 20;
        assert adapter.pid== 222;
        assert adapter.tileTypeOne.equals("JUNGLE");
        assert adapter.tileTypeTwo.equals("JUNGLE");
        assert adapter.xPlaced == -1;
        assert adapter.yPlaced == -1;
        assert adapter.zPlaced == -1;
        assert adapter.orientation == 5;
        assert adapter.xBuilt== -5;
        assert adapter.yBuilt== -5;
        assert adapter.zBuilt == -5;
        assert adapter.totoro;

        testString = "GAME 2 MOVE 22 PLAYER 223 PLACED GRASS+GRASS AT -1 -1 -5 4 BUILT TIGER PLAYGROUND AT -6 -6 -6";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 2;
        assert adapter.moveNum == 22;
        assert adapter.pid== 223;
        assert adapter.tileTypeOne.equals("GRASS");
        assert adapter.tileTypeTwo.equals("GRASS");
        assert adapter.xPlaced == -1;
        assert adapter.yPlaced == -1;
        assert adapter.zPlaced == -5;
        assert adapter.orientation == 4;
        assert adapter.xBuilt== -6;
        assert adapter.yBuilt== -6;
        assert adapter.zBuilt== -6;
        assert adapter.tiger;

        testString = "GAME 2 OVER PLAYER 1 200 PLAYER 2 205";
        adapter.parseStringFromServer(testString);
        assert adapter.gid == 2;
        assert adapter.pid == 1;
        assert adapter.p1Score== 200;
        assert adapter.oid == 2;
        assert adapter.p2Score== 205;

        testString = "END OF ROUND 1 OF 2";
        adapter.parseStringFromServer(testString);
        assert adapter.rid == 1;
        assert adapter.numRounds == 2;


    }

}
