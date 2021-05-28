package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Lorenzo;

public class LorenzoMsg extends NetworkMessage{
    private Lorenzo lorenzo;

    /**
     * Sets the data of Lorenzo we want the Server to send to the Client
     * @param lorenzo is data we send to the Client
     */
    public LorenzoMsg (Lorenzo lorenzo){this.lorenzo=lorenzo;}

    //ho bisogno di comunicare la posizione di lorenzo sul tracciato

    /**
     * Sends as a message to the client, the actual position of Lorenzo on the FaithTrack
     * @return the position of Lorenzo
     */
    public int getTrackPositionLore(){return lorenzo.getNumber();}

    /**
     * Sends as a message to the client, the data of Lorenzo
     * @return the data of Lorenzo
     */
    public Lorenzo getLorenzo() {
        return lorenzo;
    }
}
