package it.polimi.ingsw.model;
import java.util.ArrayList;


public class Game {
    private static ArrayList<Player> players;
    private static int n_players;
    private static DevelopeDecks developedecks[];  //dichiaro tutto static?
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;
    private static int timer_VR=0;

    public static int getTimer_VR() {
        return timer_VR;
    }

    public static void setTimer_VR(int timer_VR) {
        if (timer_VR>=3){
            VR++;
            setVR(VR);
            timer_VR=0;
        }
        Game.timer_VR = timer_VR;
    }

    public static int getN_players() {
        return n_players;
    }

    public static void setN_players() {
        n_players=players.size();
    }


    /**
     * Gets VaticanReport index
     * @return int VR
     */
    public static int getVR() {
        return VR;
    }

    /**
     * Sets VaticanReport index for all players when all players have ended their after the call of VaticanReport
     * @param timer_VR int
     */
    public static void setVR(int timer_VR) {

        if (timer_VR>=(n_players-1)) {  //finito il giro, aumento VR
            Game.VR++;
        }
        return;
    }


    /**
     * 1) shuffle all the decks
     * 2) add all the players
     * starts a loop that
     * 3) calls turn for every player and
     * 4) checks if the game is ended
     * @param args string
     */
    public static void main( String[] args )
    {
        Game game = new Game();
        developedecks = new DevelopeDecks[12];
        leaderdeck = new LeaderDeck();
        players = new ArrayList<Player>();
        Turn turn = new Turn();
        int actualplayer=0;
        //1)
        game.Shuffle();

        //2)
        //players.add()    ????

        setN_players();// mi setta la costante che riguarda il n_partecipanti
                       // questa costante verrà usata quando chiamo VaticanReport

        while(true) {
            //3)
            turn.setActualplayer(players.get(actualplayer));
            turn.main();

            //4)
            if (game.Endgame(players.get(actualplayer))) {
                System.out.println("The winner is " + game.Victory());
                return;
            }

            actualplayer++;
            if(actualplayer>=players.size()) actualplayer=0;
        }
    }

    /**
     * shuffle all the decks and the market calling the random function
     */
    public void Shuffle()
    {
        for(int i=0; i<12; i++)
            developedecks[i].setStructure(developedecks[i].setDeck(developedecks[i].getStructure()));

        leaderdeck.setStructure(leaderdeck.setDeck(leaderdeck.getStructure()));

        //market random
    }

    /**
     * check if the game is ended, checking the actualplayer faith track(1) and developequantity(2)
     * @return boolean
     */
    public boolean Endgame(Player actualplayer)
    {
            //1)
            if(actualplayer.getTrackposition()>=24)   return true;

            //2)
            if(actualplayer.getDevelopementquantity()>=7)   return true;

        return false;
    }

    /**
     * find the winner, for each player at 1)we check if the player is arrived at the end
     * of the faith track, at 2)we check wich player has more Victory Points
     * @return string
     */
    public String Victory()
    {
        Player winner = new Player();
        winner.setPv(0);
        for(int i=0 ; i<players.size(); i++)
        {
            //1)
            if(players.get(i).getTrackposition()>=24)   return players.get(i).getName();

            //2)
            if(winner.getPv() < players.get(i).getPv()) winner.equals(players.get(i));

        }
        return winner.getName();
    }
}
