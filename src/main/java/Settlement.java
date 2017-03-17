/**
 * Created by KWong on 3/16/2017.
 */
import java.util.ArrayList;

public class Settlement {
    private ArrayList<Coordinates> entityCoordinates;

    public Settlement(Coordinates coordinates){
        entityCoordinates = new ArrayList<Coordinates>();
        entityCoordinates.add(coordinates);
    }

    public int SettlementSize(){
        return entityCoordinates.size();
    }
}
