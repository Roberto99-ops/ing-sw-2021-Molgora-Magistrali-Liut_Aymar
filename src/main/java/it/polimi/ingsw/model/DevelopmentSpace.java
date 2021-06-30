package it.polimi.ingsw.model;

import java.io.Serializable;


/**
 * getter and setter
 */

public class DevelopmentSpace implements Serializable {

    private DevelopDecks miniDeck1 = new DevelopDecks();
    private DevelopDecks miniDeck2 = new DevelopDecks();
    private DevelopDecks miniDeck3 = new DevelopDecks();


    /**
     * getter and setter
     */

    public void setMiniDeck1(DevelopDecks miniDeck1) {
        this.miniDeck1 = miniDeck1;
    }
    public void setMiniDeck2(DevelopDecks miniDeck2) {
        this.miniDeck2 = miniDeck2;
    }
    public void setMiniDeck3(DevelopDecks miniDeck3) {
        this.miniDeck3 = miniDeck3;
    }
    public DevelopDecks getMiniDeck1() {
        return miniDeck1;
    }
    public DevelopDecks getMiniDeck2() {
        return miniDeck2;
    }
    public DevelopDecks getMiniDeck3() {
        return miniDeck3;
    }


    /**
     * Gets the first card on top of a specific miniDeck
     * @param choice : which miniDeck do you want to consider
     * @return  card on top of the selected miniDeck
     * @throws Exception in case of the choice made doesn't exists
     */

    public DevelopCard getCard(int choice) throws Exception {
        int i;
        try {
            if (choice == 1) {
                i = miniDeck1.getStructure().size();
                return miniDeck1.getStructure().get(i - 1);
            } else if (choice == 2) {
                i = miniDeck2.getStructure().size();
                return miniDeck2.getStructure().get(i - 1);
            } else if (choice == 3) {
                i = miniDeck3.getStructure().size();
                return miniDeck3.getStructure().get(i - 1);
            }
        } catch (Exception e) {System.out.println("Choose one of the three spaces");}
         return null;
    }




    /**
     * Puts a card into a specific miniDeck if possible (every time it is called, this method checks if the selected Deck
     * is empty or if the card's level on top is compatible with the card I'm adding, according to the game's rules)
     * @param card: card to put
     * @param choice: miniDeck where to put
     * @return boolean that works as a flag that tells me if the process has finished successfully or not
     */

    public boolean setCard(DevelopCard card, int choice) {

        int i;

        //1: I put the card on the first DevelopDeck starting from the left, on the PlayerBoard
        if (choice == 1) {
            i = miniDeck1.getStructure().size();
            //according to the rules, a card can be added if: there's an empty space on the PlayerBoard or the difference
            //between levels of the card on top of the miniDeck and the card I'm adding is equal to 1
            if ((i == 0 && card.getLevel() == 1) || (i!=0 && miniDeck1.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck1.getStructure().add(card);
                return true;
            }

            i = miniDeck2.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck2.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck2.getStructure().add(card);
                return true;
            }


            i = miniDeck3.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck3.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck3.getStructure().add(card);
                return true;
            }

        }


        //2: I put the card on deck at the centre
        if (choice == 2) {
            i = miniDeck2.getStructure().size();
            if ((i == 0 && card.getLevel() == 1) || (i!=0 && miniDeck2.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck2.getStructure().add(card);
                return true;
            }

            i = miniDeck1.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck1.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck1.getStructure().add(card);
                return true;
            }


            i = miniDeck3.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck3.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck3.getStructure().add(card);
                return true;
            }
        }



        //3: I put the the card on the third and last deck, the one on the right side of the PlayerBoard
        if (choice == 3) {
            i = miniDeck3.getStructure().size();
            if ((i == 0 && card.getLevel() == 1) || (i!=0 && miniDeck3.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck3.getStructure().add(card);
                return true;
            }

            i = miniDeck1.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck1.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck1.getStructure().add(card);
                return true;
            }


            i = miniDeck2.getStructure().size();

            if ((i == 0 && card.getLevel() == 1) || (i !=0 && miniDeck2.getStructure().get(i - 1).getLevel() == card.getLevel() - 1)) {
                miniDeck2.getStructure().add(card);
                return true;
            }
        }

        return false;
    }






}
