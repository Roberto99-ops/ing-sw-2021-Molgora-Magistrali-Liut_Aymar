package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.FaithTrack;

//NON HA MOLTO SENSO... PERCHÈ LA CLASSE FAITHTRACK NON HA ALCUN ATTRIBUTO DA CAMBIARE E QUINDI NOTIFICARE
public class FaithTrackMsg extends NetworkMessage{
    private FaithTrack faithTrack;

    public FaithTrackMsg (FaithTrack faithTrack){
        this.faithTrack= faithTrack;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
}
