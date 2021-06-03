package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.Server.ObserverSingleGame;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;
import it.polimi.ingsw.model.Turn;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * it manages a game in singleplayer. it doesn't extend GameManger because
 * it would have simply overrided the main method that is static and that's not possible.
 */
public class SingleGameManager {

    private static Game game;
    private static ClientHandler client;
    private static Player L;

    /**
     * costructor. the clienthandler istance is passed so is easy to
     * separate controller and network.
     * @param clientin: the clienthandler that manages the player.
     */
    public SingleGameManager(ClientHandler clientin) {
        game = new SingleGame();
        client = clientin;
    }

    public static void main() throws Exception {
        ObserverSingleGame player = client.getPlayer();
        game.getPlayers().add(player);
        PlayerMsg msg = new PlayerMsg(player, game);
        client.sendMessage(msg);

        //chiedere al gicatore quali carte leader vuole
        Shuffle();
        player.updateActionStructure(client);

        while(!game.Endgame(player)) {
            TurnManager turnManager = new TurnManager();
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished");
        }

        String winner = game.Victory();
        client.sendMessage(winner);
    }

    private static void Shuffle() throws FileNotFoundException {
        SingleGame.getActionStructure().ShuffleSignal();
        game.Shuffle();
    }
}
