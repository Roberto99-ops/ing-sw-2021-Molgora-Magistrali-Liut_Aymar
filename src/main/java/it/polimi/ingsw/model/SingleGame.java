package it.polimi.ingsw.model;

public class SingleGame extends Game {
    @Override
    public void Shuffle()
    {

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