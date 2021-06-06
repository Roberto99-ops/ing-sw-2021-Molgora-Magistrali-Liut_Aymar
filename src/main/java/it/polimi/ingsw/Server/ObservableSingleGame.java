package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Game;

import java.util.Observer;

public class ObservableSingleGame {

    private ClientHandler clh;

    private ObserverSingleGame observerSingleGame;

    public  ObserverSingleGame getObserverSingleGame() {
        return observerSingleGame;
    }

    public void setObserverSingleGame(ObserverSingleGame observerSingleGame) {
        this.observerSingleGame = observerSingleGame;
    }


    public void notifySingleGameObserver (ClientHandler client, Game game) throws Exception {

        clh = client;

        observerSingleGame.updateDevelopementDecks(clh);
        observerSingleGame.updateLeaderDeck(clh);
        observerSingleGame.updateMarket(clh);
        /*observerSingleGame.updateTimer_VR(clh);
        observerSingleGame.updateVR(clh);
        observerSingleGame.updateDevelopementSpace(clh);
        observerSingleGame.updateStorage(clh);
        observerSingleGame.updateStrongbox(clh);
        observerSingleGame.updateLeaderCards(clh);
        observerSingleGame.updateDevelopementQuantity(clh);
        observerSingleGame.updateSkill1(clh);
        observerSingleGame.updateSkill2(clh);
        observerSingleGame.updatePV(clh);
        observerSingleGame.updateFaithTrack(clh);*/
        observerSingleGame.updatePlayerBoard(clh, game);

    }

}



