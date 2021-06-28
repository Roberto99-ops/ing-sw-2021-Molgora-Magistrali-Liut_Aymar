package it.polimi.ingsw.Server;

import it.polimi.ingsw.Server.messages.ActionStructureMsg;
import it.polimi.ingsw.model.ActionStructure;
import it.polimi.ingsw.model.SingleGame;

public class SingleGameHandler extends GameHandler {

    /**
     * send to the client the new actionstructure
     * @param clh
     */

    public void updateActionStructure(ClientHandler clh) throws Exception {
        ActionStructure actualActionStructure = SingleGame.getActionStructure();
        ActionStructureMsg msg = new ActionStructureMsg(actualActionStructure);
        clh.sendMessage(msg);
    }

}
