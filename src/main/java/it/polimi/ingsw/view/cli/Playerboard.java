package it.polimi.ingsw.view.cli;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * this class draw the playerboard
 */
public class Playerboard extends PaintCards{
    private static final int VERT_SIZE = 45;
    private static final int HORIZ_SIZE = 150;  // posso farlo di 150?

    private String[][] playerboard = new String[VERT_SIZE][HORIZ_SIZE];

    Playerboard(Player player) throws FileNotFoundException {
        Perimeter(playerboard, VERT_SIZE, HORIZ_SIZE, Color.BLACK);
        FaithTrack();
        Storage();
        Strongbox(player.getStrongBox().getStructure());
        LeaderSpace(player);
        Developementspace();
    }

    /**
     * this class draw the faith track space
     * @throws FileNotFoundException
     */
    private void FaithTrack() throws FileNotFoundException   //devo mettere lo sfondo di un altro colore
    {
        int MAX_HIGH = 13;
        Color basecolor = Color.BACKGROUND_CYAN;

        for(int i=1; i<MAX_HIGH; i++)
            for (int j = 1; j < HORIZ_SIZE-1; j++)
                playerboard[i][j]=basecolor + " " + Color.RESET;

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
    }

    /**
     * this class is called by the faithtrack class, it draws inside the faith track space
     * the properly faith track
     * @param LeftHighCorner_VERT = is a number that defines the vertical position of them cell's right corner
     * @param LeftHighCorner_HORIZ = is a number that defines the horizontal position of them cell's right corner
     * @param content = is the content of the cell (usually a number but it could be the faithmarker)
     */
    private void Square(int LeftHighCorner_VERT, int LeftHighCorner_HORIZ, int content)
    {
        int SQUARE_HIGH = 4;
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

        if(content>9 || content==0)
            square[(SQUARE_HIGH-1)/2][(SQUARE_LENGHT-1)/2+1]="";
        square[(SQUARE_HIGH-1)/2][(SQUARE_LENGHT-1)/2]=Color.YELLOW + String.valueOf(content).toString();


        if(content%3==0 && content!=0)
            for (int i = 1; i < SQUARE_HIGH-1; i++)
                for (int j = 1; j < SQUARE_LENGHT-1; j++)
                    square[i][j]=Color.BACKGROUND_YELLOW.getEscape() +  " " + Color.RESET;

        if(content%8==0 && content!=0){
            for (int i = 1; i < SQUARE_HIGH-1; i++)
                for (int j = 1; j < SQUARE_LENGHT-1; j++)
                    square[i][j] = Color.BACKGROUND_PURPLE + " " + Color.RESET;
            square[SQUARE_HIGH-1][(SQUARE_LENGHT-1)/2]=Color.PURPLE.getEscape() + Simbol.SEGNALINO_PAPALE.getForm();
        }

        if(content==0)  square[1][(SQUARE_LENGHT-1)/2]=Color.PURPLE.getEscape() + Simbol.CROCE + Color.RESET;

        int inizialVert=9;
        int inizialHoriz=7;
        for (int i = 0; i < SQUARE_HIGH; i++)
            for (int j = 0; j < SQUARE_LENGHT; j++)
                playerboard[inizialVert-SQUARE_HIGH*LeftHighCorner_VERT+i][inizialHoriz+SQUARE_LENGHT*LeftHighCorner_HORIZ+j]= square[i][j];
    }

    /**
     * it draws the storage space inside the playerboard
     */
    private void Storage()
    {
        int MAX_VERT_SIZE = VERT_SIZE-13-2-VERT_SIZE/4;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/4;
        Color color = Color.STRONGBOX_COLOR;
        String[][] storage = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                storage[i][j]=color + " " + Color.RESET;


        int initialVert=14;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][1+j]=storage[i][j];
    }

    /**
     * it draws the strongbox space inside the playerboard
     */
    private void Strongbox(ResourceStructure box)
    {
        int MAX_VERT_SIZE = VERT_SIZE/4;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/4;
        Color color = Color.STRONGBOX_COLOR;
        String[][] strongbox = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                strongbox[i][j]=color + " " + Color.RESET;

        int initialVert=33;

        for (int i = 1; i < MAX_HORIZ_SIZE-1; i++)
            strongbox[0][i] = color + "_" + Color.RESET;
        for (int i = 1; i < MAX_HORIZ_SIZE-1; i++)
            strongbox[MAX_VERT_SIZE-1][i] = color + "_" + Color.RESET;

        for (int i = 1; i < MAX_VERT_SIZE; i++)
            strongbox[i][0] = color + "|" + Color.RESET;
        for (int i = 1; i < MAX_VERT_SIZE; i++)
            strongbox[i][MAX_HORIZ_SIZE-1] = color + "|" + Color.RESET;

        putString("STRONGBOX", strongbox, 1, 15);

        //here it draws the resources
        //!!!!it deletes all the resources in the strongbox
        for(int j=2; box.size()>0; j++) {
            ArrayList<Character> resources = new ArrayList<>();
            for (int i = 0; box.size()>0 && i < MAX_HORIZ_SIZE-2; i++) {
                resources.add((Character)box.get(0));
                box.remove(0);
            }

            convertSymbols(resources, strongbox, j, 1);
        }

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][1+j]=strongbox[i][j];
    }


    /**
     * it draws the space where we can see our leaderecards
     * @param player
     */
    private void LeaderSpace(Player player)
    {
        int MAX_VERT_SIZE = (VERT_SIZE-13-4)/2;
        int MAX_HORIZ_SIZE = 17;
        Color backgroundcolor = Color.BACKGROUND_GRAY;
        String[][] leaderspace1 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        String[][] leaderspace2 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
            {leaderspace1[i][j]=backgroundcolor + " " + Color.RESET;
                leaderspace2[i][j]=backgroundcolor + " " + Color.RESET;}

        String[][] card1;
        String[][] card2;
        if(player.getLeadercards().getStructure().size()>0) {
            card1 = DrawLeadercard(player.getLeadercards().getStructure().get(0));
            if(player.getLeadercards().getStructure().size()==2)
                card2 = DrawLeadercard(player.getLeadercards().getStructure().get(1));
            else
                card2 = DrawLeadercard(null);
        }
        else
        { card1 = DrawLeadercard(null);
            card2 = DrawLeadercard(null);}

        for (int i = 0; i < MAX_VERT_SIZE-2; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE-2; j++) {
                leaderspace1[i + 1][j + 1] = card1[i][j];
                leaderspace2[i + 1][j + 1] = card2[i][j];
            }


        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/4+2;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++) {
                playerboard[initialVert + i][initialHoriz + j] = leaderspace1[i][j];
                playerboard[initialVert + i + MAX_VERT_SIZE + 2][initialHoriz + j] = leaderspace2[i][j];
            }
    }


    /**
     * it draws the developementspace inside the playerboard
     */
    private void Developementspace()
    {
        int MAX_VERT_SIZE = VERT_SIZE-13-2;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/2+17;
        Color backgroundcolor = Color.BACKGROUND_GREEN;
        String[][] developementspace = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                developementspace[i][j]=backgroundcolor + " " + Color.RESET;


        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/2-1-17;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][initialHoriz+j]=developementspace[i][j];
    }

    public void Print()
    {
        Print(playerboard, VERT_SIZE, HORIZ_SIZE);
    }
}
