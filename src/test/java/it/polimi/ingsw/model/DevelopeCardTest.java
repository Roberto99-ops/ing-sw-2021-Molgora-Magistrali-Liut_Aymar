package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class DevelopeCardTest {

    @Test
    public void doProduction() {
    }

    /**
     * this test tests that a developecard is picked and memorized correctly from the json file
     * @throws FileNotFoundException
     */
    @Test
    public void setCard() throws FileNotFoundException {
        DevelopeCard card = new DevelopeCard();
        card.setCard(0);

        assertEquals(1, card.getLevel());
        assertEquals('G', card.getColour());
        assertEquals(1, card.getPv());

        ResourceStructure array = new ResourceStructure();
        array.AddResource(2, 'B');
        assertEquals(array.getVector().get(0), card.getCost().getVector().get(0));
        assertEquals(array.getVector().get(1), card.getCost().getVector().get(1));

        ResourceStructure array1 = new ResourceStructure();
        array1.AddResource(1,'Y');
        assertEquals(array1.getVector().get(0), card.getInputproduction().getVector().get(0));

        ResourceStructure array2 = new ResourceStructure();
        array2.AddResource(1,'R');
        assertEquals(array2.getVector().get(0), card.getOutputproduction().getVector().get(0));
    }
}