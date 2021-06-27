package it.polimi.ingsw.Server;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;

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
import java.util.Scanner;


public class Server {

    private final static int SO_TIMEOUT = 10;
    private static GameManager gameManager = new GameManager();
    private static Thread game;
    private static boolean closeserver = false;
    private static int numberofsockets = 0;
    private static Thread firstClient;

    public static void setCloseserverTrue() {
        closeserver = true;
    }
    public static void setNumberofsockets(int numberofsockets) {
        Server.numberofsockets = numberofsockets;
    }



    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());
        Scanner scan = new Scanner(System.in);
        String numberPort;

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


        while (!closeserver) {
            Socket client;
            try {
                client = socket.accept();
                numberofsockets++;
                ClientHandler clientHandler = new ClientHandler(client, numberofsockets);
                Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                if (numberofsockets == 1) firstClient = thread;
                thread.start();
            }catch (IOException e){}
        }
    }



    /**
     * this method start a gamemanager
     */

    public static void runGame()
    {
        game = new Thread(gameManager, "gameManager_");
        game.start();
    }



    /**
     * this method close a game, so it close the socket and reset numberofsockets
     */

    public static void closeGame() {
        numberofsockets=0;
        Server.setCloseserverTrue();
        if(game != null)
                game.stop();
        firstClient.stop();
    }
}
