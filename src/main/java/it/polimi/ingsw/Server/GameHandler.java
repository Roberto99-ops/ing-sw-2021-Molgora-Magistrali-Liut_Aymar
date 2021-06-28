package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

import java.io.IOException;



public class GameHandler extends Player {


    /**
     * send to the client the new market
     * @param clh
     */

    public void updateMarket(ClientHandler clh) throws IOException {
        Market actualMarket = Game.getMarket();
        MarketMsg msg = new MarketMsg(actualMarket);
        clh.sendMessage(msg);

    }



    /**
     * send to the client the new developementdecks
     * @param clh
     */

    public void updateDevelopementDecks(ClientHandler clh) throws IOException {
        for (int i = 0; i < 12; i++) {
            DevelopeDecks[] actualDevelopeDecks = new DevelopeDecks[12];
            for (int j = 0; j < 12; j++)
                actualDevelopeDecks[i] = Game.getDevelopedecks(i);
            DevelopeDecksMsg msg = new DevelopeDecksMsg(actualDevelopeDecks);
            clh.sendMessage(msg);
        }

    }



    /**
     * send to the client the new leaderdeck
     * @param clh
     */

    public void updateLeaderDeck(ClientHandler clh) throws IOException {
        LeaderDeck actualLeaderDeck = Game.getLeaderdeck();
        LeaderDeckMsg msg = new LeaderDeckMsg(actualLeaderDeck);
        clh.sendMessage(msg);
    }



    /**
     * send to the client the new playerboard
     * @param clh
     * @param game
     */

    public void updatePlayerBoard(ClientHandler clh, Game game) throws IOException {
        PlayerMsg msg = new PlayerMsg(this, game);
        clh.sendMessage(msg);
    }

}