package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;



/**
 * abstract class with generics so we can override from developedeck and leaderdeck
 * @param <T>
 */

public abstract class Deck <T> implements Serializable {
    private ArrayList<T> structure;



    /**
     getter and setter
     */

    public ArrayList<T> getStructure() {
        return structure;
    }
    public void setStructure(ArrayList<T> structure) {
        this.structure = structure;
    }




    /**
     * method that allow us to pick the card on the top of the deck
     * @param structure
     * @return
     */

    public T pick(ArrayList<T> structure) {

        T firstElement;
        firstElement=structure.get(0);
        structure.remove(0);
        return firstElement;
    }




    /**
     * method that randomly mix the deck
     * @param structure
     * @return
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
