package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LorenzoTest {

    /**
     * Tests if the number given to Lorenzo (character) is the one we gave to him
     */
    @Test
    public void setNumber() {
        Lorenzo lorenzo = new Lorenzo();
        int number=0;
        number++;
        lorenzo.setNumber(number);
        assertNotEquals(0,lorenzo.getNumber());
        lorenzo.setNumber(0);
    }

    /**
     * Tests if Lorenzo (character) moves correctly when one of these actions is called.
     * It also checks if ActionStructure has been shuffled when the player picks ActionSignal #7-> Lorenzo moves forward
     * by 1 + shuffle ActionStructure
     */
    @Test
    public void lorenzomoves() {
        Lorenzo lorenzo = new Lorenzo();
        lorenzo.forwardTwo();
        assertEquals(2,lorenzo.getNumber());
        lorenzo.forwardOne();
        assertEquals(3, lorenzo.getNumber());
        int[] arr = {1,2,3,4,5,6,7};
        assertNotEquals(arr, SingleGame.getActionStructure());
    }
}