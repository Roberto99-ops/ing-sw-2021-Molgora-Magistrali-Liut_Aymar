package it.polimi.ingsw;
import java.lang.String;

public class Player {
    private int number;
    private int developementquantity;
    private int pv;
    private int trackposition;
    private String name;
    private Leaderdeck leadercards;

    public int getDevelopementquantity() {
        return developementquantity;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseDevelopQuantity()
    {

    }

}
