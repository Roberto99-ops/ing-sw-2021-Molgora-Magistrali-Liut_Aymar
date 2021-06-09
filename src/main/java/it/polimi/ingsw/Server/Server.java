package it.polimi.ingsw.Server;

import it.polimi.ingsw.controller.GameManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

//dobbiamo usare javaSE??

public class Server {
    private static Random random = new Random();
    private final static int SO_TIMEOUT = 10;
    private static GameManager gameManager = new GameManager();
    private static Thread game = new Thread(gameManager, "gameManager_");

    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());

        //apro le porte # ...
        ArrayList<ServerSocket> openSockets = new ArrayList<>();
        ServerSocket socket;
        try {
            //... creando un socket per quei numeri di porta
            for (int i=1000;i<1020;i++){
                socket = new ServerSocket(i);
                socket.setSoTimeout(SO_TIMEOUT);
                openSockets.add(socket);
            }
            System.out.println("Server is running");
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }


        //...e il server si mette in ascolto.
        // Per ogni nuova connessione stabilita, viene creato un nuovo thread di ClientHandler
        int numberofsockets=0; //serve cosicchè il primo giocatore sappia di essere il primo e crei la partita
        while (true) {
            /* accepts connections; for every connection we accept,
             * create -- a new Thread executing a ClientHandler -- */
            Socket client= new Socket();
            for(int i=0; !client.isConnected(); i++) {
                if(i==20) i=0;
                try {
                    client = openSockets.get(i).accept();
                } catch (IOException e) {}
                //if (client.isConnected()) break;
            }
            //il clientHandler si occupa di gestire la connessione con il client
            numberofsockets++;
            ClientHandler clientHandler = new ClientHandler(client, numberofsockets);
            //bisogna creare un nuovo thread che si occupi di gestire il clienthandler
            Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
            thread.start();
        }
    }

    public static void runGame()
    {
        game.start();
    }

}
