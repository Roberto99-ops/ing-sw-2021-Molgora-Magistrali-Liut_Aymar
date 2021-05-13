package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.Server.messages.AnswerMsg;
import it.polimi.ingsw.Server.messages.CommandMsg;

import java.io.DataInputStream;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientHandler implements Runnable {

    private Socket client;
    private ObjectOutputStream output;
    private DataInputStream input;
    private Game game;


    /**
     * Initializes a new handler using a specific socket connected to
     * a client.
     *
     * @param client The socket connection to the client.
     */
     ClientHandler(Socket client) {
        this.client = client;
        this.game = new Game();
    }




    private void handleClientConnection() throws IOException
    {
        System.out.println("Connected to " + client.getInetAddress());
        //per mandare qualcosa al client
        PrintWriter outputToClient = new PrintWriter(client.getOutputStream(),true);
        //per ricevere qualcosa dal client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));



        try {
            while (client.isConnected()) {
                try {

                    /* read a String from the stream and write an uppercase string in response */
                    String message = "";

                    message= inputFromClient.readLine();
                    System.out.println(message);
                    outputToClient.println("Nice to meet u");
                    outputToClient.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                ///* simulate a complex computation*/
                    //TimeUnit.SECONDS.sleep(10);
                //} catch (InterruptedException e) { }


                /*
                try {
                    /* simulate a complex computation
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) { }*/

                //output.println(message.toUpperCase());
            }
        } catch (ClassCastException e) {
            System.out.println("invalid stream from client");
        }

        client.close();
    }
    /*
        try {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("could not open connection to " + client.getInetAddress());
            return;
        }

        System.out.println("Connected to: " + client.getInetAddress());

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
    /*
    private void handleClientConnection() throws IOException {
        try {
            String name = input.readUTF();//.readObject();
            System.out.println("Name of the client is: " + name);

            while (!client.isClosed()) {
                /* read commands from the client, process them, and send replies
                String next = input.readUTF();
                //CommandMsg command = (CommandMsg) next;//qui o pattern state o classi o json
                //command.processMessage(this);
                System.out.println("Client " + name + " sent " + next);
                if(next == "close") input.close();
            }
        } catch (ClassCastException e) {
            System.out.println("invalid stream from client");
        }
    }
*/
    /**
     * Connects to the client and runs the event loop.
     */
    @Override
    public void run() {
        try {
            handleClientConnection();
        } catch (IOException e) {
            System.out.println("client " + client.getInetAddress() + " connection dropped");
        }
    }

    /**
     * The game instance associated with this client.
     *
     * @return The game instance.
     */
    public Game getGame() {
        return game;
    }


    /**
     * Sends a message to the client.
     *
     * @param answerMsg The message to be sent.
     * @throws IOException If a communication error occurs.
     */
    public void sendAnswerMessage(AnswerMsg answerMsg) throws IOException {
        output.writeObject((Object) answerMsg);
    }
}