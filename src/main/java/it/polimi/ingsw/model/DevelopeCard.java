package it.polimi.ingsw.model;

import com.google.gson.*;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.Server.GameHandler;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;




public class DevelopeCard implements Serializable {

    private char colour; // card's colours: G,B,Y,P
    private int level;
    private int pv;
    private ResourceStructure cost;
    private ResourceStructure inputproduction;
    private ResourceStructure outputproduction;


    /**
     * getter and setter
     */

    public void setColour(char colour) { this.colour = colour; }
    public void setInputproduction(ResourceStructure inputproduction) { this.inputproduction = inputproduction; }
    public char getColour() { return colour; }
    public int getLevel() { return level; }
    public int getPv() { return pv; }
    public ResourceStructure getCost() { return cost; }
    public ResourceStructure getInputproduction() { return inputproduction; }
    public ResourceStructure getOutputproduction() { return outputproduction; }


    /**
     * use the number in input to read on the Json file the DevelopeCard I need.
     * return a DevelopeCard with all the parametres of the card i meant.
     * @param number
     * @return
     * @throws FileNotFoundException
     */


    public DevelopeCard setCard(int number) throws FileNotFoundException {

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
     * this method print the details about a developeCard
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
