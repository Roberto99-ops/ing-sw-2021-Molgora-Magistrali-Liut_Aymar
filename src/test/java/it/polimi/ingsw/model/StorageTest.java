package it.polimi.ingsw.model;

import it.polimi.ingsw.Server.GameHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class StorageTest {

    @Test

    public void checkStorage() {


        GameHandler observertested = new GameHandler();

        Storage box = new Storage();
        Storage scat = new Storage();


        box.setinStorage('B',0);
        box.setinStorage('P',1);
        box.setinStorage('P',2);
        box.setinStorage('W',3);
        box.setinStorage('W',4);
        box.setinStorage('W',5);
        box.deleteResources(1,'W');
        box.printPanel();

        scat.setinStorage('B',0);
        scat.setinStorage('P',1);
        scat.setinStorage('P',2);
        scat.setinStorage('P',3);
        scat.setinStorage('W',4);
        scat.setinStorage('W',5);
        scat.printPanel();

        assertEquals(5, box.getTotResourceStorage());
        assertEquals(6, scat.getTotResourceStorage());
        assertEquals(1, box.countTypeS('B'));
        assertEquals(2, box.countTypeS('P'));
        assertEquals(true, box.checkStorage());
        assertEquals(false, scat.checkStorage());



    }

}

