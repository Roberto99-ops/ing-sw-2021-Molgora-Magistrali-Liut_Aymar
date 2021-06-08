package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageGameManager;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

// import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;

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
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static int actualturn = 0;
    private static SingleGameManager singleGameManager;

    private static Timer timer = new Timer();

            /* metodo 1 con javax (60000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("rbjfb");

            while (true) {

                System.out.println("rbjfb");

                if (clientList.size() == 4)
                    break;

            }
        }
    });
    +/

             */

     /* metodo 2 con util
     private static TimerTask timertask = new TimerTask() {

        @Override
        public void run() {
            while (true) {

                System.out.println("rbjfb");

                if (clientList.size() == 4)
                    break;

            }
        }
    };
 */


    public GameManager(ClientHandler temporary, GameHandler player) {
        clientList.add(temporary);
        game.getPlayers().add(player);
        singleGameManager = new SingleGameManager(clientList.get(0));
    }




    public static void main() throws Exception {

        //passare a questa classe l'istanza di clienthandler così da poter chiamare
        //i supi metodi per inviare e ricevere e poter anche condividire l'istanza
        //comune a tutti di game? ciò vuol dire fare attenzione alla sincronizzazione
        //tra i thread perchè saranno tutti attivi contemporaneamente su gamemanager.
        //(ma forse lo saranno su istanze diverse di gamemanager?) forse farlo static?
        //game = new Game();

        TurnManager turnmanager = new TurnManager();

        // il timer serve per mettere in attesa i vari giocatori fino a che scade
        // e si inizia a giocare con i giocatori connessi (max 4)

        // timer.start();
        // timer.schedule;

        // se nessuno si collega entro un minuto parte singlegame del primo giocatore che si è collegato

        /*
        if (clientList.size() == 1)

            clientList.get(0).sendMessage("Nobody is connected with you\nStart a Single Game");
            singleGameManager.main();

*/

        while (actualturn < 4) {


            turnmanager.main(clientList.get(actualturn), game, actualturn);


            if (game.callEndgame(game.getPlayers().get(actualturn))) {
                for (int i = 1; i < Game.getN_players(); i++) {
                    clientList.get(i).sendMessage("The winner is " + game.callVictory());
                    return;
                }
            }


            actualturn++;
            if (actualturn == 5) actualturn = 0;
        }

    }

}



        /*

        MessageGameManager obsG = new MessageGameManager();
        // Game.getLeaderdeck() = new LeaderDeck();
        // leaderdeck = new LeaderDeck();
        int actualplayer = 0;
        Scanner scan = new Scanner(System.in);

        //1)

        // clientList.get(0).handleClientConnection();



       clientList.get(0).sendMessage("Do you want to start a game? yes (A) no (B)");
        if ((clientList.get(0).receiveMessage() == "A")) {

            // collegamento client-server primo giocatore
            GameHandler player1 = new GameHandler();
            game.getPlayers().add(player1);
            obsG.addHendlers(player1);
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

                GameHandler temporaryplayer = new GameHandler();
                game.getPlayers().add(temporaryplayer);
                obsG.addHendlers(temporaryplayer);
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

*/
