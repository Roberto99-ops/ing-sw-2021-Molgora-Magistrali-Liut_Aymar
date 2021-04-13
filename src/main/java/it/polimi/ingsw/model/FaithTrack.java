package it.polimi.ingsw.model;

public class FaithTrack {
    private int PV_Track = 0; //a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
    //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
    //oppure si può usare il PV di PLayer?


    /**
     * checks if a player has reached the Pope Space and the others are inside or not a Vatican Report Section
     * VR changes according to which area is reached by the player ahead, so the others will not be able to call
     * the Vatican Report again when they reach that area.
     */
    public void VaticanReport() {
        int i, VR, Tposition, Pnumber;
        Player player = new Player();
        Tposition = player.getTrackposition(); //riceve la posizione in cui si trova il segnalino sul tracciato
        Game game = new Game();
        VR = game.getVR(); //prendo il valore attuale di VR

        // caso in cui un giocatore arrivi per primo a fare il VR
        if ((Tposition >= 8 && Tposition <= 15) && VR == 0) {
            PV_Track += 2; //il VR della prima area vale 2
            //controllo che i giocatori si trovino nell'area del VR e se lo sono sommo i punti
            for (i = 0; i < 3; i++) {
                if (Tposition > 4 && Tposition < 9) {
                    PV_Track += 2;
                }
            }
            game.setVR(1); //setta il VR di tutti i giocatori a 1 perchè la prima area è stata superata
        } else if ((Tposition >= 16 && Tposition <= 23) && VR == 1) {
            PV_Track += 3; // seconda area vale 3
            for (i = 0; i < 3; i++) {
                if (Tposition > 11 && Tposition < 17) {
                    PV_Track += 3;
                }
            }
            game.setVR(2);
        } else if ((Tposition == 24) && VR == 2) {
            PV_Track += 4; // terza area vale 4
            for (i = 0; i < 3; i++) {
                if (Tposition > 18) {
                    PV_Track += 4;
                }
            }
            //chiama EndGame() e Victory()
            game.Endgame(player); //chiama poi victory()
        }

    }

}

