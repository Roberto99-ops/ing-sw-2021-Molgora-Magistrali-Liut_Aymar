package it.polimi.ingsw.Server.messages;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.LeaderDeck;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The structure sent is an array of integer.
 * Every element of the array is the position fo that card into the json file.
 */
public class LeaderDeckMsg extends NetworkMessage{
    private LeaderDeck leaderDeck= new LeaderDeck();
    //private ArrayList<Integer> array;

    /**
     * Setter for the instance 'leaderDeck': it will be the data sent to the client
     * @param leaderDeck
     */
    public LeaderDeckMsg(LeaderDeck leaderDeck){
        this.leaderDeck=leaderDeck;
    }

    /*public LeaderDeckMsg()
    {
        array = new ArrayList<>();
    }
     */


    /*
    public ArrayList<Integer> getArray() {
        return array;
    }

     */

    /**
     * Getter for the data we want the Server to send to the Client
     * @return
     */
    public LeaderDeck getDeck(){return leaderDeck;}

    /*
     * This method has to be called by the receiver, to "unpack" the array of integer and
     * trasform it into a deck of leadercard.
     * @return the deck of leadercard
     * @throws FileNotFoundException

    public LeaderDeck getDeck() throws FileNotFoundException {
        LeaderDeck deck = new LeaderDeck();
        for (int i = 0; i < this.getArray().size(); i++) {
            LeaderCard card = new LeaderCard();
            card.setCard(this.getArray().get(i));
            deck.getStructure().add(card);
        }
        return deck;
    }

     */
}
