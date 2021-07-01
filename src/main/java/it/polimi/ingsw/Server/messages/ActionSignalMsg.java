package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ActionSignal;

/**
 * This class allows me to receive messages that concerns ActionSignal from the Server
 */

public class ActionSignalMsg extends NetworkMessage {
    private ActionSignal actionSignal;

    /**
     * Setter for the instance 'actionSignal': it will be the data sent to the client from the server
     * @param actionSignal : it's the data we want to receive from the server (the actionSignal the Player picks on hi/her turn )
     */

    public ActionSignalMsg(ActionSignal actionSignal){this.actionSignal=actionSignal;}

    public ActionSignal getAction() {
        return actionSignal;
    }
}

