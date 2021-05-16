package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Scanner;

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



    public static int lonely;
    private static ArrayList<Player> players;
    private static int n_players;
    private static DevelopeDecks[] developedecks = new DevelopeDecks[12];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;



    public static void main(String[] args) throws Exception {
        Game game = new Game();
        SingleGame singleGame = new SingleGame();
        // game.getLeaderdeck() = new LeaderDeck();
        leaderdeck = new LeaderDeck();
        players = new ArrayList<Player>();
        Turn turn = new Turn();
        int actualplayer = 0;
        Scanner scan = new Scanner(System.in);

        //1)
        System.out.println("Do you want to start a game? yes (A) no (B)"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            // collegamento client-server
            Player player1 = new Player();
            players.add(player1);
            System.out.println("Choose your NAME"); //CLIENTHENDLER
            players.get(0).setName(scan.nextLine());
            players.get(0).setNumber(1);
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
                players.add(temporaryplayer);
                System.out.println("Choose your NAME"); //CLIENTHENDLER
                players.get(i).setName(scan.nextLine());
                players.get(i).setNumber(i+1);
            }

            game.setPlayers(players);

        } // aggiunta di errore nel caso in cui il carattere scelto non sia uno proposto


        while (true) {
            //3)
            turn.setActualplayer(players.get(actualplayer));
            turn.main();

            //4)
            if (game.Endgame(players.get(actualplayer))) {
                System.out.println("The winner is " + game.Victory()); // CLIENTHENDLER
                return;
            }

            actualplayer++;
            if (actualplayer >= players.size()) actualplayer = 0;
        }
    }
}


