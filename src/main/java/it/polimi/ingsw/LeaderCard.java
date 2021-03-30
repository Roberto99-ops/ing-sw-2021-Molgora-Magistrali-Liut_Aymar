package it.polimi.ingsw;
public class LeaderCard extends Card{
    private ResourceStructure priceR;
    private DevelopeCard priceC[];
    private int pv;
    private int skill;

    //@Override
    public LeaderCard getCard(int number)
    {
        //ArrayList<Resource> array = new ArrayList<Resource>();
        //Resource r = new Resource();
        //LeaderCard card = new LeaderCard();
        this.priceR = new ResourceStructure();
        this.priceC = new DevelopeCard[2];

        switch(number) {
            case 0:
                //r.setColour('Y');
                //this.priceR.getVector().add('Y');
                //this.priceR.AddResource('Y');
                //r.setColour('G');
                //this.priceR.getVector().add('G');
                //this.priceR.AddResource('G');
                this.priceR=null;
                //this.priceC[0];
                this.pv=2;
                this.skill=0;
                break;
            case 1:
                this.priceR=null;
                this.priceC=null;
                this.pv=2;
                this.skill=1;
                break;
            case 2:
                this.priceR=null;
                this.priceC=null;
                this.pv=2;
                this.skill=2;
                break;
            case 3:
                this.priceR=null;
                this.priceC=null;
                this.pv=3;
                this.skill=3;
                break;
            case 4:
                this.priceR.AddResource(5, 'Y');
                this.priceC=null;
                this.pv=3;
                this.skill=4;
                break;
            case 5:
                this.priceR.AddResource(5, 'W');
                this.priceC=null;
                this.pv=3;
                this.skill=5;
                break;
            case 6:
                this.priceR.AddResource(5, 'P');
                this.priceC=null;
                this.pv=3;
                this.skill=6;
                break;
            case 7:
                this.priceR.AddResource(5, 'B');
                this.priceC=null;
                this.pv=3;
                this.skill=7;
                break;
            case 8:
                this.priceR=null;
                this.priceC=null;
                this.pv=3;
                this.skill=8;
                break;
        }
        return this;
    }
    public void Skill(int number)
    {

    }
    public void Print()
    {
        System.out.println("pv: " + this.pv);
        if (this.priceC != null)  System.out.println("priceC: " + this.priceC);
        else System.out.println("priceC: null");
        if (this.priceR != null)  System.out.println("priceR: " + this.priceR.getVector());
        else System.out.println("priceR: null");
        System.out.println("skill: " + this.skill + "\n");
    }
}
