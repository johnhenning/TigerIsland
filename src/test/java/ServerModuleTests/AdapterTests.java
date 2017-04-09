package ServerModuleTests;

import ServerModule.Adapter;
import GameStateModule.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by carlos on 4/3/2017.
 */
public class AdapterTests {

    private Adapter adapter;
    private String testString;
    private String authenticationString;
    private int[] cubicCoord;
    private int[] axialCoord;
    private int x;
    private int y;
    private int z;
    private Coordinate habitableCoordinates[];
    private Coordinate volcanoCoordinate;
    int orientation;



    @Before
    public void setup()throws Exception{
        adapter = new Adapter(null);
        testString = "WAIT FOR THE TOURNAMENT TO BEGIN 2";
        authenticationString = "WELCOME TO ANOTHER EDITION OF THUNDERDOME!";
        x = 0;
        y = 0;
        z = 0;

        volcanoCoordinate = new Coordinate(100, 100);
        orientation = 1;



    }


    @Test
    public void parseStringFromServerTest(){
        adapter.parseStringFromServer(testString);
        assert adapter.ourPid.equals("2");

        testString = "NEW CHALLENGE 28 YOU WILL PLAY 8 MATCHES";
        adapter.parseStringFromServer(testString);
        assert adapter.cid.equals("28");
        assert adapter.numRounds.equals("8");

        testString = "BEGIN ROUND 1 OF 2";
        adapter.parseStringFromServer(testString);
        assert adapter.rid.equals("1");

        testString = "NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 234";
        adapter.parseStringFromServer(testString);
        assert adapter.oid.equals("234");

        testString = "MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECONDS: MOVE 5 PLACE LAKE+ROCK";
        adapter.parseStringFromServer(testString);
        assert adapter.gidOne.equals("2");
        assert adapter.moveNum == 5;
        assert adapter.tileTypeOne.equals("LAKE");
        assert adapter.tileTypeTwo.equals("ROCK");

        testString = "GAME 2 MOVE 8 PLAYER 222 PLACED JUNGLE+GRASS AT -1 -1 -2 1 FOUNDED SETTLEMENT AT -1 -2 3";
        adapter.parseStringFromServer(testString);
        assert adapter.gidTwo.equals("2");;
        assert adapter.moveNum == 8;
        assert adapter.pid.equals("222");
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
        assert adapter.gidTwo.equals("1");;
        assert adapter.moveNum == 10;
        assert adapter.pid.equals("223");;
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
        assert adapter.gidTwo.equals("1");;
        assert adapter.moveNum== 20;
        assert adapter.pid.equals("222");
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
        assert adapter.gidTwo.equals("2");
        assert adapter.moveNum == 22;
        assert adapter.pid.equals("223");
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
        assert adapter.gidTwo.equals("2");
        assert adapter.pid.equals("1");
        assert adapter.p1Score== 200;
        assert adapter.oid.equals("2");
        assert adapter.p2Score== 205;



    }

    @Test
    public void convertCubeToAxialTest(){
        axialCoord = adapter.convertCubeToAxial(x,y,z);
        assert axialCoord[0] == 100;
        assert axialCoord[1] == 100;

        x=0;
        y=1;
        z=-1;
        axialCoord = adapter.convertCubeToAxial(x,y,z);
        assert axialCoord[0] == 99;
        assert axialCoord[1] == 99;
    }

    @Test
    public void convertAxialToCubeTest(){
        x = 100;
        y = 100;
        cubicCoord = adapter.convertAxialToCube(x,y);
        assert cubicCoord[0] == 0;
        assert cubicCoord[1]== 0;
        assert cubicCoord[2]==0;

        x=99;
        y=99;
        cubicCoord = adapter.convertAxialToCube(x,y);
        assert cubicCoord[0] == 0;
        assert cubicCoord[1] == 1;
        assert cubicCoord[2] == -1;

        x=100;
        y=99;
        cubicCoord = adapter.convertAxialToCube(x,y);
        assert cubicCoord[0] == 1;
        assert cubicCoord[1] == 0;
        assert cubicCoord[2] == -1;

    }

    @Test
    public void getCoordinatesOfOpponentsTileTest(){
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 99) && (habitableCoordinates[0].getY() == 99);
        assert (habitableCoordinates[1].getX() == 100) && (habitableCoordinates[1].getY() == 99);

        volcanoCoordinate = new Coordinate(98,100);
        orientation = 2;
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 98) && (habitableCoordinates[0].getY() == 99);
        assert (habitableCoordinates[1].getX() == 99) && (habitableCoordinates[1].getY() == 100);

        volcanoCoordinate = new Coordinate(98,100);
        orientation = 3;
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 99) && (habitableCoordinates[0].getY() == 100);
        assert (habitableCoordinates[1].getX() == 98) && (habitableCoordinates[1].getY() == 101);

        volcanoCoordinate = new Coordinate(100,100);
        orientation = 4;
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 100) && (habitableCoordinates[0].getY() == 101);
        assert (habitableCoordinates[1].getX() == 99) && (habitableCoordinates[1].getY() == 101);

        volcanoCoordinate = new Coordinate(103,98);
        orientation = 5;
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 102) && (habitableCoordinates[0].getY() == 99);
        assert (habitableCoordinates[1].getX() == 102) && (habitableCoordinates[1].getY() == 98);

        volcanoCoordinate = new Coordinate(100,100);
        orientation = 6;
        habitableCoordinates = adapter.getCoordinatesOfOpponentsTile(volcanoCoordinate, orientation);
        assert (habitableCoordinates[0].getX() == 99) && (habitableCoordinates[0].getY() == 100);
        assert (habitableCoordinates[1].getX() == 99) && (habitableCoordinates[1].getY() == 99);
    }

    @Test
    public void getOrientationFromOurTileTest(){
        habitableCoordinates = new Coordinate[2];
        habitableCoordinates[0] = new Coordinate(99,99);
        habitableCoordinates[1] = new Coordinate(100,99);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 1;

        habitableCoordinates[0] = new Coordinate(100,99);
        habitableCoordinates[1] = new Coordinate(101,100);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 2;

        habitableCoordinates[0] = new Coordinate(101,100);
        habitableCoordinates[1] = new Coordinate(100,101);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 3;

        habitableCoordinates[0] = new Coordinate(100,101);
        habitableCoordinates[1] = new Coordinate(99,101);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 4;

        habitableCoordinates[0] = new Coordinate(99,101);
        habitableCoordinates[1] = new Coordinate(99,100);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 5;

        habitableCoordinates[0] = new Coordinate(99,100);
        habitableCoordinates[1] = new Coordinate(99,99);
        orientation = adapter.getOrientationFromOurTile(volcanoCoordinate, habitableCoordinates);
        assert orientation == 6;
    }
}
