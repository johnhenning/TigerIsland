/**
 * Created by johnhenning on 3/19/17.
 */
public class TigerIsland {
    public static void main(String[] args) {
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
    }
}
