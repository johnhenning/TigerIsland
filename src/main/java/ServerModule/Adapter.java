package ServerModule;

import GameStateModule.BuildMove;
import GameStateModule.Coordinate;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import IOModule.Message;

import java.util.ArrayList;

/**
 * Created by carlos on 4/4/2017.
 */
public class Adapter {
    public static String[] serverMessage;
    public static String delimiters = "[ +]+";
    public static String ourPid;
    public static String pid;
    public static String cid;
    public static String numRounds;
    public static String rid;
    public static String oid;
    public static String gidOne;
    public static String gidTwo;
    public static int moveNum;

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

    public static boolean gameJustStarted;

    private KnockKnockClient kkc;

    public Adapter(KnockKnockClient kkc){
        this.kkc = kkc;

    }

    public Message getAITileInfo(){
        parseStringFromServer(kkc.receiveMessage());
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.valueOf(tileTypeOne));
        terrains.add(TerrainType.valueOf(tileTypeTwo));
        return new Message(new Tile(terrains), null);
    }
    public Message getAITileInfo(String s){
        parseStringFromServer(s);
        ArrayList<TerrainType> terrains = new ArrayList<>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.valueOf(tileTypeOne));
        terrains.add(TerrainType.valueOf(tileTypeTwo));
        return new Message(new Tile(terrains), null);
    }

    public static void parseStringFromServer(String fromServer){
        serverMessage = fromServer.split(delimiters);

        if(fromServer.contains("WAIT FOR THE TOURNAMENT TO BEGIN ")) {
            ourPid =serverMessage[6];
        }
        else if(fromServer.contains("NEW CHALLENGE ")){
            cid = serverMessage[2];
            numRounds = serverMessage[6];
        }
        else if(fromServer.contains("BEGIN ROUND "))
            rid = serverMessage[2];
        else if(fromServer.contains("NEW MATCH BEGINNING")) {
            gameJustStarted = true;
            oid = serverMessage[8];
        }
        else if (fromServer.contains("MAKE YOUR MOVE IN GAME")){
            if(gameJustStarted) {
                gameJustStarted = false;
                gidOne = serverMessage[5];
            }
            moveNum = Integer.parseInt(serverMessage[10]);
            tileTypeOne = serverMessage[12];
            tileTypeTwo = serverMessage[13];

        }
        else if(fromServer.contains("PLACED")){
            gidTwo = serverMessage[1];
            moveNum = Integer.parseInt(serverMessage[3]);
            pid = serverMessage[5];
            tileTypeOne = serverMessage[7];
            tileTypeTwo = serverMessage[8];
            xPlaced = Integer.parseInt(serverMessage[10]);
            yPlaced = Integer.parseInt(serverMessage[11]);
            zPlaced = Integer.parseInt(serverMessage[12]);
            orientation = Integer.parseInt(serverMessage[13]);
            if (fromServer.contains("FOUNDED")){
                xBuilt = Integer.parseInt(serverMessage[17]);
                yBuilt = Integer.parseInt(serverMessage[18]);
                zBuilt = Integer.parseInt(serverMessage[19]);
                founded = true;
            }
            else if (fromServer.contains("EXPANDED")){
                xBuilt = Integer.parseInt(serverMessage[17]);
                yBuilt = Integer.parseInt(serverMessage[18]);
                zBuilt = Integer.parseInt(serverMessage[19]);
                expdanded = true;
                terrainType = serverMessage[20];
            }
            else if (fromServer.contains("TOTORO")){
                xBuilt = Integer.parseInt(serverMessage[18]);
                yBuilt = Integer.parseInt(serverMessage[19]);
                zBuilt = Integer.parseInt(serverMessage[20]);
                totoro = true;
            }
            else if (fromServer.contains("TIGER")){
                xBuilt = Integer.parseInt(serverMessage[18]);
                yBuilt = Integer.parseInt(serverMessage[19]);
                zBuilt = Integer.parseInt(serverMessage[20]);
                tiger = true;
            }
        }
        else if (fromServer.contains("OVER PLAYER")){
            gidTwo = serverMessage[1];
            pid = serverMessage[4];
            p1Score = Integer.parseInt(serverMessage[5]);
            oid = serverMessage[7];
            p2Score = Integer.parseInt(serverMessage[8]);
        }
        else if(fromServer.contains("FORFEITED") || fromServer.contains("LOST")){
            gidTwo = serverMessage[1];
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

    public static Coordinate[] getCoordinatesOfOpponentsTile(Coordinate volcanoCoordinate, int orientation){
        Coordinate habitableTerrainCoordinates[] = new Coordinate[2];
        switch (orientation){
            case 1:
                habitableTerrainCoordinates[0] = topLeft(volcanoCoordinate);
                habitableTerrainCoordinates[1] = topRight(volcanoCoordinate);
                break;
            case 2:
                habitableTerrainCoordinates[0] = topRight(volcanoCoordinate);
                habitableTerrainCoordinates[1] = rightOfHex(volcanoCoordinate);
                break;
            case 3:
                habitableTerrainCoordinates[0] = rightOfHex(volcanoCoordinate);
                habitableTerrainCoordinates[1] = downRight(volcanoCoordinate);
                break;
            case 4:
                habitableTerrainCoordinates[0] = downRight(volcanoCoordinate);
                habitableTerrainCoordinates[1] = downLeft(volcanoCoordinate);
                break;
            case 5:
                habitableTerrainCoordinates[0] = downLeft(volcanoCoordinate);
                habitableTerrainCoordinates[1] = leftOfHex(volcanoCoordinate);
                break;
            case 6:
                habitableTerrainCoordinates[0] = leftOfHex(volcanoCoordinate);
                habitableTerrainCoordinates[1] = topLeft(volcanoCoordinate);
                break;
        }
        return habitableTerrainCoordinates;
    }

    public static int getOrientationFromOurTile(Coordinate volcanoCoordinate, Coordinate ourCoordinate[]){
        if(ourCoordinate[0].equals(topLeft(volcanoCoordinate))
                && ourCoordinate[1].equals(topRight(volcanoCoordinate)))
            return 1;
        else if(ourCoordinate[0].equals(topRight(volcanoCoordinate))
                && ourCoordinate[1].equals(rightOfHex(volcanoCoordinate)))
            return  2;
        else if(ourCoordinate[0].equals(rightOfHex(volcanoCoordinate))
                && ourCoordinate[1].equals(downRight(volcanoCoordinate)))
            return  3;
        else if(ourCoordinate[0].equals(downRight(volcanoCoordinate))
                && ourCoordinate[1].equals(downLeft(volcanoCoordinate)))
            return  4;
        else if(ourCoordinate[0].equals(downLeft(volcanoCoordinate))
                && ourCoordinate[1].equals(leftOfHex(volcanoCoordinate)))
           return  5;
        else if(ourCoordinate[0].equals(leftOfHex(volcanoCoordinate))
                && ourCoordinate[1].equals(topLeft(volcanoCoordinate)))
           return  6;
        return 0;
    }
    public static Coordinate downRight(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0)
            return new Coordinate(x,y+1);
        return new Coordinate(x+1,y+1);
    }
    public static Coordinate downLeft(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0)
            return new Coordinate(x-1,y+1);
        return new Coordinate(x,y+1);
    }
    public static Coordinate topRight(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0)
            return new Coordinate(x,y-1);
        return new Coordinate(x+1,y-1);
    }
    public static Coordinate topLeft(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0)
            return new Coordinate(x-1,y-1);
        return new Coordinate(x,y-1);
    }

    public static Coordinate leftOfHex(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        return new Coordinate(x-1,y);
    }

    public static Coordinate rightOfHex(Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        return new Coordinate(x+1,y);
    }

    public void sendAIMove(Message message){
        sendTileMove(message);
        sendBuildMove(message);
    }
    public void sendTileMove(Message message){
        Tile tile = message.tile;
        Coordinate[] coordinates2 = new Coordinate[2];
        coordinates2[0] = tile.getCoords().get(1);
        coordinates2[1] = tile.getCoords().get(2);
        String tileMessage = "Placed ";
        tileMessage += tile.getHexes().get(1).getTerrain().toString() + "+" + tile.getHexes().get(2).getTerrain().toString();
        Coordinate volcanoCoordinate = tile.getCoords().get(0);
        int[] coordinates = convertAxialToCube(volcanoCoordinate.getX(), volcanoCoordinate.getY());
        tileMessage += " AT " + coordinates[0] + " " + coordinates[1] + " " + coordinates[2];
        tileMessage += " " + getOrientationFromOurTile(volcanoCoordinate, coordinates2);

        kkc.sendMessage(tileMessage);
    }
    public void sendBuildMove(Message message){
        BuildMove buildMove = message.buildMove;
        String buildMessage = buildMove.toString(buildMove.buildMoveType);
        int [] coordinates = convertAxialToCube(buildMove.coordinate.getX(), buildMove.coordinate.getY());
        buildMessage += coordinates[0] + " " + coordinates[1] + " " + coordinates[2];
        if(buildMove.terrainType != null)
            buildMessage += " " + buildMove.terrainType.toString();
        kkc.sendMessage(buildMessage);

    }
    /*public static Message makeAIMessage(String terrainTypeOne, String terrainTypeTwo){
        //Arr
    }

    public static Message makeOpponentMessage(){

    }*/
}

