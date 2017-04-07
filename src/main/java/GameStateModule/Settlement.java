package GameStateModule;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Settlement {
    private ArrayList<Coordinate> settlementCoordinates;
    private Player owner; //Are we going to change the owner/player relationship as discussed?
    private int settlementID;

    public Settlement(ArrayList<Coordinate> settlementCoordinates, Player owner, int settlementID) { //Is this necessary? : yes for splitting settlements up
        this.settlementCoordinates = settlementCoordinates;
        this.owner = owner;
        this.settlementID = settlementID;
    }

    public Settlement(Coordinate coordinate, Player owner, int settlementID) {
        settlementCoordinates = new ArrayList<Coordinate>();
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
            int x = c.getX();
            int y = c.getY();
            for(Coordinate d: coordinates){
                int x1 = d.getX();
                int y1 = d.getY();
                if(x == x1 && y == y1+1)
                    return true;
                else if(x == x1 && y == y1-1)
                    return true;
                else if(x == x1-1 && y == y1+1)
                    return true;
                else if(x == x1-1 && y == y1-1)
                    return true;
                else if(x == x1-1 && y == y1)
                    return true;
                else if(x == x1+1 && y == y1)
                    return true;
                else if(x == x1+1 && y == y1+1)
                    return true;
                else if(x == x1+1 && y == y1-1)
                    return true;
            }
        }
        return false;
    }

    public void printSettlementInfo(){
        System.out.println("settlementID " + settlementID);
        System.out.println("Coordinates in Settlement: ");
        for(Coordinate c : settlementCoordinates){
            System.out.println("x: "+c.getX()+ " y: " + c.getY());
        }

    }


}
