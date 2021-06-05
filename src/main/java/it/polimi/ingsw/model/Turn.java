package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.Server.messages.DevelopeDeckMsg;
import it.polimi.ingsw.Server.messages.MarketMsg;

import java.io.IOException;
import java.io.Serializable;


public class Turn implements Serializable {

    private ObserverGame actualplayer;
    private Game game;
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

    /**
     * getter and setter
     *
     * @return
     */

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

    public ObserverGame getActualplayer() {

        return actualplayer;
    }

    public void setActualplayer(ObserverGame actualplayer) {

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

    public int shopCard(Game game) throws Exception {
        DevelopeCard card;
        ResourceStructure newcost = new ResourceStructure();
        DevelopeDecks[] gameDeck = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            gameDeck[i] = Game.getDevelopedecks(i);
        }

        DevelopeDeckMsg msg = new DevelopeDeckMsg(gameDeck);
        client.sendMessage(msg);
        client.sendMessage("Choose the number of the card you want to buy");
        String next = client.receiveMessage();
        int cardNum = next.charAt(0) - 48;
        card = gameDeck[cardNum].getStructure().get(0);

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "PriceSkill")
                newcost = this.actualplayer.getLeadercards().getStructure().get(i).changePriceSkill(card);

            //se le risorse presenti in Storage e SB sono sufficienti allora le risorse richieste le elimino e attivo la produzione
            //IDEA: controllare quante risorse di un tipo si necessitano per comprare. Ex: W W Y.
            //      Prima controllo se ho 2 o + W. Se ce le ho, passo a controllare se ho 1 o + Y
        //ex. if (newcost<=actualplayer.getStorage().countTypeS()+actualplayer.getStrongBox()...)

        if (this.actualplayer.checkResources(newcost)==0) return 0;

        if (this.actualplayer.checkResources(newcost)==1)
        {
            for(int i=0; i<newcost.getVector().size(); i++)
                this.actualplayer.removeResource(newcost.getVector().get(i));
        }

        if(this.actualplayer.checkResources(newcost)==2)
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
     * @param
     * @return
     */

    public ResourceStructure buyResource() throws IOException, ClassNotFoundException {
        ResourceStructure product = new ResourceStructure();
        int RoworCol = 0;
        int number;

        MarketMsg msg = new MarketMsg(Game.getMarket());
        client.sendMessage(msg);

        client.sendMessage("Do you want to choose a row or a column?\n");
        String next = client.receiveMessage();

        if (next.equals("row")) {
            client.sendMessage("Which row do you want to take? (from 0 to 2) \n");
            RoworCol = Integer.parseInt(client.receiveMessage());;
        }
        if (next.equals("column")) {
            client.sendMessage("Which column do you want to take? (from 0 to 3) \n");
            RoworCol = Integer.parseInt(client.receiveMessage());;
        }

        // cosa significa?

        next = client.receiveMessage();
        number = next.charAt(0) - 48;

        product.setVector(Game.getMarket().doMarket(RoworCol, number, actualplayer));

        for (int i = 0; i < this.actualplayer.getLeadercards().getStructure().size(); i++)
            if (this.actualplayer.getLeadercards().getStructure().get(i).getSkill() == "WhiteMarbleSkill")
                product = this.actualplayer.getLeadercards().getStructure().get(i).changeWhiteMarbleSkill(product);

        return product;

    }

}

