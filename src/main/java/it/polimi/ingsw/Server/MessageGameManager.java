package it.polimi.ingsw.Server;

import java.util.*;
import it.polimi.ingsw.model.Game;


/**
 * This class manages the relation between players, every time there is an update of PlayerBoard: the player is notified
 * every time there is an update of market/leaderDeck/developmentDeck: all the players are notified
 */

public class MessageGameManager {


    private static List<GameHandler> hendlers = new List<GameHandler>() {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<GameHandler> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(GameHandler observer) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends GameHandler> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends GameHandler> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public GameHandler get(int index) {
            return null;
        }

        @Override
        public GameHandler set(int index, GameHandler element) {
            return null;
        }

        @Override
        public void add(int index, GameHandler element) {

        }

        @Override
        public GameHandler remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<GameHandler> listIterator() {
            return null;
        }

        @Override
        public ListIterator<GameHandler> listIterator(int index) {
            return null;
        }

        @Override
        public List<GameHandler> subList(int fromIndex, int toIndex) {
            return null;
        }
    };



    /**
     * Sends to all clients the new common structures (DevelopmentDecks, LeaderDeck, Market)
     * @param clh: the clientHandler (Server) that will send the info to the Client
     */


    public static void generalChanges (ClientHandler clh) throws Exception {

       for (int i = 0; i < hendlers.size(); i++) {

           hendlers.get(i).updateDevelopementDecks(clh);
           hendlers.get(i).updateLeaderDeck(clh);
           hendlers.get(i).updateMarket(clh);
       }

   }



    /**
     * Sends to the the client his PlayerBoard
     * @param clh: the clientHandler (Server) that will send the info to the Client
     * @param handler: the GameHandler that manages the relation between Client and Server
     * @param game: the actual game I'm playing
     */

       public static void personalChanges (ClientHandler clh, GameHandler handler, Game game) throws Exception {

           handler.updatePlayerBoard(clh, game);

    }

}

