package it.polimi.ingsw;
//import java.awt.geom.Area;
import java.util.Random;
import java.util.ArrayList;

public abstract class Deck <T>{
    public ArrayList<T> getStructure() {
        return structure;
    }

    public void setStructure(ArrayList<T> structure) {
        this.structure = structure;
    }

    private ArrayList<T> structure;

    public T getDeck(ArrayList<T> structure)
    {
        T firstElement;
        firstElement=structure.get(0);
        structure.remove(0);
        return firstElement;
    }

    public ArrayList<T> setDeck(ArrayList<T> structure) //non so se funzionano pe riferimento o cosa
    {
        ArrayList<T> mixedStructure = new ArrayList<T>();
        Random mixer = new Random();
        while(structure.size() > 0)
        {
            int mix = mixer.nextInt(structure.size());
            mixedStructure.add(structure.get(mix));
            structure.remove(mix);
        }
        return mixedStructure;
    }
}
