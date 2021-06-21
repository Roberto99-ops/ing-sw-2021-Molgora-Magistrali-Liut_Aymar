package it.polimi.ingsw.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Storage extends ArrayList<Character> implements Serializable {

    /**
     * Storage is formed by 2 ArrayList
     * attribute: panel is an ArrayList of 6 char formed by 3 floors (0, 1-2, 3-4-5)
     * attribute: extrapanel
     * attribute: typExtrapanel is a char that is setted to 'Z' if extrapanel is not activated, or with the right type
     * attribute: counterSP
     * attribute: counterSEP
     */

    private ArrayList<Character> panel;
    private ResourceStructure extrapanel;
    private char typeExtrapanel = 'Z';
    private int counterSP = 0;
    private int counterSEP = 0;


    /**
     * Initialization of Storage
     */

    public Storage() {
        panel = new ArrayList<Character>(List.of('N', 'N', 'N', 'N', 'N', 'N')); // arraylist di 6 spazi di base
        extrapanel = new ResourceStructure();
        extrapanel.addResource(2, 'N');
    }


    /**
     * ONLY USED FOR TESTING
     * print all resources in strongbox
     */

    public void printPanel() {
        for (int i = 0; i < 6; i++) {
            System.out.println(this.panel.get(i));
        }
        System.out.println("\n");
    }




    /**
     * getter and setter
     */

    public void setPanel(ResourceStructure panel) { this.panel = panel; }
    public ResourceStructure getExtrapanel() { return extrapanel; }
    public void setExtrapanel(ResourceStructure extrapanel) { this.extrapanel = extrapanel; }
    public ArrayList<Character> getPanel() { return panel; }
    public char getTypeExtrapanel() { return typeExtrapanel; }
    public void setTypeExtrapanel(char typeExtrapanel) { this.typeExtrapanel = typeExtrapanel; }
    public void setCounterSP(int counterSP) {
        this.counterSP = counterSP;
    }
    public void setCounterSEP(int counterSEP) {
        this.counterSEP = counterSEP;
    }



    /**
     * Counts the amount of one kind of resource in panel and extrapanel
     * @param neededRes: type of resource the player needs to pay/activate something
     * @return counterS: the amount of that resource in Storage
     */

    public int countTypeS(char neededRes) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (this.panel.get(i) == neededRes)
                count++;
        }
        setCounterSP(count);

        for (int i = 0; i < 2; i++) {
            if (this.extrapanel.getVector().get(i) == neededRes)
                count++;
        }
        setCounterSEP(count - counterSP);

        return count;
    }



    /**
     * this method try to switch the resources of two floors that if one is full
     * @param resource: type of resource the player needs to insert in a full floor
     * @param k: position where the player want to insert the resource
     * @return boolean: true if the resources are switched, false if not
     */

    public boolean switchresources(char resource, int k) {

        char temporary = 'N';


        if (k == 2 && this.panel.get(5) == 'N') {

            if (this.panel.get(3) != 'N' && this.panel.get(4) != 'N') {

                temporary = this.panel.get(3);

                this.panel.set(1, temporary);
                this.panel.set(2, temporary);
                this.panel.set(3, resource);
                this.panel.set(4, resource);
                this.panel.set(5, resource);
                return true;

            } else if (this.panel.get(3) != 'N' && this.panel.get(4) == 'N') {

                temporary = this.panel.get(3);

                this.panel.set(1, temporary);
                this.panel.set(2, 'N');
                this.panel.set(3, resource);
                this.panel.set(4, resource);
                this.panel.set(5, resource);
                return true;

            } else if (this.panel.get(3) == 'N' && this.panel.get(4) == 'N') {
                this.panel.set(1, 'N');
                this.panel.set(2, 'N');
                this.panel.set(3, resource);
                this.panel.set(4, resource);
                this.panel.set(5, resource);
                return true;

            } else return false;

        }


        if (k == 0) {

            if ((this.panel.get(1) != 'N' && this.panel.get(2) == 'N') ||
                    (this.panel.get(1) == 'N' && this.panel.get(2) == 'N')) {

                this.panel.set(0, this.panel.get(1));
                this.panel.set(1, resource);
                this.panel.set(2, resource);
                return true;

            } else if (this.panel.get(1) != 'N' && this.panel.get(2) != 'N' && ((this.panel.get(3) != 'N' && this.panel.get(4) == 'N') || (this.panel.get(3) == 'N' && this.panel.get(4) == 'N'))) {

                this.panel.set(0, this.panel.get(3));
                this.panel.set(3, resource);
                this.panel.set(4, resource);

                return true;
            }

        } else return false;

        return false;
    }



}