package GameInteractionModule.Rules;

import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TileNukeRules extends TilePlacementRules {

    public static int isValidNuke(Tile tile, Hex[][] gameboard){
        int lowerLevel = CheckLowerHexesAreSameLevel(tile,gameboard);
        if (lowerLevel == -1) throw new AssertionError();
        boolean MultipleTiles = CheckHexesSpanMultipleTiles(tile, gameboard);
        if(!MultipleTiles) throw new AssertionError();
        boolean VolcanoLineUp = CheckVolcanoesLineUp(tile,gameboard);
        if(!VolcanoLineUp) throw new AssertionError();
        boolean tileDoesNotContainTotoro = TileNukeRules.CheckTileNotContainTotoro(tile, gameboard);
        if(tileDoesNotContainTotoro == false) throw new AssertionError();
        //TODO: need to check tiger
        return lowerLevel;
    }

    public static int getNewTileLevel(Tile tile, Hex[][] gameboard){
        int lowerLevel = CheckLowerHexesAreSameLevel(tile, gameboard);
        return lowerLevel+1;
    }
    public static int CheckLowerHexesAreSameLevel(Tile tile, Hex[][] gameboard) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) return -1;

        int lowerLevel0 = lower_hex0.getLevel();
        int lowerLevel1 = lower_hex1.getLevel();
        int lowerLevel2 = lower_hex2.getLevel();

        if (lowerLevel0 == lowerLevel1 && lowerLevel1 == lowerLevel2) {
            return lowerLevel0;
        } else {
            return -1;
        }
    }

    public static boolean CheckHexesSpanMultipleTiles(Tile tile, Hex[][] gameboard) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) return false;

        int tileIndex0 = lower_hex0.getTurnPlaced();
        int tileIndex1 = lower_hex1.getTurnPlaced();
        int tileIndex2 = lower_hex2.getTurnPlaced();

        if (tileIndex0 == tileIndex1 && tileIndex1 == tileIndex2) return false;

        return true;
    }

    public static boolean CheckVolcanoesLineUp(Tile tile, Hex[][] gameboard) {

        for (Hex hex : tile.getHexes()) {
            if (hex.getTerrain() == TerrainType.VOLCANO) {
                if (gameboard[hex.getx()][hex.gety()].getTerrain() == TerrainType.VOLCANO) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckTileNotContainTotoro(Tile tile, Hex[][]gameboard){
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex lower_hex0 = gameboard[hex0.getx()][hex0.gety()];
        Hex lower_hex1 = gameboard[hex1.getx()][hex1.gety()];
        Hex lower_hex2 = gameboard[hex2.getx()][hex2.gety()];

        if(lower_hex0.hasTotoro() || lower_hex1.hasTotoro() || lower_hex2.hasTotoro()){
            return false;
        }
        else{
            return true;
        }
    }

}
