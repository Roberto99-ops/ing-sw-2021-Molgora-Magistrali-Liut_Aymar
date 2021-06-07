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

    @Test

    public void countTypeS(){
        Player player = new Player();
        //creo uno storage fittizio, non importa se non rispetta le regole del gioco
        player.getStorage().getPanel().set(0,'B');
        player.getStorage().getPanel().set(1,'Y');
        player.getStorage().getPanel().set(2,'B');
        player.getStorage().getPanel().set(3,'G');
        player.getStorage().getPanel().set(4,'P');
        player.getStorage().getPanel().set(5,'G');
        player.getStorage().getExtrapanel().getVector().set(0,'P');
        player.getStorage().getExtrapanel().getVector().set(1,'P');


        assertEquals(2,player.getStorage().countTypeS('B'));
        assertEquals(player.getStorage().countTypeS('G'), player.getStorage().countTypeS('P')-1);

    }
}

