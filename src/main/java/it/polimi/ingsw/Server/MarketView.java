package it.polimi.ingsw.Server;

import it.polimi.ingsw.model.Game;

/**
 * observ the Market in model returning the essential information about it
 */

public abstract class MarketView {

    public char[][] getMatrixView(Game game) {
        return game.getMarket().getMatrix();
    }

    public char getExtraball(Game game) {
        return game.getMarket().getExtraball();
    }

}
