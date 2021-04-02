package it.polimi.ingsw;  //mi sa che quelli che abbiamo fatto sono quasi tutti overload e non override
import java.util.ArrayList;

public class DevelopeDecks extends Deck<DevelopeCard>{
    private ArrayList<DevelopeCard> structure = new ArrayList<DevelopeCard>();

    @Override
    public ArrayList<DevelopeCard> getStructure() {
        return structure;
    }

    @Override
    public void setStructure(ArrayList<DevelopeCard> structure) {
        this.structure = structure;
    }
}