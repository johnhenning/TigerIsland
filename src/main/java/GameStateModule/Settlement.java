package GameStateModule;

import java.util.ArrayList;

/**
 * Created by johnhenning on 3/19/17.
 */
public class Settlement {
    private ArrayList<Coordinate> settlementCoordinates;
    private Player owner; //Are we going to change the owner/player relationship as discussed?

    public Settlement(ArrayList<Coordinate> settlementCoordinates, Player owner) {//Is this necessary? : yes for splitting settlements up
        this.settlementCoordinates = settlementCoordinates;
        this.owner = owner;
    }

    public Settlement(Coordinate coordinate, Player owner) {
        settlementCoordinates = new ArrayList<Coordinate>();
        settlementCoordinates.add(coordinate);
        this.owner = owner;
    }

    public void expandSettlement(ArrayList<Coordinate> coordinates){
        settlementCoordinates.addAll(coordinates);    
    }

    public Player getOwner(){ 
        return owner;
    }

    public int getSize(){
        return settlementCoordinates.size();
    }

}
