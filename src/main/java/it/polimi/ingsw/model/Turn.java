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
        int cardNum;
        DevelopeCard card;
        ResourceStructure cost = new ResourceStructure();
        DevelopeDecks[] gameDeck = new DevelopeDecks[12];
        for (int i = 0; i < 12; i++) {
            gameDeck[i] = Game.getDevelopedecks(i);
        }

        DevelopeDecksMsg msg = new DevelopeDecksMsg(gameDeck);
        client.sendMessage(msg);
        do {
            client.sendMessage("Choose the number of the card you want to buy ");
            String next = client.receiveMessage();

            cardNum = next.charAt(0) - 48;
            if(next.length() == 2) {
                cardNum = cardNum * 10 + next.charAt(1) - 48;   //to convert a number > 9
            }

            if(gameDeck[cardNum].getStructure().size() == 0)    cardNum = 20; //only to avoid to choose an empty deck
        } while(cardNum > 12);
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


        String c;
        int choice;

        do {
            client.sendMessage("Choose the space where you want to put this card( 1, 2 , 3)  ");
            c = client.receiveMessage();
            choice = c.charAt(0) - 48;
        } while (choice > 3 || choice <= 0);

        //add the developecard if possible and remove the cost resources from the player
        if(actualplayer.getDSpace().setCard(card, choice))
        {
            actualplayer.increasePV(card.getPv());
            gameDeck[cardNum].getStructure().remove(0);
            // Game.getDevelopedecks(cardNum).getStructure().remove(0);
            actualplayer.deleteResources(check, cost.getVector());
            msg = new DevelopeDecksMsg(gameDeck);
            client.sendMessage(msg);
            // actualplayer.updateDevelopementDecks(client);
            client.sendMessage("These are the new developedecks( press any key )");
            client.receiveMessage();
            return;
        }

        client.sendMessage("you can't buy this card( press any key )");
        client.receiveMessage();

    }

    /**
     * this method manages the action of buying resources at the market.
     * the player choose which row/column and call the manager market method
     */

    public void buyResource() throws IOException, ClassNotFoundException {

        ResourceStructure product = new ResourceStructure();
        int RoworCol = 0;
        int number = 0;
        String next = new String();
        MarketMsg msg = new MarketMsg(Game.getMarket());
        client.sendMessage(msg);

        while(!next.equals("row") && !next.equals("column")){
            client.sendMessage("Do you want to choose a row or a column?\n");
             next = client.receiveMessage();
        }

        if (next.equals("row"))
            do {
                client.sendMessage("Which row do you want to take? (from 0 to 2) \n");
                RoworCol = 1;
                String num = client.receiveMessage();
                number = num.charAt(0) - 48;
            }while(number > 2 || number <0);

        if (next.equals("column"))
            do{
                client.sendMessage("Which column do you want to take? (from 0 to 3) \n");
                RoworCol = 2;
                String num = client.receiveMessage();
                number = num.charAt(0) - 48;
        }while(number > 3 || number <0);

        product.setVector(Game.getMarket().doMarket(RoworCol, number));

        if (actualplayer.getSkill1() == 1 && actualplayer.getLeadercards().getStructure().get(0).getSkill().equals("WhiteMarbSkil"))
                product = actualplayer.getLeadercards().getStructure().get(0).changeWhiteMarbleSkill(product);
        if (actualplayer.getSkill2() == 1 && actualplayer.getLeadercards().getStructure().get(1).getSkill().equals("WhiteMarbSkil"))
                product = actualplayer.getLeadercards().getStructure().get(1).changeWhiteMarbleSkill(product);

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
            actualplayer.addResourceStorage(product.getVector().get(i), game);

        }

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
                    actualplayer.increasePV(card.getPv());
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
                    actualplayer.increasePV(card.getPv());
                    if(card.getSkill().equals("StorageSkill"))
                        actualplayer.getStorage().setTypeExtrapanel(card.getInputskill());
                }
                else {
                    client.sendMessage("You don't own enough Developecards. ( press enter )");
                    client.receiveMessage();
                }
            }
        }


        //player choose second card
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
                    actualplayer.increasePV(card.getPv());
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
                    actualplayer.increasePV(card.getPv());
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
                if(num == 0) {
                    if(actualplayer.getSkill1() == 1)   actualplayer.decreasePV(card.getPv());
                    actualplayer.setSkill1(actualplayer.getSkill2());
                }
                if(num == 1)
                    if(actualplayer.getSkill2() == 1)   actualplayer.decreasePV(card.getPv());
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
