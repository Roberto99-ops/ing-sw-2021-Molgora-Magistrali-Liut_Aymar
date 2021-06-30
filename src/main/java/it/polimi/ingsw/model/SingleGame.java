package it.polimi.ingsw.model;

import java.io.FileNotFoundException;

public class SingleGame extends Game {

    //The players are 2: the Player and Lorenzo
    private final static int n_players = 2;
    private static int VR_SG=0;
    private static ActionStructure actionStructure = new ActionStructure();
    private static Lorenzo lorenzo = new Lorenzo();

    public SingleGame()
    {
        actionStructure.shuffleSignal();
    }

    public static Lorenzo getLorenzo(){
        return lorenzo;
    }

    public static ActionStructure getActionStructure(){return actionStructure;}

    /**
     * Overrides method 'Endgame' in "Game" class. It checks if the
     * requirements to call the end of the game are satisfied and sets
     * the winner's name.
     * @param actualPlayer: the player playing in that turn (the player or Lorenzo)
     * @return true if the game ends. False if not.
     */

    @Override
    public boolean callEndgame(Player actualPlayer)
    {
        //the player wins if reaches position #24 before Lorenzo
        if(actualPlayer.getTrackPosition()>=24) {
            getWinner().setName(actualPlayer.getName());
            return true; //vinci tu
        }

        //Lorenzo wins if reaches position #24
        if(this.getLorenzo().getNumber() >= 24)
        {
            getWinner().setName("Lorenzo il Magnifico");
            return true;
        }

        //the player wins if he owns 7 DevelopCards
        if(actualPlayer.getDevelopmentQuantity()>=7) {
            getWinner().setName(actualPlayer.getName());
            return true;
        }

        //if DevelopDecks of a specific color are not available, Lorenzo wins
        if ((Game.getDevelopDecks(0).getStructure().isEmpty() && Game.getDevelopDecks(4).getStructure().isEmpty() && Game.getDevelopDecks(8).getStructure().isEmpty())||
                (Game.getDevelopDecks(1).getStructure().isEmpty() && Game.getDevelopDecks(5).getStructure().isEmpty() && Game.getDevelopDecks(9).getStructure().isEmpty())||
                    (Game.getDevelopDecks(2).getStructure().isEmpty() && Game.getDevelopDecks(6).getStructure().isEmpty() && Game.getDevelopDecks(10).getStructure().isEmpty())||
                        (Game.getDevelopDecks(3).getStructure().isEmpty() && Game.getDevelopDecks(7).getStructure().isEmpty() && Game.getDevelopDecks(11).getStructure().isEmpty())){
            getWinner().setName("Lorenzo il Magnifico");
            return true;
        }
        return false;

    }

    /**
     * Overrides method 'Victory' in Game class. It gets the winner's name
     * @return the winner's name
     */
    @Override
    public String callVictory()
    {
        return getWinner().getName();
    }


    /**
     * Overrides method 'shuffle' in Game. It shuffles ActionStructure
     */
    @Override
    public void shuffle() throws FileNotFoundException {
        actionStructure.shuffleSignal();
        super.shuffle();
    }
}
