package it.polimi.ingsw;
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
    public DevelopeCard getCard(int number)
    {
        inputproduction = new ResourceStructure();
        outputproduction = new ResourceStructure();
        cost = new ResourceStructure();
        switch (number)
        {
            case 0:
                this.level=1;
                this.colour='G';
                this.pv=1;
                this.cost.AddResource(2, 'B');
                this.inputproduction.AddResource(1,'Y');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 1:
                this.level=1;
                this.colour='P';
                this.pv=1;
                this.cost.AddResource(2, 'P');
                this.inputproduction.AddResource(1,'W');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 2:
                this.level=1;
                this.colour='B';
                this.pv=1;
                this.cost.AddResource(2, 'Y');
                this.inputproduction.AddResource(1,'B');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 3:
                this.level=1;
                this.colour='Y';
                this.pv=1;
                this.cost.AddResource(2, 'W');
                this.inputproduction.AddResource(1,'P');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 4:
                this.level=1;
                this.colour='G';
                this.pv=2;
                this.cost.AddResource(1, 'B');
                this.cost.AddResource(1, 'W');
                this.cost.AddResource(1, 'P');
                this.inputproduction.AddResource(1,'W');
                this.outputproduction.AddResource(1, 'P');
                break;
            case 5:
                this.level=1;
                this.colour='P';
                this.pv=2;
                this.cost.AddResource(1, 'B');
                this.cost.AddResource(1, 'Y');
                this.cost.AddResource(1, 'P');
                this.inputproduction.AddResource(1,'Y');
                this.outputproduction.AddResource(1, 'B');
                break;
            case 6:
                this.level=1;
                this.colour='B';
                this.pv=2;
                this.cost.AddResource(1, 'Y');
                this.cost.AddResource(1, 'W');
                this.cost.AddResource(1, 'P');
                this.inputproduction.AddResource(1,'P');
                this.outputproduction.AddResource(1, 'W');
                break;
            case 7:
                this.level=1;
                this.colour='Y';
                this.pv=2;
                this.cost.AddResource(1, 'B');
                this.cost.AddResource(1, 'W');
                this.cost.AddResource(1, 'Y');
                this.inputproduction.AddResource(1,'B');
                this.outputproduction.AddResource(1, 'Y');
                break;
            case 8:
                this.level=1;
                this.colour='G';
                this.pv=3;
                this.cost.AddResource(3, 'B');
                this.inputproduction.AddResource(2,'P');
                this.outputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(1, 'W');
                break;
            case 9:
                this.level=1;
                this.colour='P';
                this.pv=3;
                this.cost.AddResource(3, 'P');
                this.inputproduction.AddResource(2,'Y');
                this.outputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(1, 'W');
                break;
            case 10:
                this.level=1;
                this.colour='B';
                this.pv=3;
                this.cost.AddResource(3, 'Y');
                this.inputproduction.AddResource(2,'W');
                this.outputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(1, 'P');
                break;
            case 11:
                this.level=1;
                this.colour='Y';
                this.pv=3;
                this.cost.AddResource(3, 'W');
                this.inputproduction.AddResource(2,'B');
                this.outputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(1, 'W');
                break;
            case 12:
                this.level=1;
                this.colour='G';
                this.pv=4;
                this.cost.AddResource(2, 'B');
                this.cost.AddResource(2, 'Y');
                this.inputproduction.AddResource(1,'P');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 13:
                this.level=1;
                this.colour='P';
                this.pv=4;
                this.cost.AddResource(2, 'P');
                this.cost.AddResource(2, 'W');
                this.inputproduction.AddResource(1,'Y');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 14:
                this.level=1;
                this.colour='B';
                this.pv=4;
                this.cost.AddResource(2, 'P');
                this.cost.AddResource(2, 'Y');
                this.inputproduction.AddResource(1,'B');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 15:
                this.level=1;
                this.colour='Y';
                this.pv=4;
                this.cost.AddResource(2, 'B');
                this.cost.AddResource(2, 'W');
                this.inputproduction.AddResource(1,'Y');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 16:
                this.level=2;
                this.colour='G';
                this.pv=5;
                this.cost.AddResource(4, 'B');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 17:
                this.level=2;
                this.colour='P';
                this.pv=5;
                this.cost.AddResource(4, 'P');
                this.inputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 18:
                this.level=2;
                this.colour='B';
                this.pv=5;
                this.cost.AddResource(4, 'Y');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 19:
                this.level=2;
                this.colour='Y';
                this.pv=5;
                this.cost.AddResource(4, 'W');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 20:
                this.level=2;
                this.colour='G';
                this.pv=6;
                this.cost.AddResource(3, 'B');
                this.cost.AddResource(2, 'P');
                this.inputproduction.AddResource(1, 'B');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(3, 'W');
                break;
            case 21:
                this.level=2;
                this.colour='P';
                this.pv=6;
                this.cost.AddResource(3, 'P');
                this.cost.AddResource(2, 'Y');
                this.inputproduction.AddResource(1, 'Y');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(3, 'B');
                break;
            case 22:
                this.level=2;
                this.colour='B';
                this.pv=6;
                this.cost.AddResource(3, 'Y');
                this.cost.AddResource(2, 'W');
                this.inputproduction.AddResource(1, 'Y');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(3, 'P');
                break;
            case 23:
                this.level=2;
                this.colour='Y';
                this.pv=6;
                this.cost.AddResource(3, 'W');
                this.cost.AddResource(2, 'B');
                this.inputproduction.AddResource(1, 'W');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(3, 'Y');
                break;
            case 24:
                this.level=2;
                this.colour='G';
                this.pv=7;
                this.cost.AddResource(5, 'B');
                this.inputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 25:
                this.level=2;
                this.colour='P';
                this.pv=7;
                this.cost.AddResource(5, 'P');
                this.inputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 26:
                this.level=2;
                this.colour='B';
                this.pv=7;
                this.cost.AddResource(5, 'Y');
                this.inputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 27:
                this.level=2;
                this.colour='Y';
                this.pv=7;
                this.cost.AddResource(5, 'W');
                this.inputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 28:
                this.level=2;
                this.colour='G';
                this.pv=8;
                this.cost.AddResource(3, 'B');
                this.cost.AddResource(3, 'Y');
                this.inputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 29:
                this.level=2;
                this.colour='P';
                this.pv=8;
                this.cost.AddResource(3, 'P');
                this.cost.AddResource(3, 'B');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 30:
                this.level=2;
                this.colour='B';
                this.pv=8;
                this.cost.AddResource(3, 'W');
                this.cost.AddResource(3, 'Y');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 31:
                this.level=2;
                this.colour='Y';
                this.pv=8;
                this.cost.AddResource(3, 'W');
                this.cost.AddResource(3, 'P');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 32:
                this.level=3;
                this.colour='G';
                this.pv=9;
                this.cost.AddResource(6, 'B');
                this.inputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(3, 'W');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 33:
                this.level=3;
                this.colour='P';
                this.pv=9;
                this.cost.AddResource(6, 'P');
                this.inputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(3, 'Y');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 34:
                this.level=3;
                this.colour='B';
                this.pv=9;
                this.cost.AddResource(6, 'Y');
                this.inputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(3, 'B');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 35:
                this.level=3;
                this.colour='Y';
                this.pv=9;
                this.cost.AddResource(6, 'W');
                this.inputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(3, 'P');
                this.outputproduction.AddResource(2, 'R');
                break;
            case 36:
                this.level=3;
                this.colour='G';
                this.pv=10;
                this.cost.AddResource(5, 'B');
                this.cost.AddResource(2, 'P');
                this.inputproduction.AddResource(1, 'Y');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 37:
                this.level=3;
                this.colour='P';
                this.pv=10;
                this.cost.AddResource(5, 'P');
                this.cost.AddResource(2, 'Y');
                this.inputproduction.AddResource(1, 'W');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 38:
                this.level=3;
                this.colour='B';
                this.pv=10;
                this.cost.AddResource(5, 'Y');
                this.cost.AddResource(2, 'W');
                this.inputproduction.AddResource(1, 'Y');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(2, 'P');
                this.outputproduction.AddResource(2, 'W');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 39:
                this.level=3;
                this.colour='Y';
                this.pv=10;
                this.cost.AddResource(5, 'W');
                this.cost.AddResource(2, 'P');
                this.inputproduction.AddResource(1, 'W');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(2, 'B');
                this.outputproduction.AddResource(2, 'Y');
                this.outputproduction.AddResource(1, 'R');
                break;
            case 40:
                this.level=3;
                this.colour='G';
                this.pv=11;
                this.cost.AddResource(7, 'B');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(3, 'R');
                break;
            case 41:
                this.level=3;
                this.colour='P';
                this.pv=11;
                this.cost.AddResource(7, 'P');
                this.inputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(3, 'R');
                break;
            case 42:
                this.level=3;
                this.colour='B';
                this.pv=11;
                this.cost.AddResource(7, 'Y');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(3, 'R');
                break;
            case 43:
                this.level=3;
                this.colour='Y';
                this.pv=11;
                this.cost.AddResource(7, 'W');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(3, 'R');
                break;
            case 44:
                this.level=3;
                this.colour='G';
                this.pv=12;
                this.cost.AddResource(4, 'B');
                this.cost.AddResource(4, 'Y');
                this.inputproduction.AddResource(1, 'W');
                this.outputproduction.AddResource(3, 'Y');
                this.outputproduction.AddResource(1, 'B');
                break;
            case 45:
                this.level=3;
                this.colour='P';
                this.pv=12;
                this.cost.AddResource(4, 'P');
                this.cost.AddResource(4, 'B');
                this.inputproduction.AddResource(1, 'Y');
                this.outputproduction.AddResource(3, 'W');
                this.outputproduction.AddResource(1, 'P');
                break;
            case 46:
                this.level=3;
                this.colour='B';
                this.pv=12;
                this.cost.AddResource(4, 'Y');
                this.cost.AddResource(4, 'W');
                this.inputproduction.AddResource(1, 'P');
                this.outputproduction.AddResource(3, 'B');
                this.outputproduction.AddResource(1, 'Y');
                break;
            case 47:
                this.level=3;
                this.colour='Y';
                this.pv=12;
                this.cost.AddResource(4, 'P');
                this.cost.AddResource(4, 'W');
                this.inputproduction.AddResource(1, 'B');
                this.outputproduction.AddResource(3, 'P');
                this.outputproduction.AddResource(1, 'W');
                break;
        }
        return this;
    }

    //serve solo per testare la classe
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
