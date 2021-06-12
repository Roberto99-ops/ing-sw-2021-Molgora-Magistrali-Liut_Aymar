package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.CliManager;
import it.polimi.ingsw.view.cli.DevelopeDecksView;
import it.polimi.ingsw.view.cli.Playerboard;
import it.polimi.ingsw.view.cli.Utility;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

    //private static final int DEFAULT_PORT = 1000;

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
        System.out.print("\nInsert Port number of Server:");
        String serverport = scan.nextLine();



        /*Per aprire connessione col server*/
        Socket server;
        try {
            /*if(!serverport.equals(""))
                server = new Socket(ip, Integer.parseInt(serverport));  //ipServer Roby: 95.250.236.230
            else
                server = new Socket(ip, 1000);
            */
            server = new Socket(ip, Integer.parseInt(serverport));  //ipServer Roby: 95.250.236.230

        } catch (IOException e) {
            System.out.println("\nServer unreachable");
            return;
        }
        System.out.println("\nConnected");


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
                if(next.equals("clean screen") || ((String) next).charAt(0) == '\n') {
                    if (next.equals("clean screen")) {
                        Utility.Clean();
                    }
                    if (((String) next).charAt(0) == '\n') {
                        System.out.print("\n" + next);
                    }
                }
                else {
                    System.out.print("\n" + next);
                    String string = scan.nextLine();
                    output.writeObject(string);
                    output.flush();
                    output.reset();
                }
            }
            else
                CliManager.update(next, player);
            next = input.readObject();
        }while(!next.equals("Turn Finished"));   //ci aspettiamo un messaggio di turno finito a fine turno
        try {
            server.close();
            input.close();
            output.close();
        } catch (IOException ex) { System.out.println("\nServer close gone wrong");}
    }





}