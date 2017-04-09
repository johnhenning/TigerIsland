import GameInteractionModule.Rules.GameEndingRules;
import GameInteractionModule.Turn;
import GameStateModule.*;
import IOModule.AI;
import IOModule.Opponent;
import ServerModule.Adapter;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import GameInteractionModule.Game;
import GameInteractionModule.Turn;
import GameStateModule.*;
import IOModule.AI;
import IOModule.Message;
import ServerModule.Adapter;
import ServerModule.KnockKnockClient;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kyle on 4/8/2017.
 */
public class TigerIslandWithAI {

    /**
     * Created by johnhenning on 3/19/17.
     */


    public static TerrainType generateTerrain() {
        Random rand = new Random();
        int randNum = rand.nextInt(4) + 1;

        switch (randNum) {
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

    public static Tile generateTileForRobot() {
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(generateTerrain());
        terrains.add(generateTerrain());
        return new Tile(terrains);
    }

    public static Tile generateTile() {
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(generateTerrain());
        terrains.add(generateTerrain());
        System.out.println(terrains.get(1).toString() + " " + terrains.get(2).toString());
        System.out.println("Enter coordinates for volcano: ");
        Scanner in = new Scanner(System.in);
        Coordinate coordinateOfVolcanoHex = new Coordinate(in.nextInt(), in.nextInt());
        System.out.println("Enter the orientation of the tile: ");
        int orientation = in.nextInt();
        Coordinate[] habitableTerrainCoordinates = Adapter.getCoordinatesOfOpponentsTile(coordinateOfVolcanoHex, orientation);
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(coordinateOfVolcanoHex);
        coordinates.add(habitableTerrainCoordinates[0]);
        coordinates.add(habitableTerrainCoordinates[1]);
        return new Tile(coordinates, terrains);


    }

    public static BuildMove getBuildMove() {
        BuildMove buildMove;
        BuildMoveType buildType;
        Coordinate coord;
        TerrainType terrain = TerrainType.ROCKY;
        String buildTypeString;
        String terrainString;

        System.out.println("Enter build move: ");
        Scanner in = new Scanner(System.in);
        buildTypeString = in.nextLine();
        switch (buildTypeString.toUpperCase()) {
            case "F":
                buildType = BuildMoveType.FOUNDSETTLEMENT;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(), in.nextInt());
                return new BuildMove(buildType, coord, terrain);
            case "E":
                buildType = BuildMoveType.EXPANDSETTLEMENT;
                System.out.println("Enter coordinate: ");
                coord = new Coordinate(in.nextInt(), in.nextInt());
                System.out.println("Enter terrain: ");
                terrainString = in.next();
                switch (terrainString.toUpperCase()) {
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
        Thread serverTest = new Thread(new Runnable() {
            @Override
            public void run() {
                int portNumber = 2222;
                try (
                        ServerSocket serverSocket = new ServerSocket(portNumber);

                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out =
                                new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    String inputLine, outputLine;

                    out.println("WELCOME TO ANOTHER EDITION OF THUNDERDOME!");
                    String password = "Cheese";
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("ENTER THUNDERDOME " + password)) {
                            System.out.println(inputLine);
                            break;
                        }
                    }
                    out.println("TWO SHALL ENTER, ONE SHALL LEAVE");
                    String username = "Cheese123";
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("I AM " + username + " " + password)) ;
                        System.out.println(inputLine);
                        break;
                    }
                    String pid = "456";
                    out.println("WAIT FOR THE TOURNAMENT TO BEGIN " + pid);
//                    out.println("THANK YOU FOR PLAYING! GOODBYE");
                    int i = 0;
                    while(true){
                        out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1.5 SECONDS: MOVE " + i++ + " PLACE " +
                                            generateTerrain().toString() + "+" + generateTerrain().toString());

                        for(int j = 0; j < 2; j++) inputLine = in.readLine();

                        System.out.println("THIS IS THE SERVER TALKING: PLEASE SIMULATE AN INPUT");


                        System.out.println(inputLine);

                    }


                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        });

        serverTest.start();
        try{serverTest.sleep(1000);} catch (InterruptedException e){};
        KnockKnockClient gameClient = new KnockKnockClient("localhost", 2222);
        Adapter adapter = new Adapter(gameClient);
        gameClient.authenticateConnection("Cheese", "Cheese123", "456");

       /* gameClient.waitForChallenge();
        while(!adapter.endOfChallengs){
            gameClient.roundProtocol();
            for(numofRounds){
                GameState game1 = new GameState();
                GameState game2 = new GameState();
                gameClient.waitForMatch();
                Message message = adapter.getAITileInfo();
                while(!matchOver){
                    if(!gameClient.game1Over){
                        if(AITurn){
                            //make AI move
                        }
                        else if(OpponentTurn){
                            //make Opponent move
                        }
                    }
                    gameClient.moveProtocol();
                    if(!gameClient.game2Over){
                        if(AITurn){
                            //make AI move
                        }
                        else if(OpponentTurn){
                            //make Opponent move
                        }
                    }
                    gameClient.moveProtocol();

                }
            }
            gameClient.challengeProtocol();
        }*/

        GameState gameState = new GameState();
        Turn turn = new Turn();
        TerrainType terrain;
        boolean GameOver = true;
        boolean Player1Turn = true;
        boolean Player2Turn = true;
        Scanner in = new Scanner(System.in);

        AI player1 = new AI();
        gameState.getCurrentPlayer().removeMeeple(17);

        while (GameOver) {
            Player1Turn = true;

            System.out.println("Player 1's turn!");
            while (Player1Turn) {
                if(GameEndingRules.playerCannotCompleteBuildAction(gameState.getCurrentPlayer(), gameState)){
                    GameOver = false;
                    System.out.println("Our AI is stupid");
                    break;
                }
                Message message = adapter.getAITileInfo();
                long start_time = System.currentTimeMillis();
                player1.completeTurn(message, gameState);
                Player1Turn = false;
                System.out.println("Time to complete : " + (System.currentTimeMillis() - start_time));
                adapter.sendAIMove(message);
                Player2Turn = true;
            }
            if(GameOver == false){


                break;
            }

            System.out.println("Player 2's turn!");
            while (Player2Turn) {
                boolean invalidTilePlacement = true;
                while (invalidTilePlacement) {
                    Tile tile = generateTile();
                    if (turn.makeTileMove(tile, gameState)) {
                        invalidTilePlacement = false;
                    } else {
                        System.out.println("Invalid tile placement!!!");
                    }
                }
                boolean invalidBuildMove = true;
                while (invalidBuildMove) {
                    BuildMove buildMove = getBuildMove();
                    if (turn.makeBuildMove(buildMove, gameState)) {
                        invalidBuildMove = false;
                    } else {
                        System.out.println("Invalid build move!!!");
                    }
                }
                Player2Turn = false;
            }
            System.out.println("Would you like a game summary? y/n");
            String s = in.nextLine();
            if (s.contains("n")) {

            } else if (s.contains("y")) {
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
                if (s2.contains("y")) {
                    System.out.println("Enter Hex coordinates");
                    gameState.printHexSummary(in.nextInt(), in.nextInt());
                }
            }

            try{serverTest.sleep(1000);} catch (InterruptedException e){};



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



