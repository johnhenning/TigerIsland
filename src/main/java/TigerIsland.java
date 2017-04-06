import ServerModule.KnockKnockClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by johnhenning on 3/19/17.
 */
public class TigerIsland{
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
