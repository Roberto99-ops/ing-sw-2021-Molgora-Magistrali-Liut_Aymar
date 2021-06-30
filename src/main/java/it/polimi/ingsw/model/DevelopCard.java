package it.polimi.ingsw.model;

import com.google.gson.*;
import com.google.gson.JsonParser;

import java.io.*;


public class DevelopCard implements Serializable {

    private char colour; // card's colours: G,B,Y,P
    private int level;
    private int pv;
    private ResourceStructure cost;
    private ResourceStructure inputproduction;
    private ResourceStructure outputproduction;


    /**
     * Getter and setter
     */

    public char getColour() { return colour; }
    public int getLevel() { return level; }
    public int getPv() { return pv; }
    public ResourceStructure getCost() { return cost; }
    public ResourceStructure getInputproduction() { return inputproduction; }
    public ResourceStructure getOutputproduction() { return outputproduction; }


    /**
     * Uses the number in input to read from the Json file the DevelopCard's info I need.
     * Returns a DevelopCard with all the parameters of the card I want to use.
     * @param number: index of the DevelopCard I need, inside the Json file
     * @return the DevelopCard I want to use
     * @throws FileNotFoundException: if the requested DevelopCard doesn't exist
     */


    public DevelopCard setCard(int number) throws FileNotFoundException {

        inputproduction = new ResourceStructure();
        outputproduction = new ResourceStructure();
        cost = new ResourceStructure();
        int size;

        InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/DevelopeCards.json"));
        Object obj = JsonParser.parseReader(reader);
        JsonObject jsonObject = (JsonObject)obj;
        JsonArray cardsArray = (JsonArray)jsonObject.get("DevelopeCards");
        JsonObject card = (JsonObject)cardsArray.get(number);

        this.level = card.get("level").getAsInt();
        this.colour = card.get("colour").getAsCharacter();
        this.pv = card.get("pv").getAsInt();

        size = card.get("cost").getAsJsonArray().size();
        for(int i=0; i < size; i++)
            cost.getVector().add(card.get("cost").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("inputproduction").getAsJsonArray().size();
        for(int i=0; i < size; i++)
            inputproduction.getVector().add(card.get("inputproduction").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("outputproduction").getAsJsonArray().size();
        for(int i=0; i < size; i++)
            outputproduction.getVector().add(card.get("outputproduction").getAsJsonArray().get(i).getAsCharacter());

        return this;
    }




    /**
     *  ONLY USED FOR TESTING
     * this method print the details about a developCard
     */


    public void print() {
        System.out.println("pv: " + this.pv);
        System.out.println("colour: " + this.colour);
        System.out.println("cost: " + this.cost.getVector());
        System.out.println("inputproduction: " + this.inputproduction.getVector());
        System.out.println("outputproduction: " + this.outputproduction.getVector());
        System.out.println("level: " + this.level + "\n");
    }
}
