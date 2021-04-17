package it.polimi.ingsw.model;
import java.lang.String;

public class Player {

    //ha bisogno di istanze di tutta la plancia

    private String name;  //forse va istanziato
    private int number;
    private int developementquantity;
    private int pv;
    private int trackposition;
    private LeaderDeck leadercards;  //e pure questo
    //ogni player deve avere come attributi anche gli elementi della plancia credo

    /*
    private int VR=0; contatore che per ogni giocatore comincia da 0 e ogni volta che
                        qualcuno arriva in VR, si incrementa fino a 2 (#3 non serve perchè una volta che un segnalino
                        arriva alla fine del tracciato la partita finisce ).
                        Così sappiamo qual è l'area VR in cui si trova il segnalino che è in vantaggio.

                        JUAN look in faith track, ho implementato lì
                        va in game
                        */
    public int getTrackposition() { return trackposition; }

    public LeaderDeck getLeadercards() { return leadercards; }

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

    /**
     * increase the counter that count the quantity of developement card that this player owns
     */
    public void increaseDevelopQuantity() { this.developementquantity = developementquantity+1; }

    public DevelopeDecks getDevelopecards()
    {//deve "intreccarsi" con la plancia in modo da restituire
        //la lista delle carte sviluppo in cima ai mazzetti, forse servono solo quelli
        //della prima fila in quanto la produzioni si fanno solo
        //con le carte in cima ai mazzetti
        return null;
    }

    public ResourceStructure getResources()
    {
        //stesso discorso della plancia anche qui,
        //deve restituire le risorse totali, servono per attivare
        //le produzioni
        return null;
    }

    //quindi servono tutti i getter getStrongbox ecc...
}
