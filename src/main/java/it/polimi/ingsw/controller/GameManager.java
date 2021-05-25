package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ObservableGame;
import it.polimi.ingsw.model.*;

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

public class GameManager extends Game {

    // inizializzazione di view e model


    public static void main(String[] args) throws Exception {
        Game game = new Game();
        ObservableGame obsG = new ObservableGame();
        SingleGame singleGame = new SingleGame();
        // game.getLeaderdeck() = new LeaderDeck();
        // leaderdeck = new LeaderDeck();
        Turn turn = new Turn();
        int actualplayer = 0;
        Scanner scan = new Scanner(System.in);

        //1)
        System.out.println("Do you want to start a game? yes (A) no (B)"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            // collegamento client-server
            Player player1 = new Player();
            // Observer obs1 = new ObserverGame(); // meglio con player ?
            game.getPlayers().add(player1);
            // obsG.addObserver(obs1);
            System.out.println("Choose your NAME"); //CLIENTHENDLER
            game.getPlayers().get(0).setName(scan.nextLine());
            game.getPlayers().get(0).setNumber(1);
        } else if ((scan.nextLine()) == "B") {
            // chiudi il collegamento
        }



        System.out.println("Do you want to play alone(A) or against other players(B)?"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            SingleGame.main();
        } else if ((scan.nextLine()) == "B") {
            System.out.println("Insert the number of players:"); //CLIENTHENDLER
            int n_players = scan.nextInt();
            for (int i = 1; i < n_players; i++) {
                Player temporaryplayer = new Player();
                // Observer temporaryobserver = new ObserverGame();
                game.getPlayers().add(temporaryplayer);
                // obsG.addObserver(temporaryobserver); // meglio con player???
                System.out.println("Choose your NAME"); //CLIENTHENDLER
                game.getPlayers().get(i).setName(scan.nextLine());
                game.getPlayers().get(i).setNumber(i+1);
            }

            game.setPlayers(game.getPlayers());

        } // aggiunta di errore nel caso in cui il carattere scelto non sia uno proposto


        while (true) {
            //3)
            turn.setActualplayer(game.getPlayers().get(actualplayer));
            turn.main();

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


