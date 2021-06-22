package it.polimi.ingsw.model;

import java.io.Serializable;



public class Lorenzo implements Serializable {


    /**
     * name: indicate the name of singleplayer
     * number: indicate the position on faithtrack
     */

    private static String name = "Lorenzo Il Magnifico";
    private  int number = 0;



    /**
     * getter and setter
     */

    public  int getNumber() {
        return number;
    }
    public  void setNumber(int num) {
        number = num;
    }



    /**
     * Moves Lorenzo forward by 2, on the FaithTrack
     */

    public void forwardTwo(){
        number+=2;
        if(number > 24)
            number = 24;
    }


    /**
     * Moves Lorenzo forward by 1, on the FaithTrack, and shuffles the Action Signal's Structure
     */

    public void forwardOne(){
        number++;
        SingleGame.getActionStructure().shuffleSignal();
    }


}
