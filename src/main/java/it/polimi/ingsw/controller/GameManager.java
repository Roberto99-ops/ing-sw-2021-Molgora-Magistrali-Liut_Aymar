package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

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
        leaderdeck = new LeaderDeck();
        players = new ArrayList<Player>();
        Turn turn = new Turn();
        int actualplayer = 0;
        Scanner scan = new Scanner(System.in);

        //1)
        System.out.println("Do you want to play alone(A) or against other players(B)?"); //CLIENTHENDLER
        if ((scan.nextLine()) == "A") {
            SingleGame.main();
        } else if ((scan.nextLine()) == "B") {
            game.Shuffle();
            //3)
            //players.add()    ????

            Game.setPlayers(players);

            // setN_players(players.size());// mi setta la costante che riguarda il n_partecipanti
            // questa costante verrà usata quando chiamo VaticanReport
        } // aggiunta di errore nel caso in cui il carattero scelto non sia uno proposto


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
/**

}
