package it.polimi.ingsw;

public class ActionSignal {
    private int number;

    public void Action(int number){
        Lorenzo Lore = new Lorenzo();
        //enumero i segnalini da 1 a 7
        switch (number){
            case 1://blu-2

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
