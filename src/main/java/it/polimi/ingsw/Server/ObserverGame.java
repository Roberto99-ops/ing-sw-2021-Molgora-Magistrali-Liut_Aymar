package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.MarketMsg;
import it.polimi.ingsw.model.*;


/*

rappresenta il client dentro al server
obeserver observable su model internet, comunica al controller le scelte dell'utente.
una classe che osserva il model e che se la virtual view riceve le notifiche lo sa


client - clienthendler - controller - model - virtualviewvedemodelmodificato -

clienthendler grazie alla virtual view aggiorna l'utente usando pattern state per sveglire il tipo di messaggio
da mandare (json o stringa o...) se si possono inviare file facciamo file json (oppure istanze di classi messaggio?)
per aggiornamenti del tipo planciastringhe per messaggio "video" esempio su chi ha vinto

*/


public class ObserverGame extends Player {

    public static void updateMarket() {
        Market actualMarket = Game.getMarket();
        System.out.println("marketmessage");


    }

    public static void updateDevelopementDecks() {
        for (int i = 0; i < 12; i++) {
            DevelopeDecks actualDevelopementDecks = Game.getDevelopedecks(i);
            System.out.println("developedeckmessage n #" + i);
        }

    }

    public static void updateLeaderDeck() {
        LeaderDeck actualLeaderDeck = Game.getLeaderdeck();
        System.out.println("leaderdeckmessage");

    }

    public void updateStorage() {
        Storage actualStorage = this.getStorage();
        this.getStorage().printPanel();
        System.out.println("storagemessage");

    }

    public void updateStrongbox() {
        StrongBox actualStrongbox = this.getStrongBox();
        this.getStrongBox().printAll();
        System.out.println("strongboxmessage");

    }


    public void updateDevelopementSpace() {
        DevelopementSpace actualDevelopementSpace = this.getDSpace();
        System.out.println("developementspacemessage");

    }

    public static void updateTimer_VR () {
        int actualTimer_VR = Game.getTimer_VR();
        System.out.println("Timer_VR:" + actualTimer_VR );

    }

    public static void updateVR () {
        int actualVR = Game.getVR();
        System.out.println("VR:" + actualVR );

    }


}
