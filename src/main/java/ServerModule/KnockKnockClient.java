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

    public KnockKnockClient(String hostName, int portNumber){
        try {
            this.kkSocket = new Socket(hostName, portNumber);
            this.out = new PrintWriter(kkSocket.getOutputStream(), true);
            this.serverMessage = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

        }
        catch (IOException e){ System.out.println("Shits Fucked");}
    }

    public void authenticateConnection(String tournamentPassword, String username, String userPassword){
        if(getServerMessage().contains("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")){
            out.println("ENTER THUNDERDOME"+ " " + tournamentPassword);

        }
        if(getServerMessage().contains("TWO SHALL ENTER, ONE SHALL LEAVE")){
            out.println("I AM" + " " + username + " " + userPassword);
        }
        String serverMessage = getServerMessage();
        if(serverMessage.contains("WAIT FOR THE TOURNAMENT TO BEGIN")){
            String[] parsedString = serverMessage.split(" ");
            int pid = Integer.parseInt(parsedString[6]);
            //out.println("PID IS " + pid);

        }

    }
    public String getServerMessage(){
        String fromServer;
        try
        {
            while ((fromServer = serverMessage.readLine()) != null){
                System.out.println("Server: " + fromServer);
                out.println("Got it!");
                if(fromServer.contains("THANK YOU FOR PLAYING")){
                    break;
                }
                return fromServer;
            }
            return null;
        }
        catch (IOException e){}
        return null;
    }



    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;



            while ((fromServer = in.readLine()) != null) {
                parseStringFromServer(fromServer);
                if (fromServer.equals("THANK YOU FOR PLAYING! GOODBYE"))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
