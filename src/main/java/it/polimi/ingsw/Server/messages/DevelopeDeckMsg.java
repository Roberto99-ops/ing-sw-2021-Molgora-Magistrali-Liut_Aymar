package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The structure sent is an array of integers.
 * Every element of the array is the position of that card into the json file.
 */

public class DevelopeDeckMsg extends NetworkMessage {
    private DevelopeDecks developeDecks = new DevelopeDecks();
    //private ArrayList<Integer> array;

    /**
     * Setter for the instance 'developeDecks': it will be the data sent to the client
     * @param developeDecks
     */
    public  DevelopeDeckMsg(DevelopeDecks developeDecks){
        this.developeDecks = developeDecks;
    }

    /*public void DevelopeDeckMsgAB()
    {
        array = new ArrayList<>();
    }*/

    /*
    public ArrayList<Integer> getArray() {
        return array;
    }*/

    /**
     * Getter for the data we want the Server to send to the Client
     * @return
     */
    public DevelopeDecks getDeck(){return developeDecks;}

    /*
     * This method needs to be called by the receiver, to "unpack" the array of integer and
     * transform it into a deck of Developecards.
     * @return the deck of Developecards
     * @throws FileNotFoundException

    public DevelopeDecks getDeck() throws FileNotFoundException {
        DevelopeDecks deck = new DevelopeDecks();
        for (int i = 0; i < this.getArray().size(); i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(this.getArray().get(i));
            deck.getStructure().add(card);
        }
        return deck;
    }

     */
}
