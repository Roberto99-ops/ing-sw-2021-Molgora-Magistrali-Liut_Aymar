package it.polimi.ingsw.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
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
     * Getter and setter
     */

    public String getSkill() {
        return skill;
    }
    public char getInputSkill() {
        return inputskill;
    }
    public int getNumber() {
        return number;
    }
    public ResourceStructure getPriceR() {
        return priceR;
    }

    //PriceC: it is an arrayList where every index is occupied with a specific resource
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
     * Uses the input number to read from the Json file the LeaderCard I need.
     * Returns a LeaderCard with all the parameters of the card I want to use.
     * @param number : index of the Card I need, inside of the Json file
     * @return the LeaderCard I want to use
     * @throws FileNotFoundException : when the asked LeaderCard is not available
     */

    public LeaderCard setCard(int number) throws FileNotFoundException {

        priceR = new ResourceStructure();
        priceC = new ArrayList<Character>();
        int size;

        InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/LeaderCards.json"));
        Object obj = JsonParser.parseReader(reader);
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
     * This skill decreases the DevelopCard's price by a specify resource
     * @param card: the card whose price I want to decrease
     * @return the "new" cost of the card given as input
     */

    public ResourceStructure changePriceSkill(DevelopCard card)
    {
        ResourceStructure newCost = new ResourceStructure();
        if(!card.getCost().getVector().contains(this.inputskill))  return card.getCost();
        card.getCost().removeThis(this.inputskill);
        newCost = card.getCost();
        return newCost;
    }

    /**
     * This skill activates the additional storage layer by specifying the type of resource it can contain
     * @param player: player that get the additional layer
     */

    public void addStorageSkill(Player player)
    {
        player.getStorage().setTypeExtraPanel(this.inputskill);
    }


    /**
     * This skill changes white marbles in the row/column chosen, with marbles of a specific colour
     * @param row: it is the row of the market containing marbles
     * @return the "new" row, where white marbles are switched with marbles of a given color
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
     * Prints the LeaderCard's info
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
