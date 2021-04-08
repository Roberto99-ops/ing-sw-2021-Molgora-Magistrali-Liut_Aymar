package it.polimi.ingsw;
import java.util.ArrayList;

public class Game {
    private static ArrayList<Player> players;
    private static DevelopeDecks developedecks[];  //dichiaro tutto static?
    private static LeaderDeck leaderdeck;
    private static Market market = new Market();

    /**
     * 1) shuffle all the decks
     * 2) add all the players
     * starts a loop that
     * 3) calls turn for every player and
     * 4) checks if the game is ended
     * @param args
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
     * @return
     */
    public boolean Endgame(Player actualplayer)
    {
        Player player = new Player();
            //1)
            if(actualplayer.getTrackposition()==21)   return true;

            //2)
            if(actualplayer.getDevelopementquantity()>=7)   return true;

        return false;
    }

    /**
     * find the winner, for each player at 1)we check if the player is arrived at the end
     * of the faith track, at 2)we check wich player has more Victory Points
     * @return
     */
    public String Victory()
    {
        Player winner = new Player();
        winner.setPv(0);
        for(int i=0 ; i<players.size(); i++)
        {
            //1)
            if(players.get(i).getTrackposition()==21)   return players.get(i).getName();

            //2)
            if(winner.getPv() < players.get(i).getPv()) winner.equals(players.get(i));

        }
        return winner.getName();
    }
}
