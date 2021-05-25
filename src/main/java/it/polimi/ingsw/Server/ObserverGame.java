package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.MarketMessage;
import it.polimi.ingsw.model.*;

public class ObserverGame extends Player {

    public static void updateMarket() {
        Market actualMarket = Game.getMarket();
        System.out.println("marketmessage");
        ObservableGame.notifyAllObservers();

    }

    public static void updateDevelopementDecks() {
        for (int i = 0; i < 12; i++) {
       // DevelopeDecks[] actualDevelopementDecks = Game.getDevelopedecks(i);
        System.out.println("developedeckmessage");
            ObservableGame.notifyAllObservers();
        }
    }

    public static void updateLeaderDeck() {
        LeaderDeck actualLeaderDeck = Game.getLeaderdeck();
        System.out.println("leaderdeckmessage");
        ObservableGame.notifyAllObservers();
    }

    public void updateStorage() {
        Storage actualStorage = this.getStorage();
        System.out.println("storagemessage");
        ObservableGame.personalObservers(this);
    }

    public void updateStrongbox() {
        StrongBox actualStrongbox = this.getStrongBox();
        System.out.println("strongboxmessage");
        ObservableGame.personalObservers(this);
    }


    public void updateDevelopementSpace() {
        DevelopementSpace actualDevelopementSpace = this.getDSpace();
        System.out.println("strongboxmessage");
        ObservableGame.personalObservers(this);
    }

    public static void updateTimer_VR () {
        int actualTimer_VR = Game.getTimer_VR();
        System.out.println("Timer_VR:" + actualTimer_VR );
        ObservableGame.notifyAllObservers();
    }

    public static void updateVR () {
        int actualVR = Game.getVR();
        System.out.println("VR:" + actualVR );
        ObservableGame.notifyAllObservers();
    }


}
