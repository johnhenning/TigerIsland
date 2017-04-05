import ServerModule.KnockKnockClient;

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
            while((inputLine = in.readLine()) != null){
                System.out.println(inputLine);
            }

            return;

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
