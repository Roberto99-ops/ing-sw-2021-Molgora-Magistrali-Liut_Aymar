package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.GameHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;


public class Game implements Serializable {

    public static int lonely;
    private static ArrayList<GameHandler> players;
    private static int n_players;
    private static DevelopDecks[] developDecks = new DevelopDecks[12];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR=0;
    private static int timer_VR=0;
    private static Player winner;



    /**
     * Game initialization: the DevelopDecks, the LeaderDecks, the Market and the Players are prepared
     */

    public Game()
    {
        players = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            developDecks[i] = new DevelopDecks();
        }
        leaderdeck = new LeaderDeck();
        market = new Market();
        winner = new Player();
    }




    /**
     * Getter and setter
     */

    public ArrayList<GameHandler> getPlayers() { return players; }
    public void setPlayers(ArrayList<GameHandler> players) { Game.players = players; }
    public static LeaderDeck getLeaderDeck() {
        return leaderdeck;
    }
    public static void setLeaderDeck(LeaderDeck leaderdeck) {
        Game.leaderdeck = leaderdeck;
    }
    public static int getLonely() { return lonely; }
    public static DevelopDecks getDevelopDecks(int i) { return developDecks[i]; }
    public static Market getMarket() { return market; }
    public static void setDevelopDecks(DevelopDecks developDeck, int i) { developDecks[i] = developDeck; }
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
     * Shuffles all the decks and the market, calling the random function
     */


    public void shuffle() throws FileNotFoundException {

        for (int i = 0; i < 12; i++) {
            int k = i;
            DevelopDecks deck = new DevelopDecks();
            for (int j = 0; j < 4; j++) {
                DevelopCard card = new DevelopCard();

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

            developDecks[k].setStructure(deck.getStructure());
        }


        for(int i=0; i<12; i++)
            developDecks[i].setStructure(developDecks[i].shuffleDeck(developDecks[i].getStructure()));


        for (int i = 0; i < 16; i++) {
            LeaderCard card = new LeaderCard();
            card.setCard(i);
            leaderdeck.getStructure().add(card);
        }

        leaderdeck.setStructure(leaderdeck.shuffleDeck(leaderdeck.getStructure()));


        market.setExtraBall('B');
        market.setResourceInMarket(0,0,'B');
        market.setResourceInMarket(0,1,'G');
        market.setResourceInMarket(0,2,'G');
        market.setResourceInMarket(0,3,'P');
        market.setResourceInMarket(1,0,'P');
        market.setResourceInMarket(1,1,'R');
        market.setResourceInMarket(1,2,'Y');
        market.setResourceInMarket(1,3,'Y');
        market.setResourceInMarket(2,0,'W');
        market.setResourceInMarket(2,1,'W');
        market.setResourceInMarket(2,2,'W');
        market.setResourceInMarket(2,3,'W');

        market.randomizeMarket();
    }





    /**
     * Checks if the game is ended, checking the actualPlayer faith track(1) and DevelopQuantity(2)
     * @return a boolean that tells the Game if someone has the requirements to end the game (position on the FaithTrack
     * or 7 DevelopCards on his/her PlayerBoard)
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
     * Finds the winner, for each player: 1) checks if the player is arrived at the end
     * of the faith track; 2) checks which player has more Victory Points
     * @return a string with the name of the Player who has won the Game
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
     * This method gives to the player 4 LeaderCards
     * @return a deck of 4 LeaderCards from which the player must choose 2 at the beginning of the Game/SingleGame
     */


    public LeaderDeck leaderChoice() {
        LeaderDeck deck = new LeaderDeck();
        for (int i = 0; i < 4; i++) {
            LeaderCard card;
            card = Game.getLeaderDeck().getStructure().get(0);
            deck.getStructure().add(card);
            Game.getLeaderDeck().getStructure().remove(0);
        }
        return deck;
    }
}
