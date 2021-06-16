package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;

import java.util.ArrayList;

public class PlayerMsg extends NetworkMessage{
    private GameHandler player;
    private ArrayList<GameHandler> players;
    private boolean SingleorNot = false;
    private int signal;
    private int Lorenzo;
    private int VR;

    /**
     * Prepares the data of the Player (and the game because we need this message to build the playerboard)
     * we want the Server to send to the Client
     * @param player: data requested about the player
     * @param gamein: the game we are playing
     */
    public PlayerMsg(GameHandler player, Game gamein){
        this.player = player;
        players = new ArrayList<>();
        for (int i = 0; i < gamein.getPlayers().size(); i++) {
            players.add(gamein.getPlayers().get(i));
        }
        VR = Game.getVR();
        if(gamein.getClass().equals(SingleGame.class))
        {
            SingleorNot = true;
            Lorenzo = SingleGame.getLorenzo().getNumber();
            signal = SingleGame.getActionStructure().getActionSignal(SingleGame.getActionStructure().getOLD_AS_COUNTER());
        }
    }

    /**
     * Return data about the Player and game
     */
    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameHandler> getPlayers() {
        return players;
    }

    public boolean getSingleorNot() {
        return SingleorNot;
    }

    public int getSignal() {
        return signal;
    }

    public int getLorenzo() {
        return Lorenzo;
    }

    public int getVR() {
        return VR;
    }

}
