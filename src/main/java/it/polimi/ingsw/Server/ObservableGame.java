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

    private List<Observer> observers = new List<Observer>() {

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
        public Iterator<Observer> iterator() {
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
        public boolean add(Observer observer) {
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
        public boolean addAll(Collection<? extends Observer> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Observer> c) {
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
        public Observer get(int index) {
            return null;
        }

        @Override
        public Observer set(int index, Observer element) {
            return null;
        }

        @Override
        public void add(int index, Observer element) {

        }

        @Override
        public Observer remove(int index) {
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
        public ListIterator<Observer> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Observer> listIterator(int index) {
            return null;
        }

        @Override
        public List<Observer> subList(int fromIndex, int toIndex) {
            return null;
        }
    };





    public void addObserver(Observer n_actualplayer) {

        this.observers.add(n_actualplayer);

    }



    public void removeObserver(Observer n_actualplayer) {

        this.observers.remove(n_actualplayer);

    }


// notify allObservers
   public void notifyAllObservers() {
      // metodo che notifica a tutti gli observers (giocatori) che c'è stato un cambiamento nel model
    }

}

