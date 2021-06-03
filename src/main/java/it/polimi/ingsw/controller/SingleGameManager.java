package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.ObserverSingleGame;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.LeaderDeck;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SingleGame;

import java.io.FileNotFoundException;

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
        client.sendMessage("clean screen");
        ObserverSingleGame player = client.getSinglePlayer();
        game.getPlayers().add(player);

        Shuffle();
        LeaderDeck leaderChoice = game.leaderChoice();
        LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
        client.sendMessage(mess);
        client.sendMessage("Choose one: ");
        int choice = Integer.parseInt(client.receiveMessage());
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice));
        client.sendMessage("Choose another one: ");
        choice = Integer.parseInt(client.receiveMessage());
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice));
        client.sendMessage("clean screen");

        PlayerMsg msg = new PlayerMsg(player, game);
        client.sendMessage(msg);

        player.updateActionStructure(client);

        while(!game.callEndgame(player)) {
            TurnManager turnManager = new TurnManager();
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished");
        }

        String winner = game.callVictory();
        client.sendMessage(winner);
    }

    private static void Shuffle() throws FileNotFoundException {
        SingleGame.getActionStructure().shuffleSignal();
        game.shuffle();
    }
}
