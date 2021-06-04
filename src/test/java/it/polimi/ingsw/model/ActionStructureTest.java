package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ActionStructureTest {

    /**
     * Tests if the action of a specific signal is correctly called.
     * Since this class and its method 'pickSignal' calls all the methods in ActionSignal, it also verifies that class
     */
    @Test
    public void pickSignal() throws FileNotFoundException {
        ActionSignal Signal = new ActionSignal();
        //case #1-4: deletes two cards from a Develope Deck
        //sets all 12 decks with four cards

        for (int n=0; n<12 ;n++) {
            DevelopeDecks deck = new DevelopeDecks();
            for (int i = 0; i < 4; i++) {
                DevelopeCard card = new DevelopeCard();
                card.setCard(i);
                deck.getStructure().add(card);
            }
            //creo un mazzo con 4 carte e lo inserisco dentro l'array di Developedeck
            Game.setDevelopedecks(deck, n);
        }

        //prendo la prima carta del secondo mazzo di Developedecks
        DevelopeCard card1 = Game.getDevelopedecks(2).getStructure().get(2);
        DevelopeCard card2 = Game.getDevelopedecks(2).getStructure().get(0);
        //System.out.println(card1.getColour());
        Signal.action(1); // prende il mazzo blu
        assertNotEquals(card2, Game.getDevelopedecks(2).getStructure().get(0));
        assertEquals(card1, Game.getDevelopedecks(2).getStructure().get(0));
        //elimino un mazzo per controllare se Action=2 mi permette di spostarmi sul mazzo dopo e di effettuare l'azione del segnalino
        for (int i=0; i<4 ;i++){
            Game.getDevelopedecks(3).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(7).getStructure().get(2);
        Signal.action(2); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(7).getStructure().get(0));
        //elimino tre carte da un mazzo ed eseguo Action:3 (questo metodo parte dal mazzo 0)
        for (int i=0; i<3 ;i++){
            Game.getDevelopedecks(0).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(4).getStructure().get(1);
        Signal.action(3); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(4).getStructure().get(0));
        //elimino i tre mazzi di un certo tipo ed eseguo Action=4 (questo metodo parte dal mazzo 1)
        for (int c=1; c<11; c=c+4){
            for (int i=0; i<4; i++){
                Game.getDevelopedecks(c).getStructure().remove(0);
            }
        }
        Signal.action(4);
        assertTrue(Game.getDevelopedecks(9).getStructure().isEmpty());

        //case #5-6: Lorenzo moves forward by +2
        //Lorenzo lore = new Lorenzo();
        //System.out.println(SingleGame.getLorenzo().getNumber());
        Signal.action(5); //calls Lorenzomoves(2) -> position:2
        //System.out.println(SingleGame.getLorenzo().getNumber());
        assertEquals(2,SingleGame.getLorenzo().getNumber());
        //so, Lorenzo can move forward by +1 or +2

        //controllo AS_Counter e considero il caso#7: Lorenzo si sposta in avanti di +1 e mischio i segnalini
        ActionStructure actionStructure = new ActionStructure();
        int[] arr = {1,2,3,4,5,6,7};
        actionStructure.setAS_Counter(6);
        actionStructure.pickSignal(); // chiamerà l'azione del segnalino 7 -> +1 e shuffle
        assertEquals(3, SingleGame.getLorenzo().getNumber());
        assertNotEquals(arr, actionStructure.getStructure()); //con il segnalino #7, ho anche lo shuffle della pila di segnalini
        assertEquals(0, actionStructure.getAS_Counter());

        SingleGame.getLorenzo().setNumber(0);




    }

    /**
     * Tests if the new shuffled Actions' Structure is different from the previous structure
     */
    @Test
    public void shuffleSignal() {
        //case #7: shuffling the actions' structure
        int[] structure = {1,2,3,4,5,6,7};
        ActionStructure actionStructure = new ActionStructure();
        actionStructure.shuffleSignal();
        /*int[] temp;
        temp=actionStructure.getStructure();
        for(int i=0; i<7;i++) {
            System.out.println(temp[i]);
        }*/
        //Collections.shuffle(Arrays.asList(ActionStructure.getStructure()));
        //confront actionStructure.structure and this.structure
        assertNotEquals(structure, actionStructure.getStructure());


    }
}