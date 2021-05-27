package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class GameTest {

    Game giocotest = new Game();
    Player francesco = new Player();


    @Test

    public void endgametest() throws FileNotFoundException {

        francesco.setName("Francesco");

        //caso 1a: francesco arriva alla fine del tracciato
        //sposto il giocatore alla posizione 24

        for(int i=0; i<24 ;i++) francesco.increaseTrackposition();
        //controllo che il nome restituito sia francesco
        giocotest.Endgame(francesco);
        assertTrue(giocotest.Endgame(francesco));
        System.out.println("game finished because francesco have reached 24th position");

        //considero ora la posizione di francesco in 23

        francesco.setTrackposition(23);
        assertFalse(giocotest.Endgame(francesco));
        System.out.println("game is not finished because francesco haven't reached 24th position");

        //caso2a: il giocatore ha solo 6 carte sviluppo
        //aumento la quantità di carte di francesco a 6

        for (int i=0; i<6;i++) francesco.increaseDevelopQuantity();
        giocotest.Endgame(francesco);
        assertFalse(giocotest.Endgame(francesco));
        System.out.println("game is not finished because francesco haven't got 7 develope cards");

        //caso 2b: francesco compra la settima carta sviluppo
        francesco.increaseDevelopQuantity();
        giocotest.Endgame(francesco);
        assertTrue(giocotest.Endgame(francesco));
        System.out.println("game is finished because francesco have got 7 develope cards");

    }

    }


