package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;
import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class DevelopeDecksViewTest extends TestCase {

    public void testPrint() throws FileNotFoundException {
        DevelopeDecks[] decks = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            //DevelopeDecks deck = new DevelopeDecks();
            decks[i] = new DevelopeDecks();
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            //deck.getStructure().add(card);
            decks[i].getStructure().add(card);
        }

        decks[6].getStructure().remove(0);
        DevelopeDecksView Dview = new DevelopeDecksView(decks);
        Dview.Print();
    }
}