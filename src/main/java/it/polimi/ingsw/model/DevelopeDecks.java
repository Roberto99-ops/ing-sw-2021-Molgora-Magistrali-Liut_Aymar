package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.ArrayList;


public class DevelopeDecks extends Deck<DevelopeCard> implements Serializable {
    private ArrayList<DevelopeCard> structure = new ArrayList<DevelopeCard>();

    /**
     * these get and set let me manage the structure
     * to modify decks there are methods (generics) in Deck class
     * @return
     */

    @Override
    public ArrayList<DevelopeCard> getStructure() {
        return structure;
    }
    @Override
    public void setStructure(ArrayList<DevelopeCard> structure) {
        this.structure = structure;
    }



    /**
     * print the deck using generic method Print in Deck class
     */

    public void print()
    {
        for(int i=0; i<this.structure.size(); i++) this.structure.get(i).print();
    }



}