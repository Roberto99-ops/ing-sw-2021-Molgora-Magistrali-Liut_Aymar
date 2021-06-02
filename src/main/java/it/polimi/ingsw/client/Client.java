package it.polimi.ingsw.client;

import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.CliManager;
import it.polimi.ingsw.view.cli.DevelopeDecksView;
import it.polimi.ingsw.view.cli.MarketView;
import it.polimi.ingsw.view.cli.Playerboard;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

public class Client {
    //Di seguito verranno istanziati elementi che il client userà per aggiornare l'utente sul gioco (questi elementi sono
    // salvati dentro il ServerHandler che sta all'interno del Client stesso):
    // questa è una copia  del player e dei suoi dati/carte presenti nel server. Questa istanza permette al client di
    // avere tutti i dati ricevuti dal clienthandler in modo ordinato e di visualizzarli quando ne ha bisogno
    private static Player player;
    //questa è una copia del market nel caso in cui il giocatore giochi in compagnia
    private static Market market;
    //questa è una copia dei segnalini che possono essere pescati dal giocatore quando gioca da solo
    private static ActionSignal actionSignal;

    private static DevelopeDecks[] DDecks;

    private static final int DEFAULT_PORT = 1000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        player = new Player();
        //market = new Market();
        actionSignal = new ActionSignal();
        DDecks = new DevelopeDecks[12];
        Playerboard board;
        //MarketView marketView = new MarketView(market);
        DevelopeDecksView DDecksView = new DevelopeDecksView(DDecks);
        //actionsignalview

        Scanner scan = new Scanner(System.in);

        System.out.print("Insert IP address of Server: ");
        String ip = scan.nextLine();
        System.out.print("Insert Port number of Server \n(if don't needed just press enter): ");
        String serverport = scan.nextLine();


        /*Per aprire connessione col server*/
        Socket server;
        try {
            if(!serverport.equals(""))
                server = new Socket(ip, Integer.parseInt(serverport));  //ipServer Roby: 95.250.236.230
            else
                server = new Socket(ip, 1000);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected");


        //try {
        ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(server.getInputStream());
        //} catch (IOException e) {   System.out.println("server has died");
        //        } catch (ClassCastException e) {
        //            System.out.println("protocol violation");
        //        }
        Object next = input.readObject();
        do{
            if(next.getClass().equals(String.class)) {
                System.out.println(next);
                String string = scan.nextLine();
                output.writeObject(string);
                output.flush();
            }
            else
                CliManager.Update(next, player);
            next = input.readObject();
        }while(!next.equals("Turn Finished"));   //ci aspettiamo un messaggio di turno finito a fine turno



        /*TURNO - FATTO
        TurnMsg msg = (TurnMsg)next;
        System.out.println(msg.getTurn().getActualplayer().getName());

         */

        /*PLAYER - FATTO
        PlayerMsg msg = (PlayerMsg) next;
        System.out.println(msg.getPlayer().getName()+"  "+msg.getPlayer().getDevelopementquantity()+"  "+msg.getPlayer().getPv());
         */

        /*PROVA CON STORAGE - FATTO
        StorageMsg msg = (StorageMsg) next;
        System.out.println(msg.getStorage().getPanel().get(0)); // controllo il posto 0 perchè è l'unico in cui ho messo qualcosa
        //ho controllato anche i posti dove ho 'N' e risultano esserci->OK
        */

        /*PROVA CON DEVELOPECARD-FATTO
        DevelopCardMsg msg = (DevelopCardMsg) next;
        System.out.println(msg.getDevelopeCard().getColour());
         */

        /*PROVA CON TURN
        String msg = (String) next;
        System.out.println(msg);
        /*PROVA CON STORAGE E SB - FATTO
        String msg = (String) next;
        System.out.println(msg);

         */

        /*PROVA CON LORENZO - FATTO
        int msg = (int) next;
        System.out.println(msg); //notifico posizione di Lore sul tracciato
         */

        /*PROVA CON ACTIONSTRUCTURE - FATTO
        String msg = (String) next;
        System.out.println(msg);
        */

        /*PROVA CON STRONGBOX
        StrongboxMsg msg = (StrongboxMsg) next;
        System.out.println(msg.getStrongBox().get(0));
         */

        /*
        DataOutputStream output = new DataOutputStream(server.getOutputStream());
        String something;
        System.out.print("Write your name: ");
        something = scan.nextLine();
        output.writeUTF(something);
        output.flush();

        while(!server.isClosed()){
            System.out.print("Write something: ");
            something = scan.nextLine();
            output.writeUTF(something);
            output.flush();
            if(something.equals("close")) { */
        try {
            server.close();
            input.close();
            output.close();
        } catch (IOException ex) { System.out.println("Server close gone wrong");}
    }





}