package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.ArrayList;

public class LeaderDeck extends Deck <LeaderCard> implements Serializable {

    private ArrayList<LeaderCard> structure = new ArrayList<LeaderCard>();

    /**
     * These getter and setter let me manage the Deck's structure
     * To modify decks, there are methods (generics) in Deck class
     */

    @Override
    public ArrayList<LeaderCard> getStructure() { return structure; }

    @Override
    public void setStructure(ArrayList<LeaderCard> structure)
    {
        this.structure=structure;
    }

    public void print()
    {
        for(int i=0; i<this.structure.size(); i++) this.structure.get(i).Print();
    }

}
