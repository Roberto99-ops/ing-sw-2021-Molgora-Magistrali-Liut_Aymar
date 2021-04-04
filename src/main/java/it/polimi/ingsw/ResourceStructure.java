package it.polimi.ingsw;
import java.util.ArrayList;
public class ResourceStructure {

    private ArrayList<Character> vector = new ArrayList<Character>();


    // ritorna l'arraylist in questione
    public ArrayList<Character> getVector() {
        return vector;
    }

    // setta l'arraylist in questione in base all'array che gli si passa
    public void setVector(ArrayList<Character> vector) {
        this.vector = vector;
    }


    public ArrayList<Character> Order(ArrayList<Character> vector)
    {
        // da implementare ?
        return vector;
    }

    // controlla che un array list sia composto da una sola risorsa
    public boolean checkEquality(ArrayList<Character> vector) {
        int counter = 0;
        Character first = vector.get(0);
        for(int i=0; i <= vector.size(); i++) {
            if (vector.get(i) == first) {
                counter ++;
            }
        }
    if (counter == vector.size() + 1) {
        return true;
    } else {
        return false;
    }

    }


    public ArrayList<Character> AddResource (int quantity, Character resource) {
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }

}
