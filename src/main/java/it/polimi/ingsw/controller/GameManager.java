package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;




    /**
     * 1) shuffle all the decks and market
     * 2) add all the players
     * starts a loop that
     * 3) calls turn for every player and
     * 4) checks if the game is ended
     */

public class GameManager implements Runnable{

    private static final Game game = new Game();
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static int actualturn = 0;
    private static SingleGameManager singleGameManager;
    private static boolean end = false;
    public static ArrayList<ClientHandler> getClientList() {
        return clientList;
    }

    @Override
    public void run() {
        TurnManager turnmanager = new TurnManager();
            //the servers waits 25 seconds, if none (except the actual player) connects,
            //it starts automatically a single game
        try {
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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
                Server.closeGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (clientList.size() != 1) {

            try {
                game.shuffle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < clientList.size(); i++) {
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
            }




                    if (clientList.size() >= 2) {

                        String j = new String();

                        try {
                            clientList.get(1).sendMessage("Tell me the initial resource that you want (P,B,G,Y) \n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                                clientList.get(1).getPlayer().addResourceStorage(clientList.get(1).receiveMessage().charAt(0), game);
                            }  catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                    }



                if (clientList.size() >= 3) {

                    try {
                        clientList.get(2).sendMessage("Tell me the initial resource that you want (P,B,G,Y) \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                            clientList.get(2).getPlayer().addResourceStorage(clientList.get(2).receiveMessage().charAt(0), game);
                        }  catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                    clientList.get(2).getPlayer().increaseTrackPosition();

                }



                if (clientList.size() == 4) {

                    String r = new String();


                    try {
                        clientList.get(3).sendMessage("Tell me the first resource that you want (P,B,G,Y) \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {


                            clientList.get(3).getPlayer().addResourceStorage(clientList.get(3).receiveMessage().charAt(0), game);
                        }  catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        clientList.get(3).sendMessage("Tell me the second resource that you want (P,B,G,Y) \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {


                            clientList.get(3).getPlayer().addResourceStorage(clientList.get(3).receiveMessage().charAt(0), game);
                       }  catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                    clientList.get(3).getPlayer().increaseTrackPosition();


                }


                while (!end) {
                    try {
                        clientList.get(actualturn).sendMessage("\n\n\n\t\t\t\t\t\t\t\tIt's your turn " + clientList.get(actualturn).getPlayer().getName() + ".");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < clientList.size(); i++) {
                        if (i != actualturn) {
                            try {
                                clientList.get(i).sendMessage("\n\n\n\n\t\t\t\t\t\t\t\tIt's " + clientList.get(actualturn).getPlayer().getName() + " turn.\n\t\t\t\t\t\t\t(just wait)");
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


            end = false;
            String winner = game.callVictory();
            for (int i = 0; i < clientList.size(); i++) {
                try {
                    clientList.get(i).sendMessage("clean screen");
                    clientList.get(i).sendMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\tThe winner is " + winner + ", game is finished\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    clientList.get(i).sendMessage("Game Ended");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Game Ended");
            while( clientList.size()>0) {
                clientList.remove(0);
            }
            Server.setNumberofsockets(0);
            Server.setCloseserverTrue();
        }
    }



    /**
     * this method add a player to the game and to the KeepAlive class in a synchronized way, so we shouldn't have, for example,
     * GameManager.clientlist = [1, 2] && KeepAlive.clientlist = [2, 1]
     * @param temporary
     * @param player
     */

    public static synchronized void addPlayer(ClientHandler temporary, GameHandler player){
        clientList.add(temporary);
        game.getPlayers().add(player);
        KeepAlive.addClient(temporary);
    }


}

