package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.lang.ref.Cleaner;

/**
 * this class recognizes what type of message the client received;
 * then updates the view.
 */
public class CliManager {

    private static Market market;
    private static DevelopeDecks[] DDecks;
    private static LeaderDeck LDeck;
    private static ActionSignal signal;
    private static Game game;

    /**
     * this method recognizes what type of message is receiving.
     * @param msg: msg received
     * @param player: old player istance (maybe not necessary, it depends if we can print
     *              playerboard every time we receive an update)
     * @throws FileNotFoundException
     */
    public static void Update(Object msg, Player player) throws FileNotFoundException {
        /**
         * in case we receive a Market message we update the Market view and print it.
         */
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
        /**
         * in case we receive a player message we update the playerboard and print it
         */
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

            if(playerMsg.getSingleorNot())
                game = new SingleGame();
            else
                game = new Game();

            for (int i = 0; i < playerMsg.getPlayers().size(); i++)
                game.getPlayers().add(playerMsg.getPlayers().get(i));

            if(playerMsg.getSingleorNot()){
                SingleGame.getLorenzo().setNumber(playerMsg.getLorenzo());
               // SingleGame.getActionStructure().get.getActionSignal(0) = playerMsg.getSignal();i think this is to fix, it shouldn't work but it does
            }


            Playerboard board = new Playerboard(player, game);
            board.Print();
        }

        /**
         * in case we receive a DDeck message we update it and print.
         */
        if(msg.getClass().equals(DevelopeDeckMsg.class)) {
            DevelopeDeckMsg DDeckMsg = (DevelopeDeckMsg) msg;
            //perchè è stato modificato il messaggio developedecks?
            DDecks = DDeckMsg.getDecks();
            /*for (int i = 0; i < 12; i++) {
                DDecks[i] = DDeckMsg.getDecks();
                DDecks[i].setStructure(DDeckMsg.getDeck()[i].getStructure());
            }*/
            DevelopeDecksView DDecksView = new DevelopeDecksView(DDecks);
            DDecksView.Print();
        }

        /**
         * in case we receive a LDeck message we and print (we receive it only at the start of the game)
         */
        if(msg.getClass().equals(LeaderDeckMsg.class)) {
            LeaderDeckMsg LDeckMsg = (LeaderDeckMsg) msg;
            LDeck = new LeaderDeck();
            LDeck.setStructure(LDeckMsg.getDeck().getStructure());
            LeaderChooseView LView = new LeaderChooseView(LDeck);
            LView.Print();
        }

        /**
         * in case we receive a ActionSignal message we update it and print it.
         */
        if(msg.getClass().equals(ActionSignalMsg.class)) {
           ActionSignalMsg ActionMsg = (ActionSignalMsg) msg;
           signal = new ActionSignal();
           signal.setNumber(ActionMsg.getAction().getNumber()); //credo ci sia bisogno di un json per i signal e tutto, come per le carte
           //actionview, che poi alla fine è un'estensione di playerboard FORSE
        }
    }
}
