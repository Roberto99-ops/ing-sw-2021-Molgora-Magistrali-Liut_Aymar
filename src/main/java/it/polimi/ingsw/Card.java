package it.polimi.ingsw;
public abstract class Card {
    private ResourceStructure cost;  //decidere se mettere ste classi astratte o farne a meno

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    private int pv;
    private int number;

    //gli diamo il # della carta e ci restituisce la carta
    public Card getCard() { return this; }

}
