package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopDecks;

/**
 * The structure sent is an array of integers.
 * Every element of the array is the position of that card into the Json file.
 */

public class DevelopDecksMsg extends NetworkMessage {

    private DevelopDecks[] developDecks;

    /**
     * Setter for the instance 'DevelopDeck': it will be the data sent to the client from the server
     * @param decks : its the data we want to receive from the server (a group of DevelopCards)
     */


    public DevelopDecksMsg(DevelopDecks[] decks)
    {
        developDecks = new DevelopDecks[12];
        for (int i = 0; i < 12; i++) {
            developDecks[i] = decks[i];
        }
    }

    public DevelopDecks[] getDecks()
    {
        return developDecks;
    }



}
