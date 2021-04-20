package it.polimi.ingsw.model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class LeaderCard{

    private ResourceStructure priceR;
    private ArrayList<Character> priceC;
    private int cardLevel;
    private int pv;
    private char skill;

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
     * return the skill, skill is the resource (char) that we usa in the Skill
     * @return
     */
    public char getSkill() {
        return skill;
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
        this.skill = card.get("skill").getAsCharacter();
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
    /*public void Skill(int number)
    {
        DevelopeCard card=new DevelopeCard();
        char resource;
        switch (number)
        {
            case 0:
                resource='P';
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }*/


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
        newCost.equals(card.getCost().getVector().remove(resource)); //!!!non testato
        return newCost;
    }

    //la gestione è tutta dentro player

    /**
     * this skill give to the player the additional storage layer
     * @param resource
     * @return
     */
    public char AdditionalStorageSkill(char resource)
    {
        return resource;
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
    public ResourceStructure AdditionalProductionSkill(char resource, ResourceStructure storage)
    {
        if(!storage.getVector().contains(resource)) return null; //sbagliata, resource la ricevo, non la pago credo
        storage.getVector().remove(resource);
        ResourceStructure production = new ResourceStructure();
        production.getVector().add('R');
        //qui va fatta la scelta della risorsa da produrre, come facciamo a chiderlo all'utente? printf scanf?
        return production;
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