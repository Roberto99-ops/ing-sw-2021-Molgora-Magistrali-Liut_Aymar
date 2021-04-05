package it.polimi.ingsw;
import java.lang.String;

public class Player {
    private String name;
    private int number;
    private int developementquantity;
    private int pv;
    private int trackposition;
    private Leaderdeck leadercards;

    private int VR=0; /*contatore che per ogni giocatore comincia da 0 e ogni volta che
                        qualcuno arriva in VR, si incrementa fino a 2 (#3 non serve perchè una volat che un segnalino
                        arriva alla fine del tracciato la partita finisce ).
                        Così sappiamo qual è l'area VR in cui si trova il segnalino che è in vantaggio.

                        JUAN look in faith track, ho implementato lì

                        */

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
