package it.polimi.ingsw;  //mi sa che quelli che abbiamo fatto sono quasi tutti overload e non override

public class DevelopeDecks extends Deck{
    private Deck structure;

    @Override
    public DevelopeCard getDeck() //qui abbiamo scritto male sull'UML
    {
        //non mi fa mettere attributi dentro al metodo
            //qui boh dice cose strane l'override
    }
    public DevelopeCard getDeck(int row, int column) //qui abbiamo scritto male sull'UML
    {
        //non mi fa mettere attributi dentro al metodo
        //qui boh dice cose strane l'override
    }

    @Override
    public void setDeck()
    {

    }
}
