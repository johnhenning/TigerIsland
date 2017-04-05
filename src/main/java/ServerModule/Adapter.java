package ServerModule;

/**
 * Created by carlos on 4/4/2017.
 */
public class Adapter {

    public static String[] message;
    public static String delimiters = "[ +]+";
    public static int pid;
    public static int cid;
    public static int numRounds;
    public static int rid;
    public static int oid;
    public static int gid;
    public static int moveNum;
    public static int tid;
    public static int xPlaced;
    public static int yPlaced;
    public static int zPlaced;
    public static int xBuilt;
    public static int yBuilt;
    public static int zBuilt;
    public static int orientation;
    public static int p1Score;
    public static int p2Score;
    public static String tileTypeOne;
    public static String tileTypeTwo;
    public static String terrainType;
    public static boolean founded;
    public static boolean expdanded;
    public static boolean totoro;
    public static boolean tiger;
    public static boolean authentication;

    public static void parseStringFromServer(String fromServer){
        message = fromServer.split(delimiters);

        if(fromServer.contains("WAIT FOR THE TOURNAMENT TO BEGIN ")) {
            pid = Integer.parseInt(message[6]);
        }
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

    public int[] convertCubeToAxial(int x, int y, int z){
        int axialCoord[] = new int[2];
        axialCoord[0] = (x + (z - (z&1)) / 2)+100;
        axialCoord[1] = (z)+100;
        return axialCoord;
    }

    public int[] convertAxialToCube(int x, int y){
        int cubicCoord[] = new int[3];
        cubicCoord[0] = ((x-100)-((y-100)-((y-100)&1))/2);
        cubicCoord[2] = y-100;
        cubicCoord[1] = (-(cubicCoord[0])-cubicCoord[2]);
        return cubicCoord;
    }
}
