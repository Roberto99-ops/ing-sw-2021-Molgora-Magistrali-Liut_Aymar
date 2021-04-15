package it.polimi.ingsw.model;

public class FaithTrack {
    //private int PV_Track = 0; a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
    //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
    //oppure si può usare il PV di PLayer?


    /**
     * checks if a player has reached the Pope Space and the others are inside or not a Vatican Report Section
     * VR changes according to which area is reached by the player ahead, so the others will not be able to call
     * the Vatican Report again when they reach that area.
     */
    public void VaticanReport() {
        int VR, Tposition, PV,timer_VR, n_player;
        Player player = new Player();
        Tposition = player.getTrackposition(); //riceve la posizione in cui si trova il segnalino sul tracciato
        Game game = new Game();
        timer_VR=Game.getTimer_VR();
        VR = Game.getVR(); //prendo il valore attuale di VR
        PV = player.getPv();
        n_player = Game.getN_players();

        //un player arriva per primo nell'area 1
        if ((Tposition>=8)&&(VR==0)) {
            PV+= 2; //il VR della prima area vale 2
            player.setPv(PV);
            Game.setTimer_VR(0); //setto il timer a 0 per cominciare il conteggio dei turni
            return;
        } else if ((Tposition>=5)&&(VR==0)) {
            if ((timer_VR<(n_player))){
                PV+= 2;
                player.setPv(PV);
            }
            timer_VR++;
            Game.setTimer_VR(timer_VR); //una volta completato il giro, Vr viene settato a 1
            return;
        }

        //un player arriva nell'area 2
        if ((Tposition>=16)&&(VR==1)) {
            PV+= 3; //il VR della seconda area vale 3 -> aggiungo i p.ti direttamente a Player
            player.setPv(PV);
            Game.setTimer_VR(0); //setto il timer a 0 per cominciare il conteggio dei turni
            return;
        } else if ((Tposition>=12)&&(VR==1)) {
            if ((timer_VR<(n_player))){
                PV+= 3;
                player.setPv(PV);
            }
            timer_VR++;
            Game.setTimer_VR(timer_VR); //una volta completato il giro, Vr viene settato a 2
            return;
        }

        //un player arriva nell'area 3
        if ((Tposition>=24)&&(VR==2)) {
            PV+= 4; //il VR della terza area vale 4 -> aggiungo i p.ti direttamente a Player
            player.setPv(PV);
            Game.setTimer_VR(0); //setto il timer a 0 per cominciare il conteggio dei turni
        } else if ((Tposition>=19)&&(VR==2)) {
            if ((timer_VR<(n_player))){
                PV+= 4;
                player.setPv(PV);
            }
            timer_VR++;
            Game.setTimer_VR(timer_VR); //una volta completato il giro, Vr viene settato a 3
            if (VR>=(n_player-1)) {
                //chiama EndGame() e Victory()
                game.Endgame(player); //chiama poi victory()
            }
        }
    }

}

