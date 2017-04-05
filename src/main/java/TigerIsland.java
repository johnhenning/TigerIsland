/**
 * Created by johnhenning on 3/19/17.
 */
import ServerModule.KnockKnockClient;

import java.net.*;
import java.io.*;

public class TigerIsland {
    public static void main(String[] args) {
        int portNumber = 2222;
        KnockKnockClient kkc = new KnockKnockClient("localhost",portNumber);
        kkc.authenticateConnection("Cheese", "Cheese123", "Cheddar");
        System.out.println("we did it");
        return;
    }
    /*
        Game game = new Game();
        AI ai = new AI();
        Server server = new Server();


        if (server.firstplayer == 'you') {
            firstPlayer = ai;
            secondPlayer = server.messages();
        } else {
            firstPlayer = server.messages();
            secondPlayer = ai;
        }

        while(!game.IsOver()){
            game.makeMove(firstPlayer.getMessage());
            game.makeMove(secondPlayer.getMessage());


        }
    */

}

