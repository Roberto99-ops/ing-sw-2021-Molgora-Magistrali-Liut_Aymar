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
     * TurnManager manages every turn:
     * 1)asks us what we'd like to do
     * 2)in case we choose to active a production, we enter in a cycle that activates all productions we want
     *      2.1) it lists us all our LeaderCards so we can choose to active one of those
     *      2.2) it does the same with our DevelopCards
     * then the turn ends
     */


    public void main(ClientHandler client, Game game, int actualplayer) throws Exception {
        Turn turn = new Turn(client, game);
        GameHandler player = turn.getActualPlayer();
        String reception;
        int leaderAction = 0;
        boolean additionalAction = false;
        //flag used to check if one of the three available actions in one turn has been done
        boolean actionDone = true;

        //if I'm playing in SingleGame mode
        if (game.getClass().equals(SingleGame.class)) {
            SingleGame.getActionStructure().pickSignal();
            System.out.println(SingleGame.getActionStructure().getAS_Counter() + " \t" + SingleGame.getActionStructure().getOLD_Signal() +  " " + Arrays.toString(SingleGame.getActionStructure().getStructure()));
            player.updatePlayerBoard(client, game);
            if(SingleGame.getLorenzo().getNumber() == 24) return;
        }
        //if I'm playing in Multi Player mode
        else
            player.updatePlayerBoard(client, game);

        String c;
        String msg;
        do {
            do {
                if (!actionDone) {
                    //if one of the 3 actions has not been done, the Player can choose again
                    if (player.getLeadercards().getStructure().size() > 0) {
                        client.sendMessage("Since you have done no actions: What do you want to do?\n  1)Shop a developement card\t2)Take resources at the market\n  3)Active a production\t4)Do a Leader action ");
                    } else {
                        client.sendMessage("Since you have done no actions: What do you want to do?\n  1)Shop a developement card\t2)Take resources at the market\n  3)Active a production ");
                    }
                } else {
                    if (player.getLeadercards().getStructure().size() > 0) {
                        client.sendMessage("What do you want to do?\n  1)Shop a development card\t2)Take resources at the market\n  3)Active a production\t4)Do a Leader action ");
                    } else {
                        client.sendMessage("What do you want to do?\n  1)Shop a development card\t2)Take resources at the market\n  3)Active a production ");
                    }
                    actionDone = false;
                }
                 msg = client.receiveMessage();
                 if (msg.equals("")) msg = "Z";
            }while(msg.charAt(0)-48 != 1 && msg.charAt(0)-48 != 2 && msg.charAt(0)-48 != 3 && msg.charAt(0)-48 != 4);

            //1)  Shop card
            try {
                if (msg.charAt(0) - 48 == 1) {
                    if (!turn.shopCard()) {actionDone = false;}
                    else {
                        actionDone = true;
                        client.sendMessage("clean screen");
                        player.updatePlayerBoard(client, game);
                    }
                }


                //2) Market
                else if (msg.charAt(0) - 48 == 2) {
                    turn.buyResource();
                    client.sendMessage("clean screen");
                    player.updateMarket(client);
                    client.sendMessage("The market has changed (press any key)");
                    client.receiveMessage();
                    client.sendMessage("clean screen");
                    player.updatePlayerBoard(client, game);
                    actionDone = true;
                }

                //3) Production: basic production + production based on DevelopCards
                else if (msg.charAt(0) - 48 == 3) {


                    boolean ableTo;
                    char chosenResource = 'N';

                    do {
                        ArrayList<Character> vectorInProduction = new ArrayList<>();
                        ArrayList<Character> vectorOutProduction = new ArrayList<>();

                        if (player.getSkill1() == 1 && player.getLeadercards().getStructure().get(0).getSkill().equals("Prod.Skill")) {
                            String s;
                            do {
                                client.sendMessage("Do you want to do LeaderCard's (0) Production Skill? (y/n) \n");
                                s = client.receiveMessage();
                            }while(!s.equals("y") && !s.equals("n"));
                            if (s.equals("y")) {
                                do {
                                    client.sendMessage(" Tell me the resource you want to product (P,B,G,Y) \n");
                                    chosenResource=client.receiveMessage().charAt(0);
                                } while (chosenResource!='P'&& chosenResource!='B'&&chosenResource!='G'&& chosenResource!='Y');
                                vectorOutProduction.add(chosenResource);
                                vectorInProduction.add(player.getLeadercards().getStructure().get(0).getInputSkill());
                                vectorOutProduction.add('R');
                            }
                        }

                                if (player.getSkill2() == 1 && player.getLeadercards().getStructure().get(1).getSkill().equals("Prod.Skill")){
                                String s;
                                    do {
                                    client.sendMessage("Do you want to do LeaderCard's (1) Production Skill? (y/n) \n");
                                    s = client.receiveMessage();
                                }while (!s.equals("y") && !s.equals("n"));
                                        if (s.equals("y")) {
                                            do {
                                                client.sendMessage(" Tell me the resource you want to product (P,B,G,Y) \n");
                                                chosenResource = client.receiveMessage().charAt(0);
                                            }while (chosenResource!='P'&& chosenResource!='B'&& chosenResource!='G'&& chosenResource!='Y');

                                                    vectorOutProduction.add(chosenResource);
                                                    vectorInProduction.add(player.getLeadercards().getStructure().get(1).getInputSkill());
                                                    vectorOutProduction.add('R');
                                                }
                                            }


                    ableTo = false;


                        boolean[] checks = new boolean[4];

                        for (int i = 0; i < 4; i ++) checks[i] = false;
                        String s;
                        do {
                            client.sendMessage("Do you want to activate the basic production? (yes/no)\n");
                            s = client.receiveMessage();
                        }while(!s.equals("yes") && !s.equals("no"));

                        //if basic production is enabled
                        if (s.equals("yes")) {
                            checks[0] = true;
                            do {
                                client.sendMessage("Tell me the first resource you want to discard (P,B,G,Y)\n");
                                String resource = client.receiveMessage();
                                if(resource.equals(""))
                                    chosenResource = 'N';
                                else
                                    chosenResource = resource.charAt(0);
                            } while (chosenResource!='P'&& chosenResource!='B'&&chosenResource!='G'&& chosenResource!='Y');
                            vectorInProduction.add(chosenResource);

                            do{
                                client.sendMessage("Tell me the second resource you want to discard (P,B,G,Y)\n");
                                chosenResource = client.receiveMessage().charAt(0);
                            } while (chosenResource!='P'&& chosenResource!='B'&&chosenResource!='G'&& chosenResource!='Y');
                            vectorInProduction.add(chosenResource);

                            do {
                            client.sendMessage("Tell me the resource you want to product (P,B,G,Y)\n");
                                chosenResource = client.receiveMessage().charAt(0);
                            } while (chosenResource!='P'&& chosenResource!='B'&&chosenResource!='G'&& chosenResource!='Y');
                            vectorOutProduction.add(chosenResource);
                        }
                        //the game asks the player which DevelopCard production he/she wants to use
                        if (turn.getActualPlayer().getDSpace().getMiniDeck1().getStructure().size() > 0) {
                            do {
                                client.sendMessage("Do you want to activate the production of the first column of development space? (y/n)\n");
                                s = client.receiveMessage();
                            }while(!s.equals("y") && !s.equals("n"));
                        if (s.equals("y")) {
                                checks[1] = true;

                            for (int i = 0; i < player.getDSpace().getCard(1).getInputproduction().getVector().size(); i ++)
                                vectorInProduction.add(player.getDSpace().getCard(1).getInputproduction().getVector().get(i));

                            for (int i = 0; i < player.getDSpace().getCard(1).getOutputproduction().getVector().size(); i ++)
                                vectorOutProduction.add(player.getDSpace().getCard(1).getOutputproduction().getVector().get(i));
                            }
                        }



                        if (turn.getActualPlayer().getDSpace().getMiniDeck2().getStructure().size() > 0) {
                            do {
                                client.sendMessage("Do you want to activate the production of the second column of development space? (y/n)\n");
                                s = client.receiveMessage();
                            }while(!s.equals("y") && !s.equals("n"));
                            if (s.equals("y")) {
                                checks[2] = true;

                                for (int i = 0; i < player.getDSpace().getCard(2).getInputproduction().getVector().size(); i ++)
                                    vectorInProduction.add(player.getDSpace().getCard(2).getInputproduction().getVector().get(i));

                                for (int i = 0; i < player.getDSpace().getCard(2).getOutputproduction().getVector().size(); i ++)
                                    vectorOutProduction.add(player.getDSpace().getCard(2).getOutputproduction().getVector().get(i));
                            }
                        }

                        if (turn.getActualPlayer().getDSpace().getMiniDeck3().getStructure().size() > 0) {
                            do {
                                client.sendMessage("Do you want to activate the production of the third column of development space? (y/n)\n");
                                s = client.receiveMessage();
                            }while(!s.equals("y") && !s.equals("n"));
                            if (s.equals("y")) {
                                checks[3] = true;

                                for (int i = 0; i < player.getDSpace().getCard(3).getInputproduction().getVector().size(); i ++)
                                    vectorInProduction.add(player.getDSpace().getCard(3).getInputproduction().getVector().get(i));

                                for (int i = 0; i < player.getDSpace().getCard(3).getOutputproduction().getVector().size(); i ++)
                                    vectorOutProduction.add(player.getDSpace().getCard(3).getOutputproduction().getVector().get(i));
                            }
                        }

                        //As written in the rules, the production are all done at the same time
                        if (turn.getActualPlayer().checkResources(vectorInProduction) != 0) { //
                            client.sendMessage("You are able to do this production (press a key)\n");
                            client.receiveMessage();
                            ableTo=true;
                            turn.getActualPlayer().deleteResources(turn.getActualPlayer().checkResources(vectorInProduction), vectorInProduction); //

                            for (int i = 0; i < vectorOutProduction.size(); i++) {
                                turn.getActualPlayer().addResourceStrongBox(vectorOutProduction.get(i));
                            }

                            if (checks[0]) {
                                turn.getActualPlayer().addResourceStrongBox(chosenResource);
                                player.updatePlayerBoard(client,game);

                            }
                            actionDone=true;


                        } else {
                            //in case the production I decided to activate cannot be done, the game gives me another
                            //chance to do another production
                            do {
                                client.sendMessage("You can't to do this production\nDo you want to retry to do a production? (y/n)\n");
                                s = client.receiveMessage();
                            }while(!s.equals("y") && !s.equals("n"));
                            if (s.equals("n")) {
                                ableTo = true;
                                actionDone=false;
                            }
                        }

                    } while (!ableTo);


                }
                //4) LeaderAction : remove or activate a LeaderCard
                if((leaderAction<2 && (msg.charAt(0) - 48 == 1 || msg.charAt(0) - 48 == 2 || msg.charAt(0) - 48 == 3 || msg.charAt(0) -48 == 4))){
                if (player.getLeadercards().getStructure().size()>0)
                {
                            client.sendMessage("Do you want to do a Leader action? (yes/no)\n ");
                            reception = client.receiveMessage();
                            if(reception.equals("yes")) additionalAction = true;
                        }
                }else if (msg.charAt(0)-48 == 4){
                    client.sendMessage("You don't own enough Leader Cards (press enter)\n");
                    client.receiveMessage();
                }


                if (leaderAction < 2  && additionalAction) {
                    String choice;
                    do {
                        client.sendMessage("Do you want to remove a card or to activate one? (remove/activate) ");
                        choice = client.receiveMessage();
                    }while(!choice.equals("remove") && !choice.equals("activate"));
                    if(choice.equals("remove")) {
                        turn.removeLeader();
                        leaderAction++;
                    }

                    if(choice.equals("activate")) {
                        turn.activateLeader();
                        leaderAction++;
                    }

                    additionalAction = false;

                }

            } catch (IOException e) {
                System.out.println(e);
            }
            c = msg;
        } while(/*!c.equals("1") && !c.equals("2") && !c.equals("3") && !c.equals("4")|| */!actionDone /*|| c.equals("")*/);


        //in each turn, "TurnManager"'s main checks if the Vatican Report can be done
        player.getFaithTrack().callVaticanReport(player, game);
        MessageGameManager.personalChanges(client, game.getPlayers().get(actualplayer), game);
        MessageGameManager.generalChanges(client);

    }
}


