package it.polimi.ingsw.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    private static Random random = new Random();

    //non deve essere costante la porta
    //il numero di porta viene generato casualmente tra 2000 e 4000
    public static int SOCKET_PORT = 1111;//random.nextInt(8);

    public static void main(String[] args){
        //apro la porta # ...
        ServerSocket socket;
        try {
            //... creando un socket per quel numero di porta
            socket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }
        //...e il server si mette in ascolto. Per ogni nuova connessione stabilita, viene creato un nuovo thread di ClientHandler
        while (true) {
            try {
                /* accepts connections; for every connection we accept,
                 * create -- a new Thread executing a ClientHandler -- */
                Socket client = socket.accept();
                //il clientHandler si occupa di gestire la connessione con il client
                ClientHandler clientHandler = new ClientHandler(client);
                //bisogna creare un nuovo thread che si occupi di gestire il clienthandler
                Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                System.out.println("connection dropped");
            }
        }
    }


}
