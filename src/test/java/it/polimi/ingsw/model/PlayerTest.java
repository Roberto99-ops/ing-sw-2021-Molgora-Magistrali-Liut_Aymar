package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

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
       /* Player player = new Player();
        ResourceStructure storage = new ResourceStructure();
        player.getStorage().setinStorage('W', 0);
        player.getStorage().setinStorage('B', 1);    //lo storage deve controllare da solo che le risorse si possano aggiungere
        player.getStorage().setinStorage('Y', 2);
        player.getStorage().setinStorage('G', 3);
        player.removeResource('B');

        ArrayList<Character> list = new ArrayList<>();
        list.add('W');
        list.add('Y');
        list.add('G');
        for (int i = 0; i < list.size(); i++) assertEquals(list.get(i), player.getStorage().get(i));

        player.removeResource('B');
        for (int i = 0; i < list.size(); i++) assertEquals(list.get(i), player.getStorage().get(i));*/

    }

    @Test
    public void addResourceStorage() {

    }

    @Test
    public void addResourceStrongBox() {
        Player player = new Player();
        StrongBox strongbox = new StrongBox();

        Character[] resources = {'P', 'Y', 'G', 'B'};
        Random mixer = new Random();
        for (int i = 0; i < 10; i++) {
            int mix = mixer.nextInt(4);
            strongbox.getStructure().add(resources[mix]);
            player.addResourceStrongBox(resources[mix]);
        }

        for (int i = 0; i < strongbox.getStructure().size(); i++)
            assertEquals(strongbox.getStructure().get(i), player.getStrongBox().getStructure().get(i));

    }
}