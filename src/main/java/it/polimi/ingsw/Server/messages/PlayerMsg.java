package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;

import java.util.ArrayList;

public class PlayerMsg extends NetworkMessage{
    private GameHandler player;
    private ArrayList<GameHandler> players;
    private boolean SingleOrNot = false;
    private int signal;
    private int Lorenzo;
    private int VR;

    /**
     * Prepares the data of the Player (and the game because we need this message to build the PlayerBoard)
     * We want the Server to send info about the Game to the Client
     * @param player: data requested that concerns the player and his/her belongings
     * @param gameIn: the game we are playing
     */

    public PlayerMsg(GameHandler player, Game gameIn){
        this.player = player;
        players = new ArrayList<>();
        for (int i = 0; i < gameIn.getPlayers().size(); i++) {
            players.add(gameIn.getPlayers().get(i));
        }
        VR = Game.getVR();
        if(gameIn.getClass().equals(SingleGame.class))
        {
            SingleOrNot = true;
            Lorenzo = SingleGame.getLorenzo().getNumber();
            signal = SingleGame.getActionStructure().getOLD_Signal();
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

    public boolean getSingleOrNot() {
        return SingleOrNot;
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
