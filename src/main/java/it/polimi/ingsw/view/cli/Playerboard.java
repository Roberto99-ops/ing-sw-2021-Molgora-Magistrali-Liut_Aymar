package it.polimi.ingsw.view.cli;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.model.*;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class draws the PlayerBoard
 */
public class Playerboard extends PaintCards {
    private static final int VERT_SIZE = 45;
    private static final int HORIZ_SIZE = 150;
    //these variables are used to check if the player has activated the vatican reports.
    //they're set to 0 if the report isn't been activated yet,
    //to 1 if it's been activated and this player has activated the vatican report,
    //to 2 if it's been activated and this player hasn't activate the vatican report.
    private static int vatican1 = 0;
    private static int vatican2 = 0;
    private static int vatican3 = 0;


    private String[][] playerBoard;

    public Playerboard(Player player, Game game) throws FileNotFoundException {
        playerBoard = new String[VERT_SIZE][HORIZ_SIZE];
        Perimeter(playerBoard, VERT_SIZE, HORIZ_SIZE, Color.WHITEonBLACK);
        drawFaithTrack(player.getTrackPosition(), game);
        putString(player.getName(), playerBoard, 1, 70);
        drawStorage(player.getStorage());
        drawStrongbox(player.getStrongBox().getStructure().getVector());
        LeaderSpace(player);
        DevelopmentSpace(player.getDSpace());
        PointsSpace(game);
        if(game.getClass().equals(SingleGame.class))
            DrawActionSignal(SingleGame.getActionStructure().getAS_Counter());
    }

    /**
     * This class draws the faith track space
     * @param trackPosition: the position of the player on the track
     * @param game: the game the player is playing so we can know if it is a SingleGame or a Game
     * @throws FileNotFoundException if the info we want to draw is not available
     */
    private void drawFaithTrack(int trackPosition, Game game) throws FileNotFoundException
    {
        int MAX_HIGH = 13;
        //set to -1 so if I'm playing in multi player mode, it will not affect the FaithTrack
        int Lorenzo = -1;
        Color baseColor = Color.BACKGROUND_CYAN;

        if(game.getClass().equals(SingleGame.class))
            if(SingleGame.getLorenzo()!=null)
                Lorenzo = SingleGame.getLorenzo().getNumber();
            else
                Lorenzo=0;

        for(int i=1; i<MAX_HIGH; i++)
            for (int j = 1; j < HORIZ_SIZE-1; j++)
                playerBoard[i][j]=baseColor + " " + Color.RESET;

        drawVaticanCards(trackPosition);

        for (int i = 0; i < 25; i++) {
            int LeftHighCorner_VERT;
            int LeftHighCorner_HORIZ;
            int content;

            //It gets the FaithTrack's info from the Json File
            InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("/CellsPosition.json"));
            Object obj = JsonParser.parseReader(reader);
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
     * This class is called by the FaithTrack class and draws inside the FaithTrack's space
     * the properly faith track (its structure)
     * @param LeftHighCorner_VERT: is a number that defines the vertical position of them cell's right corner
     * @param LeftHighCorner_HORIZ: is a number that defines the horizontal position of them cell's right corner
     * @param content: is the content of the cell (usually a number but it could be the FaithMarker)
     * @param trackPosition: the position of the player on the track
     * @param Lorenzo: the position of Lorenzo on the track
     */

    private void drawSquare(int LeftHighCorner_VERT, int LeftHighCorner_HORIZ, int content, int trackPosition, int Lorenzo)
    {
        int SQUARE_HEIGHT = 4;
        int SQUARE_LENGTH = 7;
        String[][] square = new String[SQUARE_HEIGHT][SQUARE_LENGTH];

        for (int i = 0; i < SQUARE_HEIGHT; i++)
            for (int j = 0; j < SQUARE_LENGTH; j++)
                square[i][j]=" ";

        for (int i = 0; i < SQUARE_LENGTH; i++)
            square[0][i] = Color.RED + "═";
        for (int i = 0; i < SQUARE_LENGTH; i++)
            square[SQUARE_HEIGHT-1][i]=Color.RED + "═";

        for (int i = 0; i < SQUARE_HEIGHT; i++)
            square[i][0]=Color.RED + "║";
        for (int i = 0; i < SQUARE_HEIGHT; i++)
            square[i][SQUARE_LENGTH-1]=Color.RED + "║";

        square[0][0] = Color.RED + "╔" + Color.RESET;
        square[0][SQUARE_LENGTH-1] = Color.RED + "╗" + Color.RESET;
        square[SQUARE_HEIGHT-1][0] = Color.RED + "╚" + Color.RESET;
        square[SQUARE_HEIGHT-1][SQUARE_LENGTH-1] = Color.RED + "╝" + Color.RESET;

        if(content>9)
            square[(SQUARE_HEIGHT-1)/2][(SQUARE_LENGTH-1)/2+1]="";
        square[(SQUARE_HEIGHT-1)/2][(SQUARE_LENGTH-1)/2]=Color.YELLOW + String.valueOf(content).toString();


        if(content%3==0 && content!=0)
            for (int i = 1; i < SQUARE_HEIGHT-1; i++)
                for (int j = 1; j < SQUARE_LENGTH-1; j++)
                    square[i][j]=Color.BACKGROUND_YELLOW.getEscape() +  " " + Color.RESET;

        if(content%8==0 && content!=0){
            for (int i = 1; i < SQUARE_HEIGHT-1; i++)
                for (int j = 1; j < SQUARE_LENGTH-1; j++)
                    square[i][j] = Color.BACKGROUND_PURPLE + " " + Color.RESET;
            square[SQUARE_HEIGHT-1][(SQUARE_LENGTH-1)/2]=Color.PURPLE.getEscape() + Simbol.POPE_SYMBOL.getForm();
        }

        if(content==trackPosition) {
            square[1][(SQUARE_LENGTH - 1) / 2 - 1] = Color.PURPLE.getEscape() + Simbol.CROSS + Color.RESET;
            square[1][((SQUARE_LENGTH - 1)/2)] = " ";
            if(content > 9) square[1][((SQUARE_LENGTH - 1)/2) + 1] = " ";
        }

        if(content==Lorenzo) {
            square[2][(SQUARE_LENGTH - 1) / 2] = Color.WHITEonBLACK.getEscape() + Simbol.CROSS + Color.RESET;
            square[2][((SQUARE_LENGTH - 1)/2) + 1] = " ";
        }

        int inizialVert=9;
        int inizialHoriz=7;
        for (int i = 0; i < SQUARE_HEIGHT; i++)
            for (int j = 0; j < SQUARE_LENGTH; j++)
                playerBoard[inizialVert-SQUARE_HEIGHT*LeftHighCorner_VERT+i][inizialHoriz+SQUARE_LENGTH*LeftHighCorner_HORIZ+j]= square[i][j];
    }

    /**
     * This method draws the vatican cards and shows the player if they have been activated or not.
     * it uses the global variables vatican1, 2, 3 to set if we have activated them.
     * @param trackPosition: position of the player
     */
    private void drawVaticanCards(int trackPosition)
    {
        int MAX_VERT_SIZE = 7;
        int MAX_HORIZ_SIZE = 14;
        String[][] card1 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        String[][] card2 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        String[][] card3 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        int VR = Game.getVR();
        //this switch manages the drawing of the cards along the FaithTrack, using the VR variable in Game class
            switch (VR) {
                case 0:
                    card1 = drawVaticanCard(Color.BACKGROUND_RED, "2");
                    card2 = drawVaticanCard(Color.BACKGROUND_RED, "3");
                    card3 = drawVaticanCard(Color.BACKGROUND_RED, "4");
                    break;
                case 1:
                    //in case we have activated the first vatican report. We set vatican1 to 2
                    //so we won't activate it again in the future. Otherwise we set it to 1.
                    if(trackPosition>4 && vatican1!=2) {
                        vatican1 = 1;
                        card1 = drawVaticanCard(Color.BACKGROUND_GREEN, "2");
                    }
                    else
                        vatican1=2;

                    card2 = drawVaticanCard(Color.BACKGROUND_RED, "3");
                    card3 = drawVaticanCard(Color.BACKGROUND_RED, "4");
                    break;
                case 2:
                    if(vatican1 == 1)
                        card1 = drawVaticanCard(Color.BACKGROUND_GREEN, "2");

                    if(trackPosition>11 && vatican2!=2) {
                        vatican2 = 1;
                        card2 = drawVaticanCard(Color.BACKGROUND_GREEN, "3");
                    }
                    else
                        vatican2=2;

                    card3 = drawVaticanCard(Color.BACKGROUND_RED, "4");
                    break;
                case 3:
                    if(vatican1 == 1)
                        card1 = drawVaticanCard(Color.BACKGROUND_GREEN, "2");
                    if(vatican2 == 1)
                        card2 = drawVaticanCard(Color.BACKGROUND_GREEN, "3");

                    if(trackPosition>18 && vatican3!=2) {
                        vatican3 = 1;
                        card3 = drawVaticanCard(Color.BACKGROUND_GREEN, "4");
                    }
                    else
                        vatican3=2;
                    break;
            }

            //Draws the first card
        if(vatican1 != 2) {
            int initialVert = 4;
            int initialHoriz = 35;
            for (int i = 0; i < MAX_VERT_SIZE; i++)
                for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                    playerBoard[initialVert + i][initialHoriz + j] = card1[i][j];
        }

            //Draws the second card
        if(vatican2 != 2) {
            int initialVert = 2;
            int initialHoriz = 70;
            for (int i = 0; i < MAX_VERT_SIZE; i++)
                for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                    playerBoard[initialVert + i][initialHoriz + j] = card2[i][j];
        }

            //Draws the third card
        if(vatican3 != 2) {
            int initialVert = 4;
            int initialHoriz = 112;
            for (int i = 0; i < MAX_VERT_SIZE; i++)
                for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                    playerBoard[initialVert + i][initialHoriz + j] = card3[i][j];
        }
    }

    /**
     * It draws the storage space inside the PlayerBoard
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

        //here draws the storage's space
        putString("STORAGE", storage, 1, 16);
        for (int i = 0; i < 3; i++) storage[4][18+i] = Color.RESET + "_";
        for (int i = 0; i < 7; i++) storage[7][16+i] = Color.RESET + "_";
        for (int i = 0; i < 11; i++) storage[10][14+i] = Color.RESET + "_";
        
        //here draw the marbles in the storage
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

        //Draw the extraPanel if the player can/has one
        if(playerStorage.getTypeExtraPanel()!='Z') {
            putString("EXTRAPANEL("+playerStorage.getTypeExtraPanel()+")", storage, 15, 15);
            for (int i = 0; i < 7; i++) storage[17][16 + i] = Color.RESET + "_";
            int lung1 = playerStorage.getExtraPanel().getVector().size();
            if (lung1 > 0) {
                ArrayList<Character> panel = new ArrayList<>();
                panel.add(playerStorage.getExtraPanel().getVector().get(0));
                convertSymbols(panel, storage, 17, 17);
                if (lung1 > 1) {
                    panel.remove(0);
                    panel.add(playerStorage.getExtraPanel().getVector().get(1));
                    convertSymbols(panel, storage, 17, 21);
                }
            }
        }

        int initialVert=14;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerBoard[initialVert+i][1+j]=storage[i][j];
    }


    /**
     * It draws the strongbox space inside the PlayerBoard
     * @param box: the player's strongbox
     */
    private void drawStrongbox(ArrayList<Character> box)
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

            convertSymbols(resources, strongbox, j  , 1);
        }

        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerBoard[initialVert+i][1+j]=strongbox[i][j];
    }


    /**
     * It draws the space where the player can see his/her LeaderCards
     * @param player: the Player
     */
    private void LeaderSpace(Player player)
    {
        int MAX_VERT_SIZE = (VERT_SIZE-13-4)/2;
        int MAX_HORIZ_SIZE = 17;
        Color backgroundColor = Color.BACKGROUND_GRAY;
        String[][] leaderSpace1 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        String[][] leaderSpace2 = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
            {leaderSpace1[i][j]=backgroundColor + " " + Color.RESET;
                leaderSpace2[i][j]=backgroundColor + " " + Color.RESET;}

        String[][] card1 = new String[12][15];
        String[][] card2 = new String[12][15];
        if(player.getLeadercards().getStructure().size()>0) {
            drawLeaderCard(player.getLeadercards().getStructure().get(0), card1, 0, 0, player.getSkill1());
            if(player.getLeadercards().getStructure().size()==2)
                drawLeaderCard(player.getLeadercards().getStructure().get(1), card2, 0, 0, player.getSkill2());
            else
                drawLeaderCard(null, card2, 0, 0, 0);
        }
        else
        { drawLeaderCard(null, card1, 0, 0, 0);
            drawLeaderCard(null, card2, 0, 0, 0);}

        for (int i = 0; i < MAX_VERT_SIZE-2; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE-2; j++) {
                leaderSpace1[i + 1][j + 1] = card1[i][j];
                leaderSpace2[i + 1][j + 1] = card2[i][j];
            }


        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/4+2;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++) {
                playerBoard[initialVert + i][initialHoriz + j] = leaderSpace1[i][j];
                playerBoard[initialVert + i + MAX_VERT_SIZE + 2][initialHoriz + j] = leaderSpace2[i][j];
            }
    }

    /**
     * It draws the development space inside the playerBoard
     * @param DSpace: DSpace of the player
     */
    private void DevelopmentSpace(DevelopmentSpace DSpace)
    {
        int MAX_VERT_SIZE = VERT_SIZE-13-2;
        int MAX_HORIZ_SIZE = HORIZ_SIZE/2+17;
        Color backgroundColor = Color.BACKGROUND_GREEN;
        String[][] developmentSpace = new String[MAX_VERT_SIZE][MAX_HORIZ_SIZE];
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                developmentSpace[i][j]=backgroundColor + " " + Color.RESET;

        putString("DEVELOPMENT SPACE", developmentSpace, 2, 39);

        //draw the first miniDeck (the first DevelopDeck starting from the left)
        int minideck1Leng = DSpace.getMiniDeck1().getStructure().size();
        if(minideck1Leng > 0) {
            drawDevelopCard(DSpace.getMiniDeck1().getStructure().get(0), developmentSpace, 14, 10);
            if(minideck1Leng > 1) {
                drawDevelopCard(DSpace.getMiniDeck1().getStructure().get(1), developmentSpace, 12, 10);
                if(minideck1Leng > 2)
                    drawDevelopCard(DSpace.getMiniDeck1().getStructure().get(2), developmentSpace, 10, 10);
            }
        }

        //draw the second miniDeck (the DevelopDeck that is in the centre of the DevelopmentSpace)
        int minideck2Leng = DSpace.getMiniDeck2().getStructure().size();
        if(minideck2Leng > 0) {
            drawDevelopCard(DSpace.getMiniDeck2().getStructure().get(0), developmentSpace, 14, 40);
            if(minideck2Leng > 1) {
                drawDevelopCard(DSpace.getMiniDeck2().getStructure().get(1), developmentSpace, 12, 40);
                if(minideck2Leng > 2)
                    drawDevelopCard(DSpace.getMiniDeck2().getStructure().get(2), developmentSpace, 10, 40);
            }
        }

        //draw the third miniDeck (the last DevelopmentDeck)
        int minideck3Leng = DSpace.getMiniDeck3().getStructure().size();
        if(minideck3Leng > 0) {
            drawDevelopCard(DSpace.getMiniDeck3().getStructure().get(0), developmentSpace, 14, 70);
            if(minideck3Leng > 1) {
                drawDevelopCard(DSpace.getMiniDeck3().getStructure().get(1), developmentSpace, 12, 70);
                if(minideck3Leng > 2)
                    drawDevelopCard(DSpace.getMiniDeck3().getStructure().get(2), developmentSpace, 10, 70);
            }
        }

        int initialVert=14;
        int initialHoriz=HORIZ_SIZE/2-1-17;
        for (int i = 0; i < MAX_VERT_SIZE; i++)
            for (int j = 0; j < MAX_HORIZ_SIZE; j++)
                playerBoard[initialVert+i][initialHoriz+j]=developmentSpace[i][j];
    }

    /**
     * It signs the points of the players
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
                playerBoard[initialVert+i][initialHoriz+j]=space[i][j];
    }

    /**
     * It draws the actionSignal, if we are playing a single game
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
                playerBoard[initialVert+i][initialHoriz+j]=space[i][j];
    }

    public void Print()
    {
        Print(playerBoard, VERT_SIZE, HORIZ_SIZE);
    }
}
