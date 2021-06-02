package it.polimi.ingsw.Server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.controller.SingleGameManager;
import it.polimi.ingsw.model.*;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ClientHandler implements Runnable {

    public ObserverSingleGame getPlayer() {
        return player;
    }

    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameManager gameManager;  //non sono attributi
    private SingleGameManager singleGameManager; //non sono attributi
    private ObserverSingleGame player;
    private int number;


    /**
     * Initializes a new handler using a specific socket connected to
     * a client.
     *
     * @param client The socket connection to the client.
     */

    public ClientHandler(Socket client, int numberofsockets) {
        this.client = client;
        this.player = new ObserverSingleGame();
        this.number = numberofsockets;
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

        System.out.println("Connected to: " + client.getInetAddress());

        /*//mando messaggio con elenco delle porte aperte
        try {
            this.sendMessage("You can connect to one of these ports:\n\t1010\n\t1020\n\t1030\n\t1040\n\t1050\n");
            String msg= this.receiveMessage();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

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

    private void handleClientConnection() throws IOException {
        try {
            //TURNO - FATTO
            //Player player = new Player();
            this.sendMessage("What's your name? ");
            String next = this.receiveMessage();
            player.setName(next);

            if(this.number==1) {
                this.sendMessage(player.getName() + " do you want to play alone?(yes/no) ");
                next = this.receiveMessage();
                if(next.equals("yes")) {
                    singleGameManager = new SingleGameManager(this);
                    singleGameManager.main();
                }
                else    {
                    // gameManager = new GameManager(this);
                    // gameManager.main();
                }

            }


            //GameManager.main(game);

            //Turn turn = new Turn();
            //turn.setActualplayer(player);

           // TurnMsg msg = new TurnMsg(turn);
           // output.writeObject(msg);
           // output.flush();



            /*PLAYER - FATTO
            Player player = new Player();
            player.setName("Mario Bros");
            player.setDevelopementquantity(7);
            player.setNumber(2);
            player.setPv(100);
            PlayerMsg msg = new PlayerMsg(player);
            output.writeObject(msg);
            output.flush();

             */

            /*DEVELOPCARD - FATTO

            //caso in cui voglio una carta direttamente da json (es. la #5)
            DevelopeCard developeCard = new DevelopeCard();
            DevelopCardMsg msg = new DevelopCardMsg(developeCard);
            msg.JsonDevelopCard(5);
            //developeCard.setCard(0);
            System.out.println(msg.getDevelopeCard().getColour());

            //DevelopCardMsg msg = new DevelopCardMsg(developeCard);
            output.writeObject(msg);
            output.flush(); // così mando la carta

             */

            /*STORAGE E SB-FATTO
            Storage storage = new Storage();
            storage.getPanel().set(0,'B');
            StrongBox strongBox = new StrongBox();
            strongBox.getStructure().add('G');
            StrongboxMsg msg = new StrongboxMsg(strongBox.getStructure());
            //StorageMsg msg = new StorageMsg(storage);

            output.writeObject(msg);
            output.flush();
            */

            /* ACTIONSTRUCTURE - FATTO
            //considero una struttura di segnalini azione
            ActionStructure actionStructure = new ActionStructure();
            //provo a modificare la struttura cambiando l'ordine dei segnalini
            actionStructure.ShuffleSignal();

            ActionStructureMsg msg = new ActionStructureMsg(actionStructure);
            output.writeObject(msg.getAction());
            output.flush();
 */

            /*LORENZO - FATTO
            Lorenzo lorenzo = new Lorenzo();
            lorenzo.Lorenzomoves(2);
            LorenzoMsg msg = new LorenzoMsg(lorenzo);
            output.writeObject(msg.getTrackPositionLore());
            output.flush();
             */


            /* FAITHTRACK - OBSOLETO
            Game game = new Game(); // VR = 0
            //setto il numero di giocatori = 0
            game.setN_players(0);
            Player player1 = new Player();
            Player player2 = new Player();
            //metto player1 in posizione per il VR e player2 nell'area VR
            for (int i=0 ; i<9;i++){
                player1.increaseTrackposition();
            }
            for (int i=0 ; i<6;i++){
                player2.increaseTrackposition();
            }
            //chiamo il vaticanReport per player1
            player1.getFaithTrack().VaticanReport(player1, game);
            */

            /* PROVA CON STRONGBOX - FATTO
            StrongBox strongBox = new StrongBox();
            Character[] resources = {'P', 'Y', 'G', 'B'};
            Random mixer = new Random();
            for (int i = 0; i < 20; i++) {
                int mix = mixer.nextInt(4);
                strongBox.getStructure().add(resources[mix]);
            }
            System.out.println(strongBox.getStructure());
            System.out.println(strongBox.getStructure().getVector());

            StrongboxMsg msg = new StrongboxMsg(strongBox.getStructure());
            output.writeObject(msg);
            output.flush();
             */
            //        String name = input.readUTF();//.readObject();
            //        System.out.println("Name of the client is: " + name);

            //        while (!client.isClosed()) {
            /* read commands from the client, process them, and send replies */
            //            String next = input.readUTF();
            //CommandMsg command = (CommandMsg) next;//qui o pattern state o classi o json
            //command.processMessage(this);
            //            System.out.println("Client " + name + " sent: " + next);
            //            if(next == "close")
            output.close();
            input.close();
            client.close();
            //        }
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