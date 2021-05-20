package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.MarketMessage;
import it.polimi.ingsw.model.*;

public class ObserverGame implements Observer {

    private Player playerObservable;

    public void updateMarket() {
        Market actualMarket = Game.getMarket();
        System.out.println("marketmessage");

    }

    public void updateDevelopementDecks() {
        for (int i = 0; i < 12; i++) {
       // DevelopeDecks[] actualDevelopementDecks = Game.getDevelopedecks(i);
        System.out.println("developedeckmessage"); }
    }

    public void updateLeaderDeck() {
        LeaderDeck actualLeaderDeck = Game.getLeaderdeck();
        System.out.println("leaderdeckmessage");
    }

    public void updateStorage(Player actualplayer) {
        Storage actualStorage = Game.getPlayers().get(actualplayer.getNumber()).getStorage();
        System.out.println("storagemessage");
    }

    public void updateStrongbox(Player actualplayer) {
        StrongBox actualStrongbox = Game.getPlayers().get(actualplayer.getNumber()).getStrongBox();
        System.out.println("strongboxmessage");
    }


    public void updateDevelopementSpace(Player actualplayer) {
        DevelopementSpace actualDevelopementSpace = Game.getPlayers().get(actualplayer.getNumber()).getDSpace();
        System.out.println("strongboxmessage");
    }

    public void updateTimer_VR () {
        int actualTimer_VR = Game.getTimer_VR();
        System.out.println("Timer_VR:" + actualTimer_VR );
    }

    public void updateVR () {
        int actualVR = Game.getVR();
        System.out.println("VR:" + actualVR );
    }


}
