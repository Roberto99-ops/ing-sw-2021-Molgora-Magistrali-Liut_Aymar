package it.polimi.ingsw.Server;

import it.polimi.ingsw.controller.GameManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {


    private final static int SO_TIMEOUT = 10;
    private static GameManager gameManager = new GameManager();
    private static Thread game;
    private static boolean closeServer = false;
    private static int numberOfSockets = 0;
    private static Thread firstClient;

    public static void setCloseServerTrue() {
        closeServer = true;
    }
    public static void setNumberOfSockets(int numberOfSockets) {
        Server.numberOfSockets = numberOfSockets;
    }



    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());
        Scanner scan = new Scanner(System.in);
        String numberPort;

        //This opens the socket port
        ServerSocket socket;
        try {
            System.out.println("Which port do you want to open?");
            numberPort = scan.nextLine();
            socket = new ServerSocket(Integer.parseInt(numberPort));
            socket.setSoTimeout(SO_TIMEOUT);
            System.out.println("Server is running");
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }


        while (!closeServer) {
            Socket client;
            try {
                client = socket.accept();
                numberOfSockets++;
                ClientHandler clientHandler = new ClientHandler(client, numberOfSockets);
                Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                if (numberOfSockets == 1) firstClient = thread;
                thread.start();
            }catch (IOException e){}
        }
    }



    /**
     * This method starts a GameManager
     */

    public static void runGame()
    {
        game = new Thread(gameManager, "gameManager_");
        game.start();
    }



    /**
     * This method closes a game, so it closes the socket and resets NumberOfSockets
     */

    public static void closeGame() {
        numberOfSockets =0;
        Server.setCloseServerTrue();
        if(game != null)
                game.stop();
        firstClient.stop();
    }
}
