package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.lang.ref.Cleaner;

/**
 * this class recognize what type of message the client received;
 * then update the view.
 */
public class CliManager {

    private static Market market;
    private static DevelopeDecks DDecks;
    private static LeaderDeck LDeck;
    private static ActionSignal signal;

    public static void Update(Object msg, Player player) throws FileNotFoundException {
        if(msg.getClass().equals(MarketMsg.class)) {
            market = new Market();
            MarketMsg marketMsg = (MarketMsg)msg;
            market.setMatrix(marketMsg.getMarket().getMatrix());
            market.setExtraball(marketMsg.getMarket().getExtraball());
            MarketView marketView = new MarketView(market);
            marketView.Print();
        }

        /*if(msg.getClass().equals(StorageMsg.class)) {
            StorageMsg storageMsg = (StorageMsg) msg;
            player.setStorage(storageMsg.getStorage());
        }
        if(msg.getClass().equals(FaithTrackMsg.class)) {
            FaithTrackMsg faithTrackMsg = (FaithTrackMsg) msg;
            // player.setTrackposition();   implementare meglio faithtrackmsg, deve inviare un int con dentro trackposition
        }
        if(msg.getClass().equals(LeaderDeckMsg.class)) {
            LeaderDeckMsg leaderDeckMsg = (LeaderDeckMsg) msg;
            player.setLeadercards(leaderDeckMsg.getDeck());
        }*/
        if(msg.getClass().equals(PlayerMsg.class)) {
            PlayerMsg playerMsg = (PlayerMsg) msg;
            player.setLeadercards(playerMsg.getPlayer().getLeadercards());
            player.setTrackposition(playerMsg.getPlayer().getTrackposition());
            player.setStorage(playerMsg.getPlayer().getStorage());
            player.setName(playerMsg.getPlayer().getName());
            player.setDSpace(playerMsg.getPlayer().getDSpace());
            player.setDevelopementquantity(playerMsg.getPlayer().getDevelopementquantity());
            player.setMinideck1(playerMsg.getPlayer().getMinideck1());
            player.setMinideck2(playerMsg.getPlayer().getMinideck2());
            player.setMinideck3(playerMsg.getPlayer().getMinideck3());
            player.setNumber(playerMsg.getPlayer().getNumber());
            player.setPv(playerMsg.getPlayer().getPv());

            Playerboard board = new Playerboard(player);
            board.Print();
        }
        //...

    }
}
