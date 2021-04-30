package it.polimi.ingsw.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        //int num = jsonObject.get("ciao").getAsInt();
        //System.out.println(num);
        JsonArray cardsArray = (JsonArray) jsonObject.get("LeaderCards");
        JsonObject card = (JsonObject) cardsArray.get(number);

        this.cardLevel = card.get("cardLevel").getAsInt();
        //this.skill = card.get("skill").getAsString();
        this.inputskill = card.get("skill").getAsCharacter();
        this.pv = card.get("pv").getAsInt();

        size = card.get("priceR").getAsJsonArray().size();
        for (int i = 0; priceR.getVector().size() < size; i++)
            priceR.getVector().add(card.get("priceR").getAsJsonArray().get(i).getAsCharacter());

        size = card.get("priceC").getAsJsonArray().size();
        for (int i = 0; priceC.size() < size; i++)
            priceC.add(card.get("priceC").getAsJsonArray().get(i).getAsCharacter());

        return this;
    }


    //idea, switch con parametro e che a sua volta chiama una delle
    //4 funzioni di Skill che poi fanno il vero lavoro
    //OPPURE mi sa che è meglio semplicemente trattare tutti i casi nella classe player o turno --> perciò attributo
    //skill delle carte diventa un char
    //number è il numero della carta ovvero la sua posizione nell'array
    public ResourceStructure Skill()
    {
        DevelopeCard card=new DevelopeCard();
        try {
            if (this.number < 12) {
                if (this.number < 8) {
                    if (this.number < 4) {
    //da sistemare                    return this.PriceSkill(boh, this.skill);
                    }
                    return this.AdditionalStorageSkill(this.inputskill);
                }
    //da sistemare           return this.ConvertWhiteMarbleSkill(this.skill, boh);
            }
   //da sistemare         return this.AdditionalProductionSkill(this.skill, boh);
        } catch(Exception e){System.out.println("Inexistent LeaderCard");}
        return null;
    }


    /**
     * this skill decreases the develope card price of a specify resource
     * @param card
     * @param resource
     * @return
     */
    public ResourceStructure PriceSkill(DevelopeCard card, char resource)
    {
        ResourceStructure newCost = new ResourceStructure();
        if(!card.getCost().getVector().contains(resource))  return card.getCost();
        newCost.equals(card.getCost().getVector().remove(resource));
        return newCost;
    }

    /**
     * this skill give to the player the type fo resource that can be
     * contained in the additional storage layer
     * @param resource
     * @return
     */
    public ResourceStructure AdditionalStorageSkill(char resource)
    {
        ResourceStructure resourceType = new ResourceStructure();
        resourceType.AddResource(1, resource);
        return resourceType;   //deve mettere a 1 il flag che dice che il player ha un piano in più nello storage
    }

    /**
     * this skill "replace" white marbles in the row/column chosen, with marbles of a given colour
     * @param resource
     * @param row
     * @return
     */
    public ResourceStructure ConvertWhiteMarbleSkill(char resource, ResourceStructure row)
    {
        while(row.getVector().contains('W'))
        {
            row.getVector().remove('W');
            row.getVector().add(resource);
        }
        return row;
    }

    //non so come abbiamo implementato i punti fede quindi per
    //ora li tratto solo come una risorsa rossa
    //non so se devo dargli in ingresso anche il baule o storage in modo da togliere quella risorsa

    /**
     * this skill give us an additional production we can do
     * @param resource
     * @param storage
     * @return
     */
    public ResourceStructure AdditionalProductionSkill(char resource, ResourceStructure storage) throws IOException {
        char resourceChosen;    //storage in ingresso
        if(!storage.getVector().contains(resource)) return null;
        storage.getVector().remove(resource);
        ResourceStructure production = new ResourceStructure();
        production.AddResource(1, 'R');
        System.out.println("What resource do you want to produce?\n(B, G, Y, P)\t");
        resourceChosen = (char)System.in.read();
        production.AddResource(1, resourceChosen);
        return production;  //copiare quello di developecard e al posto della risorsa rossa fare player.setposition++
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
