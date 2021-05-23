package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderDeck;

/**
 * this class draw the 4 leadercard that the player can choose at the gamestart
 */
public class LeaderChooseView extends PaintCards{
    private final int VERT_SIZE = 28;
    private final int HORIZ_SIZE = 36;
    private String [][] space;

    LeaderChooseView(LeaderDeck deck)
    {
        space = new String[VERT_SIZE][HORIZ_SIZE];
        for (int i = 0; i < VERT_SIZE; i++)
            for (int j = 0; j < HORIZ_SIZE; j++)
                space[i][j] = " ";

        drawCard(deck);
    }

    /**
     * draw the 4 leadercards
     * @param deck: the 4 leadercards
     */
    private void drawCard(LeaderDeck deck)
    {
        int contH=0;
        int contV=0;
        for (int i = 0; i < 4; i++) {
            if(i%2 == 0) contH=0;

            if(i>=2) contV = 1;

            DrawLeadercard(deck.getStructure().get(i), space, 14 * contV, 18 * contH);

            contH++;
        }
    }

    public void Print()
    {
        Print(space, VERT_SIZE, HORIZ_SIZE);
    }

}
