package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.messages.DevelopeDeckMsg;
import it.polimi.ingsw.Server.messages.MarketMsg;
import it.polimi.ingsw.Server.messages.PlayerMsg;
import it.polimi.ingsw.client.Client;

import java.io.IOException;
import java.io.Serializable;


public class Turn implements Serializable {

    private Player actualplayer;
    private Game game;
    private int l = game.getLonely();
    private ActionStructure aStructure;
    private ActionSignal signal;
    private ClientHandler client;

    public Turn(ClientHandler clientin)
    {
        aStructure = new ActionStructure();
        signal = new ActionSignal();
        client = clientin;
        actualplayer = client.getPlayer();
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public ActionStructure getaStructure() {
        return aStructure;
    }

    public void setaStructure(ActionStructure aStructure) {
        this.aStructure = aStructure;
    }

    public void setSignal(ActionSignal signal) {
        this.signal = signal;
    }

    public ActionSignal getSignal() {
        return signal;
    }

    public Player getActualplayer() {

        return actualplayer;
    }

    public void setActualplayer(Player actualplayer) {

        this.actualplayer = actualplayer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    /**
     * this method manages the purchase of a DevelopeCard.
     * the class asks to the costumer what card he does want to buy
     * then checks if he owns enough resources and complete the transaction
     */

    public int ShopCard(Game game) throws Exception {
        DevelopeCard card;
        ResourceStructure newcost = new ResourceStructure();
        DevelopeDecks[] gameDeck = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            gameDeck[i] = Game.getDevelopedecks(i);
        }

        DevelopeDeckMsg msg = new DevelopeDeckMsg(gameDeck);
        client.sendMessage(msg);

        client.sendMessage("Choose the number of the card you want to buy ");

        String next = client.receiveMessage();
        int cardNum = next.charAt(0) - 48;
        card = gameDeck[cardNum].getStructure().get(0);

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "PriceSkill")
                newcost = this.actualplayer.getLeadercards().getStructure().get(i).PriceSkill(card);

            //se le risorse presenti in Storage e SB sono sufficienti allora le risorse richieste le elimino e attivo la produzione
            //IDEA: controllare quante risorse di un tipo si necessitano per comprare. Ex: W W Y.
            //      Prima controllo se ho 2 o + W. Se ce le ho, passo a controllare se ho 1 o + Y
        //ex. if (newcost<=actualplayer.getStorage().countTypeS()+actualplayer.getStrongBox()...)

        if (this.actualplayer.CheckResources(newcost)==0) return 0;

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
        return 1;
    }

    /**
     * this method manages the action of buying resources at the market.
     * the player choose which row/column and call the manager market method
     *
     * @param
     * @return
     */

    public ResourceStructure Buyresource() throws IOException, ClassNotFoundException {
        ResourceStructure product = new ResourceStructure();
        Market market = Game.getMarket();
        int RoworCol;
        int number;

        MarketMsg msg = new MarketMsg(market);
        client.sendMessage(msg);

        client.sendMessage("Do you want to choose a row or a column?\n\t1)Row\n\t2)Column\n");
        String next = client.receiveMessage();
        RoworCol = next.charAt(0) - 48;

        if (RoworCol == 1)
            client.sendMessage("Which row do you want to take?\n");
        if (RoworCol == 2)
            client.sendMessage("Which column do you want to take?\n");
        next = client.receiveMessage();
        number = next.charAt(0) - 48;

        product.setVector(game.getMarket().doMarket(RoworCol, number));

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "WhiteMarbSkil")
                product = this.actualplayer.getLeadercards().getStructure().get(i).WhiteMarbleSkill(product);

        return product;
    }

}

