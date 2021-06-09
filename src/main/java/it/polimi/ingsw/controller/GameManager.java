package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageGameManager;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * 1) shuffle all the decks and market
 * 2) add all the players
 * starts a loop that
 * 3) calls turn for every player and
 * 4) checks if the game is ended
 * @param
 */

public class GameManager {

    private static final Game game = new Game();
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static int actualturn = 0;
    private static SingleGameManager singleGameManager;
    private static boolean end = false;


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


        TimeUnit.SECONDS.sleep(10);

        // se nessuno si collega entro 30 secondi parte singlegame del primo giocatore che si è collegato


        if (clientList.size() == 1) {
            clientList.get(0).sendMessage("\nNobody is connected with you\n\nIf you want to start a Single Game press enter");
            singleGameManager.main();
        }


        if (clientList.size() != 1) {

            game.shuffle();

            for (int i = 0; i < clientList.size(); i++) {
                //player.updateActionStructure(client);
                int choice2;
                LeaderDeck leaderChoice = game.leaderChoice();
                LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
                clientList.get(i).sendMessage(mess);
                clientList.get(i).sendMessage("\nChoose one " + clientList.get(i).getPlayer().getName() + ": ");
                int choice1 = Integer.parseInt(clientList.get(i).receiveMessage());
                clientList.get(i).getPlayer().getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice1));


                do {
                    clientList.get(i).sendMessage("\nChoose another one " + clientList.get(i).getPlayer().getName() + ": ");
                    choice2 = Integer.parseInt(clientList.get(i).receiveMessage());
                } while (choice2 == choice1);
                clientList.get(i).getPlayer().getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice2));
                clientList.get(i).sendMessage("\nclean screen");

            }


            while (actualturn < 4 && end == false) {

                clientList.get(actualturn).sendMessage("\nIt's your Turn " + clientList.get(actualturn).getPlayer().getName());

                for (int i = 0; i < clientList.size() - 1; i++) {
                    if (i != actualturn)
                        clientList.get(i).sendMessage("\nIt's " + clientList.get(actualturn).getPlayer().getName() + " Turn.");
                }


                turnmanager.main(clientList.get(actualturn), game, actualturn);


                for (int i = 0; i < clientList.size(); i++) {

                    if (game.callEndgame(clientList.get(i).getPlayer()) == true) end = true;

                }

                actualturn++;
                if (actualturn == clientList.size() - 1) actualturn = 0;

            }




            for (int i = 0; i < clientList.size(); i++) {

                clientList.get(i).sendMessage("\nThe winner is " + game.callVictory() + "Game is finished");

            }


        }


    }


}

