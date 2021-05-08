package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LorenzoTest {

    @Test
    public void setNumber() {
        int number=0;
        number++;
        Lorenzo.setNumber(number);
        assertNotEquals(0,Lorenzo.getNumber());
    }

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