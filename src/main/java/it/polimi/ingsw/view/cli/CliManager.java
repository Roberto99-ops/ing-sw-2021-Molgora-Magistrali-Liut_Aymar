package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

/**
 * this class recognize what type of message the client received;
 * then update the view.
 */
public class CliManager {

    private static Market market;
    private static DevelopeDecks DDecks;
    private static LeaderDeck LDeck;
    private static ActionSignal signal;

    public static void Update(Object msg, Player player)
    {
        if(msg.getClass().equals(MarketMsg.class)) {
            market = new Market();
            MarketMsg marketMsg = (MarketMsg)msg;
            market.setMatrix(marketMsg.getMarket().getMatrix());
            market.setExtraball(marketMsg.getMarket().getExtraball());
        }

        if(msg.getClass().equals(StorageMsg.class)) {
            StorageMsg storageMsg = (StorageMsg) msg;
            player.setStorage(storageMsg.getStorage());
        }
        if(msg.getClass().equals(FaithTrackMsg.class)) {
            FaithTrackMsg faithTrackMsg = (FaithTrackMsg) msg;
           // player.setTrackposition();   implementare meglio faithtrackmsg, deve inviare un int con dentro trackposition
        }
        if(msg.getClass().equals(LeaderDeck.class)) {
            LeaderDeckMsg leaderDeckMsg = (LeaderDeckMsg) msg;
            player.setLeadercards(leaderDeckMsg.getDeck());
        }
        //...

    }
}
