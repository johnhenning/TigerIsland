package GameInteractionModule.Rules;
import GameStateModule.Hex;
import GameStateModule.Tile;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TilePlacementRules extends Rules {
    public boolean CheckForUnoccupiedHexes(Tile tile, Hex[][] gameboard){ //changed to public so I can use in tests
        for (Hex hex : tile.getHexes()) {
            if (gameboard[hex.getx()][hex.gety()] != null) {
                return false;
            }
        }
        return true;
    }


    public boolean CheckForAdjacentHex(Tile tile, Hex[][] gameboard){

        for (Hex hex : tile.getHexes()) {
            int x = hex.getx();
            int y = hex.gety();

            // TODO : need to figure out edge cases
            if(hex.getx() < 0 || hex.gety() > 200){
                //do nothing
            }
            else{

                if(gameboard[x][y+1] == null)
                    return true;
                else if(gameboard[x][y-1] != null)
                    return true;
                else if(gameboard[x+1][y-1] != null)
                    return true;
                else if(gameboard[x+1][y+1] != null)
                    return true;
                else if(gameboard[x+1][y] != null)
                    return true;
                else if(gameboard[x-1][y] != null)
                    return true;
                else if(gameboard[x-1][y+1] != null)
                    return true;
                else if(gameboard[x-1][y-1] != null)
                    return true;
            }
        }

        return false;
    }


}
