import ServerModule.KnockKnockClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jslocke on 4/5/17.
 */
public class Tiger {
    public static void main(String[] args)
    {
        Thread tigerIsland = new Thread(new Runnable() {
            @Override
            public void run() {
                int portNumber = 2222;
                KnockKnockClient kkc = new KnockKnockClient("localhost",portNumber);
                kkc.authenticateConnection("Cheese", "Cheese123", "Cheddar");
                System.out.println("we did it");
                return;
            }
        });

        Thread serverTest = new Thread(new Runnable() {
            @Override
            public void run() {
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
                    String pid = "456";
                    out.println("WAIT FOR THE TOURNAMENT TO BEGIN "+pid);
                    out.println("THANK YOU FOR PLAYING! GOODBYE");
                    return;

                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        });
        tigerIsland.start();
        serverTest.start();
    }
}
