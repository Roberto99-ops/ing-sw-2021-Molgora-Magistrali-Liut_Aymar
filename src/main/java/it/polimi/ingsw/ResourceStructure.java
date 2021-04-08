package it.polimi.ingsw;
import java.util.ArrayList;
public class ResourceStructure {
    /**
     * resourcestructure is an arraylist of char wich can be W,R,B,G,P,Y
     */
    private ArrayList<Character> vector = new ArrayList<Character>(); //è giusto istanziarlo così o va istanziato dentro ogni metodo?


    public ArrayList<Character> getVector() { return vector; }

    public void setVector(ArrayList<Character> vector) { this.vector = vector; }

    /**
     * this method reorder the resourcestructure, for type of resource, in this order: Y,G,P,B,W,R
     * @param vector
     * @return
     */
    public ArrayList<Character> Order(ArrayList<Character> vector)
    {
        // da implementare ?
        return vector;
    }


    /**
     * this method checks if the given structure is composed by only one type of resource
     * @param vector
     * @return
     */
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

    /**
     * this method adds a given quantity of a resource type to the caller structure
     * @param quantity
     * @param resource
     * @return
     */
    public ArrayList<Character> AddResource (int quantity, Character resource) {//differenza tipi primitivi e normali
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }

}
