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

    //FATTO
    //questo meth prende le carte in cima ai minideck
    @Test
    public void getDevelopecards() throws Exception {
        Player player = new Player();
        //creo il primo minideck
        for (int c = 0; c < 3; c++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(c);
            player.getDSpace().getMinideck1().getStructure().add(card);
        }
        //il secondo
        for (int c = 0; c < 3; c++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(c);
            player.getDSpace().getMinideck2().getStructure().add(card);
        }
        //il terzo
        for (int c = 0; c < 3; c++) {
            DevelopeCard card = new DevelopeCard();
            card.setCard(c);
            player.getDSpace().getMinideck3().getStructure().add(card);
        }

        //creo un developedeck che contenga le prime carte dei minideck
        DevelopeDecks TopCards = new DevelopeDecks();
        TopCards.getStructure().add(player.getDSpace().getMinideck1().getStructure().get(0));
        TopCards.getStructure().add(player.getDSpace().getMinideck2().getStructure().get(0));
        TopCards.getStructure().add(player.getDSpace().getMinideck3().getStructure().get(0));

        player.getDevelopecards();
        //devi paragonare le strutture e non i deck finali perchè questi sono diversi tra loro, ma identici nella struttura
        assertEquals(TopCards.getStructure(), player.getTopCardsOnBoard().getStructure());
    }

    //DA FARE
    @Test
    public void removeResource(){
        Player player = new Player();
        DevelopementSpace DSpace = new DevelopementSpace();
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
        for (int i = 0; i < list.size(); i++) assertEquals(list.get(i), player.getStorage().get(i));

    }

    //FATTO
    @Test
    public void addResourceStorage() {
        Player player = new Player();
        player.getStorage().setTypeExtrapanel('B');
        //caso1: storage vuoto in cui aggiungo il primo elemnto in cima
        //considero uno storage vuoto
        for(int i=0; i<6; i++){
            player.getStorage().getPanel().getVector().add('N'); //N: null= non c'è niente
        }
        for (int i=0; i<2; i++){
            player.getStorage().getExtrapanel().getVector().add('N');
        }
        //riempiamo lo storage (in cima) del player con una sola risorsa
        assertTrue(player.addResourceStorage('G'));
        assertTrue(player.getStorage().getPanel().getVector().get(0)=='G');

        //caso2: ho una sola risorsa nel 2o piano
        player.getStorage().getPanel().getVector().set(1,'W');
        assertTrue(player.addResourceStorage('W'));
        assertTrue(player.getStorage().getPanel().getVector().get(2)=='W'); //il terzo elemento (== il secondo sul secondo piano)
        //è quello inserito

        //caso3: ho risorse sul primo e secondo piano e un po' sul terzo e ne voglio aggiungere una in quest'ultimo
        for(int i=3; i<5 ;i++)player.getStorage().getPanel().getVector().set(i, 'Y');
        assertTrue(player.addResourceStorage('Y'));
        assertTrue(player.getStorage().getPanel().getVector().get(5)=='Y');

        //caso4a: ho lo storage completo e ho spazio in Extrapanel di tipo B. Dico che la risorsa da mettere è di tipo A
        assertTrue(player.addResourceStorage('A')); //questo stampa una volta 'No more space available' giustamente

        //caso4b: decido di aggiungere una risorsa in Extrapanel di tipo B
        assertTrue(player.addResourceStorage('B'));
        assertTrue(player.getStorage().getExtrapanel().getVector().get(0)=='B');
        assertTrue(player.getStorage().getExtrapanel().getVector().get(1)=='N');

        //caso 4c: elimino la risorsa in cima allo storage e aggiungo una rsorsa di tipo B. questa dovrebbe andare in extrapanel
        player.getStorage().getPanel().getVector().set(0,'N');
        assertTrue(player.addResourceStorage('B'));
        assertTrue(player.getStorage().getExtrapanel().getVector().get(1)=='B');



    }


    //FATTO
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