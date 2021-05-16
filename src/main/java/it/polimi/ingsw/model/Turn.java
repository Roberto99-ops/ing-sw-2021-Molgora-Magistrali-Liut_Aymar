package it.polimi.ingsw.model;
import java.io.IOException;


public class Turn {

    private Player actualplayer;
    private Game game;
    private int l = game.getLonely();
    private ActionStructure aStructure;
    private ActionSignal signal;

    public Player getActualplayer() {

        return actualplayer;
    }

    public void setActualplayer(Player actualplayer) {

        this.actualplayer = actualplayer;
    }

    //da spostare nel controller
    public void main() throws Exception {
        int action = 0;
        char again = 'n';
        int card = 0;

        //Ad ogni turno, effettuo il controllo del Vatican Report
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.VaticanReport(actualplayer);

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (l == 1) {
            signal.Action(aStructure.PickSignal());
        }
/*
        //1)
        System.out.println("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
        try {
            action = System.in.read();
            if (action == 1) this.ShopCard(game);
            if (action == 2) this.Buyresource(game);
            //2)
            if(action == 3) { do{//2.1)
                System.out.println("Which LeaderCard do you want to enable(0=none)?\n");
                this.actualplayer.getLeadercards().Print();
                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                if(card>0) this.actualplayer.getLeadercards().getStructure().get(card-1).Skill();

                //2.2)
                System.out.println("Which DevelopeCard do you want to enable(0=none)?\n");
                this.actualplayer.TopCardsOnBoard.Print(); //l'arraylist di carte viene stampato
                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                //prendo la carta nella posizione i-1 nell'arraylist ed eseguo la sua produzione
                if(card>0) this.actualplayer.TopCardsOnBoard.getStructure().get(card-1).DoProduction(this.actualplayer);

                System.out.println("Do you want to do another production(y/n)?\n");
                try{ again = (char) System.in.read(); } catch (IOException e){ System.out.println(e);} }while(again=='y');}
        } catch (IOException e){ System.out.println(e);}
*/

    }



    /**
     * this method manages the purchase of a DevelopeCard.
     * the class asks to the costumer what card he does want to buy
     * then checks if he owns enough resources and complete the transaction
     */

    public void ShopCard(Game game) throws Exception {
        int choose = 0;
        DevelopeCard card = new DevelopeCard();
        ResourceStructure newcost = new ResourceStructure();
        System.out.println("Choose the number of the card you want to buy ");
        try {
            choose = (char) System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
        card.equals(game.getDevelopedecks(choose).getStructure().get(0));

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "PriceSkill")
                newcost = this.actualplayer.getLeadercards().getStructure().get(i).PriceSkill(card);

            //se le risorse presenti in Storage e SB sono sufficienti allora le risorse richieste le elimino e attivo la produzione
            //IDEA: controllare quante risorse di un tipo si necessitano per comprare. Ex: W W Y.
            //      Prima controllo se ho 2 o + W. Se ce le ho, passo a controllare se ho 1 o + Y
        //ex. if (newcost<=actualplayer.getStorage().countTypeS()+actualplayer.getStrongBox()...)

        if (this.actualplayer.CheckResources(newcost)==0) return;

        if (this.actualplayer.CheckResources(newcost)==1)
        {
            for(int i=0; i<newcost.getVector().size(); i++)
                this.actualplayer.removeResource(newcost.getVector().get(i));
        }

        if(this.actualplayer.CheckResources(newcost)==2)
        {
            for(int i=0; i<newcost.getVector().size(); i++) {

                    if (this.actualplayer.getStorage().getPanel().contains(newcost.getVector().get(i)))
                        this.actualplayer.removeResource(newcost.getVector().get(i));
                    else
                        this.actualplayer.getStrongBox().deleteResource(newcost.getVector().get(i));
                }
        }

        this.actualplayer.getDevelopecards().getStructure().add(card);
    }

    /**
     * this method manages the action of buying resources at the market.
     * the player choose which row/column and call the manager market method
     *
     * @param game
     * @return
     */

    public ResourceStructure Buyresource(Game game) {
        ResourceStructure product = new ResourceStructure();
        Market market = game.getMarket();
        int RoworCol = 0;
        int number = 0;

        System.out.println("Do you want to choose a row or a column?\n\t1)Row\n\t2)Column\n");
        try {
            RoworCol = System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (RoworCol == 1)
            System.out.println("Which row do you want to take?\n");
        if (RoworCol == 2)
            System.out.println("Which column do you want to take?\n");
        try {
            number = System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }

        product.setVector(game.getMarket().doMarket(RoworCol, number));

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "WhiteMarbSkil")
                product = this.actualplayer.getLeadercards().getStructure().get(i).WhiteMarbleSkill(product);

        return product;
    }

}

