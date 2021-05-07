package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.ArrayList;

public class SingleGameTest {

    @Test
    public void main() {
    }

    @Test
    public void endgame() {
        SingleGame singleGame = new SingleGame();
        Player player = new Player();
        ArrayList<Player> players = new ArrayList<>();

        //creare i dodici deck sviluppo e lasciarne 3 vuoti



        //creo un arraylist di players e ci aggiungo il giocatore fittizio
        singleGame.getPlayers().add(player);
        DevelopeDecks developeDecks = new DevelopeDecks();
        player.setDevelopementquantity(7);
        singleGame.Endgame(player);
        //ora dico che il numero di carte sviluppo del giocatore è sceso a 6
        player.setDevelopementquantity(6);
        //sposto il giocatore alla fine del tracciato fede
        while (player.getTrackposition()!=24){
            player.increaseTrackposition();
        }
        //ora il player è nella posizione 24 == fine del tracciato
        singleGame.Endgame(player);

    }

    @Test
    public void victory() {
    }
}