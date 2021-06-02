package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.*;

import java.util.Scanner;

public class TurnMsg extends NetworkMessage {

    /*private Turn turn = new Turn();
    private Game game = new Game();
    private int choice;

    /**
     * Prepares the data the Server is going to send to the Client about the current Turn
     * @param turn the data requested
     */
    //public TurnMsg(Turn turn){this.turn=turn;}

    /**
     * Sends the data of the current Turn
     * @return the data requested
     */
    /*public Turn getTurn(){
        return turn;
    }


    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void AskChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?\n(1)Take Resources from the Market\n(2)Buy one Development Card\n(3)Activate the Production");
        //L'utente sceglie, mandando un messaggio al server. Questo poi controlla se l'azione si può effettuare
        //choice=scanner.nextInt();
    }

    /*cosa deve notificare?
    -chiede quale azione bisogna fare
        - prendere dal mercato
        - acquisto carta sviluppo
        -attivare la produzione
     */

   /* private void Choice(int choice) throws Exception {
        if (choice == 1) {
            //prendo risorse dal market
            //manda market all'utente così può scegliere la riga/col
            MarketMsg();
            //l'utente manderà poi la riga o la col scelta e infine riceverà messaggio con esito
        } else if (choice == 2) {
            //compro una carta sviluppo
            CardBought();
        } else if (choice == 3) {
            //attivare la produzione
            DevelopementSpaceMsg();
            //il client poi dice quale carta sviluppo vuole attivare
            //si aspetta una risposta
            //poi manda via mex il risultato (Production())
        }
    }

    /**
     * Sends the client the cards at the top of each minideck, so the user can choose which one he/she wants to use
     * @return a Develop Deck with all the cards from the minidecks
     */
   /* private DevelopeDecks DevelopementSpaceMsg(){
        DevelopeDecks topDevelopementSpace = new DevelopeDecks();
        //creo una struttura di carte con quelle in cima al DSpace
        topDevelopementSpace.getStructure().add(turn.getActualplayer().getDSpace().getMinideck1().getStructure().get(0));
        topDevelopementSpace.getStructure().add(turn.getActualplayer().getDSpace().getMinideck2().getStructure().get(0));
        topDevelopementSpace.getStructure().add(turn.getActualplayer().getDSpace().getMinideck3().getStructure().get(0));
        //mando la struttura così il client può scegliere
        return topDevelopementSpace;
        //poi bisogna apettare la risposta del client
    }


    /**
     * Starts the production described on Develop Cards, after the server has received the choice from the client
     * @param topCard is the card choosen from the Development Space by the user
     * @return a string that tells the user the result of the prduction
     */
   /* private String Production(int topCard){
        DevelopeCard developeCard = new DevelopeCard();

        if (topCard==0){
            developeCard = turn.getActualplayer().getDSpace().getMinideck1().getStructure().get(0);
            //produco con la carta 1
        }else if (topCard==1){
            // con la 2
            developeCard = turn.getActualplayer().getDSpace().getMinideck2().getStructure().get(0);
        }else if (topCard==2){
            // con la 3
            developeCard = turn.getActualplayer().getDSpace().getMinideck3().getStructure().get(0);
        }
        if (developeCard==null){
            return "That card doesn't exist!";
        }
        if (developeCard.DoProduction(turn.getActualplayer())==1){
            return "The production has been completed!";
        } else {
            return "The production has failed";
        }

    }

    /**
     * Sends the client a copy of the actual situation of the Market
     * @return the copy of Market
     */
    /*private Market MarketMsg() {
        return game.getMarket();
    }

    /**
     * Notifies the player the specs of the bought new card
     * @return a string that tells the client if a card has been bought (specifying its specs) or not
     * @throws Exception
     */
    /*private String CardBought() throws Exception {
        //compro una carta sviluppo
        if (turn.ShopCard(game) == 0) {
            //non ho acquistato niente
            return "The card cannot be bought";
        } else if (turn.ShopCard(game) == 1) {
            int i = turn.getActualplayer().getDevelopecards().getStructure().size();
            //ho acquistato la carta
            return "Your new card is:\n" + "Level: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getLevel() + "\nColour: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getColour() + "\nPV: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getPv() + "\nCost: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getCost() + "\nInput production: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getInputproduction() + "\nOutput: " + turn.getActualplayer().getDevelopecards().getStructure().get(i).getOutputproduction();
        }
        return "Error";
    }

*/
}