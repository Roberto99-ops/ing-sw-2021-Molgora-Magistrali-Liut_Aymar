package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopCard;
import it.polimi.ingsw.model.DevelopDecks;
import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class DevelopDecksViewTest extends TestCase {

    public void testPrint() throws FileNotFoundException {
        DevelopDecks[] decks = new DevelopDecks[12];
        for (int i = 0; i < 12; i++) {
            //DevelopeDecks deck = new DevelopeDecks();
            decks[i] = new DevelopDecks();
            DevelopCard card = new DevelopCard();
            card.setCard(i);
            //deck.getStructure().add(card);
            decks[i].getStructure().add(card);
        }

        decks[6].getStructure().remove(0);
        DevelopeDecksView Dview = new DevelopeDecksView(decks);
        Dview.print();
    }
}