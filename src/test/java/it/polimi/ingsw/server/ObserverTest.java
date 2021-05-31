package it.polimi.ingsw.server;

import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.DevelopeDecks;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Storage;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ObserverTest {



    @Test

    public void updateMarketTest () throws FileNotFoundException {

        Game game = new Game();
        ObserverGame observertested = new ObserverGame();
        game.getMarket().randomizeMarket();
        game.getMarket().printMatrix();
        game.getMarket().randomizeMarket();
        game.getMarket().printMatrix();
        observertested.updateMarket();


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

    public void updateDevelopementSpaceTest () throws FileNotFoundException {

        ObserverGame observertested = new ObserverGame();

        for (int i = 0; i < 2; i++) {

            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            observertested.getDSpace().setCard(card, 1);
            observertested.getDSpace().setCard(card, 2);
            observertested.getDSpace().setCard(card, 3);
            observertested.updateDevelopementSpace();

        }

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

    @Test

    public void updateDevelopementDecks () throws FileNotFoundException {

        ObserverGame observertested = new ObserverGame();
        Game game = new Game();
        DevelopeDecks deck = new DevelopeDecks();
        DevelopeDecks oldDeck = new DevelopeDecks();

        for(int i=0; i<5; i++)
        {
            DevelopeCard card = new DevelopeCard();
            card.setCard(i);
            deck.getStructure().add(card);

        }

        Game.setDevelopedecks(deck,1);

        Game.getDevelopedecks(1).Print();

        deck.setStructure(deck.shuffleDeck(deck.getStructure()));

        ObserverGame.updateDevelopementDecks();

        Game.getDevelopedecks(1).Print();

    }






}





