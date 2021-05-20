package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopeCard;

import java.io.FileNotFoundException;


public class DevelopCardMsg extends NetworkMessage {
    private DevelopeCard developeCard;

    /**
     * Sets the instance of DevelopeCard
     * @param developeCard
     */
    public DevelopCardMsg(DevelopeCard developeCard){
        this.developeCard=developeCard;
    }

    /**
     * Gets the instance of DevelopeCard
     * @return the DevelopeCard
     */
    public DevelopeCard getDevelopeCard(){
        return developeCard;
    }

    /**
     * Gets a specific card and its information directly from the Json File
     * @param number the number of the wanted card inside the Json File
     * @return the card on that index
     * @throws FileNotFoundException
     */
    //per prendere una carta dal file json
    public DevelopeCard JsonDevelopCard(int number) throws FileNotFoundException {
        developeCard=developeCard.setCard(number);
        return developeCard;
    }
}
