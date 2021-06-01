package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;
import it.polimi.ingsw.model.Turn;

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
        Player player = client.getPlayer();
        PlayerMsg msg = new PlayerMsg(player);
        client.sendMessage(msg);
        game.getPlayers().add(player);
        //mischiare segnalini e carte e market
        Shuffle();

        while(!game.Endgame(player)) {
            TurnManager turnManager = new TurnManager();
            //Turn turn = new Turn();
            //turn.setActualplayer(player);
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished");
        }

        String winner = game.Victory();
        client.sendMessage(winner);
    }

    private static void Shuffle()
    {
        SingleGame.getActionStructure().ShuffleSignal();
        //game.Shuffle(); non ho ancora capito come devono essre "mischiati" i mazzetti ma vanno mishcaiti dentro shuffle credo
    }
}
