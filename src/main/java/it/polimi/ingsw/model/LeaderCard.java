package it.polimi.ingsw.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LeaderCard implements Serializable {

    private ResourceStructure priceR;
    private ArrayList<Character> priceC;  //this price is in DevelopeCard matching the color
    private int cardLevel;  //level of the developecard I need to buy this card
    private int pv;
    private String skill;
    private char inputskill;
    private int number;


    /**
     * getter and setter
     */

    public String getSkill() {
        return skill;
    }
    public char getInputskill() {
        return inputskill;
    }
    public int getNumber() {
        return number;
    }
    public ResourceStructure getPriceR() {
        return priceR;
    }

    /**
     * return priceC, priceC is an arraylist witch every cell is a resource (char)
     * @return
     */

    public ArrayList<Character> getPriceC() {
        return priceC;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public int getPv() {
        return pv;
    }



    /**
     * use the number in input to read on the Json file the LeaderCard I need.
     * return a LeaderCard with all the parametres of the card I meant.
     * @param number
     * @return
     * @throws FileNotFoundException
     */


    public LeaderCard setCard(int number) throws FileNotFoundException {

        priceR = new ResourceStructure();
        priceC = new ArrayList<Character>();
        int size;

        FileReader stringa = new FileReader("src/main/resources/LeaderCards.json");
        Object obj = JsonParser.parseReader(stringa);
        JsonObject jsonObject = (JsonObject) obj;
        JsonArray cardsArray = (JsonArray) jsonObject.get("LeaderCards");
        JsonObject card = (JsonObject) cardsArray.get(number);

        this.cardLevel = card.get("cardLevel").getAsInt();
        this.skill = card.get("skill").getAsString();
        this.inputskill = card.get("inputskill").getAsCharacter();
        this.pv = card.get("pv").getAsInt();

        size = card.get("priceR").getAsJsonArray().size();
        for (int i = 0; i < size; i++)
            priceR.getVector().add(card.get("priceR").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("priceC").getAsJsonArray().size();
        for (int i = 0; i < size; i++)
            priceC.add(card.get("priceC").getAsJsonArray().get(i).getAsCharacter());

        return this;
    }




    /**
     * this skill decreases the develope card price of a specify resource
     * @param card: card in input to decrease price
     * @return: the "new" cost
     */

    public ResourceStructure changePriceSkill(DevelopeCard card)
    {
        ResourceStructure newCost = new ResourceStructure();
        int pos;
        if(!card.getCost().getVector().contains(this.inputskill))  return card.getCost();
        card.getCost().removeThis(this.inputskill);
        newCost = card.getCost();
        return newCost;
    }




    /**
     * this skill activate the additional storage layer by specifying the type of resource it can contain
     * @param player: player that get the additional layer
     */

    public void addStorageSkill(Player player)
    {
        player.getStorage().setTypeExtrapanel(this.inputskill);
    }





    /**
     * this skill "replace" white marbles in the row/column chosen, with marbles of a given colour
     * @param row: is the row of the market containing marbles
     * @return: the "new" row, where white marbles are substituted by marbles of the given color
     */

    public ResourceStructure changeWhiteMarbleSkill(ResourceStructure row)
    {
        while(row.getVector().contains('W'))
        {
            row.removeThis('W');
            row.addResource(1, this.inputskill);
        }
        return row;
    }




    /**
     * this method do a production.
     * (1)checks if the player owns enough resources to activate the production;
     * (2)then if storage contains enough resources, it removes from there the input resources and add the output resources to the strongbox;
     * (3)if the storage doesn't contains enough resources it does the same thing with the strongbox.
     * it uses the (0,1,2) logic defined into the player.checkresources method to check where the resources are.
     * @param client: is the client who wants to do a production
     * @param game: game passed (necessary because of the different definition of the player in singlegame or game)
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */


    public int doProductionSkill(ClientHandler client, Game game) throws IOException, ClassNotFoundException {
        GameHandler player;
        if(game.getClass().equals(SingleGame.class))
            player = client.getSinglePlayer();
        else
            player = client.getPlayer();

        ArrayList<Character> list = new ArrayList<>();
        list.add(this.inputskill);
        int check = player.checkResources(list);

        //(1)
        if(check != 0) {
            //(2)
            if(check == 1)
            {
                player.deleteResources(1, list);
                player.increaseTrackposition();
                client.sendMessage("What resource do you want? (Y, P, G, B) ");
                char resource = client.receiveMessage().charAt(0);
                player.addResourceStrongBox(resource); }
            //(3)
            if(check == 2)
            {
                player.deleteResources(2, list);
                player.increaseTrackposition();
                client.sendMessage("What resource do you want? (Y, P, G, B) ");
                char resource = client.receiveMessage().charAt(0);
                player.addResourceStrongBox(resource); }
            return 1;
        }
        else {
            client.sendMessage("You don't own enough Resources. (press enter)");
            client.receiveMessage();
        }
        return 0;
    }




    /**
     * print the leadercard
     */

    public void Print()
    {
        System.out.println("pv: " + this.pv);
        if (this.priceC != null)  System.out.println("priceC: " + this.priceC);
        else System.out.println("priceC: null");
        System.out.println("cardLevel: " + this.cardLevel);
        if (this.priceR != null)  System.out.println("priceR: " + this.priceR.getVector());
        else System.out.println("priceR: null");
        System.out.println("skill: " + this.skill + "\n");
    }
}
