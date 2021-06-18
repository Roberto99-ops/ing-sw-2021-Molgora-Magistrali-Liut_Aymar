package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;

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
    public static void update(Object msg, Player player) throws FileNotFoundException {
        /**
         * in case we receive a Market message we update the Market view and print it.
         */
        if(msg.getClass().equals(MarketMsg.class)) {
            market = new Market();
            MarketMsg marketMsg = (MarketMsg)msg;
            market.setMatrix(marketMsg.getMarket().getMatrix());
            market.setExtraball(marketMsg.getMarket().getExtraball());
            MarketView marketView = new MarketView(market);
            marketView.print();
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
            player.getDSpace().setMinideck1(playerMsg.getPlayer().getDSpace().getMinideck1());
            player.getDSpace().setMinideck2(playerMsg.getPlayer().getDSpace().getMinideck2());
            player.getDSpace().setMinideck3(playerMsg.getPlayer().getDSpace().getMinideck3());
            player.setNumber(playerMsg.getPlayer().getNumber());
            player.setPv(playerMsg.getPlayer().getPv());
            player.setStrongBox(playerMsg.getPlayer().getStrongBox());
            player.setSkill1(playerMsg.getPlayer().getSkill1());
            player.setSkill2(playerMsg.getPlayer().getSkill2());

            if(playerMsg.getSingleorNot())
                game = new SingleGame();
            else
                game = new Game();

            for (int i = 0; i < playerMsg.getPlayers().size(); i++)
                game.getPlayers().add(playerMsg.getPlayers().get(i));
            Game.setVR(playerMsg.getVR());

            if(playerMsg.getSingleorNot()){
                SingleGame.getLorenzo().setNumber(playerMsg.getLorenzo());
                SingleGame.getActionStructure().setAS_Counter(playerMsg.getSignal());
            }


            Playerboard board = new Playerboard(player, game);
            board.Print();
        }

        /**
         * in case we receive a DDeck message we update it and print.
         */
        if(msg.getClass().equals(DevelopeDecksMsg.class)) {
            DevelopeDecksMsg DDeckMsg = (DevelopeDecksMsg) msg;
            DDecks = DDeckMsg.getDecks();
            DevelopeDecksView DDecksView = new DevelopeDecksView(DDecks);
            DDecksView.print();
        }

        /**
         * in case we receive a LDeck message we and print (we receive it only at the start of the game)
         */
        if(msg.getClass().equals(LeaderDeckMsg.class)) {
            LeaderDeckMsg LDeckMsg = (LeaderDeckMsg) msg;
            LDeck = LDeckMsg.getDeck();
            LeaderChooseView LView = new LeaderChooseView(LDeck);
            LView.print();
        }

        /**
         * in case we receive a ActionSignal message we update it and print it.
         */
        if(msg.getClass().equals(ActionSignalMsg.class)) {
           ActionSignalMsg ActionMsg = (ActionSignalMsg) msg;
           signal = new ActionSignal();
           signal.setNumber(ActionMsg.getAction().getNumber());
        }
    }
}
