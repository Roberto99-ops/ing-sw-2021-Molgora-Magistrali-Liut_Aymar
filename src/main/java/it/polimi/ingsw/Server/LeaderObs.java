package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;


/**
 * observ LeaderDecks and Leadercards in model and return the essential information about it
 * also return all the specifics about a card
 */

public abstract class LeaderObs {

        // leggo in input il leaderdeck

        public LeaderDeck getDecksinGameView () { // leggo in input il deck che voglio
            return Game.getLeaderdeck();
        }

        // perchè è giusto così e non se aggiungo .getstructure???


        public int getCardlevelView (LeaderCard card) {
            return card.getCardLevel();
        }


        public int getCardPvView (LeaderCard card) {
            return card.getPv();
        }


        public int getCardNumberView (LeaderCard card) {
            return card.getNumber();
        }


        public ResourceStructure getCardPriceRView (LeaderCard card) {
            return card.getPriceR();
        }

        public ArrayList<Character> getCardPriceCView (LeaderCard card) {
            return card.getPriceC();
        }

        public String getCardSkill (LeaderCard card) {
            return card.getSkill();
        }

        public char getInputSkill (LeaderCard card) {
            return card.getInputskill();
        }

    }


