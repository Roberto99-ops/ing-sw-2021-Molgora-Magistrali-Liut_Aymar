package it.polimi.ingsw.model;

import java.io.Serializable;

public class Lorenzo implements Serializable {

    private static String name = "Lorenzo Il Magnifico";

    public static String getName() {
        return name;
    }

    private  int number = 0; //posizione su tracciato

    public  int getNumber() {
        return number;
    }

    public  void setNumber(int num) {
        number = num;
    }

    /**
     * Moves Lorenzo forward by 2, on the FaithTrack
     */
    public void forwardTwo(){number+=2;}

    /**
     * Moves Lorenzo forward by 1, on the FaithTrack, and shuffles the Action Signal's Structure
     */
    public void forwardOne(){
        number++;
        SingleGame.getActionStructure().ShuffleSignal();
    }

    /*
    /**
     * Moves Lorenzo forward by 1 or 2 cells on the FaithTrack, according to the picked Signal
     * @param forward
     * @return

    public static void Lorenzomoves(int forward){
        if (forward==2){
            number=number+2;
        } else if (forward==1){
            number++;
            SingleGame.getActionStructure().ShuffleSignal();
        }
        return;
    }
    */
}
