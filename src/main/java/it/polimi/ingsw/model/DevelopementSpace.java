package it.polimi.ingsw.model;

import java.io.Serializable;

public class DevelopementSpace implements Serializable {

    private DevelopeDecks minideck1 = new DevelopeDecks();
    private DevelopeDecks minideck2 = new DevelopeDecks();
    private DevelopeDecks minideck3 = new DevelopeDecks();
    public void setMinideck1(DevelopeDecks minideck1) {
        this.minideck1 = minideck1;
    }

    public void setMinideck2(DevelopeDecks minideck2) {
        this.minideck2 = minideck2;
    }

    public void setMinideck3(DevelopeDecks minideck3) {
        this.minideck3 = minideck3;
    }

    public DevelopeDecks getMinideck1() {
        return minideck1;
    }

    public DevelopeDecks getMinideck2() {
        return minideck2;
    }

    public DevelopeDecks getMinideck3() {
        return minideck3;
    }

    /**
     * Gets the first card on top of a specific minideck
     * @param choice : which minideck do you want to consider
     * @return  card on top of the selected minideck
     * @throws Exception in case the choice made doesn't exists
     */
    public DevelopeCard getCard(int choice) throws Exception{
        int i = 0;
        try {
            if (choice == 1) {
                i = minideck1.getStructure().size();
                return minideck1.getStructure().get(i - 1);
            } else if (choice == 2) {
                i = minideck2.getStructure().size();
                return minideck2.getStructure().get(i - 1);
            } else if (choice == 3) {
                i = minideck3.getStructure().size();
                return minideck3.getStructure().get(i - 1);
            }
        }catch(Exception e){System.out.println("Choose one of the three spaces");}
         return null;
    }


    /**
     * put a card into a specific minideck if possible
     * @param card: card to put
     * @param choice: minidick where to put
     * @return
     */
    public boolean setCard(DevelopeCard card, int choice) {
        int i;

        if (choice == 1) {
            i = minideck1.getStructure().size();
            if (i == 0 || (minideck1.getStructure().get(i - 1).getColour() == card.getColour() && minideck1.getStructure().get(i - 1).getLevel() == card.getLevel() - 1))
                minideck1.getStructure().add(card);
            return true;
        }


        if (choice == 2) {
            i = minideck2.getStructure().size();
            if (i == 0 || (minideck2.getStructure().get(i - 1).getColour() == card.getColour() && minideck2.getStructure().get(i - 1).getLevel() == card.getLevel() - 1))
                minideck2.getStructure().add(card);
            return true;
        }


        if (choice == 3) {
            i = minideck3.getStructure().size();
            if (i == 0 || (minideck3.getStructure().get(i - 1).getColour() == card.getColour() && minideck3.getStructure().get(i - 1).getLevel() == card.getLevel() - 1))
                minideck3.getStructure().add(card);
            return true;
        }
        return false;
    }

    /**
     * tell if there is already a card with that color in the DSpace
     * @param color: color to check
     * @return: minideck that contains the card. returns 0 if we can put it into an empty minideck. returns 4 if we have a fourth color
     */
    public int colorPresent(Character color)
    {
        if(minideck1.getStructure().size() != 0 && color.equals(minideck1.getStructure().get(0).getColour())) return 1;

        if(minideck2.getStructure().size() != 0 && color.equals(minideck2.getStructure().get(0).getColour())) return 2;

        if(minideck3.getStructure().size() != 0 && color.equals(minideck3.getStructure().get(0).getColour())) return 3;

        if(minideck1.getStructure().size() != 0 && minideck2.getStructure().size() != 0 && minideck3.getStructure().size() != 0) return 4;

        return 0;
    }

    public boolean checkDeck(DevelopeDecks minideck) {
        if ((minideck.getStructure().size() == 3) && ((minideck.getStructure().get(2).getColour()) == (minideck.getStructure().get(1).getColour())) &&
                (minideck.getStructure().get(1).getColour()) == (minideck.getStructure().get(0).getColour()) &&
                (minideck.getStructure().get(0).getLevel() == 1) && (minideck.getStructure().get(1).getLevel() == 2) && (minideck.getStructure().get(2).getLevel() == 3) ||
                (minideck.getStructure().size() == 2) &&
                        (minideck.getStructure().get(1).getColour()) == (minideck.getStructure().get(0).getColour()) &&
                        (minideck.getStructure().get(0).getLevel() == 1) && (minideck.getStructure().get(1).getLevel() == 2)||
                ((minideck.getStructure().size() == 1) && minideck.getStructure().get(0).getLevel() == 1)){
            return true;
        } else {
            return false;
        }

    }
}
