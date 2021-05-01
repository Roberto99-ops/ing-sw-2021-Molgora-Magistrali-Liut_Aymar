package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.management.PlatformLoggingMXBean;

import static org.junit.Assert.*;

public class DevelopeCardTest {

    /**
     * test the DoProduction method.
     * (1) tests the case which the inputresources are all inside the storage
     * (2) tests the case which the inputresources are all inside the strongbox
     * (3) tests the case which the inputresources are divided by storage and strongbox
     * (4) tests the case which the player doesn't own enough resources
     * @throws FileNotFoundException
     */
    @Test
    public void doProduction() throws FileNotFoundException {
       /* DevelopeCard card = new DevelopeCard();
        card.setCard(21);

        //(1)
        Player player = new Player();
        ResourceStructure output = new ResourceStructure();
        player.addResourceStorage('B');
        player.addResourceStorage('P');
        output.AddResource(3, 'W');
        card.DoProduction(player);
        assertEquals(null, player.getStorage());
        assertTrue(player.getStorage().getinStorage(3, 'W'));

        //(2)
        Player player1 = new Player();
        ResourceStructure output1 = new ResourceStructure();
        player.addResourceStrongBox('B');
        player.addResourceStrongBox('P');
        output1.AddResource(3, 'W');
        card.DoProduction(player1);
        for(int i=0; i<player1.getStorage().size(); i++)    assertEquals(output1.getVector().get(i), player1.getStrongBox().getStructure().getVector().get(i));


        */
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