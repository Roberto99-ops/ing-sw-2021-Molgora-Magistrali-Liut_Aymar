package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;



/**
 * Abstract class with generics so we can override it from DevelopDeck and LeaderDeck
 * @param <T>
 */

public abstract class Deck <T> implements Serializable {
    private ArrayList<T> structure;



    /**
     Getter and setter
     */

    public ArrayList<T> getStructure() {
        return structure;
    }
    public void setStructure(ArrayList<T> structure) {
        this.structure = structure;
    }




    /**
     * Method that allows us to pick the card on the top of the deck
     * @param structure : it is the deck we are using
     * @return the card on the top of the deck
     */

    public T pick(ArrayList<T> structure) {

        T firstElement;
        firstElement=structure.get(0);
        structure.remove(0);
        return firstElement;
    }




    /**
     * Method that randomly mixes the deck
     * @param structure : it is the deck we are using
     * @return the shuffled deck
     */

    public ArrayList<T> shuffleDeck(ArrayList<T> structure) {

        ArrayList<T> mixedStructure = new ArrayList<T>();
        Random mixer = new Random();

        while(structure.size() > 0) {
            int mix = mixer.nextInt(structure.size());
            mixedStructure.add(structure.get(mix));
            structure.remove(mix);
        }

        return mixedStructure;
    }



}
