package GameStateModule;

/**
 * Created by johnhenning on 3/19/17.
 */
public class GameState {
    private Grid gameboard;
    private Player player1;
    private Player player2;

    public GameState(){
        gameboard = new Grid(200);
        player1 = new Player();
        player2 = new Player();
    }
    public void placeTile(Tile tile) {
        gameboard.PlaceTile(tile);
    }
    
    public void placeMeeple(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        int tileIndex = hex.getTileIndex();
        int level = gameboard.getPlacedTile(tileIndex).getLevel();
        hex.addMeeple(level);
    }

    public void placeTotoro(Coordinate coordinate) {
        Hex hex = gameboard.getHexFromCoordinate(coordinate);
        hex.addTotoro();
    }

    public Grid getGameboard(){
        return gameboard;
    }

}
