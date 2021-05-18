package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ResourceStructure;

public class DevelopementView {
    /**
     * observ Game in model and returning the essential information about it
     * also return all the specifics about a card
     */

    public abstract class DevelopementDecksView {

        // leggo in input il deck che voglio

        public DevelopeDecks getDecksinGameView (int k) { // leggo in input il deck che voglio
            return Game.getDevelopedecks(k);
        }


        public int getCardlevelView (DevelopeCard card) {
            return card.getLevel();
        }


        public char getCardcolourView (DevelopeCard card) {
            return card.getColour();
        }


        public int getCardpvView (DevelopeCard card) {
            return card.getPv();
        }


        public ResourceStructure getCardCostView (DevelopeCard card) {
            return card.getCost();
        }

        public ResourceStructure getCardOutputproductionView (DevelopeCard card) {
            return card.getInputproduction();
        }

        public ResourceStructure getCardImputproductionView (DevelopeCard card) {
            return card.getOutputproduction();
        }


    }

}
