package it.polimi.ingsw.model;

import junit.framework.TestCase;

import java.util.Random;

public class ResourceStructureTest extends TestCase {

    public void testCheckEquality() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');
        assertTrue(structure.checkEquality(structure.getVector()));

        Character[] resources = {'P', 'Y', 'G', 'B'};
        Random mixer = new Random();
        for (int i = 0; i < 20; i++) {
            int mix = mixer.nextInt(4);
            structure.addResource(1, resources[mix]);
        }
        assertFalse(structure.checkEquality(structure.getVector()));
    }

    public void testAddResource() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');

        for (int i = 0; i < 10; i++) assertTrue(structure.getVector().get(i)=='P');
    }

    public void testFirstOccurance() {
        ResourceStructure structure = new ResourceStructure();
        structure.addResource(10, 'P');
        structure.addResource(3, 'Y');

        assertEquals(10, structure.firstOccurance('Y'));
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