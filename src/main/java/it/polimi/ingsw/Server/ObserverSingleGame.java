package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.ActionStructureMsg;
import it.polimi.ingsw.Server.messages.MarketMsg;
import it.polimi.ingsw.model.ActionStructure;
import it.polimi.ingsw.model.SingleGame;

public class ObserverSingleGame extends ObserverGame {

    public void updateActionStructure(ClientHandler clh) throws Exception {
        ActionStructure actualActionStructure = SingleGame.getActionStructure();
        ActionStructureMsg msg = new ActionStructureMsg(actualActionStructure);
        clh.sendMessage(msg);
    }

}
