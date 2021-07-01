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
    private static DevelopDecks[] DDecks;
    private static LeaderDeck LDeck;
    private static ActionSignal signal;
    private static Game game;



    /**
     * This method recognizes what type of message is receiving.
     * @param msg: message received
     * @param player: old player instance (maybe not necessary, it depends if we can print
     *                PlayerBoard every time we receive an update)
     * @throws FileNotFoundException if the update cannot be done
     */

    public static void update(Object msg, Player player) throws FileNotFoundException {


         //in case we receive a Market message we update the Market view and print it.


        if(msg.getClass().equals(MarketMsg.class)) {
            market = new Market();
            MarketMsg marketMsg = (MarketMsg)msg;
            market.setMatrix(marketMsg.getMarket().getMatrix());
            market.setExtraBall(marketMsg.getMarket().getExtraBall());
            MarketView marketView = new MarketView(market);
            Utility.Clean();
            marketView.print();
        }


        //in case we receive a player message, we update the PlayerBoard and print it


        if(msg.getClass().equals(PlayerMsg.class)) {
            PlayerMsg playerMsg = (PlayerMsg) msg;
            player.setLeadercards(playerMsg.getPlayer().getLeadercards());
            player.setTrackPosition(playerMsg.getPlayer().getTrackPosition());
            player.setStorage(playerMsg.getPlayer().getStorage());
            player.setName(playerMsg.getPlayer().getName());
            player.setDSpace(playerMsg.getPlayer().getDSpace());
            player.setDevelopmentQuantity(playerMsg.getPlayer().getDevelopmentQuantity());
            player.getDSpace().setMiniDeck1(playerMsg.getPlayer().getDSpace().getMiniDeck1());
            player.getDSpace().setMiniDeck2(playerMsg.getPlayer().getDSpace().getMiniDeck2());
            player.getDSpace().setMiniDeck3(playerMsg.getPlayer().getDSpace().getMiniDeck3());
            player.setNumber(playerMsg.getPlayer().getNumber());
            player.setPv(playerMsg.getPlayer().getPv());
            player.setStrongBox(playerMsg.getPlayer().getStrongBox());
            player.setSkill1(playerMsg.getPlayer().getSkill1());
            player.setSkill2(playerMsg.getPlayer().getSkill2());

            if(playerMsg.getSingleOrNot())
                game = new SingleGame();
            else
                game = new Game();

            for (int i = 0; i < playerMsg.getPlayers().size(); i++)
                game.getPlayers().add(playerMsg.getPlayers().get(i));
            Game.setVR(playerMsg.getVR());

            if(playerMsg.getSingleOrNot()){
                SingleGame.getLorenzo().setNumber(playerMsg.getLorenzo());
                SingleGame.getActionStructure().setAS_Counter(playerMsg.getSignal());
            }


            Playerboard board = new Playerboard(player, game);
            Utility.Clean();
            board.Print();
        }


         //in case we receive a DevelopDeck message, we update the DevelopDeck and print it.


        if(msg.getClass().equals(DevelopDecksMsg.class)) {
            DevelopDecksMsg DDeckMsg = (DevelopDecksMsg) msg;
            DDecks = DDeckMsg.getDecks();
            DevelopDecksView DDecksView = new DevelopDecksView(DDecks);
            Utility.Clean();
            DDecksView.print();
        }

         //in case we receive a LeaderDeck message, we print the 4 LeaderCards (we receive it only at the start of the game)


        if(msg.getClass().equals(LeaderDeckMsg.class)) {
            LeaderDeckMsg LDeckMsg = (LeaderDeckMsg) msg;
            LDeck = LDeckMsg.getDeck();
            LeaderChooseView LView = new LeaderChooseView(LDeck);
            Utility.Clean();
            LView.print();
        }


         //in case we receive a ActionSignal message, we update the ActionSignal


        if(msg.getClass().equals(ActionSignalMsg.class)) {   //maybe useless
           ActionSignalMsg ActionMsg = (ActionSignalMsg) msg;
           signal = new ActionSignal();
           signal.setNumber(ActionMsg.getAction().getNumber());
        }
    }
}
