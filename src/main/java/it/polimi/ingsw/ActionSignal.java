package it.polimi.ingsw;

public class ActionSignal {
    private int number;

    public void Action(int number){
        //enumero i segnaili da 0 a 6
        switch (number){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            default: System.out.println("Errore. Numero non valido!"); //questo messaggio di errore è solo un esempio
            //in Java, il break nello switch è opzionale
        }
    }

}
