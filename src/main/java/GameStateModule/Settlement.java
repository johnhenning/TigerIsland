package GameStateModule;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Settlement {
    private ArrayList<Coordinate> settlementCoordinates;
    private Player owner;

    public Settlement(ArrayList<Coordinate> settlementCoordinates, Player owner) {
        this.settlementCoordinates = settlementCoordinates;
        this.owner = owner;
    }

    public Settlement(Coordinate coordinate, Player owner) {
        settlementCoordinates = new ArrayList<Coordinate>();
        settlementCoordinates.add(coordinate);
        this.owner = owner;
    }

    public void ExpandSettlement(ArrayList<Coordinate> coordinates){
        settlementCoordinates.addAll(coordinates);    
    }

    public Player getOwner(){ return owner;}

    public int getSize(){ return settlementCoordinates.size(); }
}
