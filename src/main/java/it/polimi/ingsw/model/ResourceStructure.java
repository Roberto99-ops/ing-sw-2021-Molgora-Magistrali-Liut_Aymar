package it.polimi.ingsw.model;
import java.util.ArrayList;
public class ResourceStructure extends ArrayList {


    /**
     * Resourcestructure is an arraylist of char which can be W,R,B,G,P,Y
     */

    private ArrayList<Character> vector;


    /**
     * costruction of Resourcestructure
     */

    public ResourceStructure() {
        vector = new ArrayList<>();
    }



    /**
     * getter and setter
     * * @return vectore of Resourcestructure (getter)
     */

    public ArrayList<Character> getVector() { return vector; }
    public void setVector(ArrayList<Character> vector) { this.vector = vector; }



    /**
     * this method adds a given quantity of a resource type to the caller structure
     * @param quantity
     * @param resource
     * @return the structure built with the parameters in input
     */

    public ArrayList<Character> addResource(int quantity, Character resource) {
        for(int i=0; i<quantity; i++) vector.add(resource);
        return(vector);
    }


    /**
     * @param resource
     * @return the position of the first element of a specific type of resource
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
