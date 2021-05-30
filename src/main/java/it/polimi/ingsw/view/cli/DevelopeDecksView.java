package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;
import it.polimi.ingsw.model.Game;

/**
 * draw the decks of developecards of the game
 */
public class DevelopeDecksView extends PaintCards {
    private final int VERT_SIZE = 40;
    private final int HORIZ_SIZE = 66;
    private String [][] space;

    public DevelopeDecksView(DevelopeDecks[] decks)
    {
        space = new String[VERT_SIZE][HORIZ_SIZE];
        for (int i = 0; i < VERT_SIZE; i++)
            for (int j = 0; j < HORIZ_SIZE; j++)
                space[i][j] = " ";

        drawCards(decks);
    }

    /**
     * draw all developecards at the top of the decks
     * @param decks: all the 12 decks of the game
     */
    private void drawCards(DevelopeDecks[] decks)
    {
        int contH=0;
        int contV=0;
        for (int i = 0; i < 12; i++) {
            if(i%4 == 0) contH=0;

            if(i>=4 && i<8) contV = 1;
            if(i>=8) contV = 2;

            if(decks[i]!=null && decks[i].getStructure().size() != 0)
                DrawDevelopecard(decks[i].getStructure().get(0), space, 12 * contV, 16 * contH);

            contH++;
        }
    }

    public void Print()
    {
        Print(space, VERT_SIZE, HORIZ_SIZE);
    }
}
