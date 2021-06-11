package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.MessageSingleGameManager;
import it.polimi.ingsw.Server.SingleGameHandler;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;

/**
 * it manages a game in singleplayer. it doesn't extend GameManger because
 * it would have simply overrided the main method that is static and that's not possible.
 */
public class SingleGameManager {

    private static Game game;
    private static ClientHandler client;
    private static MessageSingleGameManager observablesinglegame;

    /**
     * costructor. the clienthandler istance is passed so is easy to
     * separate controller and network.
     * @param clientin: the clienthandler that manages the player.
     */

    public SingleGameManager(ClientHandler clientin) {
        game = new SingleGame();
        observablesinglegame = new MessageSingleGameManager();
        client = clientin;
    }


    public static void main() throws Exception {
        client.sendMessage("clean screen");
        SingleGameHandler player = client.getSinglePlayer();
        game.getPlayers().add(player);
        observablesinglegame.setSingleGameHendler(player);

        Shuffle();
        //player.updateActionStructure(client);
        int choice2;
        LeaderDeck leaderChoice = game.leaderChoice();
        LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
        client.sendMessage(mess);
        client.sendMessage("Choose one: ");
        int choice1 = Integer.parseInt(client.receiveMessage());
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice1));
        //player.updateLeaderCards(client);
        //player.updateLeaderDeck(client);

        do {
            client.sendMessage("Choose another one: ");
            choice2 = Integer.parseInt(client.receiveMessage());
        } while(choice2 == choice1);
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice2));
        //player.updateLeaderCards(client);
        //player.updateLeaderDeck(client);
        client.sendMessage("clean screen");

        // spostato a sopra

        // player.updateActionStructure(client);


        while(!game.callEndgame(player)) {
            TurnManager turnManager = new TurnManager();
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished(press any key)");
            client.receiveMessage();
        }

        String winner = game.callVictory();
        client.sendMessage("\t\t\t\t\t\t\t\t\t\t" + winner + " won!");

        return;
    }

    private static void Shuffle() throws FileNotFoundException {
        SingleGame.getActionStructure().shuffleSignal();
        game.shuffle();
    }
}
