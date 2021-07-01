package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopDecks;

/**
 * Draws the decks of DevelopCards of the game
 */

public class DevelopDecksView extends PaintCards {
    private final int VERT_SIZE = 40;
    private final int HORIZ_SIZE = 77;
    private String [][] space;

    public DevelopDecksView(DevelopDecks[] decks)
    {
        space = new String[VERT_SIZE][HORIZ_SIZE];
        for (int i = 0; i < VERT_SIZE; i++)
            for (int j = 0; j < HORIZ_SIZE; j++)
                space[i][j] = " ";

        drawCards(decks);
        putIndicators();
    }

    /**
     * Draws all DevelopCards at the top of the decks
     * @param decks: all the 12 decks of the game
     */
    private void drawCards(DevelopDecks[] decks)
    {
        int contH=3;
        int contV=2;
        for (int i = 0; i < 12; i++) {
            if(contH < 0) contH=3;

            if(i>=4 && i<8) contV = 1;
            if(i>=8) contV = 0;

            if(decks[i]!=null && decks[i].getStructure().size() != 0)
                drawDevelopCard(decks[i].getStructure().get(0), space, 12 * contV, 16 * contH);

            contH--;
        }
    }

    /**
     * These indicators appears when I try to buy a DevelopCard
     */

    private void putIndicators()
    {
        putString("<-- 3 2 1 0", space, VERT_SIZE-10, HORIZ_SIZE-13);
        putString("<-- 7 6 5 4", space, VERT_SIZE-22, HORIZ_SIZE-13);
        putString("<-- 11 10 9 8", space, VERT_SIZE-34, HORIZ_SIZE-13);

        //space[VERT_SIZE-22][HORIZ_SIZE-1] = "4";
        //space[VERT_SIZE-34][HORIZ_SIZE-1] = "8";
    }

    public void print()
    {
        Print(space, VERT_SIZE, HORIZ_SIZE);
    }
}
