package it.polimi.ingsw.server;

import it.polimi.ingsw.Server.ObserverGame;
import it.polimi.ingsw.model.Storage;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ObserverTest {

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


}
