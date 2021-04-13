package it.polimi.ingsw.model;
import java.util.ArrayList;
//DOMANDA IMPORTANTE ma i mazzi vanno tenuti in memoria qui o nella classe Game?

public class DevelopeDecks extends Deck<DevelopeCard>{
    private ArrayList<DevelopeCard> structure = new ArrayList<DevelopeCard>();

    //qui, come anche in leaderdeck, uso questi get e set per accedere
    //alla struttura, ovvero ai deck, per modificarli in pratica ho
    // dei metodi generics in Deck --> setstructure non credo serva
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