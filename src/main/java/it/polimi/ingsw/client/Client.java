package it.polimi.ingsw.client;

import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

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
    //questa è una copia  del player e dei suoi dati/carte presenti nel server. Questa istanza permette al client di
    // avere tutti i dati ricevuti dal clienthandler in modo ordinato e di visualizzarli quando ne ha bisogno
    private Player playerSH = new Player();
    //questa è una copia del market nel caso in cui il giocatore giochi in compagnia
    private Market marketSH = new Market();
    //questa è una copia dei segnalini che possono essere pescati dal giocatore quando gioca da solo
    private ActionStructure actionStructureSH = new ActionStructure();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert IP address of Server ");
        String ip = scan.nextLine();

        /*Per aprire connessione col server*/
        Socket server;
        try {
            server = new Socket(ip, Server.SOCKET_PORT);  //ipServer Roby: 95.250.236.230
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
            if(something.equals("close")) {*/
        try {
            input.close();
            server.close();
        } catch (IOException ex) { System.out.println("Server close gone wrong");}
    }





}