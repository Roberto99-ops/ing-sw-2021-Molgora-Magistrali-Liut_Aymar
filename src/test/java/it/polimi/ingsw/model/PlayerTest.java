package it.polimi.ingsw.model;

import org.junit.Test;

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
        player.getStorage().getExtrapanel().getVector().set(0,'G');
        player.getStorage().getExtrapanel().getVector().set(1,'G');

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


        //assertTrue(player.getStorage().getPanel().get(2)=='N');
        //assertTrue(player.getStorage().getPanel().get(4)=='N');
        //assertTrue(player.getStorage().getPanel().get(5)=='N');

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

    //FATTO
    @Test
    public void removeResource(){
        Player player = new Player();
        player.getStorage().setTypeExtrapanel('G');
        //inizializzo lo storage (quindi panel ed extrapanel)
        //considero panel ed extrapanel con elementi
        player.getStorage().getPanel().set(0,'G');
        for (int i=1; i<3; i++){
            player.getStorage().getPanel().set(i,'B');
        }
        for (int i=3; i<6; i++){
            player.getStorage().getPanel().set(i,'Y');
        }
        player.getStorage().getExtrapanel().add('G');
        player.getStorage().getExtrapanel().add('G');
        //scelgo la risorsa da eliminare
        char resource = 'B';
        assertTrue(player.removeResourceStorage(resource));
        assertTrue(player.getStorage().getPanel().get(2)=='N');


/* (PRECEDENTE-SBAGLIATO)
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
*/
    }

    //FATTO
    @Test
    public void addResourceStorage() {
        Player player = new Player();
        player.getStorage().setTypeExtrapanel('B');
        //caso1: storage vuoto in cui aggiungo il primo elemnto in cima
        //considero uno storage vuoto
        for(int i=0; i<6; i++){
            player.getStorage().getPanel().add('N'); //N: null= non c'è niente
        }
        for (int i=0; i<2; i++){
            player.getStorage().getExtrapanel().getVector().add('N');
        }
        //riempiamo lo storage (in cima) del player con una sola risorsa
        assertTrue(player.addResourceStorage('G'));
        assertTrue(player.getStorage().getPanel().get(0)=='G');

        //caso2: ho una sola risorsa nel 2o piano
        player.getStorage().getPanel().set(1,'P');
        assertTrue(player.addResourceStorage('P'));
        assertEquals('P', (char) player.getStorage().getPanel().get(2)); //il terzo elemento (== il secondo sul secondo piano)
        //è quello inserito

        //caso3: ho risorse sul primo e secondo piano e un po' sul terzo e ne voglio aggiungere una in quest'ultimo
        for(int i=3; i<5 ;i++)player.getStorage().getPanel().set(i, 'Y');
        assertTrue(player.addResourceStorage('Y'));
        assertEquals('Y', (char) player.getStorage().getPanel().get(5));

        for(int i=0; i<5;i++){
            System.out.println(player.getStorage().getPanel().get(i));
        }
        for(int i=0; i<2;i++){
            System.out.println(player.getStorage().getExtrapanel().getVector().get(i));
        }
        //caso4a: ho lo storage completo e ho spazio in Extrapanel di tipo B. Dico che la risorsa da mettere è di tipo A
        assertTrue(player.addResourceStorage('A')); //questo stampa una volta 'No more space available' giustamente

        //caso4b: decido di aggiungere una risorsa in Extrapanel di tipo B
        assertTrue(player.addResourceStorage('B'));
        /*for(int i=0; i<5;i++){
            System.out.println(player.getStorage().getPanel().get(i));
        }
        for(int i=0; i<2;i++){
            System.out.println(player.getStorage().getExtrapanel().getVector().get(i));
        }*/
        //System.out.println(player.getStorage().getExtrapanel().getVector().get(0)+"  "+player.getStorage().countTypeS('N'));
        assertEquals('B', (char) player.getStorage().getExtrapanel().getVector().get(0));
        assertEquals('N', (char) player.getStorage().getExtrapanel().getVector().get(1));
        System.out.println(player.getStorage().getExtrapanel().getVector().get(0));
        System.out.println(player.getStorage().getExtrapanel().getVector().get(1));

        //caso 4c: elimino la risorsa in cima allo storage e aggiungo una rsorsa di tipo B. questa dovrebbe andare in extrapanel
        player.getStorage().getPanel().set(0,'N');
        assertTrue(player.addResourceStorage('B'));
        assertEquals('B', (char) player.getStorage().getExtrapanel().getVector().get(1));
        System.out.println(player.getStorage().getPanel().get(0));
        System.out.println(player.getStorage().getExtrapanel().getVector().get(1));

        //caso 5: considero storage vuoto e gli aggiungo un vettore di risorse
        player.getStorage().setTypeExtrapanel('Z');
        /*for (int i=0; i<6; i++) {
            player.getStorage().getPanel().set(i, 'N');
        }
        for (int i=0; i<2; i++){
            player.getStorage().getExtrapanel().getVector().set(i,'N');
        }*/
        Character[] vector = {'B','G','G','B'};
        for(int i=0; i<4; i++){
            player.addResourceStorage(vector[i]);
        }
        for (int i=0; i<6; i++ ) {
            System.out.println(player.getStorage().getPanel().get(i));
        }



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