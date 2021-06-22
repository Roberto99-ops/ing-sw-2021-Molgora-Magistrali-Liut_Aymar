package it.polimi.ingsw.model;

import java.io.Serializable;

public class FaithTrack implements Serializable {


    /**
     * checks if a player has reached the Pope Space and the others are inside or not a Vatican Report Section
     * VR changes according to which area is reached by the player ahead, so the others will not be able to call
     * the Vatican Report again when they reach that area.
     * @param actualplayer
     * @param game
     */


    public void callVaticanReport(Player actualplayer, Game game) {

            if ((actualplayer.getTrackPosition() >= 8) && (Game.getVR() == 0)) {


            for (int i = 0; i < game.getN_players(); i++) {
                if (game.getPlayers().get(i).getTrackPosition() >= 5) {
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv() + 2));
                }
            }
                Game.setVR(1);
                return;
            }



        if ((actualplayer.getTrackPosition()>=16 && (Game.getVR() == 1))) {

            for (int i=0; i<Game.getN_players(); i++){
                if (game.getPlayers().get(i).getTrackPosition()>=12){
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv()+3));
                }
            }
            game.setVR(2);
            return;
        }


        if ((actualplayer.getTrackPosition()>=24 && (Game.getVR() == 2))) {

            for (int i=0; i<game.getN_players(); i++){
                if (game.getPlayers().get(i).getTrackPosition()>=19) {
                    game.getPlayers().get(i).setPv((game.getPlayers().get(i).getPv()+4));
                }
            }

            game.setVR(3);
            return;
        }

    }

}



