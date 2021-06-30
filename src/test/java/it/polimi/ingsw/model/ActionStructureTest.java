package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ActionStructureTest {

    /**
     * Tests if the action of a specific signal is correctly called.
     * Since this class and its method 'pickSignal' calls all the methods in ActionSignal, it also verifies that class
     */

    @Test
    public void pickSignal() throws FileNotFoundException {
        ActionSignal Signal = new ActionSignal();

        for (int n=0; n<12 ;n++) {
            DevelopDecks deck = new DevelopDecks();
            for (int i = 0; i < 4; i++) {
                DevelopCard card = new DevelopCard();
                card.setCard(i);
                deck.getStructure().add(card);
            }

            Game.setDevelopDecks(deck, n);
        }


        DevelopCard card1 = Game.getDevelopDecks(2).getStructure().get(2);
        DevelopCard card2 = Game.getDevelopDecks(2).getStructure().get(0);

        Signal.action(1);
        assertNotEquals(card2, Game.getDevelopDecks(2).getStructure().get(0));
        assertEquals(card1, Game.getDevelopDecks(2).getStructure().get(0));

        for (int i=0; i<4 ;i++){
            Game.getDevelopDecks(3).getStructure().remove(0);
        }
        card1 = Game.getDevelopDecks(7).getStructure().get(2);
        Signal.action(2);
        assertEquals(card1, Game.getDevelopDecks(7).getStructure().get(0));

        for (int i=0; i<3 ;i++){
            Game.getDevelopDecks(0).getStructure().remove(0);
        }
        card1 = Game.getDevelopDecks(4).getStructure().get(1);
        Signal.action(3);
        assertEquals(card1, Game.getDevelopDecks(4).getStructure().get(0));

        for (int c=1; c<11; c=c+4){
            for (int i=0; i<4; i++){
                Game.getDevelopDecks(c).getStructure().remove(0);
            }
        }
        Signal.action(4);
        assertTrue(Game.getDevelopDecks(9).getStructure().isEmpty());

        Signal.action(5);
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

    /**
     * Tests if the new shuffled Actions' Structure is different from the previous structure
     */

    @Test
    public void shuffleSignal() {

        int[] structure = {1,2,3,4,5,6,7};
        ActionStructure actionStructure = new ActionStructure();
        actionStructure.shuffleSignal();
        assertNotEquals(structure, actionStructure.getStructure());


    }
}