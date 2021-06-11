package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Random;

public class ActionStructure implements Serializable {

    private  int[] structure = {1,2,3,4,5,6,7};
    private  int AS_Counter=0;
    private int OLD_AS_COUNTER=0;


    /**
     getter and setter
     */

    public  int getAS_Counter() {
        return AS_Counter;
    }
    public  int getActionSignal(int i){
        return structure[i];
    }
    public int getOLD_AS_COUNTER() { return OLD_AS_COUNTER; }
    public  void setAS_Counter(int AS_Counter) {
        this.AS_Counter = AS_Counter;
    }
    public  int[] getStructure() {
        return structure;
    }
    public  void setStructure(int[] structure) {
        this.structure = structure;
    }



    /**
     * Picks the first Signal available on the stack, calls its action and puts it back, at the
     * end of the stack
     */

    public void pickSignal() {

        OLD_AS_COUNTER = AS_Counter;
        ActionSignal Signal = new ActionSignal();
        Signal.action(structure[AS_Counter]);
        AS_Counter++;

        if (AS_Counter==7) {
            AS_Counter=0;
        }

        return;

    }



    /**
     * Shuffles the Signals' stack
     */



    public void shuffleSignal() {
        Random random = new Random();

        for (int i=0; i < getStructure().length;i++) {
            int indice = random.nextInt(getStructure().length);
            int temp = getStructure()[indice];
            structure[indice]= structure[i];
            structure[i]=temp;
        }

    }





}
