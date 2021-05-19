package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    public static int lonely;
    private static ArrayList<Player> players;
    private static int n_players;
    private static DevelopeDecks[] developedecks = new DevelopeDecks[12];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;
    private static int timer_VR=0;


    /**
     * getter and setter
     * @return
     */

    public ArrayList<Player> getPlayers() {

        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {

        Game.players = players;
    }

    public static LeaderDeck getLeaderdeck() {
        return leaderdeck;
    }

    public void setLeaderdeck(LeaderDeck leaderdeck) {
        Game.leaderdeck = leaderdeck;
    }


    public static int getLonely() {

        return lonely;
    }

    public static DevelopeDecks getDevelopedecks(int i) {

        return developedecks[i];
    }

    public Market getMarket() {

        return market;
    }

    public static void setDevelopedecks(DevelopeDecks developedecks, int i) {

        Game.developedecks[i] = developedecks;
    }

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


    public static void setN_players(int num) {
        n_players=num;
    }


    public static int getVR() {

        return VR;
    }


    public static void setVR(int num) {
        VR=num;
        return;
    }



    /**
     * shuffle all the decks and the market calling the random function
     */

    public void Shuffle()
    {
        for(int i=0; i<12; i++)
            developedecks[i].setStructure(developedecks[i].shuffleDeck(developedecks[i].getStructure()));

        leaderdeck.setStructure(leaderdeck.shuffleDeck(leaderdeck.getStructure()));

        market.randomizeMarket();
    }


    /**
     * check if the game is ended, checking the actualplayer faith track(1) and developequantity(2)
     * @return boolean
     */

    public boolean Endgame(Player actualplayer)
    {
            //1)
            if(actualplayer.getTrackposition()>=24)
                return true;

            //2)
            else if(actualplayer.getDevelopementquantity()>=7)
                return true;

            else
                return false;
    }


    /**
     * find the winner, for each player at 1) we check if the player is arrived at the end
     * of the faith track, at 2) we check which player has more Victory Points
     * @return string
     */


    public String Victory()
    {
        Player winner = new Player();
        winner.setPv(0);
        for(int i=0 ; i < players.size(); i++)
        {
            //1)
            if(players.get(i).getTrackposition()>=24)
                return players.get(i).getName();

            //2)
            else if(winner.getPv() < players.get(i).getPv())
                winner.equals(players.get(i));

        }
        return winner.getName();
    }
}
