package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {


    @Test
    public void increaseTrackposition() {
        Player player = new Player();

        player.increaseTrackPosition();
        player.increaseTrackPosition();
        assertEquals(2, player.getTrackPosition());

        for (int i = 0; i < 10; i ++) {
            player.increaseTrackPosition();
        }

        assertEquals(12, player.getTrackPosition());
        assertEquals(6, player.getPv());


    }


    @Test
    public void increaseDevelopQuantity() {
        Player player = new Player();
        player.increaseDevelopQuantity();
        player.increaseDevelopQuantity();

        assertEquals(2,player.getDevelopmentQuantity());

    }



    //FATTO
    @Test
    public void checkResources() {
        Player player = new Player();
        //inizializzo il suo storage (panel e extrapanel)
        player.getStorage().getPanel().set(0,'G');
        player.getStorage().getPanel().set(1,'B');
        player.getStorage().getPanel().set(2,'B');
        player.getStorage().getPanel().set(3,'Y');
        player.getStorage().getPanel().set(4,'Y');
        player.getStorage().getPanel().set(5,'Y');
        player.getStorage().getExtraPanel().getVector().set(0,'G');
        player.getStorage().getExtraPanel().getVector().set(1,'G');

        //inizializzo strongbox

        player.getStrongBox().getStructure().getVector().add('B');
        player.getStrongBox().getStructure().getVector().add('B');
        player.getStrongBox().getStructure().getVector().add('B');
        player.getStrongBox().getStructure().getVector().add('G');
        player.getStrongBox().getStructure().getVector().add('G');

        //VECTOR 1

        ArrayList<Character> vector0 = new ArrayList<Character>();
        vector0.add('B');
        vector0.add('B');
        vector0.add('B');
        vector0.add('B');
        vector0.add('B');
        vector0.add('B');
        assertEquals(0,player.checkResources(vector0));

        ArrayList<Character> vector1= new ArrayList<Character>();
        vector1.add('Y');
        vector1.add('B');
        vector1.add('Y');
        assertEquals(1,player.checkResources(vector1));

        ArrayList<Character> vector2 = new ArrayList<Character>();
        vector2.add('Y');
        vector2.add('B');
        vector2.add('B');
        vector2.add('B');
        vector2.add('Y');
        assertEquals(2,player.checkResources(vector2));


    }




    @Test
    public void removeResource(){
        Player player = new Player();
        player.getStorage().setTypeExtraPanel('G');

        player.getStorage().getPanel().set(0,'G');
        for (int i=1; i<3; i++){
            player.getStorage().getPanel().set(i,'B');
        }
        for (int i=3; i<6; i++){
            player.getStorage().getPanel().set(i,'Y');
        }
        player.getStorage().getExtraPanel().add('G');
        player.getStorage().getExtraPanel().add('G');

        char resource = 'B';
        assertTrue(player.removeResourceStorage(resource));
        assertTrue(player.getStorage().getPanel().get(2)=='N');

    }


    @Test
    public void addResourceStorage() throws IOException {
        Game game = new Game();
        Player player = new Player();
        player.getStorage().setTypeExtraPanel('B');

        for(int i=0; i<6; i++){
            player.getStorage().getPanel().add('N');
        }
        for (int i=0; i<2; i++){
            player.getStorage().getExtraPanel().getVector().add('N');
        }

        assertTrue(player.addResourceStorage('G', game));
        assertTrue(player.getStorage().getPanel().get(0)=='G');

        player.getStorage().getPanel().set(1,'P');
        assertTrue(player.addResourceStorage('P',game));
        assertEquals('P', (char) player.getStorage().getPanel().get(2));
        for(int i=3; i<5 ;i++)player.getStorage().getPanel().set(i, 'Y');
        assertTrue(player.addResourceStorage('Y',game));
        assertEquals('Y', (char) player.getStorage().getPanel().get(5));

        for(int i=0; i<5;i++){
            System.out.println(player.getStorage().getPanel().get(i));
        }
        for(int i=0; i<2;i++){
            System.out.println(player.getStorage().getExtraPanel().getVector().get(i));
        }

        assertFalse(player.addResourceStorage('A',game));

        assertTrue(player.addResourceStorage('B',game));

        assertEquals('B', (char) player.getStorage().getExtraPanel().getVector().get(0));
        assertEquals('N', (char) player.getStorage().getExtraPanel().getVector().get(1));
        System.out.println(player.getStorage().getExtraPanel().getVector().get(0));
        System.out.println(player.getStorage().getExtraPanel().getVector().get(1));


        player.getStorage().getPanel().set(0,'N');
        assertTrue(player.addResourceStorage('B',game));
        assertEquals('B', (char) player.getStorage().getExtraPanel().getVector().get(1));
        System.out.println(player.getStorage().getPanel().get(0));
        System.out.println(player.getStorage().getExtraPanel().getVector().get(1));


        player.getStorage().setTypeExtraPanel('Z');

        for (int i=0; i<6; i++) {
            player.getStorage().getPanel().set(i, 'N');
        }
        for (int i=0; i<2; i++){
            player.getStorage().getExtraPanel().getVector().set(i,'N');
        }

        System.out.println("\n");

        Character[] vector = {'B','G','G','B'};
        for(int i=0; i<4; i++){
            player.addResourceStorage(vector[i],game);
        }
        for (int i=0; i<6; i++ ) {
            System.out.println(player.getStorage().getPanel().get(i));
        }

        assertEquals('G', (char) player.getStorage().getPanel().get(1));
        assertEquals('G', (char) player.getStorage().getPanel().get(2));
        assertEquals('B', (char) player.getStorage().getPanel().get(3));
        assertEquals('B', (char) player.getStorage().getPanel().get(4));



    }


    //FATTO
    @Test
    public void addResourceStrongBox() {
        Player player = new Player();
        StrongBox strongbox = new StrongBox();

            strongbox.getStructure().getVector().add('B');
            player.addResourceStrongBox('B');
            strongbox.getStructure().getVector().add('G');
            player.addResourceStrongBox('G');
            strongbox.getStructure().getVector().add('G');
            player.addResourceStrongBox('G');
            strongbox.getStructure().getVector().add('Y');
            player.addResourceStrongBox('Y');



        for (int i = 0; i < strongbox.getStructure().size(); i++)
            assertEquals(strongbox.getStructure().get(i), player.getStrongBox().getStructure().get(i));

    }



    @Test

    public void addDevelopementCards () throws Exception   {

        Player player = new Player();
        //creo il primo minideck
        DevelopCard card1 = new DevelopCard();
        DevelopCard card2 = new DevelopCard();
        DevelopCard card3 = new DevelopCard();
        DevelopCard card4 = new DevelopCard();
        DevelopCard card5 = new DevelopCard();
        DevelopCard card6 = new DevelopCard();
        DevelopCard card7 = new DevelopCard();


        player.getDSpace().getMiniDeck1().getStructure().add(card1.setCard(4));
        player.getDSpace().getMiniDeck1().getStructure().add(card2.setCard(20));
        player.getDSpace().getMiniDeck2().getStructure().add(card3.setCard(14));


        assertEquals('G',player.getDSpace().getMiniDeck1().getStructure().get(0).getColour());
        assertEquals('G',player.getDSpace().getMiniDeck1().getStructure().get(1).getColour());
        assertEquals('B',player.getDSpace().getMiniDeck2().getStructure().get(0).getColour());


        assertTrue(player.getDSpace().setCard(card4.setCard(32), 1));
        assertTrue(player.getDSpace().setCard(card5.setCard(1),2));
        assertTrue(player.getDSpace().setCard(card6.setCard(25),3));
        assertFalse(player.getDSpace().setCard(card7.setCard(16),4));

        player.getDSpace().getMiniDeck1().getStructure().add(card4.setCard(32));
        player.getDSpace().getMiniDeck1().getStructure().add(card5.setCard(1));
        player.getDSpace().getMiniDeck2().getStructure().add(card6.setCard(25));


    }


}