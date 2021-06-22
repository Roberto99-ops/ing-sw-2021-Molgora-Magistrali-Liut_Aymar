package it.polimi.ingsw.model;


public class ActionSignal {
    private int number;


    /**
     getter and setter
     */

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * Applies actions described by ActionSignals
     * Cases 1 - 4: modify DevelopeDecks by removing 2 cards
     * Cases 5 - 6: Lorenzo moves forward by two spaces
     * Case 7: Lorenzo moves forward by one space
     * @param number: according to the number received, it calls a specific action
     */

    public void action(int number){
        int i;
        int countRemove=0;


        switch (number){


            case 1: // blu-2

                i=2; // liv 1 blu
                while (countRemove<2){
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<10)) {
                        i+=4;
                    }

                    if (i==10 && (Game.getDevelopedecks(i).getStructure().isEmpty())){
                        System.out.println("No more blue DevelopeCards available.");
                        return;
                    }
                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()){
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;



            case 2: // green-2
                i=3;

                while (countRemove<2) {
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<11)){
                        i+=4;
                    }

                    if (i==11 && (Game.getDevelopedecks(i).getStructure().isEmpty())) {
                        System.out.println("No more green DevelopeCards available.");
                        return;
                    }

                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()) {
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }

                    countRemove++;
                }
                return;



            case 3: // purple-2
                i=0;
                while (countRemove<2) {
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<8)){
                        i+=4;
                    }

                    if (i==8 && (Game.getDevelopedecks(i).getStructure().isEmpty())) {
                        System.out.println("No more violet DevelopeCards available.");
                        return;
                    }

                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()) {
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;


            case 4: // yellow-2
                i=1;
                while (countRemove<2) {
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<9)) {
                        i+=4;
                    }

                    if (i==9 && (Game.getDevelopedecks(i).getStructure().isEmpty())){
                        System.out.println("No more yellow DevelopeCards available.");
                        return;
                    }

                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()) {
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }

                    countRemove++;
                }
                return;


            case 5://+2
                SingleGame.getLorenzo().forwardTwo();
                break;


            case 6://+2
                SingleGame.getLorenzo().forwardTwo();
                break;


            case 7://+1&Shuffle
                SingleGame.getLorenzo().forwardOne();
                SingleGame.getActionStructure().shuffleSignal();
                break;
        }
    }

}
