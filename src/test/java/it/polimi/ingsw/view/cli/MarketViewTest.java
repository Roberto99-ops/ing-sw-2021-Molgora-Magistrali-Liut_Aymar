package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketViewTest {

    @Test
    public void print() {
        Market market = new Market();
        MarketView marketview = new MarketView(market);
        marketview.Print();
    }
}