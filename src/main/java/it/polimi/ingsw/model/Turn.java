package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.messages.DevelopeDecksMsg;
import it.polimi.ingsw.Server.messages.MarketMsg;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Turn implements Serializable {

    private GameHandler actualplayer;
    private ActionStructure aStructure;
    private ActionSignal signal;
    private ClientHandler client;
    private Game game;

    public Turn(ClientHandler clientin, Game gamein)
    {
        aStructure = new ActionStructure();
        signal = new ActionSignal();
        client = clientin;
        if(gamein.getClass().equals(SingleGame.class))
            actualplayer = client.getSinglePlayer();
        else
            actualplayer = client.getPlayer();
        game = gamein;
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

    public GameHandler getActualplayer() {

        return actualplayer;
    }

    public void setActualplayer(GameHandler actualplayer) {

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

    public void shopCard() throws Exception {
        DevelopeCard card;
        ResourceStructure cost = new ResourceStructure();
        DevelopeDecks[] gameDeck = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            gameDeck[i] = Game.getDevelopedecks(i);
        }

        DevelopeDecksMsg msg = new DevelopeDecksMsg(gameDeck);
        client.sendMessage(msg);
        client.sendMessage("Choose the number of the card you want to buy ");
        String next = client.receiveMessage();
        int cardNum = Integer.parseInt(next);
        card = gameDeck[cardNum].getStructure().get(0);
        cost.setVector(card.getCost().getVector());


        if (actualplayer.getSkill1() == 1 && actualplayer.getLeadercards().getStructure().get(0).getSkill().equals("PriceSkill"))
            cost = actualplayer.getLeadercards().getStructure().get(0).changePriceSkill(card);
        if (actualplayer.getSkill2() == 1 && actualplayer.getLeadercards().getStructure().get(1).getSkill().equals("PriceSkill"))
            cost = actualplayer.getLeadercards().getStructure().get(1).changePriceSkill(card);

        //here the server checks if the player owns enough resources and, if it is, where
        int check = actualplayer.checkResources(cost.getVector());
        if (check == 0)
        {
            client.sendMessage("You don't own enough resources ( press enter )");
            client.receiveMessage();
            return;
        }


        //add the developecard if possible and remove the cost resources from the player
        if(actualplayer.addDevelopCard(card))
        {
            gameDeck[cardNum].getStructure().remove(0);
            Game.getDevelopedecks(cardNum).getStructure().remove(0);
            actualplayer.deleteResources(check, cost.getVector());
            msg = new DevelopeDecksMsg(gameDeck);
            client.sendMessage(msg);
            // actualplayer.updateDevelopementDecks(client);
            client.sendMessage("these are the new developedecks( press any key )");
            client.receiveMessage();
            return;
        }

        client.sendMessage("you can't buy this card( press any key )");
        client.receiveMessage();
        return;

    }

    /**
     * this method manages the action of buying resources at the market.
     * the player choose which row/column and call the manager market method
     * @param
     * @return
     */

    public void buyResource() throws IOException, ClassNotFoundException {

        ResourceStructure product = new ResourceStructure();
        int RoworCol = 0;
        int number;

        MarketMsg msg = new MarketMsg(Game.getMarket());
        client.sendMessage(msg);

        client.sendMessage("Do you want to choose a row or a column?\n");
        String next = client.receiveMessage();

        if (next.equals("row")) {
            client.sendMessage("Which row do you want to take? (from 0 to 2) \n");
            RoworCol = 1;
        }
        if (next.equals("column")) {
            client.sendMessage("Which column do you want to take? (from 0 to 3) \n");
            RoworCol = 2;
        }

        next = client.receiveMessage();
        number = Integer.parseInt(next);

        product.setVector(Game.getMarket().doMarket(RoworCol, number, actualplayer));

        if (this.actualplayer.getLeadercards().getStructure().get(0).getSkill().equals("WhiteMarbleSkill") && actualplayer.getSkill1() == 1)
                product = this.actualplayer.getLeadercards().getStructure().get(0).changeWhiteMarbleSkill(product);
        if (this.actualplayer.getLeadercards().getStructure().get(1).getSkill().equals("WhiteMarbleSkill") && actualplayer.getSkill2() == 1)
            product = this.actualplayer.getLeadercards().getStructure().get(1).changeWhiteMarbleSkill(product);

        //we "clean" the vector
        for (int i = 0; i < product.getVector().size(); i++){
            if(product.getVector().get(i).equals('R')) {
                actualplayer.increaseTrackposition();
                product.getVector().set(i, 'W');
            }
        }
        for (int i = 0; i < product.getVector().size(); i++) {
            if(product.getVector().get(i).equals('W'))
            {
                product.getVector().remove(i);
                i--;
            }
        }


        for (int i = 0; i < product.getVector().size(); i++) {
            actualplayer.addResourceStorage(product.getVector().get(i));

        }

        return;

    }


    /**
     * this method implements the activation of a leadercard
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void activateLeader() throws IOException, ClassNotFoundException {

        client.sendMessage("Which card do you want to activate?(from up to down 0 1. enter=none) ");
        String choice = client.receiveMessage();

        //player choose first card
        if (choice.equals("0")) {
            LeaderCard card = actualplayer.getLeadercards().getStructure().get(0);
            ArrayList<Character> cost;

            //the leadercard cost is in terms of resources
            if (card.getPriceR().getVector().size() != 0) {
                cost = card.getPriceR().getVector();
                int check = actualplayer.checkResources(cost);
                if (check == 0) {
                    client.sendMessage("You don't own enough resources. (press enter)");
                    client.receiveMessage();
                }
                else {
                    actualplayer.setSkill1(1);
                    if(card.getSkill().equals("StorageSkill"))
                        actualplayer.getStorage().setTypeExtrapanel(card.getInputskill());
                }
            }

            //the leadercard cost is in terms of developecards
            else {
                cost = card.getPriceC();
                int level = card.getCardLevel();
                if(actualplayer.checkCards(level, cost)){
                    actualplayer.setSkill1(1);
                    if(card.getSkill().equals("StorageSkill"))
                        actualplayer.getStorage().setTypeExtrapanel(card.getInputskill());
                }
                else {
                    client.sendMessage("You don't own enough Developecards. ( press enter )");
                    client.receiveMessage();
                }
            }
        }

        if (choice.equals("1")) {
            LeaderCard card = actualplayer.getLeadercards().getStructure().get(1);
            ArrayList<Character> cost;

            //the leadercard cost is in terms of resources
            if (card.getPriceR().getVector().size() != 0) {
                cost = card.getPriceR().getVector();
                int check = actualplayer.checkResources(cost);
                if (check == 0) {
                    client.sendMessage("You don't own enough resources. (press enter)");
                    client.receiveMessage();
                }
                else {
                    actualplayer.setSkill2(1);
                    if(card.getSkill().equals("StorageSkill"))
                        actualplayer.getStorage().setTypeExtrapanel(card.getInputskill());
                }
            }

            //the leadercard cost is in terms of developecards
            else {
                cost = card.getPriceC();
                int level = card.getCardLevel();
                if(actualplayer.checkCards(level, cost)) {
                    actualplayer.setSkill2(1);
                    if(card.getSkill().equals("StorageSkill"))
                        actualplayer.getStorage().setTypeExtrapanel(card.getInputskill());
                }
                else {
                    client.sendMessage("You don't own enough Developecards. ( press enter )");
                    client.receiveMessage();
                }
            }
        }
    }


    /**
     * this method allow to the client to remove a leadercard from his leaderboard
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void removeLeader() throws IOException, ClassNotFoundException {
        client.sendMessage("Which card do you want to remove?(from up to down 0 1. enter=none) ");
        String choice = client.receiveMessage();
        LeaderDeck deck = actualplayer.getLeadercards();

        if(!choice.equals(""))
        {
            int num = Integer.parseInt(choice);
            if(deck.getStructure().size() > num) {
                LeaderCard card = actualplayer.getLeadercards().getStructure().get(num);
                actualplayer.getLeadercards().getStructure().remove(num);
                actualplayer.increaseTrackposition();
                if(num == 0)
                    actualplayer.setSkill1(actualplayer.getSkill2());
                actualplayer.setSkill2(0);
                if(card.getSkill().equals("StorageSkill")) {
                    actualplayer.getStorage().setTypeExtrapanel('Z');
                    ResourceStructure empty = new ResourceStructure();
                    empty.addResource(2, 'N');
                    actualplayer.getStorage().setExtrapanel(empty);
                }
            }
        }
    }

}
