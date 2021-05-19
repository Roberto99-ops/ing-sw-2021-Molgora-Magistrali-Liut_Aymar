package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

public abstract class FaithtrackObs {

    public ArrayList<Character> getPanelView (Player actualplayer) { // leggo in input il deck che voglio
        return actualplayer.getStorage().getPanel() ;
    }


}
