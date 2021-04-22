package it.polimi.ingsw.model;

import java.util.ArrayList;

public class ActionSignal {
    private int number;

    /**
     * Applies actions described by ActionSignals
     * @param number
     */
    public static void Action(int number){
        DevelopeDecks DDeck = new DevelopeDecks();
        ArrayList<DevelopeCard> Deck = DDeck.getStructure();
        Lorenzo Lore = new Lorenzo();

        if((Deck.isEmpty())==true){
            //passo al mazzo successivo
            //altrimenti continuo
        };
        //enumero i segnalini da 1 a 7
        switch (number){
            case 1://blu-2
                //prendo il mazzo, elimino la prima carta in cima e poi controllo se l'array è vuoto
                //se non lo è, elimino l'altra carta. Se è vuoto invece, prendo un nuovo mazzo ed
                //elimino la carta in cima


            case 2://verde-2

            case 3://viola-2

            case 4://giallo-2

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
