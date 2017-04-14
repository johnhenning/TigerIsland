import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by carlos on 4/4/2017.
 */
public class serverTest {
    public static void main(String[] args) {
        int portNumber = 2222;
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);

                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {

            String inputLine, outputLine;

            out.println("WELCOME TO ANOTHER EDITION OF THUNDERDOME!");
            String password = "Cheese";
            while((inputLine = in.readLine()) != null){
                if(inputLine.contains("ENTER THUNDERDOME " + password)) {
                    System.out.println(inputLine);
                    break;
                }
            }
            out.println("TWO SHALL ENTER, ONE SHALL LEAVE");
            String username = "Cheese123";
            while ((inputLine = in.readLine()) != null){
                if (inputLine.contains("I AM "+username+" "+password));
                System.out.println(inputLine);
                break;
            }
            String pid = "420";
            out.println("WAIT FOR THE TOURNAMENT TO BEGIN "+pid);
            out.println("NEW CHALLENGE 1 YOU WILL PLAY 2 MATCHES");
            out.println("BEGIN ROUND 1 OF 2");
            out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 69");
            out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECOND: MOVE 1 PLACE LAKE+LAKE");

            out.println(in.readLine());

            //out.println("GAME A MOVE 1 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
            out.println("GAME B MOVE 1 PLAYER 69 PLACED ROCK+GRASS AT 1 -1 0 2 FOUNDED SETTLEMENT AT 2 -1 -1");
            out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECOND: MOVE 2 PLACE ROCK+ROCK");

            out.println(in.readLine());


            out.println("GAME A MOVE 2 PLAYER 69 FORFEITED: ILLEGAL BUILD");
            out.println("GAME B MOVE 2 PLAYER 69 FORFEITED: ILLEGAL BUILD");

            out.println("GAME A OVER PLAYER 420 1 PLAYER 69 1");
            out.println("GAME B OVER PLAYER 69 1 PLAYER 420 1");

            out.println("END OF ROUND 1 OF 2 WAIT FOR THE NEXT MATCH");
            out.println("BEGIN ROUND 2 OF 2");
            out.println("NEW MATCH BEGINNING NOW YOUR OPPONENT IS PLAYER 69");

            out.println("MAKE YOUR MOVE IN GAME A WITHIN 1.5 SECOND: MOVE 1 PLACE LAKE+LAKE");
            out.println("GAME A MOVE 1 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
            out.println("GAME B MOVE 1 PLAYER 69 PLACED ROCK+GRASS AT 1 -1 0 2 FOUNDED SETTLEMENT AT 2 -1 -1");

            out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECOND: MOVE 2 PLACE ROCK+ROCK");
            out.println("GAME B MOVE 2 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
            out.println("GAME A MOVE 3 PLAYER 69 FORFEITED: ILLEGAL TILE PLACEMENT");

            out.println("GAME B MOVE 1 PLAYER 69 PLACED ROCK+GRASS AT 2 0 -2 1 FOUNDED SETTLEMENT AT 2 1 -3");
            out.println("MAKE YOUR MOVE IN GAME B WITHIN 1.5 SECOND: MOVE 2 PLACE ROCK+ROCK");
            out.println("GAME B MOVE 2 PLAYER 420 PLACED LAKE+LAKE AT 1 1 1 1 FOUNDED SETTLEMENT AT 1 2 2");
            out.println("GAME B MOVE 3 PLAYER 69 FORFEITED: ILLEGAL TILE PLACEMENT");

            out.println("GAME A OVER PLAYER 420 1 PLAYER 69 1");
            out.println("GAME B OVER PLAYER 69 1 PLAYER 420 1");

            out.println("END OF ROUND 2 OF 2");
            out.println("END OF CHALLENGES");
            out.println("THANK YOU FOR PLAYING! GOODBYE");
            out.close();
            in.close();
            return;

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
