package it.polimi.ingsw.model;

import it.polimi.ingsw.Server.ObserverGame;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    Game giocotest = new Game();
    ObserverGame francesco = new ObserverGame();
    ObserverGame roberto = new ObserverGame();
    ObserverGame juan = new ObserverGame();


    @Test

    public void shuffletest() throws FileNotFoundException {

    Market market = new Market();
        market.setExtraball('B');
        market.setResourceinMarket(0,0,'B');
        market.setResourceinMarket(0,1,'G');
        market.setResourceinMarket(0,2,'G');
        market.setResourceinMarket(0,3,'P');
        market.setResourceinMarket(1,0,'P');
        market.setResourceinMarket(1,1,'R');
        market.setResourceinMarket(1,2,'Y');
        market.setResourceinMarket(1,3,'Y');
        market.setResourceinMarket(2,0,'W');
        market.setResourceinMarket(2,1,'W');
        market.setResourceinMarket(2,2,'W');
        market.setResourceinMarket(2,3,'W');

        giocotest.shuffle();
    Game.getMarket().printMatrix();
    assertNotEquals(market, Game.getMarket());

    }

    @Test

    public void endgametest() throws FileNotFoundException {

        francesco.setName("Francesco");

        //caso 1a: francesco arriva alla fine del tracciato
        //sposto il giocatore alla posizione 24

        for(int i=0; i<24 ;i++) francesco.increaseTrackposition();
        //controllo che il nome restituito sia francesco
        giocotest.callEndgame(francesco);
        assertTrue(giocotest.callEndgame(francesco));
        System.out.println("game finished because francesco have reached 24th position");

        //considero ora la posizione di francesco in 23

        francesco.setTrackposition(23);
        assertFalse(giocotest.callEndgame(francesco));
        System.out.println("game is not finished because francesco haven't reached 24th position");

        //caso2a: il giocatore ha solo 6 carte sviluppo
        //aumento la quantità di carte di francesco a 6

        for (int i=0; i<6;i++) francesco.increaseDevelopQuantity();
        giocotest.callEndgame(francesco);
        assertFalse(giocotest.callEndgame(francesco));
        System.out.println("game is not finished because francesco haven't got 7 develope cards");

        //caso 2b: francesco compra la settima carta sviluppo
        francesco.increaseDevelopQuantity();
        giocotest.callEndgame(francesco);
        assertTrue(giocotest.callEndgame(francesco));
        System.out.println("game is finished because francesco have got 7 develope cards");

    }



    @Test


    /// ???????? non va!


    public void victorygametest() throws FileNotFoundException {

        ArrayList<ObserverGame> players = new ArrayList<ObserverGame>();
        giocotest.setPlayers(players);
        giocotest.getPlayers().add(francesco);
        giocotest.getPlayers().add(roberto);
        giocotest.getPlayers().add(juan);



        francesco.setName("Francesco");
        roberto.setName("Roberto");
        juan.setName("Juan");

        francesco.setPv(31);
        roberto.setPv(45);
        juan.setPv(28);

        francesco.setTrackposition(19);
        roberto.setTrackposition(20);
        juan.setTrackposition(24);


        assertNotEquals("Francesco", giocotest.callVictory());
        System.out.println("Francesco is not the winner");
        assertEquals("Juan", giocotest.callVictory());
        System.out.println("Juan is the winner");
        assertNotEquals("Roberto", giocotest.callVictory());
        System.out.println("Roberto is the winner");

    }


    }


