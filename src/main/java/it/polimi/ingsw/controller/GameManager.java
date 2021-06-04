package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.ObservableGame;
import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Scanner;

/*

public static int lonely;
private static ArrayList<Player> players;
private static int n_players;
private static DevelopeDecks[] developedecks = new DevelopeDecks[12];
private static LeaderDeck leaderdeck;
private static Market market;
private static int VR=0;
private static int timer_VR=0;

        */

/**
 * 1) shuffle all the decks and market
 * 2) add all the players
 * starts a loop that
 * 3) calls turn for every player and
 * 4) checks if the game is ended
 * @param
 */

public class GameManager {
    //private Game game;

    /*public GameManager(Game gamein) throws Exception {
        this.game = gamein;
        this.main();
    }*/

    // inizializzazione di view e model

    private static final Game game = new Game();
    private static ArrayList<ClientHandler> clientList;
    private static ClientHandler firstclient;


    public GameManager() {
        clientList = new ArrayList<>();
        clientList.add(0, firstclient);
    }



    public static void main() throws Exception {
        //passare a questa classe l'istanza di clienthandler così da poter chiamare
        //i supi metodi per inviare e ricevere e poter anche condividire l'istanza
        //comune a tutti di game? ciò vuol dire fare attenzione alla sincronizzazione
        //tra i thread perchè saranno tutti attivi contemporaneamente su gamemanager.
        //(ma forse lo saranno su istanze diverse di gamemanager?) forse farlo static?
        //game = new Game();

        ObservableGame obsG = new ObservableGame();
        SingleGame singleGame = new SingleGame();
        // Game.getLeaderdeck() = new LeaderDeck();
        // leaderdeck = new LeaderDeck();
        TurnManager turnmanager = new TurnManager();
        int actualplayer = 0;
        Scanner scan = new Scanner(System.in);

        //1)

        // clientList.get(0).handleClientConnection();



       clientList.get(0).sendMessage("Do you want to start a game? yes (A) no (B)");
        if ((clientList.get(0).receiveMessage() == "A")) {

            // collegamento client-server primo giocatore
            ObserverGame player1 = new ObserverGame();
            game.getPlayers().add(player1);
            obsG.addObserver(player1);
            clientList.get(0).sendMessage("Choose a Name");
            game.getPlayers().get(0).setName(clientList.get(0).receiveMessage());
            game.getPlayers().get(0).setNumber(1);

        } else if ((clientList.get(0).receiveMessage() == "B")) {

            // chiudi il collegamento
        }



        clientList.get(0).sendMessage("Do you want to play alone(A) or against other players(B)?");
        if ((clientList.get(0).receiveMessage() == "A")) {
            clientList.get(0).sendMessage("Single Game against Lorenzo start now");
            new SingleGameManager (clientList.get(0));
            SingleGameManager.main();

        } else if ((scan.nextLine()) == "B") {

            clientList.get(0).sendMessage("Multi Game start now");
            clientList.get(0).sendMessage("Insert the number of players:");
            Game.setN_players( 4 ); //clientList.get(0).receiveMessage()

            for (int i = 1; i < Game.getN_players() ; i++) {

                ObserverGame temporaryplayer = new ObserverGame();
                game.getPlayers().add(temporaryplayer);
                obsG.addObserver(temporaryplayer);
                clientList.get(i).sendMessage("Choose your NAME");
                game.getPlayers().get(i).setName(clientList.get(i).receiveMessage() );
                game.getPlayers().get(i).setNumber(i+1);
            }

            game.setPlayers(game.getPlayers()); // perchè?

        } // aggiunta di errore nel caso in cui il carattere scelto non sia uno proposto


        while (true) {
            //3)
            //turnmanager.setActualplayer(game.getPlayers().get(actualplayer));
            turnmanager.main(clientList.get(actualplayer), game, actualplayer);

            //4)
            if (game.callEndgame(game.getPlayers().get(actualplayer))) {
                for (int i = 1; i < Game.getN_players(); i++) {
                    clientList.get(i).sendMessage("The winner is " + game.callVictory());
                    return;
                }
            }

            actualplayer++;
            if (actualplayer >= game.getPlayers().size()) actualplayer = 0;
        }
    }
}


