package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class StrongBoxTest {

    @Test

    public void totalcheck() throws FileNotFoundException {

        StrongBox tex = new StrongBox();

        tex.getStructure().getVector().add('B');
        tex.getStructure().getVector().add('B');
        tex.getStructure().getVector().add('B');
        tex.getStructure().getVector().add('B');

        tex.getStructure().getVector().add('G');
        tex.getStructure().getVector().add('G');
        tex.getStructure().getVector().add('G');

        tex.getStructure().getVector().add('Y');
        tex.getStructure().getVector().add('Y');
        tex.getStructure().getVector().add('Y');
        tex.getStructure().getVector().add('Y');

        tex.getStructure().getVector().add('P');
        tex.getStructure().getVector().add('P');
        tex.getStructure().getVector().add('P');

        tex.printAll();

        assertEquals(4, tex.countTypeSB('B'));
        assertEquals(0, tex.countTypeSB('R'));
        assertEquals(4, tex.countTypeSB('Y'));
        assertEquals(3, tex.countTypeSB('P'));
        assertEquals(3, tex.countTypeSB('G'));
        assertEquals(0, tex.countTypeSB('T'));
        // assertEquals(14, tex.getStructure().getVector().size());


        tex.getStructure().getVector().remove('P');
        tex.getStructure().getVector().remove('P');
        tex.getStructure().getVector().remove('G');
        tex.getStructure().getVector().add('Y');

        tex.printAll();

        assertEquals(1, tex.countTypeSB('P'));
        assertEquals(2, tex.countTypeSB('G'));
        assertEquals(5, tex.countTypeSB('Y'));
        assertEquals(4, tex.countTypeSB('B'));
        // assertEquals(12, tex.getStructure().getVector().size());



    }
}