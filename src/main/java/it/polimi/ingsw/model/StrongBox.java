package it.polimi.ingsw.model;

import java.io.Serializable;

public class StrongBox implements Serializable {

    /**
     * StrongBox is an arraylist of ResourceStructure
     */

    private ResourceStructure structure = new ResourceStructure();


    /**
     * Getter of Strongbox's structure
     * @return structure of strongbox
     */

    public ResourceStructure getStructure() { return structure; }



    /**
     * Counts the amount of one kind of resource
     * @param neededRes: type of resource the player needs to pay/activate something
     * @return counterSB: the amount of that resource in StrongBox
     */

    public int countTypeSB(char neededRes) {
        int i = 0, counterSB = 0;
        if (this.structure.getVector().contains(neededRes)) {
            while (i != this.structure.getVector().size()) {
                if (neededRes == (char) this.structure.getVector().get(i))
                    counterSB++;
                i++;
            }
            return counterSB;
        } else {
            return 0;
        }
    }


    /**
     * ONLY USED FOR TESTING
     * print all resources in strongbox
     */

    public void printAll() {
        for (int i = 0; i < this.structure.getVector().size(); i++) {
            System.out.println(this.structure.getVector().get(i));
        }
    }


}

