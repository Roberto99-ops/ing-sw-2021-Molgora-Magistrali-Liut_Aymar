package it.polimi.ingsw.model;

public class ActionStructure {
    private int[] structure;
    private int AS_Counter=0; //ActionSignal_counter conta il segnalino che è stato preso (da 0 a 6 -> posti nella pila)

    /**
     * Picks the first Signal available on the stack, calls its action and puts it back, at the
     * end of the stack
     * @param structure
     * @param AS_Counter
     * @return
     */
    public int PickSignal(int[] structure, int AS_Counter){
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
        Game theGame = new Game();
        theGame.Shuffle();
        //oppure ho trovato questo metodo per mischiare un array usando libreria standard di Java
        //Collections.shuffle(Arrays.asList(structure))
        AS_Counter=0;
    }

}
