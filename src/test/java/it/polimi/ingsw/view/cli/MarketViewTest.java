package it.polimi.ingsw.view.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarketViewTest {

    @Test
    public void print() {
        MarketView marketview = new MarketView();
        marketview.Print();
    }
}