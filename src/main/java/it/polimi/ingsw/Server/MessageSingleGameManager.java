package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Game;

public class MessageSingleGameManager {

    private ClientHandler clh;

    private SingleGameHandler singleGameHendler;

    public SingleGameHandler getSingleGameHendler() {
        return singleGameHendler;
    }

    public void setSingleGameHendler(SingleGameHandler singleGameHendler) {
        this.singleGameHendler = singleGameHendler;
    }


    public void notifySingleGameObserver (ClientHandler client, Game game) throws Exception {

        clh = client;

        singleGameHendler.updateDevelopementDecks(clh);
        singleGameHendler.updateLeaderDeck(clh);
        singleGameHendler.updateMarket(clh);
        singleGameHendler.updatePlayerBoard(clh, game);

    }

}



