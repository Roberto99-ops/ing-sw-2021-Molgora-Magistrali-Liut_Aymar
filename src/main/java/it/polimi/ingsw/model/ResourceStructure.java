package it.polimi.ingsw.model;
import java.util.ArrayList;
public class ResourceStructure extends ArrayList {
    /**
     * resourcestructure is an arraylist of char which can be W,R,B,G,P,Y
     */
    private ArrayList<Character> vector;

    public ResourceStructure()
    {
        vector = new ArrayList<>();
    }

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
        if(vector.size()==0) return true;
        Character first = vector.get(0);
        for(int i=0; i < vector.size(); i++) {
            if (vector.get(i) == first) {
                counter ++;
            }
        }
    if (counter == vector.size()) {
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
    public ArrayList<Character> addResource(int quantity, Character resource) {//differenza tipi primitivi e normali
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }


    /**
     * return the position of the first element of a specific type of resource
     * @param resource
     * @return
     */
    public int firstOccurance(char resource)
    {
        int i=0;
        while(this.getVector().get(i) != resource) i++;
        return i;
    }

    /**
     * remove the first resource of a specific type
     * @param resource: resource to delete
     */
    public void removeThis(char resource)
    {
        int i=0;
        i=this.firstOccurance(resource);
        this.getVector().remove(i);
    }

}
