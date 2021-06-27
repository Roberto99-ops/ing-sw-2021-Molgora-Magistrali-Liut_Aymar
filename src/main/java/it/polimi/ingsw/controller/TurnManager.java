package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageGameManager;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TurnManager {

    /**
     * turnmanager manages all the turn.
     * 1)asks us what do we want to do
     * 2)in case we choose to active a production, we join a while that active all the production we want
     * 2.1)it lists us all our LeaderCards so we can choose to active one of that
     * 2.2) do the same with our Developecards
     * then the turn ends
     */


    public void main(ClientHandler client, Game game, int actualplayer) throws Exception {
        Turn turn = new Turn(client, game);
        GameHandler player = turn.getActualplayer();
        String reception;
        int leaderaction = 0;
        boolean additionalAction = false;
        boolean actionDone = true;


        if (game.getClass().equals(SingleGame.class)) {
            SingleGame.getActionStructure().pickSignal();
            System.out.println(SingleGame.getActionStructure().getAS_Counter() + " \t" + SingleGame.getActionStructure().getOLD_Signal() +  " " + Arrays.toString(SingleGame.getActionStructure().getStructure()));
            player.updatePlayerBoard(client, game);
            if(SingleGame.getLorenzo().getNumber() == 24) return;
        }
        else
            player.updatePlayerBoard(client, game);

        String c = new String();
        do {

            if (!actionDone){
                client.sendMessage("Since you have done no actions:\nWhat do you want to do?\n\t1)Shop a developement card\t2)Take resources at the market\n\t3)Active a production\n");
            } else if (actionDone) {
                client.sendMessage("What do you want to do?\n\t1)Shop a developement card\t2)Take resources at the market\n\t3)Active a production\n");//4)Do a Leader action ");
            }
                String msg = client.receiveMessage();


            try {
                System.out.println(msg.charAt(0) - 48);
                if (msg.charAt(0) - 48 == 1) {
                    if (!turn.shopCard()) {actionDone = false;}
                    else {
                        client.sendMessage("clean screen");
                        player.updatePlayerBoard(client, game);
                    }
                }



                else if (msg.charAt(0) - 48 == 2) {
                    turn.buyResource();
                    client.sendMessage("clean screen");
                    player.updateMarket(client);
                    client.sendMessage("the market is changed (press any key)");
                    client.receiveMessage();
                    client.sendMessage("clean screen");
                    player.updatePlayerBoard(client, game);
                    actionDone = true;
                }


                else if (msg.charAt(0) - 48 == 3) {


                    boolean ableTo;

                    do {
                        ArrayList<Character> vectorInProduction = new ArrayList<>();
                        ArrayList<Character> vectorOutProduction = new ArrayList<>();

                        if (player.getSkill1() == 1 && player.getLeadercards().getStructure().get(0).getSkill().equals("Prod.Skill")) {
                            client.sendMessage("Do you want to do leadercard's (0) Production Skill? (y/n) \n");
                            String s=client.receiveMessage();
                            if (s.equals("y")) {
                                client.sendMessage(" Tell me the resource you want to product (P,B,G,Y) \n");
                                vectorOutProduction.add(client.receiveMessage().charAt(0));
                                vectorInProduction.add(player.getLeadercards().getStructure().get(0).getInputskill());
                                vectorOutProduction.add('R');
                            }
                        }

                                if (player.getSkill2() == 1 && player.getLeadercards().getStructure().get(1).getSkill().equals("Prod.Skill")){
                                client.sendMessage("Do you want to do leadercard's (1) Production Skill? (y/n) \n");
                                        String s=client.receiveMessage();
                                        if (s.equals("y")) {
                                                    client.sendMessage(" Tell me the resource you want to product (P,B,G,Y) \n");
                                                    vectorOutProduction.add(client.receiveMessage().charAt(0));
                                                    vectorInProduction.add(player.getLeadercards().getStructure().get(1).getInputskill());
                                                    vectorOutProduction.add('R');
                                                }
                                            }


                    ableTo = false;


                        boolean[] checks = new boolean[4];
                        char chosenResource = 'N';
                        for (int i = 0; i < 4; i ++) checks[i] = false;
                        client.sendMessage("Do you want to activate the basic production? (y/n)\n");
                        String s=client.receiveMessage();
                        if (s.equals("y")) {
                            checks[0] = true;
                            client.sendMessage("Tell me the first resource you want to discard (P,B,G,Y)\n");
                            vectorInProduction.add(client.receiveMessage().charAt(0));
                            client.sendMessage("Tell me the second resource you want to discard (P,B,G,Y)\n");
                            vectorInProduction.add(client.receiveMessage().charAt(0));
                            do {
                            client.sendMessage("Tell me the resource you want to product (P,B,G,Y)\n");
                                chosenResource = client.receiveMessage().charAt(0);
                            } while (chosenResource!='P'&& chosenResource!='B'&&chosenResource!='G'&& chosenResource!='Y');
                        }

                        if (turn.getActualplayer().getDSpace().getMinideck1().getStructure().size() > 0) {
                        client.sendMessage("Do you want to activate the production of the first column of development space? (y/n)\n");
                        s=client.receiveMessage();
                        if (s.equals("y")) {
                                checks[1] = true;

                            for (int i = 0; i < player.getDSpace().getCard(1).getInputproduction().getVector().size(); i ++)
                                vectorInProduction.add(player.getDSpace().getCard(1).getInputproduction().getVector().get(i));

                            for (int i = 0; i < player.getDSpace().getCard(1).getOutputproduction().getVector().size(); i ++)
                                vectorOutProduction.add(player.getDSpace().getCard(1).getOutputproduction().getVector().get(i));
                            }
                        }



                        if (turn.getActualplayer().getDSpace().getMinideck2().getStructure().size() > 0) {
                            client.sendMessage("Do you want to activate the production of the second column of development space? (y/n)\n");
                            s=client.receiveMessage();
                            if (s.equals("y")) {
                                checks[2] = true;

                                for (int i = 0; i < player.getDSpace().getCard(2).getInputproduction().getVector().size(); i ++)
                                    vectorInProduction.add(player.getDSpace().getCard(2).getInputproduction().getVector().get(i));

                                for (int i = 0; i < player.getDSpace().getCard(2).getOutputproduction().getVector().size(); i ++)
                                    vectorOutProduction.add(player.getDSpace().getCard(2).getOutputproduction().getVector().get(i));
                            }
                        }

                        if (turn.getActualplayer().getDSpace().getMinideck3().getStructure().size() > 0) {
                            client.sendMessage("Do you want to activate the production of the third column of development space? (y/n)\n");
                            s=client.receiveMessage();
                            if (s.equals("y")) {
                                checks[3] = true;

                                for (int i = 0; i < player.getDSpace().getCard(3).getInputproduction().getVector().size(); i ++)
                                    vectorInProduction.add(player.getDSpace().getCard(3).getInputproduction().getVector().get(i));

                                for (int i = 0; i < player.getDSpace().getCard(3).getOutputproduction().getVector().size(); i ++)
                                    vectorOutProduction.add(player.getDSpace().getCard(3).getOutputproduction().getVector().get(i));
                            }
                        }


                        if (turn.getActualplayer().checkResources(vectorInProduction) != 0) { //
                            client.sendMessage("You are able to do this production (press a key)\n");
                            ableTo=true;
                            turn.getActualplayer().deleteResources(turn.getActualplayer().checkResources(vectorInProduction), vectorInProduction); //

                            for (int i = 0; i < vectorOutProduction.size(); i++) {
                                turn.getActualplayer().addResourceStrongBox(vectorOutProduction.get(i));
                            }

                            if (checks[0]) {
                                turn.getActualplayer().addResourceStrongBox(chosenResource);
                                player.updatePlayerBoard(client,game);

                            }
                            actionDone=true;


                        } else {
                            client.sendMessage("You can't to do this production\nDo you want to retry to do a production? (y/n)\n");
                            s = client.receiveMessage();
                            if (s.equals("n")) {
                                ableTo = true;
                                actionDone=false;
                            }
                        }

                    } while (!ableTo);


                }


                if((leaderaction<2 && (msg.charAt(0) - 48 == 1 || msg.charAt(0) - 48 == 2 || msg.charAt(0) - 48 == 3)) && player.getLeadercards().getStructure().size()>0) {
                    client.sendMessage("Do you want to do a Leader action? (yes/no)\n ");
                    reception = client.receiveMessage();
                    if(reception.equals("yes")) additionalAction = true;
                }


                if (leaderaction < 2  && additionalAction) {
                    client.sendMessage("Do you want to remove a card or to activate one? (remove/activate) ");
                    String choice = client.receiveMessage();

                    if(choice.equals("remove")) {
                        turn.removeLeader();
                        leaderaction++;
                    }

                    if(choice.equals("activate")) {
                        turn.activateLeader();
                        leaderaction++;
                    }

                    additionalAction = false;

                }

            } catch (IOException e) {
                System.out.println(e);
            }
            c = msg;
        } while(!c.equals("1") && !c.equals("2") && !c.equals("3")|| !actionDone);



        player.getFaithTrack().callVaticanReport(player, game);
        MessageGameManager.personalChanges(client, game.getPlayers().get(actualplayer), game);
        MessageGameManager.generalChanges(client);

    }
}


