package it.polimi.ingsw.model;

import com.google.gson.*;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DevelopeCard{

    //Colori carte: G,B,Y,P
    private char colour;
    private int level;
    private int pv;
    private ResourceStructure cost;
    private ResourceStructure inputproduction;
    private ResourceStructure outputproduction;


    public void setColour(char colour) {
        this.colour = colour;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setCost(ResourceStructure cost) {
        this.cost = cost;
    }

    public void setInputproduction(ResourceStructure inputproduction) {
        this.inputproduction = inputproduction;
    }

    public void setOutputproduction(ResourceStructure outputproduction) {
        this.outputproduction = outputproduction;
    }

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


    /**
     * this method do a production.
     * (1)checks if the player owns enough resources to activate the production;
     * (2)then if storage contains enough resources, it removes from there the input resources and add the output resources to the strongbox;
     * (3)if the storage doesn't contain enough resources it does the same thing with the strongbox.
     * it uses the (0,1,2) logic defined into the player.checkresources method to check where the resources are.
     * @param player: is the player who wants to do a production
     */
    public void DoProduction(Player player)
    {
        //(1)
        if(player.CheckResources(this.inputproduction.getVector())!=0) {
        //(2)
            if(player.CheckResources(this.inputproduction.getVector())==1)
            {
                for(int i=0; i<this.inputproduction.getVector().size(); i++)
                    player.removeResource(this.inputproduction.getVector().get(i));

                for(int i=0; i<this.outputproduction.getVector().size(); i++)
                    player.addResourceStrongBox(this.outputproduction.getVector().get(i)); }
        //(3)
            if(player.CheckResources(this.inputproduction.getVector())==2)
            {
                for(int i=0; i<this.inputproduction.getVector().size(); i++) {
                    {
                        if (player.getStorage().getPanel().contains(this.inputproduction.getVector().get(i)))
                            player.removeResource(this.inputproduction.getVector().get(i));
                        else
                            player.getStrongBox().deleteResource(this.inputproduction.getVector().get(i));
                    }
                }
                for(int i=0; i<this.outputproduction.getVector().size(); i++)
                    player.addResourceStrongBox(this.outputproduction.getVector().get(i)); }
           }
        else System.out.println("You don't own enough Resources");
    }


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

        FileReader stringa = new FileReader("src/main/resources/DevelopeCards.json");
        Object obj = JsonParser.parseReader(stringa);
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
