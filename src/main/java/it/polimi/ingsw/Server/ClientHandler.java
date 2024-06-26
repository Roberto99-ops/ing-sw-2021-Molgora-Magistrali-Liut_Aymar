package it.polimi.ingsw.Server;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.controller.KeepAlive;
import it.polimi.ingsw.controller.SingleGameManager;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    /**
     * Getter and setter
     */

    public GameHandler getPlayer() {
        return player;
    }
    public GameHandler getSinglePlayer() { return singlePlayer; }



    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private SingleGameManager singleGameManager;
    private GameHandler singlePlayer;
    private GameHandler player;
    private int number;



    /**
     * Initializes a new handler using a specific socket connected to a client.
     * @param client: The socket connection to the client.
     * @param numberOfSockets: the number of this player.
     */

    public ClientHandler(Socket client, int numberOfSockets) {
        this.client = client;
        this.singlePlayer = new GameHandler();
        this.player = new GameHandler();
        this.number = numberOfSockets;
        this.singlePlayer.setNumber(number);
        this.player.setNumber(number);
    }



    /**
     * Connects to the client and runs the handleConnection method.
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
    }



    /**
     * set the name of the player (asking it to the client) and if the player it's been the first to connect,
     * asks him if he want to play alone
     * @throws IOException If a communication error occurs.
     */

    public void handleClientConnection() throws IOException {
        try {
            String next;
            do {
                this.sendMessage("What's your name? ");
                next = this.receiveMessage();
            } while(next.equals(""));
            player.setName(next);
            singlePlayer.setName(next);

            if(this.number==1) {
                do {
                    this.sendMessage(player.getName() + " do you want to play alone?(yes/no) ");
                    next = this.receiveMessage();
                }while (!next.equals("yes") && !next.equals("no"));
                if(next.equals("yes")) {
                    singleGameManager = new SingleGameManager(this);
                    singleGameManager.main();
                }
                else {
                    GameManager.addPlayer(this, player);
                    Server.runGame();
                }

            }
            else {
                GameManager.addPlayer(this, player);
            }

        } catch (ClassCastException | ClassNotFoundException e) {
            System.out.println("invalid stream from client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * this method tells to the caller if the socket it's been already closed.
     * (it returns true only if we have closed the socket with the socket.close command;
     * so it doesn't detect if the client is crashed)
     * @return: true if it's been closed.
     */

    public boolean isClose()
    {
        if(this.client.isClosed()) return true;
        return false;
    }



    /**
     * this method close a socket connection
     * @throws IOException
     */

    public void closeSocket() throws IOException {
        try {
            input.close();
            output.close();
            client.close();
        }catch (IOException e){}
    }




    /**
     * this method send a message to the client. in case the client is crashed, it ends the game.
     * @param msg: message to send (it could be any type of message so we pass an Object)
     * @throws IOException
     */

    public void sendMessage(Object msg) throws IOException {
        try {
            output.writeObject(msg);
            output.flush();
            output.reset();
        }catch (IOException e){
            System.out.println("Error while writing " + msg.getClass() + " type message to " + this.client.getInetAddress() + " so the game ends.");
            this.closeSocket();
            KeepAlive.run(false);
        }
    }



    /**
     * this method receive a message from the client. in case the client is crashed, it ends the game.
     * @return: return a string as we expect always a string as a message from the client.
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public String receiveMessage() throws IOException, ClassNotFoundException {
        try {
            String next = (String) input.readObject();
            return next;
        }catch (IOException e){
            System.out.println("Error while reading from " + this.client.getInetAddress() + " so the game ends.");
            this.closeSocket();
            KeepAlive.run(false);
        }
        return "";
    }
}