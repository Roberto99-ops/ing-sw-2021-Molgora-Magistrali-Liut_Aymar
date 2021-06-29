package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The structure sent is an array of integers.
 * Every element of the array is the position of that card into the json file.
 */

public class DevelopeDecksMsg extends NetworkMessage {

    private DevelopeDecks[] developeDecks;

    public DevelopeDecksMsg(DevelopeDecks[] decks)
    {
        developeDecks = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            developeDecks[i] = decks[i];
        }
    }

    public DevelopeDecks[] getDecks()
    {
        return developeDecks;
    }



}
