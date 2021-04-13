package it.polimi.ingsw.model;

import com.google.gson.*;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DevelopeCard{
    //B, Y, P, G
    private char colour;
    private int level;
    private int pv;
    private ResourceStructure cost;
    private ResourceStructure inputproduction;
    private ResourceStructure outputproduction;

    public char getColour() {
        return colour;
    }

    public int getLevel() {
        return level;
    }

    public int getPv() {
        return pv;
    }

    public ResourceStructure getCost() {
        return cost;
    }

    public ResourceStructure getInputproduction() {
        return inputproduction;
    }

    public ResourceStructure getOutputproduction() {
        return outputproduction;
    }

    public ResourceStructure Production(ResourceStructure inputproduction)
    {
        outputproduction = new ResourceStructure();
        return outputproduction;
    }

    //@Override
    /*public DevelopeCard setCard(int number)
    {
        inputproduction = new ResourceStructure();
        outputproduction = new ResourceStructure();
        cost = new ResourceStructure();
        this.level =
        return this;
    }*/

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

        FileReader stringa = new FileReader("Sources/DevelopeCards.json");
        Object obj = JsonParser.parseReader(stringa);
        JsonObject jsonObject = (JsonObject)obj;
        //int num = jsonObject.get("ciao").getAsInt();
        //System.out.println(num);
        JsonArray cardsArray = (JsonArray)jsonObject.get("DevelopeCards");
        JsonObject card = (JsonObject)cardsArray.get(number);

        this.level = card.get("level").getAsInt();
        this.colour = card.get("colour").getAsCharacter();
        this.pv = card.get("pv").getAsInt();

        size = card.get("cost").getAsJsonArray().size();
        for(int i=0; cost.getVector().size() < size; i++)
            cost.getVector().add(card.get("cost").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("inputproduction").getAsJsonArray().size();
        for(int i=0; inputproduction.getVector().size() < size; i++)
            inputproduction.getVector().add(card.get("inputproduction").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("outputproduction").getAsJsonArray().size();
        for(int i=0; outputproduction.getVector().size() < size; i++)
            outputproduction.getVector().add(card.get("outputproduction").getAsJsonArray().get(i).getAsCharacter());

        return this;
        //char colore = element.get("colour").getAsCharacter();
        //System.out.println(colore);

        //ArrayList<Character> costo =  new ArrayList<Character>();
        //int size = element.get("cost").getAsJsonArray().size();
        //for(int i=0; costo.size()<size; i++)
        //    costo.add(element.get("cost").getAsJsonArray().get(i).getAsCharacter());
        //System.out.println(costo);
    }

    public void Print()
    {
        System.out.println("pv: " + this.pv);
        System.out.println("colour: " + this.colour);
        System.out.println("cost: " + this.cost.getVector());
        System.out.println("inputproduction: " + this.inputproduction.getVector());
        System.out.println("outputproduction: " + this.outputproduction.getVector());
        System.out.println("level: " + this.level + "\n");
    }
}
