package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class DevelopeCardTest {


    /**
     * this test tests that a developecard is picked and memorized correctly from the json file
     * @throws FileNotFoundException
     */
    @Test
    public void setCard() throws FileNotFoundException {
        DevelopeCard card = new DevelopeCard();
        DevelopeCard card10 = new DevelopeCard();
        DevelopeCard card22 = new DevelopeCard();
        DevelopeCard card37 = new DevelopeCard();

        card.setCard(0);

        assertEquals(1, card.getLevel());
        assertEquals('G', card.getColour());
        assertEquals(1, card.getPv());

        ResourceStructure array = new ResourceStructure();
        array.addResource(2, 'B');
        assertEquals(array.getVector().get(0), card.getCost().getVector().get(0));
        assertEquals(array.getVector().get(1), card.getCost().getVector().get(1));

        ResourceStructure array1 = new ResourceStructure();
        array1.addResource(1,'Y');
        assertEquals(array1.getVector().get(0), card.getInputproduction().getVector().get(0));

        ResourceStructure array2 = new ResourceStructure();
        array2.addResource(1,'R');
        assertEquals(array2.getVector().get(0), card.getOutputproduction().getVector().get(0));



        card10.setCard(10);

        assertEquals(1, card10.getLevel());
        assertEquals('B', card10.getColour());
        assertEquals(3, card10.getPv());

        ResourceStructure array10 = new ResourceStructure();
        array10.addResource(3, 'Y');
        assertEquals(array10.getVector().get(0), card10.getCost().getVector().get(0));
        assertEquals(array10.getVector().get(1), card10.getCost().getVector().get(1));
        assertEquals(array10.getVector().get(2), card10.getCost().getVector().get(2));

        ResourceStructure array101 = new ResourceStructure();
        array101.addResource(2,'G');
        assertEquals(array101.getVector().get(0), card10.getInputproduction().getVector().get(0));
        assertEquals(array101.getVector().get(1), card10.getInputproduction().getVector().get(0));

        ResourceStructure array102 = new ResourceStructure();
        array102.addResource(1,'Y');
        array102.addResource(1,'B');
        array102.addResource(1,'P');
        assertEquals(array102.getVector().get(0), card10.getOutputproduction().getVector().get(0));
        assertEquals(array102.getVector().get(1), card10.getOutputproduction().getVector().get(1));
        assertEquals(array102.getVector().get(2), card10.getOutputproduction().getVector().get(2));




        card22.setCard(22);

        assertEquals(2, card22.getLevel());
        assertEquals('B', card22.getColour());
        assertEquals(6, card22.getPv());

        ResourceStructure array22 = new ResourceStructure();
        array22.addResource(3, 'Y');
        array22.addResource(2, 'G');
        assertEquals(array22.getVector().get(0), card22.getCost().getVector().get(0));
        assertEquals(array22.getVector().get(1), card22.getCost().getVector().get(1));
        assertEquals(array22.getVector().get(2), card22.getCost().getVector().get(2));
        assertEquals(array22.getVector().get(3), card22.getCost().getVector().get(3));
        assertEquals(array22.getVector().get(4), card22.getCost().getVector().get(4));

        ResourceStructure array221 = new ResourceStructure();
        array221.addResource(1,'Y');
        array221.addResource(1,'G');
        assertEquals(array221.getVector().get(0), card22.getInputproduction().getVector().get(0));
        assertEquals(array221.getVector().get(1), card22.getInputproduction().getVector().get(1));

        ResourceStructure array222 = new ResourceStructure();
        array222.addResource(1,'P');
        array222.addResource(1,'P');
        array222.addResource(1,'P');
        assertEquals(array222.getVector().get(0), card22.getOutputproduction().getVector().get(0));
        assertEquals(array222.getVector().get(1), card22.getOutputproduction().getVector().get(1));
        assertEquals(array222.getVector().get(2), card22.getOutputproduction().getVector().get(2));
    }




}