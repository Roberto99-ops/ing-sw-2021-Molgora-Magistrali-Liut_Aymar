package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SingleGameTest {
    Player plyr = new Player();


    /**
     * Tests if SingleGame class checks correctly the situation of every player on its turn and if it calls the end of
     * the game
     * @throws FileNotFoundException
     */

    @Test
    public void endgame() throws FileNotFoundException {

        SingleGame singleGame = new SingleGame();

        for (int n=0; n<12 ;n++) {
            DevelopeDecks deck = new DevelopeDecks();
            for (int i = 0; i < 4; i++) {
                DevelopeCard card = new DevelopeCard();
                card.setCard(i);
                deck.getStructure().add(card);
            }

            Game.setDevelopedecks(deck, n);
        }

        plyr.setName("Ale");

        for(int i=0; i<24 ;i++)  plyr.increaseTrackPosition();

        singleGame.callEndgame(plyr);
        assertTrue(singleGame.callEndgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());

         plyr.setTrackPosition(1);


        for (int i=0; i<6;i++) plyr.increaseDevelopQuantity();
        singleGame.callEndgame(plyr);
        assertFalse(singleGame.callEndgame(plyr));

        plyr.increaseDevelopQuantity();
        singleGame.callEndgame(plyr);
        assertTrue(singleGame.callEndgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());


        plyr.setDevelopmentQuantity(6);


        for (int i=0; i<9; i=i+4) {
            for (int c = 0; c < 4; c++) {
                Game.getDevelopedecks(i).getStructure().remove(0);

            }
        }
        ;
        singleGame.callEndgame(plyr);
        assertTrue(singleGame.callEndgame(plyr));
        assertEquals("Lorenzo il Magnifico", singleGame.getWinner().getName());

    }


    /**
     * Tests if the winner is set correctly
     */

    @Test
    public void victory() {
        SingleGame singleGame = new SingleGame();
        plyr.setName("Ale");
        SingleGame.setWinner(plyr);
        assertEquals("Ale", singleGame.callVictory() );
    }
}