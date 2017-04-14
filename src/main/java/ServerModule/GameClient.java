package ServerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static ServerModule.Adapter.*;

/**
 * Created by jslocke on 4/3/17.
 */



public class GameClient {
    private Socket kkSocket;
    private PrintWriter out;
    private BufferedReader serverMessage;
    private BufferedReader clientMessage;
    public boolean endOfChallenge = false;
    public boolean endOfMatch = false;
    public boolean gameNotOver = true;
    public boolean gameOneNotOver = true;
    public boolean gameTwoNotOver = true;

    public String ourPID;
    public String AITile;
    public String playerMove;
    public String opponentMove;
    public String gameOneID;
    public String gameTwoID;
    public int rounds;

    public GameClient(String hostName, int portNumber){


        try {
            this.kkSocket = new Socket(hostName, portNumber);
            this.out = new PrintWriter(kkSocket.getOutputStream(), true);
            this.serverMessage = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

        }
        catch (IOException e){ System.out.println("Shits Fucked");}
    }

    public void authenticateConnection(String tournamentPassword, String username, String userPassword){
        if(receiveMessage().contains("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")){
            out.println("ENTER THUNDERDOME"+ " " + tournamentPassword);
        }
        if(receiveMessage().contains("TWO SHALL ENTER, ONE SHALL LEAVE")){
            out.println("I AM" + " " + username + " " + userPassword);
        }
        String serverMessage = receiveMessage();
        if(serverMessage.contains("WAIT FOR THE TOURNAMENT TO BEGIN")){
            parseStringFromServer(serverMessage);
            ourPID = Adapter.ourPid;
        }

    }

    public void waitForChallenge(){
        String challengeString = receiveMessage();
        if(challengeString.startsWith("NEW CHALLENGE ")){
           endOfChallenge = false;
           parseStringFromServer(challengeString);
           rounds = Integer.parseInt(numRounds);



        }else if(challengeString.contains("WAIT FOR THE NEXT")){
            waitForChallenge();
        }else if(challengeString.contains("END OF CHALLENGE")){
            endOfChallenge = true;
        }


    }



    public void roundProtocol(){
        String roundProtocolMessage = receiveMessage();
        if(roundProtocolMessage.startsWith("BEGIN ROUND")){

        }
        else if(roundProtocolMessage.contains("WAIT FOR THE NEXT MATCH")){
            endOfMatch = false;

        }
        else if(roundProtocolMessage.startsWith("END OF ROUND")){
            gameNotOver = true;
            endOfChallenge = false;
        }
        else{
            System.out.println("Error: " + roundProtocolMessage);
            //throw new AssertionError();
        }

    }
    public void matchProtocol(){
        String matchProtocolMessage = receiveMessage();
        if(matchProtocolMessage.contains("NEW MATCH")){
           //do nothing
        }
        else if(matchProtocolMessage.contains("OVER PLAYER")){
            receiveMessage();
            return;
        }
        parseStringFromServer(matchProtocolMessage);

    }
    public void moveProtocol2(){
        String moveMessage = receiveMessage();
        if(moveMessage.startsWith("MAKE YOUR MOVE")){
            AITile = moveMessage;
            return;
        }

        if(moveMessage.contains("PLAYER " + ourPID)){
            //game over message contains both PIDS, both getting sent while game occurs
            playerMove = moveMessage;
        }
        else if(moveMessage.startsWith("MAKE YOUR MOVE")){
            //this should not occur
            System.out.println("SHITS FUCKED");
            AITile = moveMessage;
        }
        else{
            opponentMove = moveMessage;
        }
    }
    public void moveProtocol(){
        String serverMessage, serverMessage2, serverMessage3;
        if(!gameNotOver) {
            serverMessage = receiveMessage();
            ArrayList<String> strings = new ArrayList<>();


            if (serverMessage.startsWith("NEW MATCH")) {

                AITile = receiveMessage();
                String[] gameIDString = AITile.split(" ");


                gameOneID = gameIDString[5];
                gameOneNotOver = true;
                gameTwoNotOver = true;
                return;
            } else if (serverMessage.contains("OVER PLAYER")) {
                receiveMessage();
                endOfMatch = true;
            }
            else if(serverMessage.contains("FORFEIT") || serverMessage.contains("LOST")){
                parseStringFromServer(serverMessage);
                if(gameOneID.equals(gidTwo)){
                    gameOneNotOver = false;
                }else{
                    gameTwoNotOver = false;
                }
                gameNotOver = false;
                moveProtocol();
            }
            else {
                serverMessage2 = receiveMessage();
                strings.add(serverMessage);
                strings.add(serverMessage2);
                for (String s : strings) {
                    if (s.contains("MAKE YOUR MOVE")) {
                        AITile = s;
                    } else if (s.contains(ourPID)) {

                    } else {
                        opponentMove = s;
                    }
                }
            }
        }
        else if(gameNotOver){
            serverMessage = receiveMessage();
            ArrayList<String> strings2 = new ArrayList<>();


            if(serverMessage.startsWith("NEW MATCH")){
                AITile = receiveMessage();
                String[] gameIDString = AITile.split(" ");
                parseStringFromServer(AITile);


                gameOneID = gameIDString[5];
                gameOneNotOver = true;
                gameTwoNotOver = true;
                return;
            }
            else if(serverMessage.contains("OVER PLAYER")){
                receiveMessage();
                endOfMatch = true;
            }
            else if(serverMessage.contains("FORFEIT") || serverMessage.contains("LOST")){
                parseStringFromServer(serverMessage);
                if(gameOneID.equals(gidTwo)){
                    gameOneNotOver = false;
                }else{
                    gameTwoNotOver = false;
                }
                gameNotOver = false;
                moveProtocol();
            }
            else{
                serverMessage2 = receiveMessage();
                serverMessage3 = receiveMessage();
                strings2.add(serverMessage);
                strings2.add(serverMessage2);
                strings2.add(serverMessage3);
                for(String s : strings2){
                    if(s.contains("MAKE YOUR MOVE")){
                        AITile = s;
                    }
                    else if(s.contains(ourPID)){

                    }
                    else{
                        opponentMove = s;
                    }
                }

            }
        }




    }

    public String receiveMessage(){
        String fromServer;
        try
        {


            fromServer = serverMessage.readLine();
            System.out.println(fromServer);
            return fromServer;
//            fromServer = serverMessage.readLine();
//            System.out.println("Received from Server: " + fromServer);
//            return fromServer;
//            while ((fromServer = serverMessage.readLine()) != null){
//                String returnString = "";
//                for(int i = 0; i < fromServer.length(); i++){
//                    returnString += fromServer.charAt(i);
//                }
//                System.out.println("Received from Server: " + fromServer);
//
//
//                return returnString;
//            }
//            return null;
        }
        catch (IOException e){ System.out.println("SHITS FUCKED"); }
        return null;
    }
    public void sendMessage(String s){

        out.println(s);
        //out.write(s);

        System.out.println("Sent to Server: " + s);
    }

    public void shutdown(){
        out.close();
        try{
            serverMessage.close();
        }catch (IOException e){}
    }

}
