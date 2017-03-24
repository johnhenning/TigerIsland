package GameInteractionModule.Rules;
import GameStateModule.Hex;
import GameStateModule.Tile;


/**
 * Created by johnhenning on 3/22/17.
 */
public class TileExpansionRules extends TilePlacementRules {

    public boolean CheckForUnoccupiedHexes(Tile tile, Hex[][] gameboard){ //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }

}
