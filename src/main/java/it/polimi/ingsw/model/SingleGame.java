package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Scanner;

public class SingleGame extends Game {
    //cosa ha:
    private static ArrayList<Player> players=new ArrayList<Player>(); //saranno due: il giocatore e Lorenzo
    private final static int n_players =2;
    private static DevelopeDecks developedecks[];
    private static LeaderDeck leaderdeck;
    private static Market market;
    private static int VR_SG=0;
    private static ActionStructure actionStructure = new ActionStructure();
    private Player winner = new Player();

    //private static int timer_VR_SG=0;   non utile perchè Lorenzo non guadagna punti se si trova in area Vatican Report

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        SingleGame.players = players;
    }

    public static int getN_players() {
        return n_players;
    }

    public static DevelopeDecks[] getDevelopedecks() {
        return developedecks;
    }

    public static void setDevelopedecks(DevelopeDecks[] developedecks) {
        SingleGame.developedecks = developedecks;
    }

    public static LeaderDeck getLeaderdeck() {
        return leaderdeck;
    }

    public static void setLeaderdeck(LeaderDeck leaderdeck) {
        SingleGame.leaderdeck = leaderdeck;
    }

    public static Market getMarket() {
        return market;
    }

    public static void setMarket(Market market) {
        SingleGame.market = market;
    }

    public static int getVR_SG() {
        return VR_SG;
    }

    public static void setVR_SG(int VR_SG) {
        SingleGame.VR_SG = VR_SG;
    }

    /*public static int getTimer_VR_SG() {
        return timer_VR_SG;
    }

    public static void setTimer_VR_SG(int timer_VR_SG) {
        SingleGame.timer_VR_SG = timer_VR_SG;
    }
    */

    /**
     * This is the main and it is executed at the beginnig of the game, if
     * the player decides to play alone
     * @throws Exception
     */
    public static void  main () throws Exception {
        SingleGame singleGame = new SingleGame();
        Scanner scan = new Scanner(System.in);
        //la modalità SingleGame è stata creata e attivata in Game-main quando il giocatore sceglie di giocare da solo
        //si crea il nuovo e solo giocatore
        Player you= new Player();
        players.add(you);
        you.setNumber(1);
        //si chiede il nome/username
        System.out.println("What's your name?");
        you.setName(scan.nextLine());
        //si aggiunge Lorenzo come secondo giocatore
        Lorenzo lore = new Lorenzo();
        Player LM = new Player();
        players.add(LM);
        LM.setName(lore.getName());
        //Si inizializza tutto:
        //- i segnalini azione (li mischio)
        ActionStructure.ShuffleSignal();
        //- i dodici mazzi di Development Cards
        developedecks = new DevelopeDecks[12];
        //- i LeaderDeck
        leaderdeck = new LeaderDeck();

        //creo l'oggetto turno
        Turn turn = new Turn();

        int actualplayer=0;


        while(true) {
            //ogni giocatore esegue il suo turno
            turn.setActualplayer(players.get(actualplayer));
            turn.main();

            if (singleGame.Endgame(players.get(actualplayer))) {
                System.out.println("The winner is " + singleGame.Victory());
                return;
            }

            actualplayer++;
            if(actualplayer>=2) actualplayer=0;
        }

    }

    /**
     * Overrides method 'Endgame' in Game class. It checks if the
     * requirements to call the end of the game are satisfied and sets
     * the winner's name.
     * @param actualplayer: the player playing in that turn (the player or Lorenzo)
     * @return true if the game ends. False if not.
     */
    @Override
    public boolean Endgame(Player actualplayer)
    {
        //riprende i casi descritti in Game.Endgame +
        //1)
        if(actualplayer.getTrackposition()>=24) {
            winner.setName(actualplayer.getName());
            return true; //vince o Lorenzo o tu
        }

        //2)
        if(actualplayer.getDevelopementquantity()>=7) {
            winner.setName(players.get(0).getName());
            return true; //vinci tu
        }
        // considero il caso in cui una colonna di carte non è più disponibile (vince Lorenzo)
        //3)
        if ((developedecks[0]==null&&developedecks[4]==null&&developedecks[8]==null)||(developedecks[1]==null&&developedecks[5]==null&&developedecks[9]==null)||
                (developedecks[2]==null&&developedecks[6]==null&&developedecks[10]==null)||(developedecks[3]==null&&developedecks[7]==null&&developedecks[11]==null)){
            winner.setName("Lorenzo il Magnifico");
            return true;
        }
        return false;

    }


    //volendo questo override potremmo toglierlo
    /**
     * Overrides method 'Victory' in Game class. It gets the winner's name
     * @return the winner's name
     */
    @Override
    public String Victory()
    {
        return winner.getName();
    }
}
