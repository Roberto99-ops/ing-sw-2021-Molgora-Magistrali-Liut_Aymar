package it.polimi.ingsw;

public class Lorenzo {
    private String name = "Lorenzo Il Magnifico";
    private int number = 0; //posizione su tracciato

    public int Lorenzomoves(int forward){
        if (forward==2){
            number+=2;
        } else if (forward==1){
            number++;
            //chiamo metodo Shuffle per ActionStructure qui?
            ActionStucture.ShuffleSignal();
        }
        return number;
    }
}
