package it.polimi.ingsw.model;

import junit.framework.TestCase;

public class DevelopmentSpaceTest extends TestCase {

    public void testGetCard() throws Exception {
        DevelopmentSpace space = new DevelopmentSpace();

        for (int i = 0; i < 2; i++) {
            DevelopCard card = new DevelopCard();
            card.setCard(i);
            space.getMiniDeck1().getStructure().add(card);
            for (int k = 0; k < space.getMiniDeck1().getStructure().size() ; k++) {
                space.getMiniDeck1().getStructure().get(k).print();
            }
        }

        for (int i = 3; i < 5; i++) {
            DevelopCard card1 = new DevelopCard();
            card1.setCard(i);
            space.getMiniDeck2().getStructure().add(card1);
            for (int k = 0; k < space.getMiniDeck2().getStructure().size() ; k++) {
                space.getMiniDeck2().getStructure().get(k).print();
            }
        }

        for (int i = 15; i < 17; i++) {
            DevelopCard card2 = new DevelopCard();
            card2.setCard(i);
            space.getMiniDeck3().getStructure().add(card2.setCard(i));
            for (int k = 0; k < space.getMiniDeck3().getStructure().size() ; k++) {
                space.getMiniDeck3().getStructure().get(k).print();
            }
        }

        DevelopCard card = space.getCard(1);
        assertEquals(1, card.getLevel());
        assertEquals('P', card.getColour());
        assertEquals(1, card.getPv());

        DevelopCard card1 = space.getCard(2);
        assertEquals(1, card1.getLevel());
        assertEquals('G', card1.getColour());
        assertEquals(2, card1.getPv());

        DevelopCard card2 = space.getCard(3);
        assertEquals(2, card2.getLevel());
        assertEquals('G', card2.getColour());
        assertEquals(5, card2.getPv());


    }


    public void testSetCard() throws Exception {
        DevelopmentSpace space = new DevelopmentSpace();
        for (int i = 0; i < 2; i++) {
            DevelopCard card = new DevelopCard();
            card.setCard(i);
            space.setCard(card, 1);
            space.setCard(card, 2);
            space.setCard(card, 3);
        }
        for (int i = 0; i < space.getMiniDeck1().getStructure().size(); i++) {
            assertEquals(space.getMiniDeck1().getStructure().get(i).getLevel(), space.getMiniDeck2().getStructure().get(i).getLevel());
            assertEquals(space.getMiniDeck1().getStructure().get(i).getColour(), space.getMiniDeck2().getStructure().get(i).getColour());
            assertEquals(space.getMiniDeck1().getStructure().get(i).getPv(), space.getMiniDeck2().getStructure().get(i).getPv());

            assertEquals(space.getMiniDeck1().getStructure().get(i).getLevel(), space.getMiniDeck3().getStructure().get(i).getLevel());
            assertEquals(space.getMiniDeck1().getStructure().get(i).getColour(), space.getMiniDeck3().getStructure().get(i).getColour());
            assertEquals(space.getMiniDeck1().getStructure().get(i).getPv(), space.getMiniDeck3().getStructure().get(i).getPv());
        }
    }





}