package it.polimi.ingsw.Server;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.controller.SingleGameManager;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    public GameHandler getPlayer() {
        return player;
    }

    public SingleGameHandler getSinglePlayer() {
        return singleplayer;
    }

    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameManager gameManager;  //non sono attributi
    private SingleGameManager singleGameManager; //non sono attributi
    private SingleGameHandler singleplayer;
    private GameHandler player;
    private int number;


    /**
     * Initializes a new handler using a specific socket connected to
     * a client.
     *
     * @param client The socket connection to the client.
     */

    public ClientHandler(Socket client, int numberofsockets) {
        this.client = client;
        this.singleplayer = new SingleGameHandler();
        this.player = new GameHandler();
        this.number = numberofsockets;
        this.singleplayer.setNumber(number);
        this.player.setNumber(number);
    }


    /**
     * Connects to the client and runs the event loop.
     */

    @Override
    public void run() {

        try {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("could not open connection to " + client.getInetAddress());
            return;
        }
        System.out.println("Connected to: " + client.getInetAddress() + " at port #" + client.getLocalPort());
        try {
            handleClientConnection();
        } catch (IOException e) {
            System.out.println("client " + client.getInetAddress() + " connection dropped");
        }
        try {
            client.close();
        } catch (IOException e) {
        }
    }


    /**
     * An event loop that receives messages from the client and processes
     * them in the order they are received.
     *
     * @throws IOException If a communication error occurs.
     */

    public void handleClientConnection() throws IOException {
        try {
            //TURNO - FATTO

            this.sendMessage("What's your name? ");
            String next = this.receiveMessage();
            player.setName(next);
            singleplayer.setName(next);

            if(this.number==1) {
                this.sendMessage(player.getName() + " do you want to play alone?(yes/no) ");
                next = this.receiveMessage();
                if(next.equals("yes")) {
                    singleGameManager = new SingleGameManager(this);
                    singleGameManager.main();
                }
                else    {
                    GameManager.addPlayer(this, player);
                    Server.runGame();
                    while (!client.isClosed()){} //non va bene
                }

            }
            else {
                GameManager.addPlayer(this, player);
                while (!client.isClosed()){}

            }


            client.close(); //togliere
            output.close();  //togliere
            input.close();
        } catch (ClassCastException | ClassNotFoundException e) {
            System.out.println("invalid stream from client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The game instance associated with this client.
     *
     * @return The game instance.
     */
    /*public Game getGame() {
        return game;
    }*/


    /**
     * this method send a message to the client
     * @param msg: message to send (it could be any type of message so we pass an Object)
     * @throws IOException
     */

    public void sendMessage(Object msg) throws IOException {
        output.writeObject(msg);
        output.flush();
        output.flush();
        output.reset();
    }

    /**
     * this method receive a message from the client
     * @return: return a string as we expect always a string as a message from the client.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String receiveMessage() throws IOException, ClassNotFoundException {
        String next = (String)input.readObject();
        return next;
    }
}