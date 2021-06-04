package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Random;

public class ActionStructure implements Serializable {

    private  int[] structure = {1,2,3,4,5,6,7};
    private  int AS_Counter=0;//ActionSignal_counter conta il segnalino che è stato preso (da 0 a 6 -> posti nella pila)

    public  int getAS_Counter() {
        return AS_Counter;
    }
    public  int getActionSignal(int i){
        return structure[i];
    }

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
    public void pickSignal(){
        ActionSignal Signal = new ActionSignal();
        Signal.action(structure[AS_Counter]); //chiamo il metodo del segnalino in base al suo numero
        AS_Counter++;
        if (AS_Counter==7){ //se arrivo alla fine della pila torno su, al posto 0
            AS_Counter=0;
        }
        return;
    }

    /**
     * Shuffles the Signals' stack
     */
    public  void shuffleSignal(){
        Random random = new Random();
        for (int i=0; i < getStructure().length;i++){
            int indice = random.nextInt(getStructure().length);
            int temp = getStructure()[indice];
            structure[indice]= structure[i];
            structure[i]=temp;
        }
        //Collections.shuffle(Arrays.asList(structure));
        //AS_Counter=6;
    }

    public void incrementAS_COUNTER()
    {
        AS_Counter++;
        if (AS_Counter==7)
            AS_Counter=0;
    }

}
