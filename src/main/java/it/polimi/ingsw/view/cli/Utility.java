package it.polimi.ingsw.view.cli;

import java.util.ArrayList;

public abstract class Utility {

    /**
     * This class draws the perimeter of the PlayerBoard
     */
    public void Perimeter(String[][] board, int VERT_SIZE, int HORIZ_SIZE, Color color)
    {
        for (int i = 0; i < VERT_SIZE; i++)
            for (int j = 0; j < HORIZ_SIZE; j++)
                board[i][j] = color + " " + Color.RESET;

        board[0][0] = color + "╔" + Color.RESET;
        board[0][HORIZ_SIZE-1] = color + "╗" + Color.RESET;
        board[VERT_SIZE-1][0] = color + "╚" + Color.RESET;
        board[VERT_SIZE-1][HORIZ_SIZE-1] = color + "╝" + Color.RESET;

        for (int i = 1; i < HORIZ_SIZE-1; i++)
            board[0][i] = color + "═" + Color.RESET;
        for (int i = 1; i < HORIZ_SIZE-1; i++)
            board[VERT_SIZE-1][i] = color + "═" + Color.RESET;

        for (int i = 1; i < VERT_SIZE-1; i++)
            board[i][0] = color + "║" + Color.RESET;
        for (int i = 1; i < VERT_SIZE-1; i++)
            board[i][HORIZ_SIZE-1] = color + "║" + Color.RESET;
    }


    /**
     * It takes a string of resources and converts it into a row or column of colored balls
     * @param resources: resources to convert into balls
     * @param returned: matrix modified (it is the card)
     * @param row: row where put the symbols
     * @param column: column where put the symbols
     */
    public void convertSymbols(ArrayList<Character> resources, String[][] returned, int row, int column)
    {
        String simbols = new String();
        for (int i = 0; i < resources.size(); i++) {
            switch (resources.get(i)) {
                case 'B':
                    simbols += Color.BLUE.getEscape() + Simbol.CIRCLE;
                    break;
                case 'Y':
                    simbols += Color.YELLOW.getEscape() + Simbol.CIRCLE;
                    break;
                case 'P':
                    simbols += Color.PURPLE.getEscape() + Simbol.CIRCLE;
                    break;
                case 'G':
                    simbols += Color.GRAY.getEscape() + Simbol.CIRCLE;
                    break;
                case 'F': //F because G was already used
                    simbols += Color.GREEN.getEscape() + Simbol.CIRCLE;
                    break;
                case 'R':
                    simbols += Color.RED.getEscape() + Simbol.CIRCLE;
                    break;
                case 'N':
                    simbols += Color.RESET + '_';
                    break;
            }
        }

        returned[row][column] = simbols;

        for (int i = 1; i < resources.size(); i++) returned[row][column+i] = "";

    }

    /**
     * Converts a char color ('Y', 'P', 'G', 'B') into its real and matching color
     * @param color: the color to convert
     */
    public String convertColor(Character color) {

        String returned = new String();
        switch (color) {
            case 'B':
                returned = Color.BLUE.getEscape();
                break;
            case 'Y':
                returned = Color.YELLOW.getEscape();
                break;
            case 'P':
                returned = Color.PURPLE.getEscape();
                break;
            case 'G':
                returned = Color.GREEN.getEscape();
                break;
        }
        return returned;
    }

    /**
     * Converts a char color ('Y', 'P', 'G', 'B') into a real color (BACKGROUND)
     * @param color: the color to convert
     */
    public String convertBackground(Character color) {

        String returned = new String();
        switch (color) {
            case 'B':
                returned = Color.BACKGROUND_BLUE.getEscape();
                break;
            case 'Y':
                returned = Color.BACKGROUND_YELLOW.getEscape();
                break;
            case 'P':
                returned = Color.BACKGROUND_PURPLE.getEscape();
                break;
            case 'G':
                returned = Color.BACKGROUND_GRAY.getEscape();
                break;
            case 'W':
                returned = Color.BACKGROUND_WHITE.getEscape();
                break;
            case 'R':
                returned = Color.BACKGROUND_RED.getEscape();
                break;
        }
        return returned;
    }

    /**
     * Puts a given string inside a given row/column of the matrix
     * @param string: string tu out inside
     * @param returned: matrix modified
     * @param row: position where put the string
     * @param column: position where put the string
     */
    public void putString(String string, String[][] returned, int row, int column)
    {
        returned[row][column] = string;
        for (int i = 1; i < string.length(); i++) returned[row][column+i] = "";
    }

    /**
     * Cleans the screen
     */
    public static void Clean()
    {
        System.out.println(Color.CLEAR);
        System.out.flush();
    }


    /**
     * This method draws the vatican cards.
     * It colors them with green if we pass them so we can know if we have
     * activated them or not.
     * @param color: green if card is activated for us, red if the card isn't been activated yet/cannot be activated
     * @param content: PV of the card (2, 3, 4)
     * @return the drawing of the card
     */
    public String[][] drawVaticanCard(Color color, String content)
    {
        int MAX_VERT_SIZE = 7;
        int MAX_HORIZ_SIZE = 14;
        String[][] card = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++) {
            for (int j = 0; j < MAX_HORIZ_SIZE; j++) {
                card[i][j] = color + " " + Color.RESET;
            }
        }

        for (int i = 0; i < MAX_HORIZ_SIZE; i++) {
            card[0][i] = Color.BACKGROUND_CYAN + "_" + Color.RESET;
            card[MAX_VERT_SIZE - 1][i] = color + "_" + Color.RESET;
        }
        for (int i = 1; i < MAX_VERT_SIZE; i++) {
            card[i][0] = color + "|" + Color.RESET;
            card[i][MAX_HORIZ_SIZE - 1] = color + "|" + Color.RESET;
        }

        for (int i = 0; i < 5; i++) {
            card[2][4 + i] = color + Simbol.VATICAN_CARD.getForm() + Color.RESET;
            card[5][4 + i] = color + Simbol.VATICAN_CARD.getForm() + Color.RESET;
        }

        for (int i = 0; i < 2; i++) {
            card[3+i][3] = color + Simbol.VATICAN_CARD.getForm() + Color.RESET;
            card[3+i][9] = color + Simbol.VATICAN_CARD.getForm() + Color.RESET;
        }

        if(content.equals("2")) card[4][6] = color + "2" + Color.RESET;
        if(content.equals("3")) card[4][6] = color + "3" + Color.RESET;
        if(content.equals("4")) card[4][6] = color + "4" + Color.RESET;

        return card;
    }

    /**
     * It prints on the screen the entire PlayerBoard
     */
    public void Print(String[][] board, int VERT_SIZE, int HORIZ_SIZE)
    {
        for (int i = 0; i < VERT_SIZE; i++) {
            for (int j = 0; j < HORIZ_SIZE; j++)
                System.out.print(board[i][j]);
            System.out.print("\n");
        }
    }
}
