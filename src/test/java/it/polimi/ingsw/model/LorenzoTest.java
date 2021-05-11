package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LorenzoTest {

    /**
     * Tests if the number given to Lorenzo (character) is the one we gave to him
     */
    @Test
    public void setNumber() {
        int number=0;
        number++;
        Lorenzo.setNumber(number);
        assertNotEquals(0,Lorenzo.getNumber());
    }

    /**
     * Tests if Lorenzo (character) moves correctly when one of these actions is called.
     * It also checks if ActionStructure has been shuffled when the player picks ActionSignal #7-> Lorenzo moves forward
     * by 1 + shuffle ActionStructure
     */
    @Test
    public void lorenzomoves() {
        Lorenzo lorenzo = new Lorenzo();
        lorenzo.Lorenzomoves(2);
        assertEquals(2,lorenzo.getNumber());
        lorenzo.Lorenzomoves(1);
        assertEquals(3, lorenzo.getNumber());
        int[] arr = {1,2,3,4,5,6,7};
        assertNotEquals(arr, ActionStructure.getStructure());
    }
}