package it.polimi.ingsw.model;
import java.io.IOException;

public class Turn {
    private Player actualplayer;

    public Player getActualplayer() {
        return actualplayer;
    }

    public void setActualplayer(Player actualplayer) {
        this.actualplayer = actualplayer;
    }

    /**
     * turn "main", it manages all the turn.
     * 1)asks us what do we want to do
     * 2)in case we choose to active a production, we join a while that active all the production we want
     *      2.1)it lists us all our LeaderCards so we can choose to active one of that
     *      2.2) do the same with our Developecards
     * then the turn ends
     */
    public void main() {
        int action=0;
        char again='n';
        int card=0;

        //Ad ogni turno, effettuo il controllo del Vatican Report
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.VaticanReport();

        //1)
        System.out.println("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
        try{ action = System.in.read();
            if(action == 1) this.ShopCard();
            if(action == 2) this.Buyresource();
            //2)
            if(action == 3) { do{//2.1)
                                System.out.println("Which LeaderCard do you want to enable(0=none)?\n");
                                this.actualplayer.getLeadercards().Print();
                                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                                if(card!=0) this.Production(this.actualplayer.getLeadercards().getStructure().get(card));

                                //2.2)
                                System.out.println("Which DevelopeCard do you want to enable(0=none)?\n");
            //da decommentare                    this.actualplayer.getDevelopecards().Print(); //non so come il player accede alle proprie carte sviluppo
                                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                                if(card!=0) this.Production(this.actualplayer.getLeadercards().getStructure().get(card));

                                System.out.println("Do you want to do another production(y/n)?\n");
                                try{ again = (char) System.in.read(); } catch (IOException e){ System.out.println(e);} }while(again=='y');}
        } catch (IOException e){ System.out.println(e);}

    }

    /**
     * this method manages the purchase of a DevelopeCard.
     * the class asks to the costumer what card he do want to buy
     * then check if he own enought resources and complete the transaction
     */
    private void ShopCard() {
        int choose=0;
        Game game=new Game();
        DevelopeCard card = new DevelopeCard();
        System.out.println("Choose the number of the card you want to buy ");
        try{ choose = (char) System.in.read(); } catch (IOException e){ System.out.println(e);}
        //card.equals(game.getCard(choose));  -> questo meth non esiste. Era forse getCard di DevelopeCard?

    //da decommentare    if(!this.actualplayer.getResources().cotains(card.getCost())) return;

    //da decommentare    this.actualplayer.setResources(this.actualplayer.getResources().remove(card.getCost()));
    //da decommentare    this.actualplayer.getDevelopeCards.add(card);
    }

    /**
     * this method manages the action of buying resources at the market.
     * the player choose which row/column and call the manager market method
     * @return
     */
    public ResourceStructure Buyresource() {
        ResourceStructure product = new ResourceStructure();
        int RoworCol = 0;
        int number = 0;

        System.out.println("Do you want to choose a row or a column?\n\t1)Row\n\t2)Column\n");
        try{ RoworCol = System.in.read(); } catch (IOException e){ System.out.println(e); }

        if(RoworCol == 1)
            System.out.println("Wich row do you want to take?\n");
        if(RoworCol == 2)
            System.out.println("Wich column do you want to take?\n");
        try{ number = System.in.read(); } catch (IOException e){ System.out.println(e); }

    //da decommentare    product.setVector(game.getMarket.doMarket(RoworCol, number)); //???

        return product;
    }

    /**
     * active the production of the given card
     * @param card
     */
    private void Production(DevelopeCard card) {
    //da decommentare    this.actualplayer.setResources(card.Production(this.actualplayer.getResources,card));
    }

    private void Production(LeaderCard card) {
    //    this.actualplayer.setResources(card.Production(this.actualplayer.getResources,card));
    }
}