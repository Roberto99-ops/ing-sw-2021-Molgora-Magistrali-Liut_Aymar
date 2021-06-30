package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.ArrayList;


public class DevelopDecks extends Deck<DevelopCard> implements Serializable {
    private ArrayList<DevelopCard> structure = new ArrayList<DevelopCard>();

    /**
     * These getter and setter let me manage the structure
     * To modify decks, there are methods (generics) in Deck class
     * @return the structure of the DevelopDeck
     */

    @Override
    public ArrayList<DevelopCard> getStructure() {
        return structure;
    }
    @Override
    public void setStructure(ArrayList<DevelopCard> structure) {
        this.structure = structure;
    }



    /**
     * ONLY USED FOR TESTING
     * print the deck using generic method Print in Deck class
     */

    public void print()
    {
        for(int i=0; i<this.structure.size(); i++) this.structure.get(i).print();
    }



}