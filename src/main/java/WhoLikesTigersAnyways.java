import GameInteractionModule.Turn;
import GameStateModule.BuildMoveType;
import GameStateModule.GameState;
import IOModule.AI;
import IOModule.Message;
import ServerModule.Adapter;
import ServerModule.KnockKnockClient;

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
                    String password = "Cheese";
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
                    String pid = "420";
                    out.println("WAIT FOR THE TOURNAMENT TO BEGIN "+pid);
                    out.println("NEW CHALLENGE 1 YOU WILL PLAY 2 MATCHES");
                    out.println("BEGIN ROUND 1 OF 2");
                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 69");
                    out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1.5 SECOND: MOVE 1 PLACE LAKE+LAKE");
                    out.println("GAME 1 MOVE 1 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
                    out.println("GAME 2 MOVE 1 PLAYER 69 PLACED ROCKY+GRASSLAND AT 1 -1 0 2 FOUNDED SETTLEMENT AT 2 -1 -1");
                    out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECOND: MOVE 2 PLACE ROCKY+ROCKY");
                    out.println("GAME 2 MOVE 2 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUND SETTLEMENT AT 1 2 2");

                    out.println("GAME 1 MOVE 2 PLAYER 69 FORFEITED: ILLEGAL BUILD");
                    out.println("GAME 2 MOVE 3 PLAYER 69 FORFEITED: ILLEGAL BUILD");

                    out.println("GAME 1 OVER PLAYER 420 1 PLAYER 69 1");
                    out.println("GAME 2 OVER PLAYER 69 1 PLAYER 420 1");
                    out.println("END OF ROUND 1 OF 2 WAIT FOR THE NEXT MATCH");
                    out.println("BEGIN ROUND 2 OF 2");
                    out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 69");

                    out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1.5 SECOND: MOVE 1 PLACE LAKE+LAKE");
                    out.println("GAME 1 MOVE 1 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
                    out.println("GAME 2 MOVE 1 PLAYER 69 PLACED ROCKY+GRASSLAND AT 1 -1 0 2 FOUNDED SETTLEMENT AT 2 -1 -1");

                    out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECOND: MOVE 2 PLACE ROCKY+ROCKY");
                    out.println("GAME 2 MOVE 2 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
                    out.println("GAME 1 MOVE 3 PLAYER 69 FORFEITED: ILLEGAL TILE PLACEMENT");

                    out.println("GAME 2 MOVE 1 PLAYER 69 PLACED ROCKY+GRASSLAND AT 2 0 -2 1 FOUNDED SETTLEMENT AT 2 1 -3");
                    out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1.5 SECOND: MOVE 2 PLACE ROCKY+ROCKY");
                    out.println("GAME 2 MOVE 2 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
                    out.println("GAME 2 MOVE 3 PLAYER 69 FORFEITED: ILLEGAL TILE PLACEMENT");

                    out.println("GAME 1 OVER PLAYER 420 1 PLAYER 69 1");
                    out.println("GAME 2 OVER PLAYER 69 1 PLAYER 420 1");

                    out.println("END OF ROUND 2 OF 2");
                    out.println("END OF CHALLENGES");
                    //out.println("THANK YOU FOR PLAYING! GOODBYE");
                    return;

                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        });
        serverTest.start();





        KnockKnockClient gameClient = new KnockKnockClient("localhost", 2222);
        Adapter adapter = new Adapter(gameClient);
        gameClient.authenticateConnection("Cheese", "Cheese123", "456");

        gameClient.waitForChallenge();

        while(!gameClient.endOfChallenge){
            for(int i = 0; i < gameClient.rounds; i++){
                gameClient.roundProtocol();
                gameClient.matchProtocol();
                boolean firstMove = true;
                boolean gameOneNotOver = true;
                boolean gameTwoNotOver = true;
                GameState gameState1 = new GameState();
                GameState gameState2 = new GameState();
                AI gameOneAI = new AI();
                AI gameTwoAI = new AI();

                while(gameOneNotOver || gameTwoNotOver){

                    if(gameOneNotOver){
                        if(firstMove){
                            firstMove = false;
                            gameClient.moveProtocol2();

                            Message AIMessage = adapter.getAITileInfo(gameClient.AITile);
                            gameOneAI.completeTurn(AIMessage, gameState1);

                            adapter.sendAIMove(AIMessage);

                            //make AI move
                            boolean forfeitAI = false;
                            if(AIMessage.buildMove.buildMoveType == BuildMoveType.UNABLE_TO_BUILD) {//read AI's echoed move
                                gameOneNotOver = false;
                            }
                            gameClient.moveProtocol2();


                        }else{
                            gameClient.moveProtocol2();
                            if(gameClient.opponentMove.contains("FORFEIT") || gameClient.opponentMove.contains("LOST")){
                                gameOneNotOver = false;
                            }
                            else{
                                //make Opponent Move
                                Message opponentMove = adapter.getOpponentMove(gameClient.opponentMove);
                                Turn.makeTileMove(opponentMove.tile, gameState1);
                                Turn.makeBuildMove(opponentMove.buildMove, gameState1);
                                //get AI tile
                                gameClient.moveProtocol2();
                                //make AI move
                                Message AIMessage = adapter.getAITileInfo(gameClient.AITile);
                                gameOneAI.completeTurn(AIMessage, gameState1);

                                adapter.sendAIMove(AIMessage);

                                boolean forfeitAI = false;
                                if(AIMessage.buildMove.buildMoveType == BuildMoveType.UNABLE_TO_BUILD) {
                                    gameOneNotOver = false;
                                }
                                gameClient.moveProtocol2();

                            }
                        }
                    }

                    if(gameTwoNotOver){
                        gameClient.moveProtocol2();
                        if(gameClient.opponentMove.contains("FORFEIT") || gameClient.opponentMove.contains("LOST")){
                            gameTwoNotOver = false;
                        }
                        else{
                            //make Opponent Move
                            Message opponentMove = adapter.getOpponentMove(gameClient.opponentMove);
                            Turn.makeTileMove(opponentMove.tile, gameState2);
                            Turn.makeBuildMove(opponentMove.buildMove, gameState2);
                            //get AI tile
                            gameClient.moveProtocol2();
                            //make AI move
                            Message AIMessage = adapter.getAITileInfo(gameClient.AITile);
                            gameTwoAI.completeTurn(AIMessage, gameState2);

                            adapter.sendAIMove(AIMessage);
                            boolean forfeitAI = false;

                            if(AIMessage.buildMove.buildMoveType == BuildMoveType.UNABLE_TO_BUILD) {//read AI's echoed move
                               gameTwoNotOver = false;
                            }
                            gameClient.moveProtocol2();

                        }
                    }
                }
                gameClient.matchProtocol();

            }
            gameClient.roundProtocol();
            gameClient.waitForChallenge();
        }
        System.out.println("we played a game");
    }

}
