package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.ActionSignal;
import it.polimi.ingsw.model.FaithTrack;
import it.polimi.ingsw.model.*;

import java.io.IOException;

public class TurnManager extends Turn{

    private Player actualplayer;

    private Game game;
    private int l = game.getLonely();
    private ActionStructure aStructure;
    private ActionSignal signal;


    /**
     * turn "main", it manages all the turn.
     * 1)asks us what do we want to do
     * 2)in case we choose to active a production, we join a while that active all the production we want
     * 2.1)it lists us all our LeaderCards so we can choose to active one of that
     * 2.2) do the same with our Developecards
     * then the turn ends
     */
    public void main(Game game) {
        int action = 0;
        char again = 'n';
        int card = 0;

        //Ad ogni turno, effettuo il controllo del Vatican Report
        FaithTrack faithTrack = new FaithTrack();
        faithTrack.VaticanReport();

        //se sono in single game, ogni volta che tocca a me, prendo un segnalino ed eseguo la sua azione
        if (l == 1) {
            signal.Action(aStructure.PickSignal());
        }

        //1)
        System.out.println("What do you want to do?\n\t1)Shop a developement card\n\t2)Take resources at the market\n\t3)Active a production\n");
        try {
            action = System.in.read();
            if (action == 1) this.ShopCard(game);
            if (action == 2) this.Buyresource(game);
            //2)
            if(action == 3) { do{//2.1)
                System.out.println("Which LeaderCard do you want to enable(0=none)?\n");
                this.actualplayer.getLeadercards().Print();
                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                if(this.actualplayer.getLeadercards().getStructure().get(card-1).getSkill() == "ProductionSkill")
                    this.actualplayer.getLeadercards().getStructure().get(card-1).ProductionSkill(this.actualplayer);

                //2.2)
                System.out.println("Which DevelopeCard do you want to enable(0=none)?\n");
                this.actualplayer.getDevelopecards().Print(); //non so come il player accede alle proprie carte sviluppo
                try{ card = System.in.read(); } catch (IOException e){ System.out.println(e);}
                if(card>0) this.actualplayer.getDevelopecards().getStructure().get(card-1).DoProduction(this.actualplayer);

                System.out.println("Do you want to do another production(y/n)?\n");
                try{ again = (char) System.in.read(); } catch (IOException e){ System.out.println(e);} }while(again=='y');}
        } catch (IOException e){ System.out.println(e);} catch (Exception e) {
            e.printStackTrace();
        }


    }

}
