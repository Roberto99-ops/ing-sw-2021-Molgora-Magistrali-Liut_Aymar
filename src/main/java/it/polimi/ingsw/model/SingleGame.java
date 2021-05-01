package it.polimi.ingsw.model;

public class SingleGame extends Game {
    private Lorenzo Lore = new Lorenzo();

    public Lorenzo getLorenzo(){return Lore;}

    public static void  main (){
        ActionStructure actionStructure = new ActionStructure();

        //mischio i segnalini appena comincia la partita
        actionStructure.ShuffleSignal();
    }


    @Override
    public boolean Endgame(Player actualplayer)
    {
        return true;
    }

    @Override
    public String Victory()
    {
        return "prova";
    }
}
