package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.*;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.ArrayList;


/*

rappresenta il client dentro al server
obeserver observable su model internet, comunica al controller le scelte dell'utente.
una classe che osserva il model e che se la virtual view riceve le notifiche lo sa


client - clienthendler - controller - model - virtualviewvedemodelmodificato -

clienthendler grazie alla virtual view aggiorna l'utente usando pattern state per sveglire il tipo di messaggio
da mandare (json o stringa o...) se si possono inviare file facciamo file json (oppure istanze di classi messaggio?)
per aggiornamenti del tipo planciastringhe per messaggio "video" esempio su chi ha vinto

*/


public class GameHandler extends Player {


    public void updateMarket(ClientHandler clh) throws IOException {
        Market actualMarket = Game.getMarket();
        MarketMsg msg = new MarketMsg(actualMarket);
        clh.sendMessage(msg);

    }


    public void updateDevelopementDecks(ClientHandler clh) throws IOException {
        for (int i = 0; i < 12; i++) {
            DevelopeDecks[] actualDevelopeDecks = new DevelopeDecks[12];
            for (int j = 0; j < 12; j++)
                actualDevelopeDecks[i] = Game.getDevelopedecks(i);
            DevelopeDeckMsg msg = new DevelopeDeckMsg(actualDevelopeDecks);
            clh.sendMessage(msg);
        }

    }

    public void updateLeaderDeck(ClientHandler clh) throws IOException {
        LeaderDeck actualLeaderDeck = Game.getLeaderdeck();
        LeaderDeckMsg msg = new LeaderDeckMsg(actualLeaderDeck);
        clh.sendMessage(msg);
    }

    public void updatePlayerBoard(ClientHandler clh, Game game) throws IOException {
        PlayerMsg msg = new PlayerMsg(this, game);
        clh.sendMessage(msg);
    }








/*
    public void updateStorage(ClientHandler clh) throws IOException {
        Storage actualStorage = this.getStorage();
        StorageMsg msg = new StorageMsg(actualStorage);
        clh.sendMessage(msg);
    }

    public void updateStrongbox(ClientHandler clh) throws IOException {
        StrongBox actualStrongbox = this.getStrongBox();
        StrongboxMsg msg = new StrongboxMsg(actualStrongbox.getStructure());
        clh.sendMessage(msg);
    }


    public void updateDevelopementSpace(ClientHandler clh) throws IOException {
        DevelopementSpace actualDevelopementSpace = this.getDSpace();


    }

    public static void updateTimer_VR (ClientHandler clh) throws IOException {
        int actualTimer_VR = Game.getTimer_VR();
        clh.sendMessage("new Timer_VR" + actualTimer_VR);

    }

    public static void updateVR (ClientHandler clh) throws IOException {
        int actualVR = Game.getVR();
        clh.sendMessage("new Timer_VR" + actualVR);

    }

   public void updatePV (ClientHandler clh) throws IOException {
       int actualPV = this.getPv();
       clh.sendMessage("new PV" + actualPV);
   }


    public void updateLeaderCards (ClientHandler clh) throws IOException {
        LeaderDeck actualLeaderDeck = this.getLeadercards();
        LeaderDeckMsg msg = new LeaderDeckMsg(actualLeaderDeck);
        clh.sendMessage(msg);

    }


    public void updateSkill1 (ClientHandler clh) throws IOException {
        int actualSkiil1 = this.getSkill1();
        clh.sendMessage("new Skiil1" + actualSkiil1);
    }

    public void updateSkill2 (ClientHandler clh) throws IOException {
        int actualSkiil2 = this.getSkill2();
        clh.sendMessage("new Skiil2" + actualSkiil2);
    }

    public void updateDevelopementQuantity (ClientHandler clh) throws IOException {
        int actualDQ = this.getSkill2();
        clh.sendMessage("new DevelopementQuantity" + actualDQ);
    }

    public void updateFaithTrack (ClientHandler clh) throws IOException {
        FaithTrack actualFaithtrack = this.getFaithTrack();
        clh.sendMessage(actualFaithtrack);
    }
*/

}
