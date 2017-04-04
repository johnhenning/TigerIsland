package ServerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jslocke on 4/3/17.
 */
public class KnockKnockClient {
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

            String[] message;
            int pid;
            int cid;
            int numRounds;
            int rid;
            int oid;
            int gid;
            int moveNum;
            int tid;
            int xPlaced;
            int yPlaced;
            int zPlaced;
            int xBuilt;
            int yBuilt;
            int zBuilt;
            int orientation;
            int p1Score;
            int p2Score;
            String tileTypeOne;
            String tileTypeTwo;
            String terrainType;
            boolean founded;
            boolean expdanded;
            boolean totoro;
            boolean tiger;

            while ((fromServer = in.readLine()) != null) {
                message = fromServer.split(" +");
                if(fromServer.contains("WAIT FOR THE TOURNAMENT TO BEGIN "))
                    pid = Integer.parseInt(fromServer.substring(fromServer.indexOf("WAIT FOR THE TOURNAMENT TO BEGIN "+1)));
                else if(fromServer.contains("NEW CHALLENGE ")){
                   cid = Integer.parseInt(message[2]);
                   numRounds = Integer.parseInt(message[6]);
                }
                else if(fromServer.contains("BEGIN ROUND "))
                    rid = Integer.parseInt(message[2]);
                else if(fromServer.contains("NEW MATCH BEGINNING"))
                    oid = Integer.parseInt(message[8]);
                else if (fromServer.contains("MAKE YOUR MOVE IN GAME")){
                    gid = Integer.parseInt(message[5]);
                    moveNum = Integer.parseInt(message[10]);
                    tileTypeOne = message[12];
                    tileTypeOne = message[13];
                }
                else if(fromServer.contains("GAME")){
                    gid = Integer.parseInt(message[1]);
                    moveNum = Integer.parseInt(message[3]);
                    pid = Integer.parseInt(message[5]);
                    tileTypeOne = message[7];
                    tileTypeTwo = message[8];
                    xPlaced = Integer.parseInt(message[10]);
                    yPlaced = Integer.parseInt(message[11]);
                    zPlaced = Integer.parseInt(message[12]);
                    orientation = Integer.parseInt(message[13]);
                    if (fromServer.contains("FOUNDED")){
                       xBuilt = Integer.parseInt(message[17]);
                       yBuilt = Integer.parseInt(message[18]);
                       zBuilt = Integer.parseInt(message[19]);
                       founded = true;
                    }
                    else if (fromServer.contains("EXPANDED")){
                        xBuilt = Integer.parseInt(message[17]);
                        yBuilt = Integer.parseInt(message[18]);
                        zBuilt = Integer.parseInt(message[19]);
                        expdanded = true;
                        terrainType = message[20];
                    }
                    else if (fromServer.contains("TOTORO")){
                        xBuilt = Integer.parseInt(message[18]);
                        yBuilt = Integer.parseInt(message[19]);
                        zBuilt = Integer.parseInt(message[20]);
                        totoro = true;
                    }
                    else if (fromServer.contains("TIGER")){
                        xBuilt = Integer.parseInt(message[18]);
                        yBuilt = Integer.parseInt(message[19]);
                        zBuilt = Integer.parseInt(message[20]);
                        tiger = true;
                    }
                }
                else if (fromServer.contains("OVER PLAYER")){
                    gid = Integer.parseInt(message[1]);
                    pid = Integer.parseInt(message[4]);
                    p1Score = Integer.parseInt(message[5]);
                    oid = Integer.parseInt(message[7]);
                    p2Score = Integer.parseInt(message[8]);
                }
                else if(fromServer.contains("END OF ROUND")){
                    rid = Integer.parseInt(message[3]);
                    numRounds = Integer.parseInt(message[5]);
                }
                if (fromServer.equals("THANK YOU FOR PLAYING! GOODBYE"))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
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
