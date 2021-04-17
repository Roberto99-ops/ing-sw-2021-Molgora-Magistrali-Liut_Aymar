package it.polimi.ingsw.model;
import java.util.ArrayList;

public class DevelopeDecks extends Deck<DevelopeCard>{
    private ArrayList<DevelopeCard> structure = new ArrayList<DevelopeCard>();

    /**
     * these get and set let me to manage the structure
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

    public void Print()
    {
        for(int i=0; i<this.structure.size(); i++) this.structure.get(i).Print();
    }
}