package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ActionSignal;
import it.polimi.ingsw.model.ActionStructure;

public class ActionStructureMsg extends NetworkMessage{

    private ActionStructure actionStructure = new ActionStructure();


    /**
     * Prepares the data the Server is going to send to the Client about the ActionSignal picked
     * @param actionStructure the data requested
     */
    public ActionStructureMsg(ActionStructure actionStructure){this.actionStructure=actionStructure;}

    /**
     * Sends the data of the ActionSignal picked by the Player in SingleGame mode
     * @return the data requested
     */
    public ActionStructure getActionStructure(){
        return actionStructure;
    }

    //notifico qual è il primo segnalino della pila

    /**
     * It prints the Action of the signal that has been picked
     * @return a statement explaining what has happened
     */
    public String getAction(){
        String action = "";
        int[] temp = actionStructure.getStructure();
        switch (temp[0]){
            case 1:
                action = "Removed 2 Blue Development Cards from the ones still available";
                break;
            case 2:
                action = "Removed 2 Green Development Cards from the ones still available";
                break;
            case 3:
                action = "Removed 2 Violet Development Cards from the ones still available";
                break;
            case 4:
                action = "Removed 2 Yellow Development Cards from the ones still available";
                break;
            case 5:
                action = "Lorenzo moves forward by +2";
                break;
            case 6:
                action = "Lorenzo moves forward by +2";
                break;
            case 7:
                action = "Lorenzo moves forward by +1 and ActionStrcuture has been shuffled";
                break;

        }
        return action;
    }
}
