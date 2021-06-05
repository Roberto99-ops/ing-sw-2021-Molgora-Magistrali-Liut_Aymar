package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.FaithTrack;
import it.polimi.ingsw.model.*;

import java.io.IOException;

public class TurnManager{

    // inizializzazione di view e model

    /*
    public Player actualplayer;
    public Game game;
    public int l = game.getLonely();
    public ActionStructure aStructure;
    public ActionSignal signal;
     */

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
        Turn turn = new Turn(client);
        turn.setActualplayer(game.getPlayers().get(actualplayer));
        Player player = turn.getActualplayer();

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (game.getClass().equals(SingleGame.class)) {
            ActionSignal signal = new ActionSignal();
            signal.action(SingleGame.getActionStructure().getActionSignal(0));
            //ObserverSingleGame.updateActionStructure(); // è giusto inserirlo qui right ????
        }

        for (int i = 0; i < 10; i++) {
            player.addResourceStrongBox('B');
            player.addResourceStrongBox('G');
            player.addResourceStrongBox('Y');
            player.addResourceStrongBox('P');
            player.addResourceStrongBox('W');
        }
        player.getStorage().getPanel().set(0, 'B');
        player.getStorage().getPanel().set(1, 'W');
        player.getStorage().getPanel().set(2, 'W');
        player.getStorage().getPanel().set(3, 'P');
        player.getStorage().getPanel().set(4, 'P');
        player.getStorage().getPanel().set(5, 'P');


        PlayerMsg msg = new PlayerMsg(player, game);
        client.sendMessage(msg);


        //1)
        //sta roba la posso fare sempre no? indipendentemente che sia partita singola o con piu player

            client.sendMessage("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
            String action = client.receiveMessage();

            try {
                if (action.equals("1")) {
                    turn.shopCard();
                }
                // ObserverGame.updateStorage();
                // ObserverGame.updateStrongbox();
                // ObserverGame.updateDevelopementSpace();

                // CONTROLLER:
                // IN) IL NUMERO DI CARTE DA COMPRARE
                // OUT) RIMUOVE LE RISORSE DI COSTO CARTA DALLA PLANCIA e AGGIUNGE NELLE CARTE SVILUPPO DI PLAYER LE CARTE VOLUTE


                if (action.equals("2")) {
                    turn.buyResource();
                }
                // ObserverGame.updateMarket();

                // CONTROLLER:
                // IN) N^ COLONNA/RIGA DEL MERCATO
                // OUT) CAMBIA IL MERCATO e RITORNA LE RISORSE COMPRATE


                if (action.equals("3")) {
                    do { //2.1)
                        client.sendMessage("Which LeaderCard do you want to enable(0=none)?\n");
                        client.sendMessage(turn.getActualplayer().getLeadercards());
                        String cardChosen = client.receiveMessage();
                        if (!cardChosen.equals("0")) {
                            int card = cardChosen.charAt(0) - 48;  //converts a char into the correspondant int
                            turn.getActualplayer().getLeadercards().getStructure().get(card - 1).getSkill();
                        }

                        //2.2)
                        client.sendMessage("Which DevelopeCard do you want to enable(0=none)?\n");
                        client.sendMessage(turn.getActualplayer().getTopCardsOnBoard());
                        cardChosen = client.receiveMessage();
                        if (!cardChosen.equals("0")) {
                            int card = cardChosen.charAt(0) - 48;  //converts a char into the correspondant int
                            turn.getActualplayer().getTopCardsOnBoard().getStructure().get(card - 1).doProduction(turn.getActualplayer());
                            // ObserverGame.updateStorage();
                            // ObserverGame.updateStrongbox();
                        }

                        PlayerMsg plyrmsg = new PlayerMsg(player, game);
                        client.sendMessage(plyrmsg);

                        client.sendMessage("Do you want to do another production(yes/no)?\n");
                        action = client.receiveMessage();

                    } while (action.equals("yes"));
                }

                PlayerMsg plyrmsg = new PlayerMsg(player, game);
                client.sendMessage(plyrmsg);

            } catch (IOException e) {
                System.out.println(e);
            }

        //Ad ogni turno, effettuo il controllo del Vatican Report
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.callVaticanReport(player, game);
    }
}


// CONTROLLER:
// IN) QUALE CARTA VUOI PRODURRE? LEADER/DEVELOPEMENT e SE CONTINUARE A PRODURRE ---> PRINT
// OUT1)
// OUT2) DO PRODUCTION: RIMUOVE DA STORAGE E AGGIUNGE IN STRONGBOX
