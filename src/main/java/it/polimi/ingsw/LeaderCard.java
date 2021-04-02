package it.polimi.ingsw;
import java.util.ArrayList;

public class LeaderCard{
    private ResourceStructure priceR;
    private ArrayList<Character> priceC;
    private int cardLevel;
    private int pv;
    private int skill;

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
                this.skill=0;
                this.cardLevel=1;
                break;
            case 1:
                this.priceR=null;
                this.priceC.add('B');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=2;
                this.skill=1;
                break;
            case 2:
                this.priceR=null;
                this.priceC.add('G');
                this.priceC.add('B');
                this.cardLevel=1;
                this.pv=2;
                this.skill=2;
                break;
            case 3:
                this.priceR=null;
                this.priceC.add('Y');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=2;
                this.skill=3;
                break;
            case 4:
                this.priceR.AddResource(5, 'Y');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill=4;
                break;
            case 5:
                this.priceR.AddResource(5, 'W');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill=5;
                break;
            case 6:
                this.priceR.AddResource(5, 'P');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill=6;
                break;
            case 7:
                this.priceR.AddResource(5, 'B');
                this.priceC=null;
                this.cardLevel=0;
                this.pv=3;
                this.skill=7;
                break;
            case 8:
                this.priceR=null;
                this.priceC.add('Y');
                this.priceC.add('Y');
                this.priceC.add('B');
                this.cardLevel=1;
                this.pv=5;
                this.skill=8;
                break;
            case 9:
                this.priceR=null;
                this.priceC.add('G');
                this.priceC.add('G');
                this.priceC.add('P');
                this.cardLevel=1;
                this.pv=5;
                this.skill=9;
                break;
            case 10:
                this.priceR=null;
                this.priceC.add('B');
                this.priceC.add('B');
                this.priceC.add('Y');
                this.cardLevel=1;
                this.pv=5;
                this.skill=10;
                break;
            case 11:
                this.priceR=null;
                this.priceC.add('P');
                this.priceC.add('P');
                this.priceC.add('G');
                this.cardLevel=1;
                this.pv=5;
                this.skill=11;
                break;
            case 12:
                this.priceR=null;
                this.priceC.add('Y');
                this.cardLevel=2;
                this.pv=4;
                this.skill=12;
                break;
            case 13:
                this.priceR=null;
                this.priceC.add('B');
                this.cardLevel=2;
                this.pv=4;
                this.skill=13;
                break;
            case 14:
                this.priceR=null;
                this.priceC.add('P');
                this.cardLevel=2;
                this.pv=4;
                this.skill=14;
                break;
            case 15:
                this.priceR=null;
                this.priceC.add('G');
                this.cardLevel=2;
                this.pv=4;
                this.skill=15;
                break;
        }
        return this;
    }
    public void Skill(int number)
    {
        DevelopeCard card=new DevelopeCard();
        switch (number)
        {
            case 0:
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
    }
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
