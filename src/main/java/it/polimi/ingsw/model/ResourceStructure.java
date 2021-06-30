package it.polimi.ingsw.model;
import java.util.ArrayList;
public class ResourceStructure extends ArrayList {


    private ArrayList<Character> vector;


    public ResourceStructure() {
        vector = new ArrayList<>();
    }



    /**
     * Getter and setter
     */

    public ArrayList<Character> getVector() { return vector; }
    public void setVector(ArrayList<Character> vector) { this.vector = vector; }



    /**
     * This method adds a specific amount of a given resource inside of a vector.
     * @param quantity : the amount of the resource we want
     * @param resource : the resource we want
     * @return a vector with a specific amount of a given resource
     */

    public ArrayList<Character> addResource(int quantity, Character resource) {
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }


    /**
     * Tells me the position of the first element of a specific type of resource
     * @param resource : the type of resource we are looking for
     * @return the position of the first resource of a specific type
     */

    public int firstOccurrence(char resource)
    {
        int i=0;
        while(this.getVector().get(i) != resource) i++;
        return i;
    }


    /**
     * Removes the first resource of a specific type
     * @param resource: resource to delete
     */

    public void removeThis(char resource)
    {
        int i=0;
        i=this.firstOccurrence(resource);
        this.getVector().remove(i);
    }

}
