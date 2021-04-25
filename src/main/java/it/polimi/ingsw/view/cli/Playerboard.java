package it.polimi.ingsw.view.cli;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Playerboard {
    private static final int VERT_SIZE = 30;
    private static final int HORIZ_SIZE = 150;  // posso farlo di 150?

    private String[][] playerboard = new String[VERT_SIZE][HORIZ_SIZE];

    Playerboard() throws FileNotFoundException {
        Perimeter();
        FaithTrack();
    }

    private void Perimeter()
    {
        Color color = Color.BLACK;
        for (int i = 0; i < VERT_SIZE; i++)
            for (int j = 0; j < HORIZ_SIZE; j++)
                playerboard[i][j] = color + " " + Color.RESET;

        playerboard[0][0] = color + "╔" + Color.RESET;
        playerboard[0][HORIZ_SIZE-1] = color + "╗" + Color.RESET;
        playerboard[VERT_SIZE-1][0] = color + "╚" + Color.RESET;
        playerboard[VERT_SIZE-1][HORIZ_SIZE-1] = color + "╝" + Color.RESET;

        for (int i = 1; i < HORIZ_SIZE-1; i++)
            playerboard[0][i] = color + "═" + Color.RESET;
        for (int i = 1; i < HORIZ_SIZE-1; i++)
            playerboard[VERT_SIZE-1][i] = color + "═" + Color.RESET;

        for (int i = 1; i < VERT_SIZE-1; i++)
            playerboard[i][0] = color + "║" + Color.RESET;
        for (int i = 1; i < VERT_SIZE-1; i++)
            playerboard[i][HORIZ_SIZE-1] = color + "║" + Color.RESET;
    }

    private void FaithTrack() throws FileNotFoundException   //devo mettere lo sfondo di un altro colore
    {
        int MAX_HIGH = VERT_SIZE/3;
        int SQUARE_HIGH = 3;
        int SQUARE_LENGHT = 6;
        //Color basecolor = Color.RESET;

        for(int i=1; i<MAX_HIGH; i++)
            for (int j = 1; j < HORIZ_SIZE-1; j++)
                playerboard[i][j]=Color.RESET + " ";

        for (int i = 0; i < 25; i++) {
            int LeftHighCorner_VERT;
            int LeftHighCorner_HORIZ;
            int content;

            FileReader stringa = new FileReader("src/main/resources/CellsPosition.json");
            Object obj = JsonParser.parseReader(stringa);
            JsonObject jsonObject = (JsonObject)obj;
            JsonArray cardsArray = (JsonArray)jsonObject.get("CellsPosition");
            JsonObject card = (JsonObject)cardsArray.get(i);

            LeftHighCorner_HORIZ = card.get("LeftHighCorner_HORIZ").getAsInt();
            LeftHighCorner_VERT = card.get("LeftHighCorner_VERT").getAsInt();
            content = card.get("content").getAsInt();

            Square(LeftHighCorner_VERT, LeftHighCorner_HORIZ, content);
        }
        /*
        Square(0,0,0);
        Square(0,1,1);
        Square(0,2,2);
        Square(1,2,3);
        Square(2,2,4);
        Square(2,3,5);
        Square(2,4,6);
        Square(2,5,7);
        Square(2,6,8);
        Square(2,7,9);
        Square(1,7,10);
        Square(0,7,11);
        Square(0,8,12);
        Square(0,9,13);
        Square(0,10,14);
        Square(0,11,15);
        Square(0,12,16);
        Square(1,12,17);
        Square(2,12,18);
        Square(2,13,19);
        Square(2,14,20);
        Square(2,15,21);
        Square(2,16,22);
        Square(2,17,23);   //da fare con json
        Square(2,18,24);*/

    }

    private void Square(int LeftHighCorner_VERT, int LeftHighCorner_HORIZ, int content)
    {
        int SQUARE_HIGH = 3;
        int SQUARE_LENGHT = 7;
        String[][] square = new String[SQUARE_HIGH][SQUARE_LENGHT];

        for (int i = 0; i < SQUARE_HIGH; i++)
            for (int j = 0; j < SQUARE_LENGHT; j++)
                square[i][j]=" ";

        for (int i = 0; i < SQUARE_LENGHT; i++)
            square[0][i] = Color.RED + "═";
        for (int i = 0; i < SQUARE_LENGHT; i++)
            square[SQUARE_HIGH-1][i]=Color.RED + "═";

        for (int i = 0; i < SQUARE_HIGH; i++)
            square[i][0]=Color.RED + "║";
        for (int i = 0; i < SQUARE_HIGH; i++)
            square[i][SQUARE_LENGHT-1]=Color.RED + "║";

        square[0][0] = Color.RED + "╔" + Color.RESET;
        square[0][SQUARE_LENGHT-1] = Color.RED + "╗" + Color.RESET;
        square[SQUARE_HIGH-1][0] = Color.RED + "╚" + Color.RESET;
        square[SQUARE_HIGH-1][SQUARE_LENGHT-1] = Color.RED + "╝" + Color.RESET;

        if(content>9)
            square[1][(SQUARE_LENGHT-1)/2+1]="";

        square[1][(SQUARE_LENGHT-1)/2]=Color.YELLOW + String.valueOf(content).toString();;


        int inizialVert=7;
        int inizialHoriz=7;
        for (int i = 0; i < SQUARE_HIGH; i++)
            for (int j = 0; j < SQUARE_LENGHT; j++)
                playerboard[inizialVert-SQUARE_HIGH*LeftHighCorner_VERT+i][inizialHoriz+SQUARE_LENGHT*LeftHighCorner_HORIZ+j]= square[i][j];
    }

    public void Print()
    {
        for (int i = 0; i < VERT_SIZE; i++) {
            for (int j = 0; j < HORIZ_SIZE; j++)
                System.out.print(playerboard[i][j]);
            System.out.print("\n");
        }
    }
}
