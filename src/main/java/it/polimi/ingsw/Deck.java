package it.polimi.ingsw;
import java.util.Random;
import java.util.ArrayList;

public abstract class Deck {
    private ArrayList<Object> structure;
    public Object getDeck()
    {

    }
    public ArrayList<Object> setDeck() //non so se funzionano pe riferimento o cosa
    {
        ArrayList<Object> mixedStructure = new ArrayList<Object>();
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
