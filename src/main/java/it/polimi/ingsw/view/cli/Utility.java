package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;

import java.util.ArrayList;

public abstract class Utility {

    /**
     * this class draw the perimeter of the playerboard
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
     * generate a string containg a "ball" of the same colour of the colour of the resource for each resource.
     * then put this string into the card and resize the card.
     * @param resources: resources to convert into balls
     * @param returned: matrix modified (it is the card)
     * @param row: row where put the simbols
     * @param column: column where put the simbols
     * @return
     */
    public void convertSymbols(ArrayList<Character> resources, String[][] returned, int row, int column)
    {
        String simbols = new String();
        for (int i = 0; i < resources.size(); i++) {
            switch (resources.get(i)) {
                case 'B':
                    simbols += Color.BLUE.getEscape() + Simbol.PALLINO;
                    break;
                case 'Y':
                    simbols += Color.YELLOW.getEscape() + Simbol.PALLINO;
                    break;
                case 'P':
                    simbols += Color.PURPLE.getEscape() + Simbol.PALLINO;
                    break;
                case 'G':
                    simbols += Color.GRAY.getEscape() + Simbol.PALLINO;
                    break;
                case 'F': //F because G was already used
                    simbols += Color.GREEN.getEscape() + Simbol.PALLINO;
                    break;
                case 'R':
                    simbols += Color.RED.getEscape() + Simbol.PALLINO;
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
     * converts a char color ('Y', 'P', 'G', 'B') into a real color
     * @param color: the color to convert
     * @return
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
     * converts a char color ('Y', 'P', 'G', 'B') into a real color (BACKGOURND)
     * @param color: the color to convert
     * @return
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
                returned = Color.BLACK.getEscape();
                break;
            case 'R':
                returned = Color.BACKGROUND_RED.getEscape();
                break;
        }
        return returned;
    }

    /**
     * puts a given string inside a given string matrix
     * @param string: string tu out inside
     * @param returned: matrix modified
     * @param row: position where put the string
     * @param column: position where put the string
     * @return
     */
    public void putString(String string, String[][] returned, int row, int column)
    {
        returned[row][column] = string;
        for (int i = 1; i < string.length(); i++) returned[row][column+i] = "";
    }

    /**
     * clean the screen
     */
    public static void Clean()
    {
        System.out.println(Color.CLEAR);
    }

    /**
     * it prints on the screen all the playeboard
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
