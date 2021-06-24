package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class SingleGameTest {
    Player plyr = new Player();
    Player Lore;


    /**
     * Tests if SingleGame class checks correctly the situation of every player on its turn and if it calls the end of
     * the game
     * @throws FileNotFoundException
     */
    @Test
    public void endgame() throws FileNotFoundException {

        SingleGame singleGame = new SingleGame();
        //mi creo 12 mazzi di carte sviluppo (con 4 carte ciascuno)
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

        plyr.setName("Ale");

        //caso 1: Giocatore arriva alla fine del tracciato
        //sposto il giocatore alla posizione 24
        for(int i=0; i<24 ;i++)  // plyr.increaseTrackPosition();
        //controllo che il nome restituito sia quello del giocatore plyr
        singleGame.callEndgame(plyr);
        assertTrue(singleGame.callEndgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());

        //considero ora la posizione di plyr in 23
        // plyr.setTrackPosition(23);

        //caso2a: il giocatore ha solo 6 carte sviluppo
        //aumento la quantità di carte di plyr a 6
        for (int i=0; i<6;i++) plyr.increaseDevelopQuantity();
        singleGame.callEndgame(plyr);
        assertFalse(singleGame.callEndgame(plyr));
        //caso 2b: plyr compra la settima carta sviluppo
        plyr.increaseDevelopQuantity();
        singleGame.callEndgame(plyr);
        assertTrue(singleGame.callEndgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());

        //tolgo una carta a plyr: ora ha 6 carte
        // plyr.setDevelopmentQuantity(6);

        //caso 3 : tre mazzi dello stesso tipo sono tutti vuoti
        //svuoto i mazzi #0,4,8
        for (int i=0; i<9; i=i+4) {
            for (int c = 0; c < 4; c++) {
                Game.getDevelopedecks(i).getStructure().remove(0);
                //Game.getDevelopedecks(i).getStructure().add(null);
            }
        }
        // System.out.println(plyr.getDevelopmentQuantity()+"   "+ plyr.getTrackPosition()+"   "+Game.getDevelopedecks(0).getStructure());
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