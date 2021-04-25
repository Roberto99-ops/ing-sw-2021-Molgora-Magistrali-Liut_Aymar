package it.polimi.ingsw.view.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerboardTest {

    @Test
    public void print() {
        Playerboard playerboard = new Playerboard();
        playerboard.Print();
    }
}