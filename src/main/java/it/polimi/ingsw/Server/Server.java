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
    private static Random random = new Random();
    private final static int SO_TIMEOUT = 10;
    private static GameManager gameManager = new GameManager();
    private static Thread game;
    private static int numberofsockets = 0; //is used to allow the first player to start a new game

    public static void setNumberofsockets(int numberofsockets) {
        Server.numberofsockets = numberofsockets;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());
        Scanner scan = new Scanner(System.in);
        String numberPort;

        //asks what port we do want to open
        ServerSocket socket;
        try {
            System.out.println("Which port do you want to open?");
            numberPort = scan.nextLine();
            socket = new ServerSocket(Integer.parseInt(numberPort));
            System.out.println("Server is running");
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }

        //starts a loop that accepts new connections
        //the loop is set to true (so it will run infinitely) because a server is an entity that should be always open to accept new connections,
        //the only reasons to allow a server to shut down itself are maybe reasons correlated to security (like too heat components or too many connections or a cyber attack),
        //reasons that don't concern THIS server. obviously if the server manager (i'm talking the person who run it) would like to shut down the server could simply turn it off.
        while (true) {
            Socket client;
            client = socket.accept();
            numberofsockets++;
            ClientHandler clientHandler = new ClientHandler(client, numberofsockets);
            Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
            thread.start();
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
        game.stop();
    }
}
