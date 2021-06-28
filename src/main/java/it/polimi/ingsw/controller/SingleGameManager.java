package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.Server;
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
        GameHandler player = client.getSinglePlayer();
        game.getPlayers().add(player);

        Shuffle();
        int choice2;
        LeaderDeck leaderChoice = game.leaderChoice();
        LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
        client.sendMessage(mess);
        client.sendMessage("Choose one: ");
        int choice1 = Integer.parseInt(client.receiveMessage());
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice1));

        do {
            client.sendMessage("Choose another one: ");
            choice2 = Integer.parseInt(client.receiveMessage());
        } while(choice2 == choice1);
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice2));


        while(!game.callEndgame(player)) {
            TurnManager turnManager = new TurnManager();
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished(press any key)");
            client.receiveMessage();
        }

        String winner = game.callVictory();
        client.sendMessage("\t\t\t\t\t\t\t\t\t\t" + winner + " won!");
        System.out.println("Game Ended");
        client.sendMessage("Game Ended");
        game.getPlayers().remove(0);
        while( GameManager.getClientList().size()>0) {
            GameManager.getClientList().remove(0);
        }
        Server.setNumberofsockets(0);
        Server.setCloseserverTrue();
    }



    /**
     * this method shuffle the deck of actionsignals
     */


    private static void Shuffle() throws FileNotFoundException {
        SingleGame.getActionStructure().shuffleSignal();
        game.shuffle();
    }
}
