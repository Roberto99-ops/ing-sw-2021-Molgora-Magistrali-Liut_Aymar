package it.polimi.ingsw.model;

import it.polimi.ingsw.Server.GameHandler;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FaithTrackTest {

    /**
     * Tests if the VaticanReport is called and executed correctly in different situations
     */

    @Test
    public void vaticanReport() {
        Game game = new Game();
        GameHandler player1 = new GameHandler();
        GameHandler player2 = new GameHandler();
        GameHandler player3 = new GameHandler();
        ArrayList<GameHandler> plyr = new ArrayList<GameHandler>();
        plyr.add(player1);
        plyr.add(player2);
        plyr.add(player3);
        game.setPlayers(plyr);
        game.setN_players(3);


        for(int i=0; i<8;i++ ) {
            player1.increaseTrackPosition();
        }

        for(int i=0; i<5;i++ ) {
            player2.increaseTrackPosition();
        }

        FaithTrack faithTrack = new FaithTrack();
        faithTrack.callVaticanReport(player1, game);

        assertEquals(4, player1.getPv());
        assertEquals(3, player2.getPv());
        assertEquals(0, player3.getPv());
        assertEquals(1, Game.getVR());


        for(int i=0; i<16;i++ ){
            player3.increaseTrackPosition();
        }

        for(int i=0; i<7;i++ ){
            player2.increaseTrackPosition();
        }
        faithTrack.callVaticanReport(player3, game);
        assertEquals(4,player1.getPv());
        assertEquals(11,player2.getPv());
        assertEquals(12,player3.getPv());
        assertEquals(2,Game.getVR());

        for(int i=0; i<16;i++ ) {
            player1.increaseTrackPosition();
        }

        for(int i=0; i<9;i++ ) {
            player2.increaseTrackPosition();
        }

        for(int i=0; i<2;i++ ) {
            player3.increaseTrackPosition();
        }

        faithTrack.callVaticanReport(player1, game);

        assertEquals(26,player1.getPv());
        assertEquals(25,player2.getPv());
        assertEquals(15,player3.getPv());
        assertEquals(3,Game.getVR());

    }
}