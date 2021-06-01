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

public class GameManager{
    //private Game game;

    /*public GameManager(Game gamein) throws Exception {
        this.game = gamein;
        this.main();
    }*/

    // inizializzazione di view e model

    private static final Game game = new Game();
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();

    public GameManager (ClientHandler client)
    {
        clientList.add(client);
    }

    public GameManager() {
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
        System.out.println("Do you want to start a game? yes (A) no (B)"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            // collegamento client-server
            ObserverGame player1 = new ObserverGame();
            game.getPlayers().add(player1);
            obsG.addObserver(player1);
            System.out.println("Choose your NAME"); //CLIENTHENDLER
            game.getPlayers().get(0).setName(scan.nextLine());
            game.getPlayers().get(0).setNumber(1);
        } else if ((scan.nextLine()) == "B") {
            // chiudi il collegamento
        }



        System.out.println("Do you want to play alone(A) or against other players(B)?"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            SingleGameManager.main();
        } else if ((scan.nextLine()) == "B") {
            System.out.println("Insert the number of players:"); // CLIENTHENDLER
            int n_players = scan.nextInt();
            for (int i = 1; i < n_players; i++) {
                ObserverGame temporaryplayer = new ObserverGame();
                game.getPlayers().add(temporaryplayer);
                obsG.addObserver(temporaryplayer);
                System.out.println("Choose your NAME"); // CLIENTHENDLER
                game.getPlayers().get(i).setName(scan.nextLine());
                game.getPlayers().get(i).setNumber(i+1);
            }

            game.setPlayers(game.getPlayers());

        } // aggiunta di errore nel caso in cui il carattere scelto non sia uno proposto


        while (true) {
            //3)
            //turnmanager.setActualplayer(game.getPlayers().get(actualplayer));
            turnmanager.main(clientList.get(0), game, 0); // contatore per i players, non è get(0) ma get(i)


            //4)
            if (game.Endgame(game.getPlayers().get(actualplayer))) {
                System.out.println("The winner is " + game.Victory()); // CLIENTHENDLER
                return;
            }

            actualplayer++;
            if (actualplayer >= game.getPlayers().size()) actualplayer = 0;
        }
    }
}


