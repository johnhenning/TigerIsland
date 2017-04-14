package GameStateModule;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Settlement {
    private ArrayList<Coordinate> settlementCoordinates;
    private Player owner;
    private int settlementID;

    public Settlement(ArrayList<Coordinate> settlementCoordinates, Player owner, int settlementID) {
        this.settlementCoordinates = settlementCoordinates;
        this.owner = owner;
        this.settlementID = settlementID;
    }

    public Settlement(Coordinate coordinate, Player owner, int settlementID) {
        settlementCoordinates = new ArrayList<>();
        settlementCoordinates.add(coordinate);
        this.owner = owner;
        this.settlementID = settlementID;
    }

    public void expandSettlement(ArrayList<Coordinate> coordinates){
        settlementCoordinates.addAll(coordinates);    
    }

    public Player getOwner(){ 
        return owner;
    }

    public int getSettlementID() {
        return settlementID;
    }

    public int getSize(){
        return settlementCoordinates.size();
    }

    public ArrayList<Coordinate> getSettlementCoordinates() { return settlementCoordinates; }

    public boolean areCoordinatesAdjacent(ArrayList<Coordinate> coordinates){
        //lists should never contain the same x,y value going to ignore for now
        for(Coordinate c: settlementCoordinates){
            for(Coordinate d: coordinates){
                if(c.equals(downRight(d)))
                    return true;
                else if(c.equals(downLeft(d)))
                    return true;
                else if(c.equals(topRight(d)))
                    return true;
                else if(c.equals(topLeft(d)))
                    return true;
                else if(c.equals(leftOfHex(d)))
                    return true;
                else if(c.equals(rightOfHex(d)))
                    return true;
            }
        }
        return false;
    }

    public void printSettlementInfo(){
        System.out.println("settlementID: " + settlementID);
        System.out.println("Coordinates in Settlement: ");
        for(Coordinate c : settlementCoordinates){
            System.out.println("x: "+c.getX()+ " y: " + c.getY());
        }

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



}
