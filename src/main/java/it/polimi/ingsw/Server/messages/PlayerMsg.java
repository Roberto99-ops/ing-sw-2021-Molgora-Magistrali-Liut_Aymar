package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ActionSignal;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;

import java.util.ArrayList;

public class PlayerMsg extends NetworkMessage{
    private Player player;
    private ArrayList<Player> players;
    private boolean SingleorNot = false;
    private int signal;
    private int Lorenzo;

    /**
     * Prepares the data of the Player (and the game because we need this message to build the playerboard)
     * we want the Server to send to the Client
     * @param player: data requested about the player
     * @param gamein: the game we are playing
     */
    public PlayerMsg(Player player, Game gamein){
        this.player = player;
        players = new ArrayList<>();
        for (int i = 0; i < gamein.getPlayers().size(); i++) {
            players.add(gamein.getPlayers().get(i));
        }

        if(gamein.getClass().equals(SingleGame.class))
        {
            SingleorNot = true;
            Lorenzo = SingleGame.getLorenzo().getNumber();
            signal = SingleGame.getActionStructure().getActionSignal(SingleGame.getActionStructure().getAS_Counter());
            System.out.println(signal + "playermsg");
        }
    }

    /**
     * Return data about the Player and game
     */
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Player> getPlayers() {
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


}
