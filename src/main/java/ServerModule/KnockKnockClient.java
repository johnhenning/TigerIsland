package ServerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static ServerModule.Adapter.parseStringFromServer;

/**
 * Created by jslocke on 4/3/17.
 */



public class KnockKnockClient {
    private Socket kkSocket;
    private PrintWriter out;
    private BufferedReader serverMessage;
    private BufferedReader clientMessage;
    public boolean endOfChallenge = false;
    public boolean endOfMatch = false;
    public boolean gameNotOver = true;

    private String ourPID;
    public String AITile;
    public String opponentMove;

    public KnockKnockClient(String hostName, int portNumber){
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
        if(!challengeString.startsWith("NEW CHALLENGE ")){
            throw new AssertionError();
        }
        parseStringFromServer(challengeString);
    }



    public void roundProtocol(){
        String roundProtocolMessage = receiveMessage();
        if(roundProtocolMessage.startsWith("BEGIN ROUND")){
            moveProtocol();
        }
        else if(roundProtocolMessage.contains("WAIT FOR NEXT MATCH")){
            roundProtocol();
        }
        else if(roundProtocolMessage.startsWith("END OF ROUND")){
            gameNotOver = true;
            endOfChallenge = true;
        }
        else{
            throw new AssertionError();
        }
//        if(checkifEndChallenges.contains("END OF CHALLENGES")){
//            challengeNotOver = false;
//        }
//        else if(checkifEndChallenges.contains("WAIT FOR NEXT CHALLENGE TO BEGIN")){
//            String s = waitForChallenge();
//            parseStringFromServer(s);
//            roundProtocol();
//        }
}


    public void moveProtocol(){
        String serverMessage, serverMessage2, serverMessage3;
        if(!gameNotOver) {
            serverMessage = receiveMessage();
            ArrayList<String> strings = new ArrayList<>();


            if (serverMessage.startsWith("NEW MATCH")) {
                AITile = receiveMessage();
                return;
            } else if (serverMessage.contains("OVER PLAYER")) {
                receiveMessage();
                endOfMatch = true;
            }
            else if(serverMessage.contains("FORFEIT") || serverMessage.contains("LOST")){
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
                return;
            }
            else if(serverMessage.contains("OVER PLAYER")){
                receiveMessage();
                endOfMatch = true;
            }
            else if(serverMessage.contains("FORFEITED") || serverMessage.contains("LOST")){
                gameNotOver = false;
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
            while ((fromServer = serverMessage.readLine()) != null){
                System.out.println("Received from Server: " + fromServer);

                return fromServer;
            }
            return null;
        }
        catch (IOException e){}
        return null;
    }
    public void sendMessage(String s){
        out.println(s);
    }

}
