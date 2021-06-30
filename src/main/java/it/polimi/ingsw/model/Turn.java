package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.Server.messages.DevelopeDecksMsg;
import it.polimi.ingsw.Server.messages.MarketMsg;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;




public class Turn implements Serializable {

    private GameHandler actualPlayer;
    private ActionStructure aStructure;
    private ActionSignal signal;
    private ClientHandler client;
    private Game game;




    public Turn(ClientHandler clientIn, Game gameIn)
    {
        aStructure = new ActionStructure();
        signal = new ActionSignal();
        client = clientIn;
        if(gameIn.getClass().equals(SingleGame.class))
            actualPlayer = client.getSinglePlayer();
        else
            actualPlayer = client.getPlayer();
        game = gameIn;
    }




    /**
     * Getter and setter
     */

    public GameHandler getActualPlayer() {

        return actualPlayer;
    }



    /**
     * This method manages the purchase of a DevelopCard: it asks what card he wants to buy, then checks if he owns
     * enough resources and completes the transaction
     */

    public boolean shopCard() throws Exception {
        int cardNum;
        DevelopCard card;
        ResourceStructure cost = new ResourceStructure();
        DevelopDecks[] gameDeck = new DevelopDecks[12];
        for (int i = 0; i < 12; i++) {
            gameDeck[i] = Game.getDevelopDecks(i);
        }

        DevelopeDecksMsg msg = new DevelopeDecksMsg(gameDeck);
        client.sendMessage(msg);
        do {
            client.sendMessage("Choose the number of the card you want to buy ");
            String next = client.receiveMessage();

            cardNum = next.charAt(0) - 48;
            if(next.length() == 2) {
                cardNum = cardNum * 10 + next.charAt(1) - 48;
            }

            if(gameDeck[cardNum].getStructure().size() == 0)    cardNum = 20;
        } while(cardNum > 12);
        card = gameDeck[cardNum].getStructure().get(0);
        cost.setVector(card.getCost().getVector());


        if (actualPlayer.getSkill1() == 1 && actualPlayer.getLeadercards().getStructure().get(0).getSkill().equals("PriceSkill"))
            cost = actualPlayer.getLeadercards().getStructure().get(0).changePriceSkill(card);
        if (actualPlayer.getSkill2() == 1 && actualPlayer.getLeadercards().getStructure().get(1).getSkill().equals("PriceSkill"))
            cost = actualPlayer.getLeadercards().getStructure().get(1).changePriceSkill(card);


        int check = actualPlayer.checkResources(cost.getVector());
        if (check == 0)
        {
            client.sendMessage("You don't own enough resources ( press enter )");
            client.receiveMessage();
            return false;
        }


        String c;
        int choice;

        //the Game asks the Player where he/she wants to put the DevelopCard on his/her DevelopmentSpace
        do {
            client.sendMessage("Choose the space where you want to put this card( 1, 2 , 3)  ");
            c = client.receiveMessage();
            choice = c.charAt(0) - 48;
        } while (choice > 3 || choice <= 0);


        if(actualPlayer.getDSpace().setCard(card, choice))
        {
            actualPlayer.increasePV(card.getPv());
            gameDeck[cardNum].getStructure().remove(0);

            actualPlayer.deleteResources(check, cost.getVector());
            msg = new DevelopeDecksMsg(gameDeck);
            client.sendMessage(msg);

            client.sendMessage("These are the new DevelopDecks (press any key)");
            client.receiveMessage();
            return true;
        }

        client.sendMessage("You can't buy this card (press any key)");
        client.receiveMessage();
        return false;

    }



    /**
     * This method manages the action of buying resources at the market: the player chooses which row/column
     * and calls the "manager market" method
     * @throws IOException if the choice made is not available
     * @throws ClassNotFoundException if the choice made is not available
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
                if(num!= "")
                    number = num.charAt(0) - 48;
            }while(number > 2 || number <0);

        if (next.equals("column"))
            do{
                client.sendMessage("Which column do you want to take? (from 0 to 3) \n");
                RoworCol = 2;
                String num = client.receiveMessage();
                if(num!= "")
                    number = num.charAt(0) - 48;
        }while(number > 3 || number <0);

        product.setVector(Game.getMarket().doMarket(RoworCol, number));

        if (actualPlayer.getSkill1() == 1 && actualPlayer.getLeadercards().getStructure().get(0).getSkill().equals("WhiteMarbSkil"))
                product = actualPlayer.getLeadercards().getStructure().get(0).changeWhiteMarbleSkill(product);
        if (actualPlayer.getSkill2() == 1 && actualPlayer.getLeadercards().getStructure().get(1).getSkill().equals("WhiteMarbSkil"))
                product = actualPlayer.getLeadercards().getStructure().get(1).changeWhiteMarbleSkill(product);


        for (int i = 0; i < product.getVector().size(); i++){
            if(product.getVector().get(i).equals('R')) {
                actualPlayer.increaseTrackPosition();
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
            actualPlayer.addResourceStorage(product.getVector().get(i), game);

        }

    }



    /**
     * this method implements the activation of a LeaderCard
     * @throws IOException if the choice made is not available
     * @throws ClassNotFoundException if the choice made is not available
     */

    public void activateLeader() throws IOException, ClassNotFoundException {

        client.sendMessage("Which card do you want to activate?(from up to down 0 1. enter=none) ");
        String choice = client.receiveMessage();


        if (choice.equals("0")) {
            LeaderCard card = actualPlayer.getLeadercards().getStructure().get(0);
            ArrayList<Character> cost;


            if (card.getPriceR().getVector().size() != 0) {
                cost = card.getPriceR().getVector();
                int check = actualPlayer.checkResources(cost);
                if (check == 0) {
                    client.sendMessage("You don't own enough resources (press enter)");
                    client.receiveMessage();

                }
                else {
                    actualPlayer.setSkill1(1);
                    actualPlayer.increasePV(card.getPv());
                    if(card.getSkill().equals("StorageSkill"))
                        actualPlayer.getStorage().setTypeExtraPanel(card.getInputSkill());
                }
            }



            else {
                cost = card.getPriceC();
                int level = card.getCardLevel();
                if(actualPlayer.checkCards(level, cost)){
                    actualPlayer.setSkill1(1);
                    actualPlayer.increasePV(card.getPv());
                    if(card.getSkill().equals("StorageSkill"))
                        actualPlayer.getStorage().setTypeExtraPanel(card.getInputSkill());
                }
                else {
                    client.sendMessage("You don't own enough DevelopCards (press enter)");
                    client.receiveMessage();
                }
            }
        }


        if (choice.equals("1")) {
            LeaderCard card = actualPlayer.getLeadercards().getStructure().get(1);
            ArrayList<Character> cost;


            if (card.getPriceR().getVector().size() != 0) {
                cost = card.getPriceR().getVector();
                int check = actualPlayer.checkResources(cost);
                if (check == 0) {
                    client.sendMessage("You don't own enough resources (press enter)");
                    client.receiveMessage();
                }
                else {
                    actualPlayer.setSkill2(1);
                    actualPlayer.increasePV(card.getPv());
                    if(card.getSkill().equals("StorageSkill"))
                        actualPlayer.getStorage().setTypeExtraPanel(card.getInputSkill());
                }
            }


            else {
                cost = card.getPriceC();
                int level = card.getCardLevel();
                if(actualPlayer.checkCards(level, cost)) {
                    actualPlayer.setSkill2(1);
                    actualPlayer.increasePV(card.getPv());
                    if(card.getSkill().equals("StorageSkill"))
                        actualPlayer.getStorage().setTypeExtraPanel(card.getInputSkill());
                }
                else {
                    client.sendMessage("You don't own enough DevelopCards (press enter)");
                    client.receiveMessage();
                }
            }
        }
    }



    /**
     * This method allows the Player to remove a LeaderCard from his leaderboard.
     * When the Player remains with just one card, the Game accepts also choice "1" even if that position is not occupied
     * @throws IOException if the choice made is not available
     * @throws ClassNotFoundException if the choice made is not available
     */

    public void removeLeader() throws IOException, ClassNotFoundException {
        client.sendMessage("Which card do you want to remove?(from up to down 0 1. enter=none) ");
        String choice = client.receiveMessage();
        LeaderDeck deck = actualPlayer.getLeadercards();

        if(!choice.equals(""))
        {
            int num = Integer.parseInt(choice);
            //if LeaderDeck is empty, the player can do nothing
            if (deck.getStructure().size()<2 && num==1) num=0;
            //if LeaderDeck is not empty and I remove one LeaderCard...
            if(deck.getStructure().size() > num) {
                LeaderCard card = actualPlayer.getLeadercards().getStructure().get(num);
                actualPlayer.getLeadercards().getStructure().remove(num);
                //... the player moves forward by one on the FaithTrack
                actualPlayer.increaseTrackPosition();
                //it removes the skill of the deleted LeaderCard
                if(num == 0) {
                    if(actualPlayer.getSkill1() == 1)   actualPlayer.decreasePV(card.getPv());
                    actualPlayer.setSkill1(actualPlayer.getSkill2());
                }
                if(num == 1)
                    if(actualPlayer.getSkill2() == 1)   actualPlayer.decreasePV(card.getPv());
                actualPlayer.setSkill2(0);
                //if the skill is "StorageSkill", the Game resets the extraPanel
                if(card.getSkill().equals("StorageSkill")) {
                    actualPlayer.getStorage().setTypeExtraPanel('Z');
                    ResourceStructure empty = new ResourceStructure();
                    empty.addResource(2, 'N');
                    actualPlayer.getStorage().setExtraPanel(empty);
                }
            }
        }
    }

}
