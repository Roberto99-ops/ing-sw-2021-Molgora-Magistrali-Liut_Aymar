package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.ObservableGame;
import it.polimi.ingsw.Server.ObservableSingleGame;
import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.FaithTrack;
import it.polimi.ingsw.model.*;

import java.io.IOException;

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
        turn.setActualplayer(game.getPlayers().get(actualplayer));
        ObserverGame player = turn.getActualplayer();
        String reception;

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (game.getClass().equals(SingleGame.class)) {
            ActionSignal signal = new ActionSignal();
            signal.action(SingleGame.getActionStructure().getActionSignal(0));
            // ObserverSingleGame.updateActionStructure(); // è giusto inserirlo qui right ????
        }

        player.updatePlayerBoard(client, game);

        //1)
        //sta roba la posso fare sempre no? indipendentemente che sia partita singola o con piu player

        client.sendMessage("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
        int action = Integer.parseInt(client.receiveMessage());

        try {
            if (action == 1) turn.shopCard();
            //game.getPlayers().get(actualplayer).updateStorage(client);
            //game.getPlayers().get(actualplayer).updateStrongbox(client);
            //game.getPlayers().get(actualplayer).updateDevelopementSpace(client);

            // CONTROLLER:
            // IN) IL NUMERO DI CARTE DA COMPRARE
            // OUT) RIMUOVE LE RISORSE DI COSTO CARTA DALLA PLANCIA e AGGIUNGE NELLE CARTE SVILUPPO DI PLAYER LE CARTE VOLUTE


            if (action == 2) {
                turn.buyResource();
                game.getPlayers().get(actualplayer).updateMarket(client);
                game.getPlayers().get(actualplayer).updatePlayerBoard(client, game);

            }

            // CONTROLLER:
            // IN) N^ COLONNA/RIGA DEL MERCATO
            // OUT) CAMBIA IL MERCATO e RITORNA LE RISORSE COMPRATE


            if (action == 3) {
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
                        //game.getPlayers().get(actualplayer).updateStorage(client);
                        //game.getPlayers().get(actualplayer).updateStrongbox(client);
                    }

                    client.sendMessage("Do you want to do another production(yes/no)?\n");
                    reception = client.receiveMessage();

                } while (reception.equals("yes"));
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        //Ad ogni turno, effettuo il controllo del Vatican Report e
        // notifico tutti gli observer dei cambiamenti avvenuti durante il turno

        FaithTrack faithTrack = new FaithTrack();
        faithTrack.callVaticanReport(player, game);
        //player.updateFaithTrack(client);

        ObservableGame.personalObservers(client, game.getPlayers().get(actualplayer));
        ObservableGame.notifyAllObservers(client);

    }
}


// CONTROLLER:
// IN) QUALE CARTA VUOI PRODURRE? LEADER/DEVELOPEMENT e SE CONTINUARE A PRODURRE ---> PRINT
// OUT1)
// OUT2) DO PRODUCTION: RIMUOVE DA STORAGE E AGGIUNGE IN STRONGBOX