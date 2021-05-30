package it.polimi.ingsw.server;

import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Storage;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ObserverTest {



    @Test

    public void updateMarketTest () throws FileNotFoundException {

        /** Game game = new Game();
        ObserverGame observertested = new ObserverGame();
        game.getMarket().randomizeMarket();
        game.getMarket().printMatrix();
        game.getMarket().randomizeMarket();
        game.getMarket().printMatrix();
        observertested.updateMarket();
         */

    }



    @Test

    public void updateStorageTest () throws FileNotFoundException {

        ObserverGame observertested = new ObserverGame();


        observertested.getStorage().setinStorage('B',0);
        observertested.getStorage().setinStorage('P',1);
        observertested.getStorage().setinStorage('P',2);
        observertested.getStorage().setinStorage('W',3);
        observertested.getStorage().setinStorage('W',4);
        observertested.getStorage().setinStorage('W',5);
        observertested.getStorage().deleteResources(1,'W');
        observertested.getStorage().printPanel();

        observertested.updateStorage();

        observertested.getStorage().setinStorage('P',3);
        observertested.getStorage().setinStorage('W',4);
        observertested.getStorage().setinStorage('W',5);
        observertested.getStorage().printPanel();

        observertested.updateStorage();


    }



    @Test

    public void updateDevelopementDecksTest () throws FileNotFoundException {


    }



    @Test

    public void updateStrongBoxTest () throws FileNotFoundException {

        ObserverGame observertested = new ObserverGame();

        observertested.getStrongBox().setResource(2, 'B');
        observertested.getStrongBox().setResource(1, 'W');
        observertested.getStrongBox().setResource(5, 'Y');
        observertested.getStrongBox().setResource(3, 'P');
        observertested.getStrongBox().setResource(4, 'G');
        observertested.getStrongBox().setResource(1, 'Y');
        observertested.updateStrongbox();
        observertested.getStrongBox().setResource(5, 'B');
        observertested.updateStrongbox();

    }





}

