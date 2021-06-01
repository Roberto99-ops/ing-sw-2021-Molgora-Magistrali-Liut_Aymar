package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ActionSignal;

public class ActionSignalMsg extends NetworkMessage {
    private ActionSignal actionSignal;

    public ActionSignalMsg(ActionSignal actionSignal){this.actionSignal=actionSignal;}

    public ActionSignal getAction() {
        return actionSignal;
    }
}

