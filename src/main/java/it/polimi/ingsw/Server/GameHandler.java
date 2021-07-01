package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

import java.io.IOException;



public class GameHandler extends Player {


    /**
     * Sends to the client the new Market
     * @param clh: the clientHandler (Server) that will send the info to the Client
     */

    public void updateMarket(ClientHandler clh) throws IOException {
        Market actualMarket = Game.getMarket();
        MarketMsg msg = new MarketMsg(actualMarket);
        clh.sendMessage(msg);

    }



    /**
     * Sends to the client the new DevelopmentDecks
     * @param clh: the clientHandler (Server) that will send the info to the Client
     */

    public void updateDevelopementDecks(ClientHandler clh) throws IOException {
        for (int i = 0; i < 12; i++) {
            DevelopDecks[] actualDevelopDecks = new DevelopDecks[12];
            for (int j = 0; j < 12; j++)
                actualDevelopDecks[i] = Game.getDevelopDecks(i);
            DevelopDecksMsg msg = new DevelopDecksMsg(actualDevelopDecks);
            clh.sendMessage(msg);
        }

    }



    /**
     * Sends to the client the new LeaderDeck
     * @param clh: the clientHandler (Server) that will send the info to the Client
     */

    public void updateLeaderDeck(ClientHandler clh) throws IOException {
        LeaderDeck actualLeaderDeck = Game.getLeaderDeck();
        LeaderDeckMsg msg = new LeaderDeckMsg(actualLeaderDeck);
        clh.sendMessage(msg);
    }



    /**
     * Sends to the client the new PlayerBoard
     * @param clh: the clientHandler (Server) that will send the info to the Client
     * @param game: the actual game I'm playing
     */

    public void updatePlayerBoard(ClientHandler clh, Game game) throws IOException {
        PlayerMsg msg = new PlayerMsg(this, game);
        clh.sendMessage(msg);
    }

}