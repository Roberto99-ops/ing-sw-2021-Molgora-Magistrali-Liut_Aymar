package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ActionStructureTest {

    /**
     * Tests if the action of a specific signal is correctly called.
     * Since this class and its method 'pickSignal' calls all the methods in ActionSignal, it also verifies that class
     */
    @Test
    public void pickSignal() throws FileNotFoundException {
        ActionSignal Signal = new ActionSignal();
        //case #1-4: deletes two cards from a Develope Deck
        ArrayList<DevelopDecks> developDecks= new ArrayList<DevelopDecks>();
        //sets all 12 decks with three cards
        for (int i=0; i<12 ; i++){
            //create a new deck at index 'i'
            developDecks.add(i,new DevelopDecks());
            for(int c=0; c<4; c++)
            {
                DevelopeCard card = new DevelopeCard();
                card.setCard(c);
                developDecks.get(i).getStructure().add(card);
            }
        }
        DevelopeCard card1 = new DevelopeCard();
        card1= developDecks.get(2).getStructure().get(2);
        Signal.Action(1);
        assertNotEquals(card1, developDecks.get(2).getStructure().get(0));



/*
        //case #5-6: Lorenzo moves forward by +2
        Lorenzo lore = new Lorenzo();
        Signal.Action(5); //calls Lorenzomoves(2) -> position:2
        assertEquals(2,lore.getNumber());
        //so, Lorenzo can move forward by +1 or +2
*/
    }

    /**
     * Tests if the new shuffled Actions' Structure is different from the previous structure
     */
    @Test
    public void shuffleSignal() {
        //case #7: shuffling the actions' structure
        int[] structure = {1,2,3,4,5,6,7};
        ActionStructure actionStructure = new ActionStructure();
        Collections.shuffle(Arrays.asList(ActionStructure.getStructure()));
        //confront actionStructure.structure and this.structure
        assertNotEquals(structure, actionStructure.getStructure());


    }
}