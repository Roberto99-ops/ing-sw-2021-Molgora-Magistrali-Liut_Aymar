package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.LeaderDeck;
import junit.framework.TestCase;

import java.io.FileNotFoundException;

public class LeaderChooseViewTest extends TestCase {

    public void testPrint() throws FileNotFoundException {
        LeaderDeck deck = new LeaderDeck();
        for (int i = 0; i < 4; i++) {
            LeaderCard card = new LeaderCard();
            card.setCard(i);
            deck.getStructure().add(card);
        }

        LeaderChooseView Lchoose = new LeaderChooseView(deck);
        Lchoose.Print();
    }
}