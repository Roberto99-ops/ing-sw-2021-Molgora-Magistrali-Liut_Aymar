package it.polimi.ingsw;

public class Lorenzo {
    private String name = "Lorenzo Il Magnifico";
    private int number = 0; //posizione su tracciato

    /**
     * Moves Lorenzo forward by 1 or 2 cells on the FaithTrack, according to the picked Signal
     * @param forward
     * @return
     */
    public int Lorenzomoves(int forward){
        ActionStructure Stack = new ActionStructure();
        if (forward==2){
            number+=2;
        } else if (forward==1){
            number++;
            Stack.ShuffleSignal();
        }
        return number;
    }
}
