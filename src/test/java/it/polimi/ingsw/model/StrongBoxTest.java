package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class StrongBoxTest {

    @Test

    public void totalcheck() throws FileNotFoundException {

        StrongBox tex = new StrongBox();
        tex.setResource(2, 'B');
        tex.setResource(1, 'W');
        tex.setResource(5, 'Y');
        tex.setResource(3, 'P');
        tex.setResource(4, 'G');
        tex.setResource(1, 'Y');


        // B B W Y Y Y Y Y P P P G G G G Y = 16

        tex.printAll();
        assertEquals(16, tex.counterResource());
        assertEquals(true, tex.findResource(3,'P'));
        assertEquals(false, tex.findResource(1,'P'));
        assertEquals(2, tex.resourceNumber('B'));
        // tex.getTotResourceSB();
        assertEquals(1, tex.countTypeSB('W'));
        tex.deleteAllTypeResource('Y');
        tex.deleteAllTypeResource('G');
        tex.deleteSpecifiedResource(1, 'P');
        tex.deleteSpecifiedResource(2, 'B');
        tex.printAll();
        assertEquals(true, tex.findResource(2,'P'));
        assertEquals(false, tex.findResource(1,'P'));
        assertEquals(true, tex.findResource(0,'Y'));
        assertEquals(false, tex.findResource(1,'Y'));
        assertEquals(3, tex.counterResource());


    }
}
    // B B W Y Y Y Y Y P P P G G G G Y
    // W P P
    // w p p
