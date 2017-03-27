package GameInteractionModule.Rules;

import GameStateModule.Hex;
import GameStateModule.TerrainType;
import GameStateModule.Tile;
import java.util.ArrayList;

/**
 * Created by johnhenning on 3/22/17.
 */
public class TileNukeRules extends TilePlacementRules {

    public static void isValidNuke(Tile tile, Hex[][] gameboard){
        CheckLowerHexesAreSameLevel(tile,gameboard);
        if(CheckHexesSpanMultipleTiles(tile, gameboard) == false) throw new AssertionError();
        if(CheckVolcanoesLineUp(tile,gameboard) == false) throw new AssertionError();
        if(CheckTileNotContainTotoro(tile, gameboard) == false) throw new AssertionError();
        if(CheckTileNotContainTiger(tile, gameboard) == false) throw new AssertionError();
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

        if (lower_hex0 == null || lower_hex1 == null || lower_hex2 == null) { return -1; }

        int lowerLevel0 = lower_hex0.getLevel();
        int lowerLevel1 = lower_hex1.getLevel();
        int lowerLevel2 = lower_hex2.getLevel();

        if (lowerLevel0 == lowerLevel1 && lowerLevel1 == lowerLevel2) {
            return lowerLevel0;
        } else {
            throw new AssertionError();
        }
    }

    public static boolean CheckHexesSpanMultipleTiles(Tile tile, Hex[][] gameboard) {
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

        if (hex_zero == null || hex_one == null || hex_two == null) { return false; }

        int tileIndex0 = hex_zero.getTurnPlaced();
        int tileIndex1 = hex_one.getTurnPlaced();
        int tileIndex2 = hex_two.getTurnPlaced();

        return !(tileIndex0 == tileIndex1 && tileIndex1 == tileIndex2);
    }

    public static boolean CheckVolcanoesLineUp(Tile tile, Hex[][] gameboard) {

        for (Hex hex : tile.getHexes()) {
            if (hex.getTerrain() == TerrainType.VOLCANO) {
                if (gameboard[hex.getx()][hex.gety()].getTerrain() == TerrainType.VOLCANO) { return true; }
            }
        }
        return false;
    }

    public static boolean CheckTileNotContainTotoro(Tile tile, Hex[][]gameboard){
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

        return !(hex_zero.hasTotoro() || hex_one.hasTotoro() || hex_two.hasTotoro());
    }

    public static boolean CheckTileNotContainTiger(Tile tile, Hex[][]gameboard){
        Hex hex0 = tile.getHexes().get(0);
        Hex hex1 = tile.getHexes().get(1);
        Hex hex2 = tile.getHexes().get(2);

        Hex hex_zero = gameboard[hex0.getx()][hex0.gety()];
        Hex hex_one = gameboard[hex1.getx()][hex1.gety()];
        Hex hex_two = gameboard[hex2.getx()][hex2.gety()];

        return !(hex_zero.hasTiger() || hex_one.hasTiger() || hex_two.hasTiger());
    }

}
