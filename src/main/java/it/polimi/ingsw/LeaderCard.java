package it.polimi.ingsw;
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
     * switch that lists all the leader cards
     * @param number
     * @return
     */
    public LeaderCard getCard(int number)
    {
        this.priceR = new ResourceStructure();
        this.priceC = new ArrayList<Character>();

        switch(number) {
            case 0:
                this.priceR=null;
                this.priceC.add('Y');  //queste carte non so se siano di livello 1
                this.priceC.add('G');  //o di livello ininfluente
                this.pv=2;
                this.skill='P';
                this.cardLevel=1;
                break;
            case 1:
                this.priceR=null;
                this.priceC.add('B');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=2;
                this.skill='B';
                break;
            case 2:
                this.priceR=null;
                this.priceC.add('G');
                this.priceC.add('B');
                this.cardLevel=1;
                this.pv=2;
                this.skill='G';
                break;
            case 3:
                this.priceR=null;
                this.priceC.add('Y');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=2;
                this.skill='Y';
                break;
            case 4:
                this.priceR.AddResource(5, 'Y');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill='G';
                break;
            case 5:
                this.priceR.AddResource(5, 'W');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill='P';
                break;
            case 6:
                this.priceR.AddResource(5, 'P');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill='B';
                break;
            case 7:
                this.priceR.AddResource(5, 'B');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill='Y';
                break;
            case 8:
                this.priceR=null;
                this.priceC.add('Y');
                this.priceC.add('Y');
                this.priceC.add('B');
                this.cardLevel=1;
                this.pv=5;
                this.skill='P';
                break;
            case 9:
                this.priceR=null;
                this.priceC.add('G');
                this.priceC.add('G');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=5;
                this.skill='B';
                break;
            case 10:
                this.priceR=null;
                this.priceC.add('B');
                this.priceC.add('B');
                this.priceC.add('Y');
                this.cardLevel=1;
                this.pv=5;
                this.skill='G';
                break;
            case 11:
                this.priceR=null;
                this.priceC.add('P');
                this.priceC.add('P');
                this.priceC.add('G');
                this.cardLevel=1;
                this.pv=5;
                this.skill='Y';
                break;
            case 12:
                this.priceR=null;
                this.priceC.add('Y');
                this.cardLevel=2;
                this.pv=4;
                this.skill='B';
                break;
            case 13:
                this.priceR=null;
                this.priceC.add('B');
                this.cardLevel=2;
                this.pv=4;
                this.skill='P';
                break;
            case 14:
                this.priceR=null;
                this.priceC.add('P');
                this.cardLevel=2;
                this.pv=4;
                this.skill='G';
                break;
            case 15:
                this.priceR=null;
                this.priceC.add('G');
                this.cardLevel=2;
                this.pv=4;
                this.skill='Y';
                break;
        }
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
     * this skill decereases the develope card price of a specify resource
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
        if(!storage.getVector().contains(resource)) return null;
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
