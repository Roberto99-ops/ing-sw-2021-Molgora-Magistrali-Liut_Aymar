package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.ActionStructure;
import it.polimi.ingsw.model.SingleGame;

public class ObserverSingleGame extends ObserverGame {

    public static void updateActionStructure() {
        ActionStructure actualActionStructure = SingleGame.getActionStructure();
        System.out.println("actionstructuremessage");
        // ObservableGame.notifyAllObservers();
    }

}
