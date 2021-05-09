package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;

public class MarketView extends Utility{
    private static final int VERT_SIZE = 30;
    private static final int HORIZ_SIZE = 80;
    private String[][] market = new String[VERT_SIZE][HORIZ_SIZE];

    MarketView(Market gamemarket)
    {
        Perimeter(market, VERT_SIZE, HORIZ_SIZE, Color.BLACK);
        Space();
        drawMarbles(gamemarket);
    }

    private void Space()
    {
        String[][] space = new String[15][40];
        //5 verticale
        //for (int i = 0; i < 12; i++)
            //quadrato


    }

    private void drawMarbles(Market gamemarket)
    {

    }

    public void Print()
    {
        Print(market, VERT_SIZE, HORIZ_SIZE);
    }

}
