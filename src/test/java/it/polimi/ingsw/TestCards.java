package it.polimi.ingsw;

public class TestCards {
    public static void main( String[] args )
    {
        LeaderCard card = new LeaderCard();
        for(int i=0; i<16; i++)
        {
            card.getCard(i);
            System.out.println("LeaderCard "+i+" :");
            card.Print();
        }
        DevelopeCard card1 = new DevelopeCard();
        for(int i=0; i<48; i++) {
            card1.getCard(i);
            System.out.println("DevelopCard " + i + " :");
            card1.Print();
        }
    }
}