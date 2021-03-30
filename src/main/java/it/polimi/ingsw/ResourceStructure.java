package it.polimi.ingsw;
import java.util.ArrayList;
public class ResourceStructure {
    private ArrayList<Character> vector = new ArrayList<Character>();
    public ArrayList<Character> getVector() { return vector; }

    public void setVector(ArrayList<Character> vector) {
        this.vector = vector;
    }

    public ArrayList<Character> Order(ArrayList<Character> vector)
    {
        return vector;
    }

    public ArrayList<Character> AddResource(int quantity, Character resource){
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }
}
