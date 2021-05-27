package it.polimi.ingsw.model;

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
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        ArrayList<Player> plyr = new ArrayList<Player>();
        plyr.add(player1);
        plyr.add(player2);
        plyr.add(player3);
        game.setPlayers(plyr);
        game.setN_players(3);

        //sposto player1 nella posizione #8, dentro area1 per il VR
        for(int i=0; i<9;i++ ){
            player1.increaseTrackposition();
        }
        //sposto player2 nella posizione #5
        for(int i=0; i<5;i++ ){
            player2.increaseTrackposition();
        }
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.VaticanReport(player1, game);
        //controllo che il giocatore abbia guadagnato due PV per essere arrivato per primo nella prima area del VR
        assertEquals(2,player1.getPv()); //player1 ha guadagnato due PV
        assertEquals(2,player2.getPv()); //player2 ha guadagnato due PV
        assertEquals(0,player3.getPv()); //player3 non ha guadagnato due PV
        assertEquals(1,Game.getVR()); //VR è stato settato a 1

        //sposto player3 nella posizione #16, dentro area2 per il VR
        for(int i=0; i<16;i++ ){
            player3.increaseTrackposition();
        }
        //sposto player2 nella posizione #12
        for(int i=0; i<7;i++ ){
            player2.increaseTrackposition();
        }
        faithTrack.VaticanReport(player3, game); //player3 chiama per primo il VR dell'area 2
        //controllo che il giocatore abbia guadagnato tre PV per essere arrivato per primo nella prima area del VR
        assertEquals(2,player1.getPv()); //player1 ha guadagnato due PV
        assertEquals(5,player2.getPv()); //player2 ha guadagnato due PV
        assertEquals(3,player3.getPv()); //player3 non ha guadagnato due PV
        assertEquals(2,Game.getVR()); //VR è stato settato a 1

        //sposto player1 nella posizione #24, dentro area3 per il VR
        for(int i=0; i<15;i++ ){
            player1.increaseTrackposition();
        }
        //sposto player2 nella posizione #19
        for(int i=0; i<7;i++ ){
            player2.increaseTrackposition();
        }
        faithTrack.VaticanReport(player1, game); //player3 chiama per primo il VR dell'area 2
        //controllo che il giocatore abbia guadagnato tre PV per essere arrivato per primo nella prima area del VR
        assertEquals(6,player1.getPv()); //player1 ha guadagnato due PV
        assertEquals(9,player2.getPv()); //player2 ha guadagnato due PV
        assertEquals(3,player3.getPv()); //player3 non ha guadagnato due PV
        assertEquals(3,Game.getVR()); //VR è stato settato a 1

    }
}