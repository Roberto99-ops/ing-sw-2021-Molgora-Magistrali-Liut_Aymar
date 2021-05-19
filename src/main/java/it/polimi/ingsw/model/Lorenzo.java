package it.polimi.ingsw.model;

public class Lorenzo {

    private static String name = "Lorenzo Il Magnifico";

    public static String getName() {
        return name;
    }

    private static int number = 0; //posizione su tracciato

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int num) {
        number = num;
    }

    /**
     * Moves Lorenzo forward by 1 or 2 cells on the FaithTrack, according to the picked Signal
     * @param forward
     * @return
     */
    public static void Lorenzomoves(int forward){
        if (forward==2){
            number=number+2;
        } else if (forward==1){
            number++;
            SingleGame.getActionStructure().ShuffleSignal();
        }
        return;
    }
}
