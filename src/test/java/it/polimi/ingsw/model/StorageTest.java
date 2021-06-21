package it.polimi.ingsw.model;

import it.polimi.ingsw.Server.GameHandler;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StorageTest {

    @Test

    public void switchResourceTest() {

        Game game = new Game();
        Player player = new Player();
        Storage box = new Storage();
        ArrayList <Character> obj = new ArrayList<>();

        box.getPanel().set(0,'B');
        box.getPanel().set(1,'P');
        box.getPanel().set(3,'Y');


        for(int i = 0; i < 6; i++) {
            obj.add('N');
        }


        obj.set(0,'B');
        obj.set(1,'P');
        obj.set(3,'Y');
        box.printPanel();
        assertEquals(obj, box.getPanel());

        box.switchresources('B',0);
        box.printPanel();
        obj.set(0, 'P');
        obj.set(1, 'B');
        obj.set(2, 'B');
        assertEquals(obj, box.getPanel());

        box.switchresources('B',2);
        box.printPanel();
        obj.set(1, 'Y');
        obj.set(2, 'N');
        obj.set(3, 'B');
        obj.set(4, 'B');
        obj.set(5, 'B');
        assertEquals(obj, box.getPanel());


        box.switchresources('P',0);
        box.printPanel();
        obj.set(0, 'Y');
        obj.set(1, 'P');
        obj.set(2, 'P');
        assertEquals(obj, box.getPanel());
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

