package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Lorenzo;

public class LorenzoMsg extends NetworkMessage{
    private Lorenzo lorenzo = new Lorenzo();

    public LorenzoMsg (Lorenzo lorenzo){this.lorenzo=lorenzo;}

    //ho bisogno di comunicare la posizione di lorenzo sul tracciato

    /**
     * Sends as a message to the client, the actual position of Lorenzo on the FaithTrack
     * @return the position of Lorenzo
     */
    public int getTrackPositionLore(){return lorenzo.getNumber();}

    /*public Lorenzo getLorenzo() {
        return lorenzo;
    }*/
}
