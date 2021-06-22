package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.GameHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;


public class Game implements Serializable {

    public static int lonely;
    private static ArrayList<GameHandler> players;
    private static int n_players;
    private static DevelopeDecks[] developedecks = new DevelopeDecks[12];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;
    private static int timer_VR=0;
    private static Player winner;



    /**
     * Game initialization
     */

    public Game()
    {
        players = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            developedecks[i] = new DevelopeDecks();
        }
        leaderdeck = new LeaderDeck();
        market = new Market();
        winner = new Player();
    }




    /**
     * getter and setter
     */

    public ArrayList<GameHandler> getPlayers() { return players; }
    public void setPlayers(ArrayList<GameHandler> players) { Game.players = players; }
    public static LeaderDeck getLeaderdeck() {
        return leaderdeck;
    }
    public static void setLeaderdeck(LeaderDeck leaderdeck) {
        Game.leaderdeck = leaderdeck;
    }
    public static int getLonely() { return lonely; }
    public static DevelopeDecks getDevelopedecks(int i) { return developedecks[i]; }
    public static Market getMarket() { return market; }
    public static void setDevelopedecks(DevelopeDecks developedeck, int i) { developedecks[i] = developedeck; }
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
    public static Player getWinner() {
        return winner;
    }
    public static void setWinner(Player winner) {
        Game.winner = winner;
    }
    public static int getN_players() {
        return n_players;
    }
    public static void setN_players(int num) {
        n_players=num;
    }
    public static int getVR() { return VR; }
    public static void setVR(int num) {
        VR=num;
        return;
    }




    /**
     * shuffle all the decks and the market calling the random function
     */


    public void shuffle() throws FileNotFoundException {

        for (int i = 0; i < 12; i++) {
            int k = i;
            DevelopeDecks deck = new DevelopeDecks();
            for (int j = 0; j < 4; j++) {
                DevelopeCard card = new DevelopeCard();

                int num = (i) + j*4;
                if(i >= 4)
                { num = (16+i%4) + j*4;
                    if(i >= 8)
                        num = (32+i%4) + j*4;
                }

                card.setCard(num);
                deck.getStructure().add(card);

            }

            if(i==0 || i==4 || i==8) k+=3;
            if (i==1 || i==5 || i==9) k -= 1;
            if (i==3 || i==7 || i==11) k -= 2;

            developedecks[k].setStructure(deck.getStructure());
        }


        for(int i=0; i<12; i++)
            developedecks[i].setStructure(developedecks[i].shuffleDeck(developedecks[i].getStructure()));


        for (int i = 0; i < 16; i++) {
            LeaderCard card = new LeaderCard();
            card.setCard(i);
            leaderdeck.getStructure().add(card);
        }

        leaderdeck.setStructure(leaderdeck.shuffleDeck(leaderdeck.getStructure()));


        market.setExtraball('B');
        market.setResourceinMarket(0,0,'B');
        market.setResourceinMarket(0,1,'G');
        market.setResourceinMarket(0,2,'G');
        market.setResourceinMarket(0,3,'P');
        market.setResourceinMarket(1,0,'P');
        market.setResourceinMarket(1,1,'R');
        market.setResourceinMarket(1,2,'Y');
        market.setResourceinMarket(1,3,'Y');
        market.setResourceinMarket(2,0,'W');
        market.setResourceinMarket(2,1,'W');
        market.setResourceinMarket(2,2,'W');
        market.setResourceinMarket(2,3,'W');

        market.randomizeMarket();
    }





    /**
     * check if the game is ended, checking the actualplayer faith track(1) and developequantity(2)
     * @return boolean
     */


    public boolean callEndgame(Player actualplayer) {
            //1)
            if(actualplayer.getTrackPosition()>=24)
                return true;

            //2)
            else if (actualplayer.getDevelopmentQuantity()>=7)
                return true;

            else
                return false;
    }




    /**
     * find the winner, for each player at 1) we check if the player is arrived at the end
     * of the faith track, at 2) we check which player has more Victory Points
     * @return string
     */


    public String callVictory()
    {
        winner.setPv(0);
        for(int i=0 ; i < players.size(); i++)
        {
            //1)
            if(players.get(i).getTrackPosition()>=24)
                return players.get(i).getName();

            //2)
            else if(winner.getPv() < players.get(i).getPv())
                winner.equals(players.get(i));

        }
        return winner.getName();
    }




    /**
     * this method give to the player 4 leadercards to choose
     * @return: the 4 cards
     */


    public LeaderDeck leaderChoice() {
        LeaderDeck deck = new LeaderDeck();
        for (int i = 0; i < 4; i++) {
            LeaderCard card;
            card = Game.getLeaderdeck().getStructure().get(0);
            deck.getStructure().add(card);
            Game.getLeaderdeck().getStructure().remove(0);
        }
        return deck;
    }
}
