package IOModule;

import GameStateModule.GameState;

/**
 * Created by johnhenning on 4/4/17.
 */
public interface Player {
    public void completeTurn(Message message, GameState gamestate);

}
