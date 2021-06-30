package it.polimi.ingsw.model;


public class ActionSignal {
    private int number;


    /**
     Getter and setter
     */

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * Applies actions described by ActionSignals
     * Cases 1 - 4: modifies DevelopDecks, removing 2 cards
     * Cases 5 - 6: Lorenzo moves forward by two spaces
     * Case 7: Lorenzo moves forward by one space
     * @param number: according to the number received, it calls a specific action
     */

    public void action(int number){
        int i;
        int countRemove=0;


        switch (number){

            //1: 2 blue DevelopCards are deleted
            case 1:

                i=2;
                while (countRemove<2){
                    while (((Game.getDevelopDecks(i).getStructure().isEmpty()) && i<10)) {
                        i+=4;
                    }

                    if (i==10 && (Game.getDevelopDecks(i).getStructure().isEmpty())){
                        System.out.println("No more blue DevelopCards available.");
                        return;
                    }
                    if (!Game.getDevelopDecks(i).getStructure().isEmpty()){
                        Game.getDevelopDecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;


            //2: 2 green DevelopCards are deleted
            case 2:
                i=3;

                while (countRemove<2) {
                    while (((Game.getDevelopDecks(i).getStructure().isEmpty()) && i<11)){
                        i+=4;
                    }

                    if (i==11 && (Game.getDevelopDecks(i).getStructure().isEmpty())) {
                        System.out.println("No more green DevelopCards available.");
                        return;
                    }

                    if (!Game.getDevelopDecks(i).getStructure().isEmpty()) {
                        Game.getDevelopDecks(i).getStructure().remove(0);
                    }

                    countRemove++;
                }
                return;


            //3: 2 purple DevelopCards are deleted
            case 3:
                i=0;
                while (countRemove<2) {
                    while (((Game.getDevelopDecks(i).getStructure().isEmpty()) && i<8)){
                        i+=4;
                    }

                    if (i==8 && (Game.getDevelopDecks(i).getStructure().isEmpty())) {
                        System.out.println("No more violet DevelopCards available.");
                        return;
                    }

                    if (!Game.getDevelopDecks(i).getStructure().isEmpty()) {
                        Game.getDevelopDecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;

            //4: 2 yellow DevelopCards are deleted
            case 4: // yellow-2
                i=1;
                while (countRemove<2) {
                    while (((Game.getDevelopDecks(i).getStructure().isEmpty()) && i<9)) {
                        i+=4;
                    }

                    if (i==9 && (Game.getDevelopDecks(i).getStructure().isEmpty())){
                        System.out.println("No more yellow DevelopCards available.");
                        return;
                    }

                    if (!Game.getDevelopDecks(i).getStructure().isEmpty()) {
                        Game.getDevelopDecks(i).getStructure().remove(0);
                    }

                    countRemove++;
                }
                return;

            case 5:
                SingleGame.getLorenzo().forwardTwo();
                break;


            case 6:
                SingleGame.getLorenzo().forwardTwo();
                break;


            case 7:
                SingleGame.getLorenzo().forwardOne();
                SingleGame.getActionStructure().shuffleSignal();
                break;
        }
    }

}
