import GameInteractionModule.Turn;
import GameStateModule.BuildMoveType;
import GameStateModule.GameState;
import IOModule.AI;
import IOModule.Message;
import ServerModule.Adapter;
import ServerModule.GameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kyle on 4/9/2017.
 */
public class WhoLikesTigersAnyways {
    public static void main(String[] args) {
//        Thread serverTest = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int portNumber = 2222;
//                try (
//                        ServerSocket serverSocket = new ServerSocket(portNumber);
//
//                        Socket clientSocket = serverSocket.accept();
//                        PrintWriter out =
//                                new PrintWriter(clientSocket.getOutputStream(), true);
//                        BufferedReader in = new BufferedReader(
//                                new InputStreamReader(clientSocket.getInputStream()));
//                ) {
//
//                    String inputLine, outputLine;
//
//                    out.println("WELCOME TO ANOTHER EDITION OF THUNDERDOME!");
//                    out.println("TWO SHALL ENTER, ONE SHALL LEAVE");
//                    out.println("WAIT FOR THE TOURNAMENT TO BEGIN 2");
//                    out.println("NEW CHALLENGE 0 YOU WILL PLAY 1 MATCH");
//                    out.println("BEGIN ROUND 1 OF 1");
//                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 1");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE JUNGLE+LAKE");
//                    out.println("GAME B MOVE 0 PLAYER 2 PLACED JUNGLE+LAKE AT 2 0 -2 1 FOUNDED SETTLEMENT AT 3 0 -3");
//                    out.println("GAME A MOVE 0 PLAYER 1 PLACED JUNGLE+LAKE AT -1 1 0 5 FOUNDED SETTLEMENT AT -2 2 0");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE GRASS+JUNGLE");
//                    out.println("GAME A MOVE 0 PLAYER 2 PLACED GRASS+JUNGLE AT -3 1 2 5 FOUNDED SETTLEMENT AT -4 2 2");
//                    out.println("GAME B MOVE 0 PLAYER 1 PLACED GRASS+JUNGLE AT 1 2 -3 1 FOUNDED SETTLEMENT AT 2 2 -4");
//                    out.println("Making move for opponent..");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 1 PLACE JUNGLE+ROCK");
//                    out.println("GAME B MOVE 1 PLAYER 2 PLACED JUNGLE+ROCK AT 3 1 -4 1 EXPANDED SETTLEMENT AT 3 0 -3 JUNGLE");
//                    out.println("GAME A MOVE 1 PLAYER 1 PLACED JUNGLE+ROCK AT -4 0 4 3 EXPANDED SETTLEMENT AT -2 2 0 JUNGLE");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 1 PLACE GRASS+ROCK");
//                    out.println("GAME B MOVE 1 PLAYER 1 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME A MOVE 1 PLAYER 2 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME A OVER PLAYER 2 FORFEITED PLAYER 1 WIN");
//                    out.println("GAME B OVER PLAYER 1 FORFEITED PLAYER 2 WIN");
//                    out.println("END OF ROUND 1 OF 1");
//                    out.println("WAIT FOR THE NEXT CHALLENGE TO BEGIN");
//                    out.println("NEW CHALLENGE 1 YOU WILL PLAY 1 MATCH");
//                    out.println("BEGIN ROUND 1 OF 1");
//                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 1");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE GRASS+GRASS");
//                    out.println("GAME B MOVE 0 PLAYER 2 PLACED GRASS+GRASS AT 2 0 -2 3 FOUNDED SETTLEMENT AT 2 -1 -1");
//                    out.println("GAME A MOVE 0 PLAYER 1 PLACED GRASS+GRASS AT 2 -1 -1 4 FOUNDED SETTLEMENT AT 1 -1 0");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE LAKE+ROCK");
//                    out.println("GAME A MOVE 0 PLAYER 2 PLACED LAKE+ROCK AT 2 -3 1 5 FOUNDED SETTLEMENT AT 1 -2 1");
//                    out.println("GAME B MOVE 0 PLAYER 1 PLACED LAKE+ROCK AT 1 -1 0 3 FOUNDED SETTLEMENT AT 1 -2 1");
//                    out.println("Making move for opponent..");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 1 PLACE ROCK+JUNGLE");
//                    out.println("GAME A MOVE 1 PLAYER 1 PLACED ROCK+JUNGLE AT 3 -2 -1 1 EXPANDED SETTLEMENT AT 1 -1 0 LAKE");
//                    out.println("GAME B MOVE 1 PLAYER 2 PLACED ROCK+JUNGLE AT 0 2 -2 2 EXPANDED SETTLEMENT AT 2 -1 -1 LAKE");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 1 PLACE LAKE+ROCK");
//                    out.println("GAME A MOVE 1 PLAYER 2 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME B MOVE 1 PLAYER 1 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME A OVER PLAYER 2 FORFEITED PLAYER 1 WIN");
//                    out.println("GAME B OVER PLAYER 1 FORFEITED PLAYER 2 WIN");
//                    out.println("END OF ROUND 1 OF 1");
//                    out.println("WAIT FOR THE NEXT CHALLENGE TO BEGIN");
//                    out.println("NEW CHALLENGE 2 YOU WILL PLAY 1 MATCH");
//                    out.println("BEGIN ROUND 1 OF 1");
//                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 1");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE JUNGLE+ROCK");
//                    out.println("GAME A MOVE 0 PLAYER 1 PLACED JUNGLE+ROCK AT 1 -1 0 2 FOUNDED SETTLEMENT AT 2 -2 0");
//                    out.println("GAME B MOVE 0 PLAYER 2 PLACED JUNGLE+ROCK AT 0 2 -2 5 FOUNDED SETTLEMENT AT -1 3 -2");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE LAKE+JUNGLE");
//                    out.println("GAME B MOVE 0 PLAYER 1 PLACED LAKE+JUNGLE AT -2 4 -2 6 FOUNDED SETTLEMENT AT -2 5 -3");
//                    out.println("Making move for opponent..");
//                    out.println("GAME A MOVE 0 PLAYER 2 PLACED LAKE+JUNGLE AT -2 1 1 6 FOUNDED SETTLEMENT AT -2 2 0");
//                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 1 PLACE LAKE+LAKE");
//                    out.println("GAME B MOVE 1 PLAYER 2 PLACED LAKE+LAKE AT -2 3 -1 4 EXPANDED SETTLEMENT AT -1 3 -2 JUNGLE");
//                    out.println("GAME A MOVE 1 PLAYER 1 PLACED LAKE+LAKE AT 1 1 -2 6 EXPANDED SETTLEMENT AT 2 -2 0 JUNGLE");
//                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 1 PLACE JUNGLE+GRASS");
//                    out.println("GAME A MOVE 1 PLAYER 2 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME B MOVE 1 PLAYER 1 FORFEITED: ILLEGAL BUILD");
//                    out.println("GAME A OVER PLAYER 2 FORFEITED PLAYER 1 WIN");
//                    out.println("GAME B OVER PLAYER 1 FORFEITED PLAYER 2 WIN");
//                    out.println("END OF ROUND 1 OF 1");
//                    out.println("END OF CHALLENGES");
//                    //out.println("");
//
//                } catch (IOException e) {
//                    System.out.println("Exception caught when trying to listen on port "
//                            + portNumber + " or listening for a connection");
//                    System.out.println(e.getMessage());
//                }
//            }
//        });
//        serverTest.start();

        String hostname = args[0];
        int portNumber = Integer.parseInt(args[1]);

        String tournamentPassword = args[2];
        String username = args[3];
        String userPassword = args[4];



        GameClient gameClient = new GameClient(hostname, portNumber);
//        GameClient gameClient = new GameClient("10.228.1.171", 1708);
        Adapter adapter = new Adapter(gameClient);
        gameClient.authenticateConnection(tournamentPassword, username, userPassword);

        gameClient.waitForChallenge();

        while(!gameClient.endOfChallenge){
//            gameClient.roundProtocol();
            gameClient.matchProtocol();
            boolean firstMove = true;
            boolean gameOneNotOver = true;
            boolean gameTwoNotOver = true;
            boolean error = false;
            GameState gameState1 = new GameState();
            GameState gameState2 = new GameState();
            AI gameOneAI = new AI();
            AI gameTwoAI = new AI();


            while(true) {

                String s = gameClient.receiveMessage();

                if(s.contains("END OF ROUND")){
                    error = true;
                    break;
                }

                if (s.contains("GAME A")) {
                    if (s.contains("MAKE YOUR MOVE")) {
                        Adapter.parseStringFromServer(s);
                        Message AIMessage = adapter.getAITileInfo(s);
                        gameOneAI.completeTurn(AIMessage, gameState1);
                        adapter.sendAIMove(AIMessage, s);
                    }
                    if(s.contains("FORFEITED") || s.contains("LOST")) {
                        gameOneNotOver = false;
                        if(!gameTwoNotOver)
                            break;
                    }
                    else if(s.contains("PLAYER " + gameClient.ourPID)) {

                    }
                    else if(s.contains("PLAYER")) {
                        Adapter.parseStringFromServer(s);
                        Message opponentMove = adapter.getOpponentMove(s);
                        Turn.makeTileMove(opponentMove.tile, gameState1);
                        Turn.makeBuildMove(opponentMove.buildMove, gameState1);
                    }

                }
                else if(s.contains("GAME B")){
                    if (s.contains("MAKE YOUR MOVE")) {
                        Adapter.parseStringFromServer(s);
                        Message AIMessage = adapter.getAITileInfo(s);
                        gameTwoAI.completeTurn(AIMessage, gameState2);
                        adapter.sendAIMove(AIMessage, s);
                    }
                    if(s.contains("FORFEITED") || s.contains("LOST")) {
                        gameTwoNotOver = false;
                        if(!gameOneNotOver)
                            break;
                    }
                    else if(s.contains("PLAYER " + gameClient.ourPID)) {

                    }
                    else if(s.contains("PLAYER")) {
                        Adapter.parseStringFromServer(s);
                        System.out.println("Making move for opponent..");
                        Message opponentMove = adapter.getOpponentMove(s);
                        Turn.makeTileMove(opponentMove.tile, gameState2);
                        Turn.makeBuildMove(opponentMove.buildMove, gameState2);
                    }
                }
                if (!gameOneNotOver && !gameTwoNotOver) {
                    break;
                }
            }
            //if(error){

            //}else{
//                    gameClient.matchProtocol();
            //}

//                gameClient.roundProtocol();


        }
        //gameClient.roundProtocol();
//            gameClient.waitForChallenge();

        System.out.println("We did it! a.k.a shit's not fucked");
        gameClient.shutdown();
    }

}

