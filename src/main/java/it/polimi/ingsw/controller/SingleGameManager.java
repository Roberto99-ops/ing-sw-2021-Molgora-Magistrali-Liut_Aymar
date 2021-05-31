package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;

import java.io.IOException;

/**
 * it manages a game in singleplayer. it doesn't extend GameManger because
 * it have would simply overrided the main method that is static and that's not possible.
 */
public class SingleGameManager{

    private static SingleGame game;
    private static ClientHandler client;

    /**
     * costructor. the clienthandler istance is passed so is easy to
     * separate controller and network.
     * @param clientin: the clienthandler that manages the player.
     */
    public SingleGameManager(ClientHandler clientin) {
        game = new SingleGame();
        client = clientin;
    }

    public static void main() throws IOException {
        Player player = client.getPlayer();
        PlayerMsg msg = new PlayerMsg(player);
        client.sendMessage(msg);
        game.getPlayers().add(player);
        client.sendMessage("Turn Finished");
    }
}
