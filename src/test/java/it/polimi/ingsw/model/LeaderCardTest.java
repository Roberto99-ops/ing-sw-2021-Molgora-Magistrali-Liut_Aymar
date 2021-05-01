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

    /**
     * tests the fact that the price returned (the price of the developecard-'P'), is right
     * @throws FileNotFoundException
     */
    @Test
    public void priceSkill() throws FileNotFoundException {
        DevelopeCard develcard = new DevelopeCard();
        LeaderCard leadercard = new LeaderCard();
        develcard.setCard(1);
        leadercard.setCard(0);
        ResourceStructure cost = new ResourceStructure();
        ResourceStructure newcost = new ResourceStructure();

        newcost=leadercard.PriceSkill(develcard);
        cost.AddResource(1,'P');
        assertEquals(1, newcost.getVector().size());
        assertEquals(cost.getVector().get(0), newcost.getVector().get(0));
    }


    /**
     * tests that the extra panel is activated and it can contain the right type of resources
     * @throws FileNotFoundException
     */
    @Test
    public void additionalStorageSkill() throws FileNotFoundException {
        Player player = new Player();
        LeaderCard card = new LeaderCard();
        card.setCard(4);

        card.AdditionalStorageSkill(player);

        assertEquals('G', player.getStorage().getTypeExtrapanel());
    }

    @Test
    public void convertWhiteMarbleSkill() {
    }

    @Test
    public void additionalProductionSkill() {
    }
}