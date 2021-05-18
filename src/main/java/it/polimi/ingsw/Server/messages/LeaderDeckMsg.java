package it.polimi.ingsw.Server.messages;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.LeaderDeck;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * the structure sended is an array of integer.
 * every element of the array is the position fo that card into the json file.
 */
public class LeaderDeckMsg extends NetworkMessage{
    private ArrayList<Integer> array;

    public LeaderDeckMsg()
    {
        array = new ArrayList<>();
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    /**
     * this method has to be called by the receiver, to "unpack" the array of integer and
     * trasform it into a deck of leadercard.
     * @return the deck of leadercard
     * @throws FileNotFoundException
     */

    public LeaderDeck getDeck() throws FileNotFoundException {
        LeaderDeck deck = new LeaderDeck();
        for (int i = 0; i < this.getArray().size(); i++) {
            LeaderCard card = new LeaderCard();
            card.setCard(this.getArray().get(i));
            deck.getStructure().add(card);
        }
        return deck;
    }
}
