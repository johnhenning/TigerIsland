package ServerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import static ServerModule.Adapter.parseStringFromServer;

/**
 * Created by jslocke on 4/3/17.
 */



public class KnockKnockClient {
    private Socket kkSocket;
    private PrintWriter out;
    private BufferedReader serverMessage;
    private BufferedReader clientMessage;

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
            String[] parsedString = serverMessage.split(" ");
            int pid = Integer.parseInt(parsedString[6]);
            //out.println("PID IS " + pid);

        }

    }
    public String receiveMessage(){
        String fromServer;
        try
        {
            while ((fromServer = serverMessage.readLine()) != null){
                System.out.println("Recieved from Server: " + fromServer);

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
