package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageGameManager;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.FaithTrack;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class TurnManager {

    /**
     * turnmanager manages all the turn.
     * 1)asks us what do we want to do
     * 2)in case we choose to active a production, we join a while that active all the production we want
     * 2.1)it lists us all our LeaderCards so we can choose to active one of that
     * 2.2) do the same with our Developecards
     * then the turn ends
     */

    //actualplayer è la posizione del gocatore attuale nell'array dei giocatori di game
    public void main(ClientHandler client, Game game, int actualplayer) throws Exception {
        Turn turn = new Turn(client, game);
        //turn.setActualplayer(game.getPlayers().get(actualplayer));
        GameHandler player = turn.getActualplayer();
        String reception;
        int action;
        int leaderaction = 0;
        boolean additionalAction = false;

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (game.getClass().equals(SingleGame.class)) {
            SingleGame.getActionStructure().pickSignal();
            player.updatePlayerBoard(client, game);
            if(SingleGame.getLorenzo().getNumber() == 24) return;
        }
        else
            player.updatePlayerBoard(client, game);
        do {
            //1)

            client.sendMessage("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n\t4)Do a Leader action\n");
            action = Integer.parseInt(client.receiveMessage());

            try {
                if (action == 1) {
                    turn.shopCard();
                    client.sendMessage("clean screen");
                    game.getPlayers().get(actualplayer).updatePlayerBoard(client, game);
                }

                // CONTROLLER:
                // IN) IL NUMERO DI CARTE DA COMPRARE
                // OUT) RIMUOVE LE RISORSE DI COSTO CARTA DALLA PLANCIA e AGGIUNGE NELLE CARTE SVILUPPO DI PLAYER LE CARTE VOLUTE


                if (action == 2) {
                    turn.buyResource();
                    client.sendMessage("clean screen");
                    game.getPlayers().get(actualplayer).updateMarket(client);
                    client.sendMessage("the market is changed (press any key)");
                    client.receiveMessage();
                    client.sendMessage("clean screen");
                    game.getPlayers().get(actualplayer).updatePlayerBoard(client, game);
                }

                // CONTROLLER:
                // IN) N^ COLONNA/RIGA DEL MERCATO
                // OUT) CAMBIA IL MERCATO e RITORNA LE RISORSE COMPRATE


                if (action == 3) {
                    do { //2.1)
                        if (player.getSkill1() == 1 || player.getSkill2() == 1) {
                            client.sendMessage("Which LeaderCard do you want to enable(form up to down 0 1. enter=none)?\n");
                            //player.updateLeaderDeck(client);
                            String cardChosen = client.receiveMessage();
                            if (!cardChosen.equals("")) {
                                int card = cardChosen.charAt(0) - 48;  //converts a char into the correspondant int
                                if (card == 0 && player.getSkill1() == 1)
                                    turn.getActualplayer().getLeadercards().getStructure().get(0).getSkill();
                                if (card == 1 && player.getSkill2() == 1)
                                    turn.getActualplayer().getLeadercards().getStructure().get(1).getSkill();
                            }
                        }

                        //2.2)
                        client.sendMessage("Which DevelopeCard do you want to enable(form left to right 0 1 2. enter=none)?\n");
                        //player.updateDevelDeck(client);
                        String cardChosen = client.receiveMessage();
                        if (!cardChosen.equals("")) {
                            int card = cardChosen.charAt(0) - 48;  //converts a char into the correspondant int
                            DevelopeDecks playercards = turn.getActualplayer().getTopCards();
                            if (card < playercards.getStructure().size())
                                playercards.getStructure().get(card).doProduction(client, game);
                            else {
                                client.sendMessage("You don't own that card.( press enter )");
                                client.receiveMessage();
                            }
                            //game.getPlayers().get(actualplayer).updateStorage(client);
                            //game.getPlayers().get(actualplayer).updateStrongbox(client);
                        }
                        player.updatePlayerBoard(client, game);
                        client.sendMessage("Do you want to do another production(yes/no)?\n");
                        reception = client.receiveMessage();

                    } while (reception.equals("yes"));
                }

                //in case we have done only 0 or 1 leaderactions during our turn, we can do another one at the end of the turn
                if(leaderaction<2) {
                    client.sendMessage("Do you want to do a Leader action? (yes/no) ");
                    reception = client.receiveMessage();
                    if(reception.equals("yes")) additionalAction = true;
                }

                //we activate a leader card if possible
                if ((action == 4 && leaderaction < 2) || additionalAction) {
                    leaderaction++;
                    client.sendMessage("Do you want to remove a card or to activate one? (remove/activate) ");
                    String choice = client.receiveMessage();

                    if(choice.equals("remove"))
                        turn.removeLeader();

                    if(choice.equals("activate"))
                        turn.activateLeader();

                    player.updatePlayerBoard(client, game);
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }while(action != 1 || action != 2 || action != 3);
        //Ad ogni turno, effettuo il controllo del Vatican Report e
        // notifico tutti gli observer dei cambiamenti avvenuti durante il turno

        FaithTrack faithTrack = new FaithTrack();
        faithTrack.callVaticanReport(player, game);
        //player.updateFaithTrack(client);

        MessageGameManager.personalChanges(client, game.getPlayers().get(actualplayer), game);
        MessageGameManager.generalChanges(client);

        return;
    }
}


// CONTROLLER:
// IN) QUALE CARTA VUOI PRODURRE? LEADER/DEVELOPEMENT e SE CONTINUARE A PRODURRE ---> PRINT
// OUT1)
// OUT2) DO PRODUCTION: RIMUOVE DA STORAGE E AGGIUNGE IN STRONGBOX