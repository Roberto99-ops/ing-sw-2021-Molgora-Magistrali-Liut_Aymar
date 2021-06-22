package it.polimi.ingsw.model;

import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class DevelopementSpaceTest extends TestCase {

    public void testGetCard() throws Exception {
        DevelopementSpace space = new DevelopementSpace();
        for (int i = 0; i < 2; i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            space.getMinideck1().getStructure().add(card);
        }

        for (int i = 3; i < 5; i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            space.getMinideck2().getStructure().add(card);
        }

        for (int i = 15; i < 17; i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            space.getMinideck3().getStructure().add(card.setCard(i));
        }

        DevelopeCard card = space.getCard(1);
        assertEquals(1, card.getLevel());
        assertEquals('P', card.getColour());
        assertEquals(1, card.getPv());
/*
        DevelopeCard card1 = space.getCard(2);
        assertEquals(1, card.getLevel());
        assertEquals('G', card.getColour());
        assertEquals(2, card.getPv());

        DevelopeCard card2 = space.getCard(3);
        assertEquals(2, card.getLevel());
        assertEquals('G', card.getColour());
        assertEquals(5, card.getPv());

 */
    }


    public void testSetCard() throws Exception {
        DevelopementSpace space = new DevelopementSpace();
        for (int i = 0; i < 2; i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            space.setCard(card, 1);
            space.setCard(card, 2);
            space.setCard(card, 3);
        }
        for (int i = 0; i < space.getMinideck1().getStructure().size(); i++) {
            assertEquals(space.getMinideck1().getStructure().get(i).getLevel(), space.getMinideck2().getStructure().get(i).getLevel());
            assertEquals(space.getMinideck1().getStructure().get(i).getColour(), space.getMinideck2().getStructure().get(i).getColour());
            assertEquals(space.getMinideck1().getStructure().get(i).getPv(), space.getMinideck2().getStructure().get(i).getPv());

            assertEquals(space.getMinideck1().getStructure().get(i).getLevel(), space.getMinideck3().getStructure().get(i).getLevel());
            assertEquals(space.getMinideck1().getStructure().get(i).getColour(), space.getMinideck3().getStructure().get(i).getColour());
            assertEquals(space.getMinideck1().getStructure().get(i).getPv(), space.getMinideck3().getStructure().get(i).getPv());
        }
    }

    public void testCheckDeck() throws FileNotFoundException {


    }



}