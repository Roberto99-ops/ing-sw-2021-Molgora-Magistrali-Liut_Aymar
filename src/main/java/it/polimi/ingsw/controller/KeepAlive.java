package it.polimi.ingsw.controller;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * this class has only one method that in case a player is crashed, closes all the socket and ends the game
 */

public class KeepAlive {

    private static ArrayList<ClientHandler> clients = new ArrayList<>();



    public static void addClient(ClientHandler client){
        clients.add(client);
    }


    /**
     * this method closed the server in case a player is crashed
     * @param endgame
     */

    public static void run(boolean endgame) throws IOException {
        while (0 < clients.size()) {
            if(!clients.get(0).isClose()) {
                if(!endgame)
                    clients.get(0).sendMessage("\nGame has ended");
                clients.get(0).closeSocket();
            }
            clients.remove(0);
            GameManager.getClientList().remove(0);
        }
        Server.closeGame();
    }
}
