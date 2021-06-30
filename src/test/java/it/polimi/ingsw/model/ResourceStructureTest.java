package it.polimi.ingsw.model;

import junit.framework.TestCase;

public class ResourceStructureTest extends TestCase {


    public void testAddResource() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');

        for (int i = 0; i < 10; i++) assertTrue(structure.getVector().get(i)=='P');
    }

    public void testFirstOccurance() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');
        structure.addResource(3, 'Y');

        assertEquals(10, structure.firstOccurrence('Y'));
    }

    public void testRemove() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');
        structure.addResource(3, 'Y');

        structure.removeThis('Y');
        assertEquals(12, structure.getVector().size());
        for (int i = 0; i < 10; i++) assertTrue(structure.getVector().get(i)=='P');
        assertTrue(structure.getVector().get(10) == 'Y');
        assertTrue(structure.getVector().get(11) == 'Y');

    }
}