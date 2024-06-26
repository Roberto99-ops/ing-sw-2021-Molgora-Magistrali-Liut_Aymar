package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Random;


public class ActionStructure implements Serializable {

    private  int[] structure = {1,2,3,4,5,6,7};
    private  int AS_Counter=0;
    private int OLD_Signal;


    /**
     Getter and setter
     */

    public  int getAS_Counter() {
        return AS_Counter;
    }
    public int getOLD_Signal() { return OLD_Signal; }
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
     * Picks the first Signal available on the stack, calls its action and puts it back, at the end of the stack
     */

    public void pickSignal() {

        OLD_Signal = structure[AS_Counter];
        ActionSignal Signal = new ActionSignal();
        Signal.action(structure[AS_Counter]);
        AS_Counter++;

        if (AS_Counter==7) {
            AS_Counter=0;
        }

    }



    /**
     * Shuffles the ActionSignals' stack
     */

    public void shuffleSignal() {
        Random random = new Random();

        for (int i=0; i < getStructure().length;i++) {
            int index = random.nextInt(getStructure().length);
            int temp = getStructure()[index];
            structure[index]= structure[i];
            structure[i]=temp;
        }
        AS_Counter = 0;

    }



}
