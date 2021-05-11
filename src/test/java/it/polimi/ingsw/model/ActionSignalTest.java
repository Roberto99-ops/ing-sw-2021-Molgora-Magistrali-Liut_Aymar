package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ActionSignalTest {

    /**
     * Tests if the number of a specific ActionSignal has been gotten correctly
     */
    @Test
    public void getNumber() {
        ActionSignal actionSignal= new ActionSignal();
        int num=5;
        actionSignal.setNumber(5);
        assertEquals(actionSignal.getNumber(),num);
    }

    /**
     * Tests if the action linked to a specific ActionSignal is called and executed correctly
     * @throws FileNotFoundException: excepection thrown when a Developecard is not set correctly
     */
    @Test
    public void action() throws FileNotFoundException {
        ActionSignal actionSignal= new ActionSignal();
        //creo un mazzo di 12 carte in Game
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
        //System.out.println(card1.getColour());
        actionSignal.Action(1); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(2).getStructure().get(0));
        //elimino un mazzo per controllare se Action:2 mi permette di spostarmi sul mazzo dopo e di effettuare l'azione del segnalino
        for (int i=0; i<4 ;i++){
            Game.getDevelopedecks(3).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(7).getStructure().get(2);
        actionSignal.Action(2); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(7).getStructure().get(0));
        //elimino tre carte da un mazzo ed eseguo Action:3 (questo metodo parte dal mazzo 0)
        for (int i=0; i<3 ;i++){
            Game.getDevelopedecks(0).getStructure().remove(0);
        }
        card1 = Game.getDevelopedecks(4).getStructure().get(1);
        actionSignal.Action(3); // prende il mazzo 2
        assertEquals(card1, Game.getDevelopedecks(4).getStructure().get(0));
        //elimino i tre mazzi di un certo tipo ed eseguo Action:4 (questo metodo parte dal mazzo 1)
        for (int c=1; c<11; c=c+4){
            for (int i=0; i<4; i++){
                Game.getDevelopedecks(c).getStructure().remove(0);
            }
        }
        actionSignal.Action(4);
        assertTrue(Game.getDevelopedecks(9).getStructure().isEmpty());

        //case #5-6: Lorenzo moves forward by +2
        Lorenzo lore = new Lorenzo();
        actionSignal.Action(5); //calls Lorenzomoves(2) -> position:2
        assertEquals(2,lore.getNumber());
        //so, Lorenzo can move forward by +1 or +2

        //controllo AS_Counter e considero il caso#7: Lorenzo si sposta in avanti di +1 e mischio i segnalini
        ActionStructure actionStructure = new ActionStructure();
        int[] arr = {1,2,3,4,5,6,7};
        actionStructure.setAS_Counter(6);
        actionStructure.PickSignal(); // chiamerà l'azione del segnalino 7 -> +1 e shuffle
        assertEquals(3, lore.getNumber());
        assertNotEquals(arr, actionStructure.getStructure()); //con il segnalino #7, ho anche lo shuffle della pila di segnalini
        assertEquals(0, actionStructure.getAS_Counter());
    }
}