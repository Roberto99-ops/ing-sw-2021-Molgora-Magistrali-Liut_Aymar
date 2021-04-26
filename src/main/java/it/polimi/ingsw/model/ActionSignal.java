package it.polimi.ingsw.model;


public class ActionSignal {
    private int number;

    public int getNumber() {
        return number;
    }

    /**
     * Applies actions described by ActionSignals
     * Cases 1 - 4: modify DevelopeDecks by removing 2 cards
     * Cases 5 - 6: Lorenzo moves forward by two spaces
     * Case 7: Lorenzo moves forward by one space
     * @param number: according to the number received, it calls a specific action
     */
    public static void Action(int number){
        Lorenzo Lore = new Lorenzo();
        int i;
        int countRemove=0;

        switch (number){
            case 1://blu-2
                //prendo il mazzo, elimino la prima carta in cima e poi controllo se l'array è vuoto
                //se non lo è, elimino l'altra carta. Se è vuoto invece, prendo un nuovo mazzo ed
                //elimino la carta in cima

                i=2; //liv 1 blu
                while (countRemove<2){
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<=11)){
                        i+=4; //mi sposto da un mazzo all'altro finchè non ne trovo uno cpn carte
                    }
                    //caso: non ho più carte in nessun mazzo
                    if (i==10 && (Game.getDevelopedecks(i).getStructure().isEmpty())){ //caso in cui arrivo all'ultimo mazzo
                        System.out.println("No more blue DevelopeCards available.");
                        return;
                    }
                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()){ //se il mazzo non è vuoto, elimino una carta
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;

            case 2://verde-2
                i=3;
                while (countRemove<2){
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<=11)){
                        i+=4; //mi sposto da un mazzo all'altro finchè non ne trovo uno cpn carte
                    }
                    //caso: non ho più carte in nessun mazzo
                    if (i==11 && (Game.getDevelopedecks(i).getStructure().isEmpty())){ //caso in cui arrivo all'ultimo mazzo
                        System.out.println("No more green DevelopeCards available.");
                        return;
                    }
                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()){ //se il mazzo non è vuoto, elimino una carta
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;

            case 3://viola-2
                i=0; //liv 1 blu
                while (countRemove<2){
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<=11)){
                        i+=4; //mi sposto da un mazzo all'altro finchè non ne trovo uno cpn carte
                    }
                    //caso: non ho più carte in nessun mazzo
                    if (i==8 && (Game.getDevelopedecks(i).getStructure().isEmpty())){ //caso in cui arrivo all'ultimo mazzo
                        System.out.println("No more violet DevelopeCards available.");
                        return;
                    }
                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()){ //se il mazzo non è vuoto, elimino una carta
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;

            case 4://giallo-2
                i=1; //liv 1 blu
                while (countRemove<2){
                    while (((Game.getDevelopedecks(i).getStructure().isEmpty()) && i<=11)){
                        i+=4; //mi sposto da un mazzo all'altro finchè non ne trovo uno cpn carte
                    }
                    //caso: non ho più carte in nessun mazzo
                    if (i==9 && (Game.getDevelopedecks(i).getStructure().isEmpty())){ //caso in cui arrivo all'ultimo mazzo
                        System.out.println("No more yellow DevelopeCards available.");
                        return;
                    }
                    if (!Game.getDevelopedecks(i).getStructure().isEmpty()){ //se il mazzo non è vuoto, elimino una carta
                        Game.getDevelopedecks(i).getStructure().remove(0);
                    }
                    countRemove++;
                }
                return;

            case 5://+2
                Lore.Lorenzomoves(2);

            case 6://+2
                Lore.Lorenzomoves(2);

            case 7://+1&Shuffle
                Lore.Lorenzomoves(1);
            //in Java, il break nello switch è opzionale
        }
    }

}
