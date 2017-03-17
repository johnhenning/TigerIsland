/**
 * Created by KWong on 3/16/2017.
 */
import java.util.ArrayList;

public class Settlement {
    private ArrayList<Coordinates> entityCoOrdinates;

    public Settlement(Coordinates coordinates){
        entityCoOrdinates.add(coordinates);
    }

    public int SettlementSize(){
        return entityCoOrdinates.size();
    }
}
