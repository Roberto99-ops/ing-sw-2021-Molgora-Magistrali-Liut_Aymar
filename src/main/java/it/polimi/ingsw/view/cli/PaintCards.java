package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.LeaderCard;

import java.util.ArrayList;

public abstract class PaintCards extends Utility {

    /**
     * draw the leadercards
     *
     * @param card: card to draw
     * @return
     */
    public void drawLeadercard(LeaderCard card, String[][] space, int row, int column) {
        int MAX_VERT_SIZE = (45 - 13 - 8) / 2;
        int MAX_HORIZ_SIZE = 17 - 2;
        Color color = Color.BACKGROUND_PURPLE;
        String[][] leadercard = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                leadercard[i][j] = color + " " + Color.RESET;

        for (int i = 1; i < MAX_HORIZ_SIZE - 1; i++)
            leadercard[0][i] = color + "_" + Color.RESET;
        for (int i = 1; i < MAX_HORIZ_SIZE - 1; i++)
            leadercard[MAX_VERT_SIZE - 1][i] = color + "_" + Color.RESET;

        for (int i = 1; i < MAX_VERT_SIZE; i++)
            leadercard[i][0] = color + "|" + Color.RESET;
        for (int i = 1; i < MAX_VERT_SIZE; i++)
            leadercard[i][MAX_HORIZ_SIZE - 1] = color + "|" + Color.RESET;

        if (card!= null) {
            putString("cardLevel: " + card.getCardLevel(), leadercard, 1, 1);
            putString("PV: " + card.getPv(), leadercard, 3, 1);
            if (card.getPriceC().size() != 0) {
                putString("priceC:", leadercard, 5, 1);
                convertSymbols(card.getPriceC(), leadercard, 5, 8);
            } else {
                putString("priceR:", leadercard, 5, 1);
                convertSymbols(card.getPriceR().getVector(), leadercard, 5, 8);
            }
            putString("skill: ", leadercard, 7, 1);
            putString(card.getSkill(), leadercard, 8, 1);

            putString("inputskill:", leadercard, 10, 1);
            ArrayList<Character> input = new ArrayList<>();
            input.add(card.getInputskill());
            convertSymbols(input, leadercard, 10, 12);
        } else
            for (int i = 1; i < MAX_VERT_SIZE - 1; i++)
                for (int j = 1; j < MAX_HORIZ_SIZE - 1; j++) leadercard[i][j] = color + "/" + Color.RESET;

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[row + i][column + j] = leadercard[i][j];
    }

    /**
     * @param card
     * @return
     */
    public void drawDevelopecard(DevelopeCard card, String[][] space, int row, int column) {
        int MAX_VERT_SIZE = 12;
        int MAX_HORIZ_SIZE = 17 - 2;
        String color = convertColor(card.getColour());
        String[][] developecard = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                developecard[i][j] = color + " " + Color.RESET;

        for (int i = 1; i < MAX_HORIZ_SIZE - 1; i++)
            developecard[0][i] = color + "_" + Color.RESET;
        for (int i = 1; i < MAX_HORIZ_SIZE - 1; i++)
            developecard[MAX_VERT_SIZE - 1][i] = color + "_" + Color.RESET;

        for (int i = 1; i < MAX_VERT_SIZE; i++)
            developecard[i][0] = color + "|" + Color.RESET;
        for (int i = 1; i < MAX_VERT_SIZE; i++)
            developecard[i][MAX_HORIZ_SIZE - 1] = color + "|" + Color.RESET;

        putString("cardLevel: " + card.getLevel(), developecard, 1, 1);
        putString("Cost:", developecard, 3, 1);
        convertSymbols(card.getCost().getVector(), developecard, 3, 6);

        putString("inputprod.:", developecard, 5, 1);
        convertSymbols(card.getInputproduction().getVector(), developecard, 6, 1);
        putString("outputprod.:", developecard, 8, 1);
        convertSymbols(card.getOutputproduction().getVector(), developecard, 9, 1);

        putString("PV: " + card.getPv(), developecard, 10, 1);

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[row + i][column + j] = developecard[i][j];
    }

}
