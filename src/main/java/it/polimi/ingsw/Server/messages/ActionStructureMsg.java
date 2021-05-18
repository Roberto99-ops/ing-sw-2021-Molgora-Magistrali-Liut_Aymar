package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ActionStructure;

public class ActionStructureMsg extends NetworkMessage{

    private ActionStructure actionStructure;


    public ActionStructureMsg(ActionStructure actionStructure){this.actionStructure.setStructure(actionStructure.getStructure());}

    public ActionStructure getActionStructure(){
        return actionStructure;
    }
}
