package ServerModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * Created by jslocke on 4/3/17.
 */



public class KnockKnockClient {

    private static String[] message;
    private static String delimiters = "[ +]+";
    private static int pid;
    private static int cid;
    private static int numRounds;
    private static int rid;
    private static int oid;
    private static int gid;
    private static int moveNum;
    private static int tid;
    private static int xPlaced;
    private static int yPlaced;
    private static int zPlaced;
    private static int xBuilt;
    private static int yBuilt;
    private static int zBuilt;
    private static int orientation;
    private static int p1Score;
    private static int p2Score;
    private static String tileTypeOne;
    private static String tileTypeTwo;
    private static String terrainType;
    private static boolean founded;
    private static boolean expdanded;
    private static boolean totoro;
    private static boolean tiger;

    public static void parseStringFromServer(String fromServer){
        message = fromServer.split(delimiters);
        if(fromServer.contains("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")){
           authenticationProtocol();
        }
        else if(fromServer.contains("WAIT FOR THE TOURNAMENT TO BEGIN "))
            pid = Integer.parseInt(message[6]);

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
            tileTypeTwo = message[13];

        }
        else if(fromServer.contains("PLACED")){
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


    }

    public static void authenticationProtocol() {
        Scanner in = new Scanner(System.in);

        String toServer;
        String fromServer;
        String enterThunderdome = "ENTER THUNDERDOME ";
        toServer = in.next();
        System.out.println(enterThunderdome + toServer);



    }
    public static int getCid() {
        return cid;
    }

    public static int getNumRounds() {
        return numRounds;
    }

    public static int getPid() {
        return pid;
    }

    public static int getRid() {
        return rid;
    }

    public static int getOid() {
        return oid;
    }

    public static int getGid() {
        return gid;
    }

    public static int getMoveNum() {
        return moveNum;
    }

    public static int getTid() {
        return tid;
    }

    public static int getxPlaced() {
        return xPlaced;
    }

    public static int getyPlaced() {
        return yPlaced;
    }

    public static int getzPlaced() {
        return zPlaced;
    }

    public static int getxBuilt() {
        return xBuilt;
    }

    public static int getyBuilt() {
        return yBuilt;
    }

    public static int getzBuilt() {
        return zBuilt;
    }

    public static int getOrientation() {
        return orientation;
    }

    public static int getP1Score() {
        return p1Score;
    }

    public static int getP2Score() {
        return p2Score;
    }

    public static String getTileTypeOne() {
        return tileTypeOne;
    }

    public static String getTileTypeTwo() {
        return tileTypeTwo;
    }

    public static String getTerrainType() {
        return terrainType;
    }

    public static boolean isFounded() {
        return founded;
    }

    public static boolean isExpdanded() {
        return expdanded;
    }

    public static boolean isTotoro() {
        return totoro;
    }

    public static boolean isTiger() {
        return tiger;
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
