package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActionSignalTest {

    @Test
    public void getNumber() {
        ActionSignal actionSignal= new ActionSignal();
        int num=5;
        actionSignal.setNumber(5);
        assertEquals(actionSignal.getNumber(),num);
    }

    @Test
    public void action() {

    }
}