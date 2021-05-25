package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.DevelopeDecks;
import it.polimi.ingsw.model.LeaderDeck;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.Player;

import java.util.*;

public class ObservableGame {

   /** public static int lonely;
    private static ArrayList<Player> players;
    private static int n_players;
    private static DevelopeDecks[] developedecks = new DevelopeDecks[12];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;
    private static int timer_VR=0;
*/

    private static List<ObserverGame> observers = new List<ObserverGame>() {

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
        public Iterator<ObserverGame> iterator() {
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
        public boolean add(ObserverGame observer) {
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
        public boolean addAll(Collection<? extends ObserverGame> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends ObserverGame> c) {
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
        public ObserverGame get(int index) {
            return null;
        }

        @Override
        public ObserverGame set(int index, ObserverGame element) {
            return null;
        }

        @Override
        public void add(int index, ObserverGame element) {

        }

        @Override
        public ObserverGame remove(int index) {
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
        public ListIterator<ObserverGame> listIterator() {
            return null;
        }

        @Override
        public ListIterator<ObserverGame> listIterator(int index) {
            return null;
        }

        @Override
        public List<ObserverGame> subList(int fromIndex, int toIndex) {
            return null;
        }
    };





    public void addObserver(ObserverGame n_actualplayer) {

        this.observers.add(n_actualplayer);

    }



    public void removeObserver(ObserverGame n_actualplayer) {

        this.observers.remove(n_actualplayer);

    }


// notify allObservers
   public static void notifyAllObservers() {
       for (int i = 0; i < observers.size(); i++) {
           ObserverGame.updateDevelopementDecks();
           ObserverGame.updateLeaderDeck();
           ObserverGame.updateMarket();
           ObserverGame.updateTimer_VR();
           ObserverGame.updateVR();
       }

   }

       public static void personalObservers (ObserverGame observerGame) {

               observerGame.updateDevelopementSpace();
               observerGame.updateStorage();
               observerGame.updateStrongbox();

    }

}

