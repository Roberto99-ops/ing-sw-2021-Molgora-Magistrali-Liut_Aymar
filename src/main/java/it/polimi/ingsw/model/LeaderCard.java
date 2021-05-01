package it.polimi.ingsw.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderCard{

    private ResourceStructure priceR;
    private ArrayList<Character> priceC;  //this price is in DevelopeCard matching the color
    private int cardLevel;  //level of the developecard I need to buy this card
    private int pv;
    private String skill;
    private char inputskill;
    private int number;

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


    //idea, switch con parametro e che a sua volta chiama una delle
    //4 funzioni di Skill che poi fanno il vero lavoro
    //OPPURE mi sa che è meglio semplicemente trattare tutti i casi nella classe player o turno --> perciò attributo
    //skill delle carte diventa un char
    //number è il numero della carta ovvero la sua posizione nell'array
   /* public ResourceStructure Skill()
    {
        try {
            if (this.number < 12) {
                if (this.number < 8) {
                    if (this.number < 4) {
                        return this.PriceSkill(boh, this.skill);
                    }
                    return this.AdditionalStorageSkill(this.inputskill);
                }
               return this.ConvertWhiteMarbleSkill(this.skill, boh);
            }
            return this.AdditionalProductionSkill(this.skill, boh);
        } catch(Exception e){System.out.println("Inexistent LeaderCard");}
        return null;
    }
*/

    /**
     * this skill decreases the develope card price of a specify resource
     * @param card: card in input to decrease price
     * @return: the "new" cost
     */
    public ResourceStructure PriceSkill(DevelopeCard card)
    {
        ResourceStructure newCost = new ResourceStructure();
        int pos;
        if(!card.getCost().getVector().contains(this.inputskill))  return card.getCost();
        pos=card.getCost().firstOccurance(this.inputskill);
        card.getCost().getVector().remove(pos);
        newCost = card.getCost();
        return newCost;
    }

    /**
     * this skill activate the additional storage layer by specifying the type of resource it can contain
     * @param player: player that get the additional layer
     */
    public void AdditionalStorageSkill(Player player)
    {
        player.getStorage().setTypeExtrapanel(this.inputskill);
    }


    /**
     * this skill "replace" white marbles in the row/column chosen, with marbles of a given colour
     * @param row: is the row of the market containing marbles
     * @return: the "new" row, where white marbles are substituted by marbles of the given color
     */
    public ResourceStructure ConvertWhiteMarbleSkill(ResourceStructure row)
    {
        while(row.getVector().contains('W'))
        {
            row.getVector().remove('W');
            row.getVector().add(this.inputskill);
        }
        return row;
    }

    /**
     * this method do a production.
     * (1)checks if the player owns enough resources to activate the production;
     * (2)then if storage contains enough resources, it removes from there the input resources and add the output resources to the strongbox;
     * (3)if the storage doesn't contain enough resources it does the same thing with the strongbox.
     * it uses the (0,1,2) logic defined into the player.checkresources method to check where the resources are.
     * @param player: is the player who wants to do a production
     */
    public void AdditionalProductionSkill(Player player) throws IOException {

        char resourcechosen;
        ResourceStructure resource = new ResourceStructure();
        resource.add(this.inputskill);

        //(1)
        if(player.CheckResources(resource)!=0) {

            player.increaseTrackposition();
            System.out.println("What resource do you want to produce?\n(B, G, Y, P)\t");
            resourcechosen = (char)System.in.read();

            //(2)
            if(player.CheckResources(resource)==1)
            {
                player.removeResource(this.inputskill);

                player.addResourceStrongBox(resourcechosen); }

            //(3)
            if(player.CheckResources(resource)==2)
            {
                if(player.getStorage().getPanel().contains(this.inputskill))
                        player.removeResource(this.inputskill);
                else
                    player.getStrongBox().deleteResource(this.inputskill);

                player.addResourceStrongBox(resourcechosen); }
        }
        else System.out.println("You don't own enought Resources");
    }


    //serve solo per testare la classe
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
