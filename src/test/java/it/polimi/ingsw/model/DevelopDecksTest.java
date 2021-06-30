package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class DevelopDecksTest {

    @Test
    /**
     * checks that the card returned is the expected one,
     * and checks that the "new" deck size is shorter.
     */

    public void getDeck() throws FileNotFoundException {
        DevelopDecks deck = new DevelopDecks();
        for(int i=0; i<5; i++)
        {
            DevelopCard card = new DevelopCard();
            card.setCard(i);
            deck.getStructure().add(card);
        }

        DevelopCard card = new DevelopCard();
        card.setCard(0);
        DevelopCard card1 = deck.pick(deck.getStructure());

        assertEquals(card.getColour(), card1.getColour());
        assertEquals(card.getCost().getVector(), card1.getCost().getVector());
        assertEquals(card.getInputproduction().getVector(), card1.getInputproduction().getVector());
        assertEquals(card.getOutputproduction().getVector(), card1.getOutputproduction().getVector());
        assertEquals(card.getLevel(), card1.getLevel());
        assertEquals(card.getPv(), card1.getPv());

        assertEquals(4, deck.getStructure().size());
    }

    @Test
    /**
     * the mixed deck contains the "old" deck,
     * the "old" deck contains the mixed deck,
     * and they are not equals.
     * so they contains the same cards but are different.
     */

    public void setDeck() throws FileNotFoundException {
        DevelopDecks deck = new DevelopDecks();
        DevelopDecks oldDeck = new DevelopDecks();
        for(int i=0; i<5; i++)
        {
            DevelopCard card = new DevelopCard();
            card.setCard(i);
            deck.getStructure().add(card);
            oldDeck.getStructure().add(card);
        }

        deck.setStructure(deck.shuffleDeck(deck.getStructure()));

        for(int i=0; i <5; i++) {
            assertTrue(deck.getStructure().contains(oldDeck.getStructure().get(i)));
            assertTrue(oldDeck.getStructure().contains(deck.getStructure().get(i)));
        }

        assertNotEquals(deck.getStructure(),oldDeck.getStructure());
    }
}