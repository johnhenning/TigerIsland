package IOModule;

import GameStateModule.*;

/**
 * Created by johnhenning on 4/4/17.
 */
public class Message {
    public Tile tile;
    public BuildMove buildMove;


    Message(Tile tile, BuildMove buildMove) {
        this.tile = tile;
        this.buildMove = buildMove;
    }

    Message(Tile tile){
        this.tile = tile;
    }

    public void setBuildMove(BuildMove buildMove){
        this.buildMove = buildMove;
    }
}
