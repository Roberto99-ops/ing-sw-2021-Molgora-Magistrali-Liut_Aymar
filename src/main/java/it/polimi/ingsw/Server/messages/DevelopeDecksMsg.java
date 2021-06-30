package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopDecks;

/**
 * The structure sent is an array of integers.
 * Every element of the array is the position of that card into the json file.
 */

public class DevelopeDecksMsg extends NetworkMessage {

    private DevelopDecks[] developDecks;

    public DevelopeDecksMsg(DevelopDecks[] decks)
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
