package it.polimi.ingsw;

public class DevelopementSpace {

    private DevelopeDecks structure1;
    private DevelopeDecks structure2;
    private DevelopeDecks structure3;


<<<<<<< HEAD
<<<<<<< HEAD
    public DevelopeCard getCard(int choice) {
        int i = 0;
        if (choice == 1) {
            i = minideck1.getStructure().size();
            return minideck1.getStructure().get(i - 1);
        }

        else if (choice == 2) {
            i = minideck2.getStructure().size();
            return minideck2.getStructure().get(i - 1);
        }


        else if (choice == 3) {
            i = minideck3.getStructure().size();
            return minideck3.getStructure().get(i - 1);
        }
        // manca:
        // return ;
    }


    public void setCard(DevelopeCard structure, int choice) {
        int i;

        if (choice == 1) {
            i = minideck1.getStructure().size();
            if ((minideck1.getStructure().get(i - 1).getColour() == structure.getColour() && minideck1.getStructure().get(1).getLevel() == structure.getLevel() - 1)
                    || i == 0)
                minideck1.getStructure().add(structure);
        }


        if (choice == 2) {
            i = minideck2.getStructure().size();
            if ((minideck2.getStructure().get(i - 1).getColour() == structure.getColour() && minideck2.getStructure().get(1).getLevel() == structure.getLevel() - 1)
                    || i == 0)
                minideck2.getStructure().add(structure);
        }


        if (choice == 3) {
            i = minideck3.getStructure().size();
            if ((minideck3.getStructure().get(i - 1).getColour() == structure.getColour() && minideck3.getStructure().get(1).getLevel() == structure.getLevel() - 1)
                    || i == 0)
                minideck3.getStructure().add(structure);
        }


    }


    public boolean checkDeck(DevelopeDecks minideck) {
        if ((minideck.getStructure().size() == 3) && ((minideck.getStructure().get(2).getColour()) == (minideck.getStructure().get(1).getColour())) &&
                (minideck.getStructure().get(1).getColour()) == (minideck.getStructure().get(0).getColour()) &&
                (minideck.getStructure().get(0).getLevel() == 1) && (minideck.getStructure().get(1).getLevel() == 2) && (minideck.getStructure().get(2).getLevel() == 2) ||
                (minideck.getStructure().size() == 2) &&
                        (minideck.getStructure().get(1).getColour()) == (minideck.getStructure().get(0).getColour()) &&
                        (minideck.getStructure().get(0).getLevel() == 1) && (minideck.getStructure().get(1).getLevel() == 2)||
        ((minideck.getStructure().size() == 1) && minideck.getStructure().get(0).getLevel() == 1)){
            return true;
        } else {
            return false;
        }

    }
=======
    public DevelopeDecks getCard() {
        return structure;
    }

    public void setCard(DevelopeCard structure) {
        this.structure = structure;

    }
>>>>>>> parent of fb965f5 ( news by Fra :))
=======
    public DevelopeDecks getCard() {
        return structure;
    }

    public void setCard(DevelopeCard structure) {
        this.structure = structure;

    }
>>>>>>> parent of fb965f5 ( news by Fra :))
}


