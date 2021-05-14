package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;

public class MarketView extends Utility{
    private static final int VERT_SIZE = 30;
    private static final int HORIZ_SIZE = 67;
    private String[][] market;

    MarketView(Market gamemarket)
    {
        market = new String[VERT_SIZE][HORIZ_SIZE];
        Perimeter(market, VERT_SIZE, HORIZ_SIZE, Color.BLACK);

        Space(gamemarket);
        for (int i = 20; i < VERT_SIZE-1; i++)
            market[i][10]=Color.BLACK + "║" + Color.RESET;
        for (int i = 50; i < HORIZ_SIZE-1; i++)
            market[5][i]=Color.BLACK + "═" + Color.RESET;
        drawArrows();
    }

    private void Space(Market gamemarket)
    {
        int MAX_VERT_SIZE=7*3-2;
        int MAX_HORIZ_SIZE=12*4-3;
        String[][] space = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[i][j]= " ";


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++) {
                //gamemarket.getcolor
                drawMarbleSquare(space, i * (MAX_VERT_SIZE/3), j * (MAX_HORIZ_SIZE/4));
            }

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                market[5+i][10+j]=space[i][j];
    }

    private void drawMarbleSquare(String[][] space, int row, int column)
    {
        Color color = Color.BLACK;
        int MAX_HORIZ_SIZE=12;
        int MAX_VERT_SIZE=7;
        String[][] marbleSquare = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        Perimeter(marbleSquare, MAX_VERT_SIZE, MAX_HORIZ_SIZE, color);
        drawMarble(marbleSquare, Color.BACKGROUND_PURPLE);

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[row+i][column+j]=marbleSquare[i][j];
    }

    private void drawMarble(String[][] marbleSquare, Color color)
    {
        int MAX_VERT_SIZE=5;
        int MAX_HORIZ_SIZE=8;
        String[][] marble = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                marble[i][j]=Color.BLACK + " ";

        for (int i = 2; i < 6; i++) marble[0][i]=color.getEscape() + " ";
        for (int i = 1; i < 7; i++) marble[1][i]=color.getEscape() + " ";
        for (int i = 0; i < 8; i++) marble[2][i]=color.getEscape() + " ";
        for (int i = 1; i < 7; i++) marble[3][i]=color.getEscape() + " ";
        for (int i = 2; i < 6; i++) marble[4][i]=color.getEscape() + " ";

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                marbleSquare[1+i][2+j]=marble[i][j];
    }

    private void drawArrows()
    {

    }


    public void Print()
    {
        Print(market, VERT_SIZE, HORIZ_SIZE);
    }

}
