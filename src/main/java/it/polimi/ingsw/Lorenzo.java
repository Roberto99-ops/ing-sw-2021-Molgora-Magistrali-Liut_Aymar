package it.polimi.ingsw;

public class Lorenzo {
    private String name;
    private int number; //posizione su tracciato

    public int Lorenzomoves(int forward){
        if (/*oggetto di ActionSignal.numero == #corrispondente al segnalino +2*/){
            number+=2;
        } else if (/*oggetto di ActionSignal.numero == #corrispondente al segnalino +1*/){
            number++;
            //chiamo metodo Shuffle per ActionStructure qui?
        }
        return number;
    }
}
