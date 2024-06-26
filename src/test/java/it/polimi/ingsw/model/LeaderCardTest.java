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

        array.addResource(1, 'Y');
        array.addResource(1, 'G');
        assertEquals(array.getVector().get(0), card.getPriceC().get(0));
        assertEquals(array.getVector().get(1), card.getPriceC().get(1));

        assertEquals(1, card.getCardLevel());
        assertEquals(2, card.getPv());
        assertEquals('P', card.getInputSkill());
        assertEquals(0, card.getNumber());
        assertEquals("PriceSkill", card.getSkill());
    }

    /**
     * tests the fact that the price returned (the price of the developecard-'P'), is right
     * @throws FileNotFoundException
     */
    @Test
    public void priceSkill() throws FileNotFoundException {
        DevelopCard develcard = new DevelopCard();
        LeaderCard leadercard = new LeaderCard();
        develcard.setCard(1);
        leadercard.setCard(0);
        ResourceStructure cost = new ResourceStructure();
        ResourceStructure newcost = new ResourceStructure();

        newcost=leadercard.changePriceSkill(develcard);
        cost.addResource(1,'P');
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

        card.addStorageSkill(player);

        assertEquals('G', player.getStorage().getTypeExtraPanel());
    }


    /**
     * tests this skill with a resourcestructure containing all types.
     * oss: the order of the new structure is different because the method adds
     * the converted resources at the end of the structure.
     * @throws FileNotFoundException
     */
    @Test
    public void convertWhiteMarbleSkill() throws FileNotFoundException {
        LeaderCard card = new LeaderCard();
        card.setCard(7);

        ResourceStructure row = new ResourceStructure();
        row.addResource(1, 'B');
        row.addResource(1, 'W');
        row.addResource(1, 'G');
        row.addResource(1, 'P');
        row.addResource(1, 'W');
        row.addResource(1, 'Y');

        row=card.changeWhiteMarbleSkill(row);

        ResourceStructure newrow = new ResourceStructure();
        newrow.addResource(1, 'B');
        newrow.addResource(1, 'G');
        newrow.addResource(1, 'P');
        newrow.addResource(1, 'Y');
        newrow.addResource(1, 'P');
        newrow.addResource(1, 'P');

        for(int i=0; i<newrow.size(); i++)  assertEquals(newrow.getVector().get(i), row.getVector().get(i));
    }

    @Test
    public void additionalProductionSkill() {
    }
}