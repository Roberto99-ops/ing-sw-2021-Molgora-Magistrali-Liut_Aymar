package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Player;

public class PlayerMsg extends NetworkMessage{
    private Player player;

    /**
     * Prepares the data of the Player we want the Server to send the Client
     * @param player: data requested about the player
     */
    public PlayerMsg(Player player){
        this.player=player;
    }
    /**
     * Returns data about a Player
     * @return player: data requested about the player
     */
    public Player getPlayer() {
        return player;
    }
}
