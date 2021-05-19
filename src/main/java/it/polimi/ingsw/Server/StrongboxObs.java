package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ResourceStructure;

import java.util.ArrayList;

public abstract class StrongboxObs {

    public ResourceStructure getStrongboxView (Player actualplayer) {
        return actualplayer.getStrongBox().getStructure();
    }


    public int getNumberofResourceView (Player actualplayer) {
        return actualplayer.getStrongBox().counterResource();
    }

}
