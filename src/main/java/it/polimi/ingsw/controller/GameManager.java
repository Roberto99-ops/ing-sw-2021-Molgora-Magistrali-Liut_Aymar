package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageGameManager;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class GameManager implements Runnable{

    private static final Game game = new Game();
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static int actualturn = 0;
    private static SingleGameManager singleGameManager;
    private static boolean end = false;

    @Override
    public void run() {

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


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // se nessuno si collega entro 30 secondi parte singlegame del primo giocatore che si è collegato


        if (clientList.size() == 1) {
            try {
                clientList.get(0).sendMessage("Nobody is connected with you.\nPress enter to start a single game");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                clientList.get(0).receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            singleGameManager = new SingleGameManager(clientList.get(0));
            try {
                singleGameManager.main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //synchronized (this){
        if (clientList.size() != 1) {
            try {
                game.shuffle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < clientList.size(); i++) {
                //player.updateActionStructure(client);
                int choice2 = 0;
                LeaderDeck leaderChoice = game.leaderChoice();
                LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
                try {
                    clientList.get(i).sendMessage(mess);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clientList.get(i).sendMessage("Choose one " + clientList.get(i).getPlayer().getName() + ": ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int choice1 = 0;
                try {
                    choice1 = Integer.parseInt(clientList.get(i).receiveMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                clientList.get(i).getPlayer().getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice1));


                do {
                    try {
                        clientList.get(i).sendMessage("Choose another one " + clientList.get(i).getPlayer().getName() + ": ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        choice2 = Integer.parseInt(clientList.get(i).receiveMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } while (choice2 == choice1);
                clientList.get(i).getPlayer().getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice2));
                try {
                    clientList.get(i).sendMessage("clean screen");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            while (!end) {
                try {
                    clientList.get(actualturn).sendMessage("\n\n\n\t\t\t\t\t\t\t\tIt's your turn " + clientList.get(actualturn).getPlayer().getName() + ". (press enter)");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clientList.get(actualturn).receiveMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < clientList.size(); i++) {
                    if (i != actualturn) {
                        try {
                            clientList.get(i).sendMessage("\n\n\n\n\t\t\t\t\t\t\t\tIt's " + clientList.get(actualturn).getPlayer().getName() + " turn.\n\t\t\t\t\t\t\t\t(press enter & wait)");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                try {
                    turnmanager.main(clientList.get(actualturn), game, actualturn);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < clientList.size(); i++) {

                    if (game.callEndgame(clientList.get(i).getPlayer())) end = true;

                }

                actualturn++;
                if (actualturn == clientList.size()) actualturn = 0;

            }
       // }



            for (int i = 0; i < clientList.size(); i++) {

                try {
                    clientList.get(i).sendMessage("\nThe winner is " + game.callVictory() + "Game is finished");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }


    }

    public static void addPlayer(ClientHandler temporary, GameHandler player){
        clientList.add(temporary);
        game.getPlayers().add(player);
    }


}

