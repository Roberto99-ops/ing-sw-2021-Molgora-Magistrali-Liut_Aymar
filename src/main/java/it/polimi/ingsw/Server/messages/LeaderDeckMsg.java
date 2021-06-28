package it.polimi.ingsw.Server.messages;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.LeaderDeck;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The structure sent is an array of integer.
 * Every element of the array is the position fo that card into the json file.
 */
public class LeaderDeckMsg extends NetworkMessage {

    private LeaderDeck leaderDeck = new LeaderDeck();

    /**
     * Setter for the instance 'leaderDeck': it will be the data sent to the client
     * @param leaderDeck
     */

    public LeaderDeckMsg(LeaderDeck leaderDeck){
        this.leaderDeck=leaderDeck;
    }

    /**
     * Getter for the data we want the Server to send to the Client
     * @return
     */

    public LeaderDeck getDeck(){ return leaderDeck;}


}
