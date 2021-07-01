package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Server.messages.LeaderDeckMsg;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;

    /**
     * It manages a game in SinglePlayer. It doesn't extend GameManger because
     * it would have simply overridden the main method that is static, and that's not possible.
     */

    public class SingleGameManager {

        private static Game game;
        private static ClientHandler client;


    /**
     * It is a constructor: the ClientHandler instance is passed so it's more easy to
     * separate controller and network.
     * @param clientIn: the clientHandler that manages the player.
     */

    public SingleGameManager(ClientHandler clientIn) {
        game = new SingleGame();
        client = clientIn;
    }




    public static void main() throws Exception {
        GameHandler player = client.getSinglePlayer();
        game.getPlayers().add(player);

        Shuffle();

        //asks the player to choose which LeaderCards he wants
        int choice2;
        int choice1;
        LeaderDeck leaderChoice = game.leaderChoice();
        LeaderDeckMsg mess = new LeaderDeckMsg(leaderChoice);
        client.sendMessage(mess);
        do {
            client.sendMessage("Choose one: ");
            choice1 = Integer.parseInt(client.receiveMessage());
        }while (choice1!=0 && choice1!=1 && choice1!=2 && choice1!=3);
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice1));

        do {
            client.sendMessage("Choose another one: ");
            choice2 = Integer.parseInt(client.receiveMessage());
        } while(choice2 == choice1 || (choice1!=0 && choice1!=1 && choice1!=2 && choice1!=3));
        player.getLeadercards().getStructure().add(leaderChoice.getStructure().get(choice2));

        //it divides turns
        while(!game.callEndgame(player)) {
            TurnManager turnManager = new TurnManager();
            turnManager.main(client, game, 0);
            client.sendMessage("Turn Finished (press any key)");
            client.receiveMessage();
        }

        //when the game ends, it calls "Victory" method
        String winner = game.callVictory();
        client.sendMessage("\t\t\t\t\t\t\t\t\t\t" + winner + " won!");
        System.out.println("Game Ended");
        client.sendMessage("Game Ended");
        game.getPlayers().remove(0);
        while( GameManager.getClientList().size()>0) {
            GameManager.getClientList().remove(0);
        }
        Server.setNumberOfSockets(0);
        Server.setCloseServerTrue();
    }



    /**
     * This method shuffle the deck of ActionSignals
     */

    private static void Shuffle() throws FileNotFoundException {
        SingleGame.getActionStructure().shuffleSignal();
        game.shuffle();
    }
}
