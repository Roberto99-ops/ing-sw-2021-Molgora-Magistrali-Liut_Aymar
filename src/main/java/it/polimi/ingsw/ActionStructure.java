package it.polimi.ingsw;

public class ActionStructure {
    private ActionSignal[] structure;
    private int AS_Counter=0; //ActionSignal_counter conta il segnalino che è stato preso (da 0 a 6 -> posti nella pila)

    //metodo che prende il primo segnalino della pila e lo rimette in fondo
    public int PickSignal(ActionStructure[] structure, int AS_Counter){
        ActionSignal.Action(structure[AS_Counter]); //chiamo il metodo del segnalino in base al suo numero
        AS_Counter++;
        if (AS_Counter==7){ //se arrivo alla fine della pila torno su, al posto 0
            AS_Counter=0;
        }
        return AS_Counter;
    }

    //metodo che mischia le carte del mazzo
    public void ShuffleSignal(){
        theGame = new Game();
        theGame.Shuffle();
        //oppure ho trovato questo metodo per mischiare un array usando libreria standard di Java
        //Collections.shuffle(Arrays.asList(structure))
    }

}
