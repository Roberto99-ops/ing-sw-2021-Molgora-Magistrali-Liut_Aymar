package it.polimi.ingsw;

public class TestDecks {
    public static void main( String[] args )
    {
        DevelopeDecks mazzo = new DevelopeDecks();
        for(int i=0;i<20; i++)
        {
            DevelopeCard carta = new DevelopeCard();
            mazzo.getStructure().add(carta.getCard(i));
        }
        mazzo.setStructure(mazzo.setDeck(mazzo.getStructure()));
        for(int i=0; i<mazzo.getStructure().size(); i++) mazzo.getStructure().get(i).Print();
        mazzo.getDeck(mazzo.getStructure()).Print();
        for(int i=0; i<mazzo.getStructure().size(); i++) mazzo.getStructure().get(i).Print();
    }

}
