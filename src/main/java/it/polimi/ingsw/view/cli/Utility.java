package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;

import java.util.ArrayList;

public abstract class Utility {

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
            }
        }

        returned[row][column] = simbols;

        for (int i = 1; i < resources.size(); i++) returned[row][column+i] = "";

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
        for (int i = 0; i < 50; i++) System.out.print("\n");
    }
}
