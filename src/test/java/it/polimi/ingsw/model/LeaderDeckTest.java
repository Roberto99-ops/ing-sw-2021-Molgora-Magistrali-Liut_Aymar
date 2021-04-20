package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class LeaderDeckTest {

    @Test
    public void getDeck() throws FileNotFoundException {
        LeaderDeck deck = new LeaderDeck();
        for(int i=0; i<5; i++)
        {
            LeaderCard card = new LeaderCard();
            card.setCard(i);
            deck.getStructure().add(card);
        }

        LeaderCard card = new LeaderCard();
        card.setCard(0);
        LeaderCard card1 = deck.getDeck(deck.getStructure());

        assertEquals(card.getCardLevel(), card1.getCardLevel());
        assertEquals(card.getPriceC(), card1.getPriceC());
        assertEquals(card.getPriceR().getVector(), card1.getPriceR().getVector());
        assertEquals(card.getSkill(), card1.getSkill());
        assertEquals(card.getPv(), card1.getPv());

        assertEquals(4, deck.getStructure().size());
    }

    @Test
    public void setDeck() throws FileNotFoundException {
        LeaderDeck deck = new LeaderDeck();
        LeaderDeck oldDeck = new LeaderDeck();
        for(int i=0; i<5; i++)
        {
            LeaderCard card = new LeaderCard();
            card.setCard(i);
            deck.getStructure().add(card);
            oldDeck.getStructure().add(card);
        }

        deck.setStructure(deck.setDeck(deck.getStructure()));
        System.out.println(deck.getStructure() + "\n"+ oldDeck.getStructure());

        for(int i=0; i <5; i++) {
            assertTrue(deck.getStructure().contains(oldDeck.getStructure().get(i)));
            assertTrue(oldDeck.getStructure().contains(deck.getStructure().get(i)));
        }

        assertNotEquals(deck.getStructure(),oldDeck.getStructure());
    }
}