package it.polimi.ingsw.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SingleGameTest {
    Player plyr = new Player();
    Player Lore;


    @Test
    public void main() throws Exception {
        SingleGame.main();

    }

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
        for(int i=0; i<24 ;i++)  plyr.increaseTrackposition();
        //controllo che il nome restituito sia quello del giocatore plyr
        singleGame.Endgame(plyr);
        assertTrue(singleGame.Endgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());

        //considero ora la posizione di plyr in 23
        plyr.setTrackposition(23);

        //caso2a: il giocatore ha solo 6 carte sviluppo
        //aumento la quantità di carte di plyr a 6
        for (int i=0; i<6;i++) plyr.increaseDevelopQuantity();
        singleGame.Endgame(plyr);
        assertFalse(singleGame.Endgame(plyr));
        //caso 2b: plyr compra la settima carta sviluppo
        plyr.increaseDevelopQuantity();
        singleGame.Endgame(plyr);
        assertTrue(singleGame.Endgame(plyr));
        assertEquals("Ale",singleGame.getWinner().getName());

        //tolgo una carta a plyr: ora ha 6 carte
        plyr.setDevelopementquantity(6);

        //caso 3 : tre mazzi dello stesso tipo sono tutti vuoti
        //svuoto i mazzi #0,4,8
        for (int i=0; i<9; i=i+4) {
            for (int c = 0; c < 4; c++) {
                Game.getDevelopedecks(i).getStructure().remove(0);
                //Game.getDevelopedecks(i).getStructure().add(null);
            }
        }
        System.out.println(plyr.getDevelopementquantity()+"   "+ plyr.getTrackposition()+"   "+Game.getDevelopedecks(0).getStructure());
        singleGame.Endgame(plyr);
        assertTrue(singleGame.Endgame(plyr));
        assertEquals("Lorenzo il Magnifico", singleGame.getWinner().getName());

    }

    @Test
    public void victory() {
        SingleGame singleGame = new SingleGame();
        plyr.setName("Ale");
        SingleGame.setWinner(plyr);
        assertEquals("Ale", singleGame.Victory() );
    }
}