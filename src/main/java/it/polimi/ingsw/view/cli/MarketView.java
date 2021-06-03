package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;

public class MarketView extends Utility {
    private static final int VERT_SIZE = 30;
    private static final int HORIZ_SIZE = 67;
    private String[][] market;

    public MarketView(Market gamemarket)

    {
        market = new String[VERT_SIZE][HORIZ_SIZE];
        Perimeter(market, VERT_SIZE, HORIZ_SIZE, Color.BACKGROUND_GRAY);

        putString("MARKET", market, 1, 30);
        drawExtraMarble(gamemarket.getExtraball());

        space(gamemarket);
        for (int i = 20; i < VERT_SIZE-1; i++)
            market[i][10]=Color.BACKGROUND_GRAY + "║" + Color.RESET;
        for (int i = 50; i < HORIZ_SIZE-1; i++)
            market[5][i]=Color.BACKGROUND_GRAY + "═" + Color.RESET;

        drawArrows();
        drawNumbers();
    }

    /**
     * draw the space composed by the 12 squares that coantain the marbles
     * @param gamemarket: the market to draw
     */
    private void space(Market gamemarket)
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
                drawMarbleSquare(space, i * (MAX_VERT_SIZE/3), j * (MAX_HORIZ_SIZE/4), gamemarket.getMatrix()[i][j]);
            }

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                market[5+i][10+j]=space[i][j];
    }

    /**
     * draw the sqares where the 12 marbles are inside
     * @param space: the space composed by the 12 squares
     * @param row: row where to put the square
     * @param column: column where to put the square
     * @param colorMarble: color of the marble
     */
    private void drawMarbleSquare(String[][] space, int row, int column, Character colorMarble)
    {
        Color color = Color.BACKGROUND_GRAY;
        String marbleColor = convertBackground(colorMarble);
        int MAX_HORIZ_SIZE=12;
        int MAX_VERT_SIZE=7;
        String[][] marbleSquare = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        Perimeter(marbleSquare, MAX_VERT_SIZE, MAX_HORIZ_SIZE, color);
        drawMarble(marbleSquare, marbleColor);

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[row+i][column+j]=marbleSquare[i][j];
    }

    /**
     * draw the extramarble
     * @param colorMarble: color of the extramarble
     */
    private void drawExtraMarble(Character colorMarble)
    {
        Color color = Color.BACKGROUND_GRAY;
        String marbleColor = convertBackground(colorMarble);
        int MAX_HORIZ_SIZE = 12;
        int MAX_VERT_SIZE = 7;
        String[][] marbleSquare = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_VERT_SIZE; j++)
                marbleSquare[i][j] = "";

        drawMarble(marbleSquare, marbleColor);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 8; j++)
                market[24+i][1+j] = marbleSquare[1+i][2+j];
    }

    /**
     * draw a marble
     * @param marbleSquare: where to draw it
     * @param color: color of the marble
     */
    private void drawMarble(String[][] marbleSquare, String color)
    {
        int MAX_VERT_SIZE=5;
        int MAX_HORIZ_SIZE=8;
        String[][] marble = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                marble[i][j]=Color.BACKGROUND_GRAY + " ";

        for (int i = 2; i < 6; i++) marble[0][i]=color + " ";
        for (int i = 1; i < 7; i++) marble[1][i]=color + " ";
        for (int i = 0; i < 8; i++) marble[2][i]=color + " ";
        for (int i = 1; i < 7; i++) marble[3][i]=color + " ";
        for (int i = 2; i < 6; i++) marble[4][i]=color + " ";

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                marbleSquare[1+i][2+j]=marble[i][j];
    }

    /**
     * draw the arrows, that sign where we can buy, of the maket
     */
    private void drawArrows()
    {
        int MAX_VERT_SIZE1 = 4;
        int MAX_HORIZ_SIZE1 = 9;
        int MAX_VERT_SIZE2 = 5;
        int MAX_HORIZ_SIZE2 = 6;
        String[][] HorizArrow = new String[MAX_VERT_SIZE1][MAX_HORIZ_SIZE1];
        String[][] VertArrow = new String[MAX_VERT_SIZE2][MAX_HORIZ_SIZE2];
        Color color = Color.BACKGROUND_GRAY;

        for (int i = 0; i < MAX_VERT_SIZE1; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE1; j++)
                HorizArrow[i][j] = color + " ";

        for (int i = 0; i < MAX_VERT_SIZE2; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE2; j++)
                VertArrow[i][j] = color + " ";


        HorizArrow[0][1] = color + "/";
        HorizArrow[1][0] = color + "/";
        HorizArrow[2][0] = color + "\\";
        HorizArrow[3][1] = color + "\\";

        VertArrow[2][5] = color + "\\";
        VertArrow[1][4] = color + "\\";
        VertArrow[0][3] = color + "\\";
        VertArrow[0][2] = color + "/";
        VertArrow[1][1] = color + "/";
        VertArrow[2][0] = color + "/";

        for (int i = 0; i < MAX_HORIZ_SIZE1-1; i++)
            HorizArrow[1][i + 1] = color + "_";

        for (int i = 0; i < MAX_VERT_SIZE2-1; i++)
            VertArrow[1+i][2] = color + "|";

        for (int i = 0; i < MAX_VERT_SIZE1; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE1; j++) {
                market[7 + i][56 + j] = HorizArrow[i][j];
                market[13 + i][56 + j] = HorizArrow[i][j];
                market[19 + i][56 + j] = HorizArrow[i][j];
            }

        for (int i = 0; i < MAX_VERT_SIZE2; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE2; j++) {
                market[24+i][13+j] = VertArrow[i][j];
                market[24+i][24+j] = VertArrow[i][j];
                market[24+i][35+j] = VertArrow[i][j];
                market[24+i][46+j] = VertArrow[i][j];
            }
    }

    /**
     * draw the number of the row/column to choose
     */
    private void drawNumbers()
    {
        for (Integer i = 0; i < 3; i++) {
            market[8+i*6][8] = Color.BACKGROUND_GRAY + i.toString() + Color.RESET;
        }
        for (Integer i = 0; i < 4; i++) {
            market[4][16+11*i] = Color.BACKGROUND_GRAY + i.toString() + Color.RESET;
        }
    }

    public void print()
    {
        Print(market, VERT_SIZE, HORIZ_SIZE);
    }

}
