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
        //sets all 12 decks with four cards

        for (int n=0; n<12 ;n++) {
            DevelopeDecks deck = new DevelopeDecks();
            for (int i = 0; i < 5; i++) {
                DevelopeCard card = new DevelopeCard();
                card.setCard(i);
                deck.getStructure().add(card);
            }
            //creo un mazzo con 4 carte e lo inserisco dentro l'array di Developedeck
            Game.setDevelopedecks(deck, n);
        }

        //prendo la prima carta del secondo mazzo di Developedecks
        DevelopeCard card1 = Game.getDevelopedecks(2).getStructure().get(2);
        //System.out.println(card1.getColour());
        Signal.Action(1);
        assertEquals(card1, Game.getDevelopedecks(2).getStructure().get(0));


        //case #5-6: Lorenzo moves forward by +2
        Lorenzo lore = new Lorenzo();
        Signal.Action(5); //calls Lorenzomoves(2) -> position:2
        assertEquals(2,lore.getNumber());
        //so, Lorenzo can move forward by +1 or +2

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