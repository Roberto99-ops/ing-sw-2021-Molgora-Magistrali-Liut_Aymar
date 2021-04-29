package it.polimi.ingsw.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //non deve essere costante la porta
    public static int SOCKET_PORT = 1111;

    public void main (String[] agrs){
        //apro la porta # ...
        ServerSocket socket;
        try {
            socket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }
        //...e il server si mette in ascolto. Per ongi nuova connessione stabilita, viene creato un nuovo thread di ClientHandler
        while (true) {
            try {
                /* accepts connections; for every connection we accept,
                 * create a new Thread executing a ClientHandler */
                Socket client = socket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandler.run();
            } catch (IOException e) {
                System.out.println("connection dropped");
            }
        }
    }


}
