package it.polimi.ingsw.view.cli;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PlayerboardTest {

    @Test
    public void print() throws FileNotFoundException {
        Playerboard playerboard = new Playerboard();
        playerboard.Print();
    }
}