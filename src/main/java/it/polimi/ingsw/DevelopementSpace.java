package it.polimi.ingsw;

public class DevelopementSpace {

    private DevelopeDecks structure1;
    private DevelopeDecks structure2;
    private DevelopeDecks structure3;

    //si potrebbe mettere un if che distingua i casi in cui si può passare a riempire una dei tre ambienti di sviluppo?

    public DevelopeDecks getCard() {
        return structure1;
    }

    public void setCard(DevelopeCard structure) {
        //structure1 = structure;  -> errore perchè ho DDecks = DCards

    }
}



