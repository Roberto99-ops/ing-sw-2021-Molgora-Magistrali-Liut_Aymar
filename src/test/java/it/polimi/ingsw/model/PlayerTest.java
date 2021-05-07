package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void increaseTrackposition() {
        Player player = new Player();
        player.increaseTrackposition();
        player.increaseTrackposition();
        assertEquals(2, player.getTrackposition());
    }

    @Test
    public void increaseDevelopQuantity() {
        Player player = new Player();
        player.increaseDevelopQuantity();
        player.increaseDevelopQuantity();
        assertEquals(2,player.getDevelopementquantity());
    }

    @Test
    public void checkResources() {
    }

    @Test
    public void getDevelopecards() throws Exception {
       /* Player player = new Player();
        DevelopementSpace dspace = new DevelopementSpace();
        for (int i = 0; i < 9; i++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            dspace.setCard(card,i/3+1);
        }
        player.setDSpace(dspace);

        DevelopeDecks topcards = player.getDevelopecards();

        int j=0;
        for (int i = 2; i < 9; i+=3) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);

            assertEquals(card.getPv(), topcards.getStructure().get(j).getPv());
            assertEquals(card.getLevel(), topcards.getStructure().get(j).getLevel());
            assertEquals(card.getColour(), topcards.getStructure().get(j).getColour());

            j++;
        }*/
    }

    @Test
    public void removeResource() {
        Player player = new Player();
        player.getStorage().add('W');
        player.getStorage().add('B');    //sicuro sia così?
        player.getStorage().add('Y');
        player.getStorage().add('G');
        player.removeResource('B');

        ArrayList<Character> list = new ArrayList<>();
        list.add('W');
        list.add('Y');
        list.add('G');
        for (int i = 0; i < list.size(); i++) assertEquals(list.get(i), player.getStorage().get(i));

        player.removeResource('B');
        for (int i = 0; i < list.size(); i++) assertEquals(list.get(i), player.getStorage().get(i));

    }

    @Test
    public void addResourceStorage() {
    }

    @Test
    public void addResourceStrongBox() {
    }
}