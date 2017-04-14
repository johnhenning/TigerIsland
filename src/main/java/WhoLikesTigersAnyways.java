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
                    String password = "heygang";
                    while((inputLine = in.readLine()) != null){
                        if(inputLine.contains("ENTER THUNDERDOME " + password)) {
                            System.out.println(inputLine);
                            break;
                        }
                    }
                    out.println("TWO SHALL ENTER, ONE SHALL LEAVE");
                    String username = "Cheese123";
                    while ((inputLine = in.readLine()) != null){
                        if (inputLine.contains("I AM "+username+" "+password));
                        System.out.println(inputLine);
                        break;
                    }
                    String pid = "4";
                    out.println("WAIT FOR THE TOURNAMENT TO BEGIN "+pid);
                    out.println("NEW CHALLENGE 0 YOU WILL PLAY 7 MATCHES");
                    out.println("BEGIN ROUND 1 OF 7");
                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 5");
                    out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECONDS: MOVE 0 PLACE GRASS+LAKE");
                    out.println(in.readLine()); //game A our first move
                    out.println("GAME B MOVE 0 PLAYER 5 PLACED GRASS+LAKE AT -1 1 0 5 FOUNDED SETTLEMENT AT 0 1 -1");

                    //out.println("GAME A MOVE 1 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
                    out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECONDS: MOVE 0 PLACE ROCK+JUNGLE");
                    out.println(in.readLine());
                    out.println("GAME A MOVE 0 PLAYER 5 PLACED ROCK+JUNGLE AT 0 -4 4 3 FOUNDED SETTLEMENT AT 0 -3 3");

                    out.println("END OF ROUND 2 OF 2");
                    out.println("END OF CHALLENGES");
                    out.println("THANK YOU FOR PLAYING! GOODBYE");
                    out.close();
                    in.close();
                    return;

                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        });
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

