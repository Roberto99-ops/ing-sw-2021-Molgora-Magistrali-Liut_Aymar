package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ActionSignalTest {

    /**
     * Tests if the number of a specific ActionSignal has been gotten correctly
     */

    @Test
    public void getNumber() {
        ActionSignal actionSignal= new ActionSignal();
        int num=5;
        actionSignal.setNumber(5);
        assertEquals(actionSignal.getNumber(),num);
    }

    /**
     * Tests if the action linked to a specific ActionSignal is called and executed correctly
     * @throws FileNotFoundException: excepection thrown when a Developecard is not set correctly
     */

    @Test
    public void action() throws FileNotFoundException {
        ActionSignal actionSignal= new ActionSignal();
        for (int n=0; n<12 ;n++) {
            DevelopeDecks deck = new DevelopeDecks();
            for (int i = 0; i < 4; i++) {
                DevelopeCard card = new DevelopeCard();
                card.setCard(i);
                deck.getStructure().add(card);
            }
            Game.setDevelopedecks(deck, n);
        }

        DevelopeCard card1 = Game.getDevelopedecks(2).getStructure().get(2);
        actionSignal.action(1);
        assertEquals(card1, Game.getDevelopedecks(2).getStructure().get(0));

        for (int i=0; i<4 ;i++) {
            Game.getDevelopedecks(3).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(7).getStructure().get(2);
        actionSignal.action(2); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(7).getStructure().get(0));

        for (int i=0; i<3 ;i++) {
            Game.getDevelopedecks(0).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(4).getStructure().get(1);
        actionSignal.action(3); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(4).getStructure().get(0));

        for (int c=1; c<11; c=c+4){
            for (int i=0; i<4; i++){
                Game.getDevelopedecks(c).getStructure().remove(0);
            }
        }
        actionSignal.action(4);
        assertTrue(Game.getDevelopedecks(9).getStructure().isEmpty());


        actionSignal.action(5);
        assertEquals(2,SingleGame.getLorenzo().getNumber());

        ActionStructure actionStructure = new ActionStructure();
        int[] arr = {1,2,3,4,5,6,7};
        actionStructure.setAS_Counter(6);
        actionStructure.pickSignal();
        assertEquals(3, SingleGame.getLorenzo().getNumber());
        assertNotEquals(arr, actionStructure.getStructure());
        assertEquals(0, actionStructure.getAS_Counter());
        SingleGame.getLorenzo().setNumber(0);
    }
}