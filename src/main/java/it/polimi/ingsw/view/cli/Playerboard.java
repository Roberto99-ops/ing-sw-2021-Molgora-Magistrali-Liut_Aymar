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
public class Playerboard extends PaintCards {
    private static final int VERT_SIZE = 45;
    private static final int HORIZ_SIZE = 150;

    private String[][] playerboard;

    public Playerboard(Player player, Game game) throws FileNotFoundException {
        playerboard = new String[VERT_SIZE][HORIZ_SIZE];
        Perimeter(playerboard, VERT_SIZE, HORIZ_SIZE, Color.BLACK);
        drawFaithTrack(player.getTrackposition(), game);
        putString(player.getName(), playerboard, 1, 70);
        drawStorage(player.getStorage());
        drawStrongbox(player.getStrongBox().getStructure());
        LeaderSpace(player);
        Developementspace(player.getDSpace());
        PointsSpace(game);
        if(game.getClass().equals(SingleGame.class))
            DrawActionSignal(SingleGame.getActionStructure().getAS_Counter());
    }

    /**
     * this class draw the faith track space
     * @param trackPosition: the position of the player on the track
     * @param game: the game the player is playing so we can know if it is a Singlegame or a Game
     * @throws FileNotFoundException
     */
    private void drawFaithTrack(int trackPosition, Game game) throws FileNotFoundException   //devo mettere lo sfondo di un altro colore
    {
        int MAX_HIGH = 13;
        int Lorenzo = -1;  //setted to -1 so if is a game mutiplayer it doesn0t effect
        Color basecolor = Color.BACKGROUND_CYAN;

        if(game.getClass().equals(SingleGame.class))
            if(SingleGame.getLorenzo()!=null)
                Lorenzo = SingleGame.getLorenzo().getNumber();
            else
                Lorenzo=0;

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

            drawSquare(LeftHighCorner_VERT, LeftHighCorner_HORIZ, content, trackPosition, Lorenzo);
        }
    }

    /**
     * this class is called by the faithtrack class, it draws inside the faith track space
     * the properly faith track
     * @param LeftHighCorner_VERT: is a number that defines the vertical position of them cell's right corner
     * @param LeftHighCorner_HORIZ: is a number that defines the horizontal position of them cell's right corner
     * @param content: is the content of the cell (usually a number but it could be the faithmarker)
     * @param trackPosition: the position of the player on the track
     * @param Lorenzo: the position of Lorenzo on the track
     */
    private void drawSquare(int LeftHighCorner_VERT, int LeftHighCorner_HORIZ, int content, int trackPosition, int Lorenzo)
    {
        int SQUARE_HEIGHT = 4;
        int SQUARE_LENGHT = 7;
        String[][] square = new String[SQUARE_HEIGHT][SQUARE_LENGHT];

        for (int i = 0; i < SQUARE_HEIGHT; i++)
            for (int j = 0; j < SQUARE_LENGHT; j++)
                square[i][j]=" ";

        for (int i = 0; i < SQUARE_LENGHT; i++)
            square[0][i] = Color.RED + "═";
        for (int i = 0; i < SQUARE_LENGHT; i++)
            square[SQUARE_HEIGHT-1][i]=Color.RED + "═";

        for (int i = 0; i < SQUARE_HEIGHT; i++)
            square[i][0]=Color.RED + "║";
        for (int i = 0; i < SQUARE_HEIGHT; i++)
            square[i][SQUARE_LENGHT-1]=Color.RED + "║";

        square[0][0] = Color.RED + "╔" + Color.RESET;
        square[0][SQUARE_LENGHT-1] = Color.RED + "╗" + Color.RESET;
        square[SQUARE_HEIGHT-1][0] = Color.RED + "╚" + Color.RESET;
        square[SQUARE_HEIGHT-1][SQUARE_LENGHT-1] = Color.RED + "╝" + Color.RESET;

        if(content>9)
            square[(SQUARE_HEIGHT-1)/2][(SQUARE_LENGHT-1)/2+1]="";
        square[(SQUARE_HEIGHT-1)/2][(SQUARE_LENGHT-1)/2]=Color.YELLOW + String.valueOf(content).toString();


        if(content%3==0 && content!=0)
            for (int i = 1; i < SQUARE_HEIGHT-1; i++)
                for (int j = 1; j < SQUARE_LENGHT-1; j++)
                    square[i][j]=Color.BACKGROUND_YELLOW.getEscape() +  " " + Color.RESET;

        if(content%8==0 && content!=0){
            for (int i = 1; i < SQUARE_HEIGHT-1; i++)
                for (int j = 1; j < SQUARE_LENGHT-1; j++)
                    square[i][j] = Color.BACKGROUND_PURPLE + " " + Color.RESET;
            square[SQUARE_HEIGHT-1][(SQUARE_LENGHT-1)/2]=Color.PURPLE.getEscape() + Simbol.SEGNALINO_PAPALE.getForm();
        }

        if(content==trackPosition) {
            square[1][(SQUARE_LENGHT - 1) / 2 - 1] = Color.PURPLE.getEscape() + Simbol.CROCE + Color.RESET;
            square[1][((SQUARE_LENGHT - 1)/2)] = " ";
        }

        if(content==Lorenzo) {
            square[2][(SQUARE_LENGHT - 1) / 2] = Color.WHITE.getEscape() + Simbol.CROCE + Color.RESET;
            square[2][((SQUARE_LENGHT - 1)/2) + 1] = " ";
        }

        int inizialVert=9;
        int inizialHoriz=7;
        for (int i = 0; i < SQUARE_HEIGHT; i++)
            for (int j = 0; j < SQUARE_LENGHT; j++)
                playerboard[inizialVert-SQUARE_HEIGHT*LeftHighCorner_VERT+i][inizialHoriz+SQUARE_LENGHT*LeftHighCorner_HORIZ+j]= square[i][j];
    }


    /**
     * it draws the storage space inside the playerboard
     * @param playerStorage: the storage of the player
     */
    private void drawStorage(Storage playerStorage)
    {
        //Storage playerStorage = player.getStorage();
        int MAX_VERT_SIZE = VERT_SIZE-13-2-VERT_SIZE/4;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/4;
        Color color = Color.STRONGBOX_COLOR;
        String[][] storage = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                storage[i][j]=color + " " + Color.RESET;

        //here I draw the storage spaces
        putString("STORAGE", storage, 1, 16);
        for (int i = 0; i < 3; i++) storage[4][18+i] = Color.RESET + "_";
        for (int i = 0; i < 7; i++) storage[7][16+i] = Color.RESET + "_";
        for (int i = 0; i < 11; i++) storage[10][14+i] = Color.RESET + "_";
        
        //here I draw the marbles in the storage
        int lung = playerStorage.getPanel().size();
        if(lung > 0) {
            ArrayList<Character> panel = new ArrayList<>();
            panel.add(playerStorage.getPanel().get(0));
            convertSymbols(panel, storage, 4, 19);
            if(lung > 1){
                panel.remove(0);
                panel.add(playerStorage.getPanel().get(1));
                convertSymbols(panel, storage, 7, 17);
                if(lung > 2) {
                    panel.remove(0);
                    panel.add(playerStorage.getPanel().get(2));
                    convertSymbols(panel, storage, 7, 21);
                    if(lung > 3) {
                        panel.remove(0);
                        panel.add(playerStorage.getPanel().get(3));
                        convertSymbols(panel, storage, 10, 16);
                        if(lung > 4) {
                            panel.remove(0);
                            panel.add(playerStorage.getPanel().get(4));
                            convertSymbols(panel, storage, 10, 19);
                            if(lung > 5) {
                                panel.remove(0);
                                panel.add(playerStorage.getPanel().get(5));
                                convertSymbols(panel, storage, 10, 22);
                            }
                        }
                    }
                }
            }
        }

        //draw the extrapanel if the player has one
        if(playerStorage.getTypeExtrapanel()!='Z') {
            putString("EXTRAPANEL", storage, 15, 15);
            for (int i = 0; i < 7; i++) storage[17][16 + i] = Color.RESET + "_";
            int lung1 = playerStorage.getExtrapanel().size();
            if (lung1 > 0) {
                ArrayList<Character> panel = new ArrayList<>();
                panel.add(playerStorage.getExtrapanel().get(0));
                convertSymbols(panel, storage, 17, 17);
                if (lung1 > 1) {
                    panel.remove(0);
                    panel.add(playerStorage.getExtrapanel().get(1));
                    convertSymbols(panel, storage, 17, 21);
                }
            }
        }

        int initialVert=14;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][1+j]=storage[i][j];
    }


    /**
     * it draws the strongbox space inside the playerboard
     * @param box: the player strongbox
     */
    private void drawStrongbox(ResourceStructure box)
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

        String[][] card1 = new String[12][15];
        String[][] card2 = new String[12][15];
        if(player.getLeadercards().getStructure().size()>0) {
            drawLeadercard(player.getLeadercards().getStructure().get(0), card1, 0, 0, player.getSkill1());
            if(player.getLeadercards().getStructure().size()==2)
                drawLeadercard(player.getLeadercards().getStructure().get(1), card2, 0, 0, player.getSkill2());
            else
                drawLeadercard(null, card2, 0, 0, 0);
        }
        else
        { drawLeadercard(null, card1, 0, 0, 0);
            drawLeadercard(null, card2, 0, 0, 0);}

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
     * it draws the developement space inside the playerboard
     * @param DSpace: DSpace of the player
     */
    private void Developementspace(DevelopementSpace DSpace)
    {
        int MAX_VERT_SIZE = VERT_SIZE-13-2;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/2+17;
        Color backgroundcolor = Color.BACKGROUND_GREEN;
        String[][] developementspace = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                developementspace[i][j]=backgroundcolor + " " + Color.RESET;

        putString("DEVELOPEMENTSPACE", developementspace, 2, 39);

        //draw first minideck
        int minideck1Leng = DSpace.getMinideck1().getStructure().size();
        if(minideck1Leng > 0) {
            drawDevelopecard(DSpace.getMinideck1().getStructure().get(0), developementspace, 14, 10);
            if(minideck1Leng > 1) {
                drawDevelopecard(DSpace.getMinideck1().getStructure().get(1), developementspace, 12, 10);
                if(minideck1Leng > 2)
                    drawDevelopecard(DSpace.getMinideck1().getStructure().get(2), developementspace, 10, 10);
            }
        }

        //draw second minideck
        int minideck2Leng = DSpace.getMinideck2().getStructure().size();
        if(minideck2Leng > 0) {
            drawDevelopecard(DSpace.getMinideck2().getStructure().get(0), developementspace, 14, 40);
            if(minideck2Leng > 1) {
                drawDevelopecard(DSpace.getMinideck2().getStructure().get(1), developementspace, 12, 40);
                if(minideck2Leng > 2)
                    drawDevelopecard(DSpace.getMinideck2().getStructure().get(2), developementspace, 10, 40);
            }
        }

        //draw first minideck
        int minideck3Leng = DSpace.getMinideck3().getStructure().size();
        if(minideck1Leng > 0) {
            drawDevelopecard(DSpace.getMinideck3().getStructure().get(0), developementspace, 14, 70);
            if(minideck1Leng > 1) {
                drawDevelopecard(DSpace.getMinideck3().getStructure().get(1), developementspace, 12, 70);
                if(minideck1Leng > 2)
                    drawDevelopecard(DSpace.getMinideck3().getStructure().get(2), developementspace, 10, 70);
            }
        }

        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/2-1-17;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][initialHoriz+j]=developementspace[i][j];
    }

    /**
     * signs the points of the players
     * @param game: the game we are playing so it contains all the players and their points
     */
    private void PointsSpace(Game game)
    {
        int MAX_VERT_SIZE = 1;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/2 + 17;
        String[][] space = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[i][j]=" ";

        for (int i = 0; i < game.getPlayers().size(); i++) {
            String string = game.getPlayers().get(i).getName();
            Integer points = game.getPlayers().get(i).getPv();
            string = string.concat(": ").concat(points.toString());

            putString(string, space, 0, i*23+1);
        }

        int initialVert=VERT_SIZE-2;
        int initialHoriz=HORIZ_SIZE/2-1-17;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][initialHoriz+j]=space[i][j];
    }

    /**
     * draw the actionsignal if we are playing a single game
     * @param signal: number of the signal
     */
    private void DrawActionSignal(int signal)
    {
        int MAX_VERT_SIZE=8;
        int MAX_HORIZ_SIZE=15;
        String[][] space = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        Color color = Color.BACKGROUND_RED;
        String skill = new String();

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                space[i][j]=color + " " + Color.RESET;

        for (int i = 0; i < 9; i++) {
            space[0][3 + i] = color + "_" + Color.RESET;
            space[MAX_VERT_SIZE-2][3 + i] = color + "_" + Color.RESET;
        }

        putString("Action Signal", space, 7, 1);

        for (int i = 0; i < 2; i++) {
            space[i+1][2-i] = color + "/" + Color.RESET;
            space[6-i][i+12] = color + "/" + Color.RESET;
            space[i+1][12+i] = color + "\\" + Color.RESET;
            space[6-i][2-i] = color + "\\" + Color.RESET;
            space[3+i][0] = color + "|" + Color.RESET;
            space[3+i][14] = color + "|" + Color.RESET;
        }

        switch (signal) {
            case 1: skill = "Blue - 2";
                break;
            case 2: skill = "Green - 2";
                break;
            case 3: skill = "Purple - 2";
                break;
            case 4: skill = "Yellow - 2";
                break;
            case 5: skill = "Lorenzo + 2";
                break;
            case 6: skill = "Lorenzo + 2";
                break;
            case 7: skill = " & shuffle ";
                    putString(skill, space, 4, 2);
                    skill = "Lorenzo + 1";
                break;
        }
        putString(skill, space, 3, 2);

        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/2-1-17;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerboard[initialVert+i][initialHoriz+j]=space[i][j];
    }

    public void Print()
    {
        Print(playerboard, VERT_SIZE, HORIZ_SIZE);
    }
}
