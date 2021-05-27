package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ObservableGame;
import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.Server.ObserverSingleGame;
import it.polimi.ingsw.model.ActionSignal;
import it.polimi.ingsw.model.FaithTrack;
import it.polimi.ingsw.model.*;

import java.io.IOException;

public class TurnManager extends Turn {

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

    public void main() throws Exception {
        int action = 0;
        char again = 'n';
        int card = 0;

        //Ad ogni turno, effettuo il controllo del Vatican Report
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.VaticanReport(actualplayer, game);

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (l == 1) {
            signal.Action(aStructure.PickSignal());
            ObserverSingleGame.updateActionStructure(); // è giusto inserirlo qui right ????

        }

        //1)
        if (l == 0) {

            System.out.println("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
            // CLIENTHENDLER


            try { //CLIENTHENDLER
                action = System.in.read();
                if (action == 1) this.ShopCard(game);
                // ObserverGame.updateStorage();
                // ObserverGame.updateStrongbox();
                // ObserverGame.updateDevelopementSpace();

                // CONTROLLER:
                // IN) IL NUMERO DI CARTE DA COMPRARE
                // OUT) RIMUOVE LE RISORSE DI COSTO CARTA DALLA PLANCIA e AGGIUNGE NELLE CARTE SVILUPPO DI PLAYER LE CARTE VOLUTE


                if (action == 2) this.Buyresource();

                // ObserverGame.updateMarket();

                // CONTROLLER:
                // IN) N^ COLONNA/RIGA DEL MERCATO
                // OUT) CAMBIA IL MERCATO e RITORNA LE RISORSE COMPRATE


                if (action == 3) {
                    do { //2.1)
                        System.out.println("Which LeaderCard do you want to enable(0=none)?\n");
                        this.actualplayer.getLeadercards().Print();
                        try {
                            card = System.in.read();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        if (card > 0) {
                            this.actualplayer.getLeadercards().getStructure().get(card - 1).getSkill();
                        }

                        //2.2)
                        System.out.println("Which DevelopeCard do you want to enable(0=none)?\n"); // CLIENTHENDLER
                        this.actualplayer.getTopCardsOnBoard().Print(); //l'arraylist di carte viene stampato
                        try {
                            card = System.in.read();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        //prendo la carta nella posizione i-1 nell'arraylist ed eseguo la sua produzione
                        if (card > 0) {
                            this.actualplayer.getTopCardsOnBoard().getStructure().get(card - 1).DoProduction(this.actualplayer);
                            // ObserverGame.updateStorage();
                            // ObserverGame.updateStrongbox();
                        }

                        System.out.println("Do you want to do another production(y/n)?\n"); // CLIENTHENDLER
                        try {
                            again = (char) System.in.read();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    } while (again == 'y');
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}


// CONTROLLER:
// IN) QUALE CARTA VUOI PRODURRE? LEADER/DEVELOPEMENT e SE CONTINUARE A PRODURRE ---> PRINT
// OUT1)
// OUT2) DO PRODUCTION: RIMUOVE DA STORAGE E AGGIUNGE IN STRONGBOX
