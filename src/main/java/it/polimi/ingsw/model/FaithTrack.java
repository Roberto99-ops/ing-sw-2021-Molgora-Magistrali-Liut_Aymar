package it.polimi.ingsw.model;

import java.io.Serializable;

public class FaithTrack implements Serializable {


    private int PV_Track = 0;
    // a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
    //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
    //oppure si può usare il PV di PLayer?


    /**
     * checks if a player has reached the Pope Space and the others are inside or not a Vatican Report Section
     * VR changes according to which area is reached by the player ahead, so the others will not be able to call
     * the Vatican Report again when they reach that area.
     */
    public void callVaticanReport(Player actualplayer, Game game) {

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


    }

}



