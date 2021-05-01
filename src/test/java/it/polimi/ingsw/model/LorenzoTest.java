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
    }
}