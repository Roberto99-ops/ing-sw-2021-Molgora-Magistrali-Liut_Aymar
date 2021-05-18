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
    public void VaticanReport(Player actualplayer, Game game) {

        //un player arriva per primo nell'area 1
        if ((actualplayer.getTrackposition()>=8)&&(Game.getVR()==0)) {
            //controllo se ci sono giocatori nell'area e do loro dei punti
            for (int i=0; i<game.getN_players(); i++){
                if (game.getPlayers().get(i).getTrackposition()>=5){
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv()+2));
                }
            }
            Game.setVR(1);//una volta completato il giro, Vr viene settato a 1
            return;
        }

        //un player arriva nell'area 2
        if ((actualplayer.getTrackposition()>=16)&&(Game.getVR()==1)) {
            //controllo se ci sono giocatori nell'area e do loro dei punti
            for (int i=0; i<Game.getN_players(); i++){
                if (game.getPlayers().get(i).getTrackposition()>=12){
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv()+3));
                }
            }
            game.setVR(2);//una volta completato il giro, Vr viene settato a 1
            return;
        }

        /*if ((Tposition>=16)&&(VR==1)) {
            PV+= 3; //il VR della seconda area vale 3 -> aggiungo i p.ti direttamente a Player
            actualplayer.setPv(PV);
            Game.setTimer_VR(0); //setto il timer a 0 per cominciare il conteggio dei turni
            return;
        } else if ((Tposition>=12)&&(VR==1)) {
            if ((timer_VR<(n_player))){
                PV+= 3;
                actualplayer.setPv(PV);
            }
            timer_VR++;
            Game.setTimer_VR(timer_VR); //una volta completato il giro, Vr viene settato a 2
            if (timer_VR==n_player){
                Game.setVR(2);//una volta completato il giro, Vr viene settato a 2
            }
            return;
        }*/

        //un player arriva nell'area 3

        if ((actualplayer.getTrackposition()>=24)&&(Game.getVR()==2)) {
            //controllo se ci sono giocatori nell'area e do loro dei punti
            for (int i=0; i<game.getN_players(); i++){
                if (game.getPlayers().get(i).getTrackposition()>=19){
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv()+4));
                }
            }
            game.setVR(3);//una volta completato il giro, Vr viene settato a 1
            return;
        }

        /*if ((Tposition>=24)&&(VR==2)) {
            PV+= 4; //il VR della terza area vale 4 -> aggiungo i p.ti direttamente a Player
            actualplayer.setPv(PV);
            Game.setTimer_VR(0); //setto il timer a 0 per cominciare il conteggio dei turni
        } else if ((Tposition>=19)&&(VR==2)) {
            if ((timer_VR<(n_player))){
                PV+= 4;
                actualplayer.setPv(PV);
            }
            timer_VR++;
            Game.setTimer_VR(timer_VR); //una volta completato il giro, Vr viene settato a 3
            if (timer_VR==n_player){
                Game.setVR(3);//una volta completato il giro, Vr viene settato a 3
            }

         */
            //chiama EndGame() e Victory()
            //Game.Endgame(Game.getPlayers().get(0)); //chiama poi victory()
    }

}



