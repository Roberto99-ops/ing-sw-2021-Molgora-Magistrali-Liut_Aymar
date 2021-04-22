package it.polimi.ingsw.model;

import java.util.Arrays;
import java.util.Collections;

public class ActionStructure {
    private int[] structure;
    private int AS_Counter=0; //ActionSignal_counter conta il segnalino che è stato preso (da 0 a 6 -> posti nella pila)

    /**
     * Picks the first Signal available on the stack, calls its action and puts it back, at the
     * end of the stack
     * @return
     */
    public int PickSignal(){
        ActionSignal Signal = new ActionSignal();
        Signal.Action(structure[AS_Counter]); //chiamo il metodo del segnalino in base al suo numero
        AS_Counter++;
        if (AS_Counter==7){ //se arrivo alla fine della pila torno su, al posto 0
            AS_Counter=0;
        }
        return AS_Counter;
    }

    /**
     * Shuffles the Signals' stack
     */
    public void ShuffleSignal(){
        Collections.shuffle(Arrays.asList(structure));
        AS_Counter=0;
    }

}
