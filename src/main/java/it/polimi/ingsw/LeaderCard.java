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

    public ArrayList<Character> getPriceC() {
        return priceC;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public int getPv() {
        return pv;
    }

    public char getSkill() {
        return skill;
    }

    //priceC si può fare un arraylist di char in cui ogni elemento è il colore di una carta.
    //per risparmiare strutture si potrebbe usare una lettera D per dire che la carta deve essere di livello 2.
    //oppure un altro attributo che indica il livello della carta.
    public LeaderCard getCard(int number)
    {
        //ArrayList<Resource> array = new ArrayList<Resource>();
        //Resource r = new Resource();
        //LeaderCard card = new LeaderCard();
        this.priceR = new ResourceStructure();
        this.priceC = new ArrayList<Character>();

        switch(number) {
            case 0:
                //r.setColour('Y');
                //this.priceR.getVector().add('Y');
                //this.priceR.AddResource('Y');
                //r.setColour('G');
                //this.priceR.getVector().add('G');
                //this.priceR.AddResource('G');
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

    //skill che diminuisce il prezzo delle carte sviluppo di una certa risorsa
    public ResourceStructure PriceSkill(DevelopeCard card, char resource)
    {
        ResourceStructure newCost = new ResourceStructure();
        if(!card.getCost().getVector().contains(resource))  return card.getCost();
        newCost.equals(card.getCost().getVector().remove(resource)); //!!!non testato
        return newCost;
    }

    //skill che da un deposito in più
    //la gestione è tutta dentro player
    public char AdditionalStorageSkill(char resource)
    {
        return resource;
    }

    //skill che sostituisce le biglie bianche della riga/colonna selezionata con una certa risorsa
    public ResourceStructure ConvertWhiteMarbleSkill(char resource, ResourceStructure row)
    {
        while(row.getVector().contains('W'))
        {
            row.getVector().remove('W');
            row.getVector().add(resource);
        }
        return row;
    }

    //skill che fornisce un potere di produzione aggiuntivo, non so come abbiamo implementato i punti fede quindi per
    //ora li tratto solo come una risorsa rossa
    //non so se devo dargli in ingresso anche il baule o storage in modo da togliere quella risorsa
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
