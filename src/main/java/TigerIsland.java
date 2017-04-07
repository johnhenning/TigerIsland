import GameInteractionModule.Game;
import GameInteractionModule.Turn;
import GameStateModule.*;
import IOModule.Message;
import ServerModule.Adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TigerIsland {

    public static TerrainType generateTerrain(){
        Random rand = new Random();
        int randNum = rand.nextInt(4)+1;

        switch(randNum){
            case 1:
                return TerrainType.GRASSLAND;
            case 2:
                return TerrainType.LAKE;
            case 3:
                return TerrainType.JUNGLE;
            case 4:
                return TerrainType.ROCKY;
        }
        return null;
    }

    public static Tile generateTile(){
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(generateTerrain());
        terrains.add(generateTerrain());
        System.out.println(terrains.get(1).toString() + " " + terrains.get(2).toString());
        System.out.println("Enter coordinates for volcano: ");
        Scanner in = new Scanner(System.in);
        Coordinate coordinateOfVolcanoHex = new Coordinate(in.nextInt(),in.nextInt());
        System.out.println("Enter the orientation of the tile: ");
        int orientation = in.nextInt();
        Coordinate[] habitableTerrainCoordinates = Adapter.getCoordinatesOfOpponentsTile(coordinateOfVolcanoHex,orientation);
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(coordinateOfVolcanoHex);
        coordinates.add(habitableTerrainCoordinates[0]);
        coordinates.add(habitableTerrainCoordinates[1]);
        return new Tile(coordinates,terrains);


    }
    public static BuildMove getBuildMove(){
        BuildMove buildMove;
        BuildMoveType buildType;
        Coordinate coord;
        TerrainType terrain = TerrainType.ROCKY;
        String buildTypeString;
        String terrainString;

        System.out.println("Enter build move: ");
        Scanner in = new Scanner(System.in);
        buildTypeString = in.nextLine();
        switch(buildTypeString.toUpperCase()){
            case "F":
                buildType = BuildMoveType.FOUNDSETTLEMENT;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(),in.nextInt());
                return new BuildMove(buildType, coord, terrain);
            case "E":
                buildType = BuildMoveType.EXPANDSETTLEMENT;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(), in.nextInt());
                System.out.println("Enter terrain: ");
                terrainString = in.next();
                switch(terrainString.toUpperCase()){
                    case "G":
                        terrain = TerrainType.GRASSLAND;
                        break;
                    case "L":
                        terrain = TerrainType.LAKE;
                        break;
                    case "J":
                        terrain = TerrainType.JUNGLE;
                        break;
                    case "R":
                        terrain = TerrainType.ROCKY;
                        break;
                }
                return new BuildMove(buildType, coord, terrain);
            case "T":
                buildType = BuildMoveType.PLACETOTORO;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(), in.nextInt());
                return new BuildMove(buildType, coord, terrain);
            case "G":
                buildType = BuildMoveType.PLACETIGER;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(), in.nextInt());
                return new BuildMove(buildType, coord, terrain);
        }
        return null;
    }
    public static void main(String[] args) {

    GameState gameState = new GameState();
    Turn turn = new Turn();
    TerrainType terrain;
    boolean GameOver = true;
    boolean Player1Turn = true;
    boolean Player2Turn = true;
    Scanner in = new Scanner(System.in);

    while(GameOver){
        Player1Turn = true;
        Player2Turn = true;
        System.out.println("Player 1's turn!");
        while(Player1Turn){
            boolean invalidTilePlacement = true;
            while(invalidTilePlacement){
                Tile tile = generateTile();
                if(turn.makeTileMove(tile, gameState)){
                    invalidTilePlacement = false;
                }
                else
                {
                    System.out.println("Invalid tile placement!!!");
                }
            }
            boolean invalidBuildMove = true;
            while(invalidBuildMove){
                BuildMove buildMove = getBuildMove();
                if(turn.makeBuildMove(buildMove,gameState)){
                    invalidBuildMove = false;
                }
                else{
                    System.out.println("Invalid build move!!!");
                }
            }
            //String s = in.nextLine();
           // System.out.println(s);

            Player1Turn = false;
        }
        System.out.println("Player 2's turn!");
        while(Player2Turn){
            boolean invalidTilePlacement = true;
            while(invalidTilePlacement){
                Tile tile = generateTile();
                if(turn.makeTileMove(tile, gameState)){
                    invalidTilePlacement = false;
                }
                else
                {
                    System.out.println("Invalid tile placement!!!");
                }
            }
            boolean invalidBuildMove = true;
            while(invalidBuildMove){
                BuildMove buildMove = getBuildMove();
                if(turn.makeBuildMove(buildMove,gameState)){
                    invalidBuildMove = false;
                }
                else{
                    System.out.println("Invalid build move!!!");
                }
            }
            Player2Turn = false;
        }
        System.out.println("Would you like a game summary? y/n");
        String s = in.nextLine();
        if(s.contains("n")){

        }
        else if(s.contains("y")){
            System.out.println("Settlement Summary: ");
            System.out.println();
            gameState.printSettlementSummary();
            System.out.println();
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Placed tiles: ");
            System.out.println();
            gameState.printPlacedTiles();
            System.out.println("Would you like information about a specific hex on the gameboard? y/n");
            String s2 = in.nextLine();
            if(s2.contains("y")){
                System.out.println("Enter Hex coordinates");
                gameState.printHexSummary(in.nextInt(), in.nextInt());
            }
        }

    }

    /*
        Game game = new Game();
        AI ai = new AI();
        Server server = new Server();




        if (server.firstplayer == 'you') {
            firstPlayer = ai;
            secondPlayer = server.messages();
        } else {
            firstPlayer = server.messages();
            secondPlayer = ai;
        }

        while(!game.IsOver()){
            game.makeMove(firstPlayer.getMessage());
            game.makeMove(secondPlayer.getMessage());


        }
    */

    }
}
