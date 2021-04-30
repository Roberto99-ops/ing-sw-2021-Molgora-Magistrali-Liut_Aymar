package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class LeaderCardTest {

    /**
     * this test tests that a developecard is picked and memorized correctly from the json file
     * @throws FileNotFoundException
     */
    @Test
    public void setCard() throws FileNotFoundException {
        LeaderCard card = new LeaderCard();
        card.setCard(0);

        ResourceStructure array = new ResourceStructure();
        assertEquals(array, card.getPriceR());

        array.AddResource(1, 'Y');
        array.AddResource(1, 'G');
        assertEquals(array.getVector().get(0), card.getPriceC().get(0));
        assertEquals(array.getVector().get(1), card.getPriceC().get(1));

        assertEquals(1, card.getCardLevel());
        assertEquals(2, card.getPv());
        assertEquals('P', card.getInputskill());
        assertEquals(0, card.getNumber());
        assertEquals("PriceSkill", card.getSkill());
    }

    @Test
    public void priceSkill() {
    }

    @Test
    public void additionalStorageSkill() {
    }

    @Test
    public void convertWhiteMarbleSkill() {
    }

    @Test
    public void additionalProductionSkill() {
    }
}