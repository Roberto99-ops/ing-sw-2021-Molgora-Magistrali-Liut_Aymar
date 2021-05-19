package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.LeaderDeck;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

public abstract class StorageObs {

    public ArrayList<Character> getPanelView (Player actualplayer) { // leggo in input il deck che voglio
        return actualplayer.getStorage().getPanel() ;
    }


    public ArrayList<Character> getextraPanelView (Player actualplayer) {
        return actualplayer.getStorage().getExtrapanel() ;
    }

}
