package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * the structure sended is an array of integer.
 * every element of the array is the position fo that card into the json file.
 */
public class DevelopeDeckMsg extends NetworkMessage{
    private ArrayList<Integer> array;

    public DevelopeDeckMsg()
    {
        array = new ArrayList<>();
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    /**
     * this method has to be called by the receiver, to "unpack" the array of integer and
     * trasform it into a deck of developecards.
     * @return the deck of developecards
     * @throws FileNotFoundException
     */
    public DevelopeDecks getDeck() throws FileNotFoundException {
        DevelopeDecks deck = new DevelopeDecks();
        for (int i = 0; i < this.getArray().size(); i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(this.getArray().get(i));
            deck.getStructure().add(card);
        }
        return deck;
    }
}
