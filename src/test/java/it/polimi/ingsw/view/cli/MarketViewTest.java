package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MarketViewTest {

    @Test
    public void print() {
        Market market = new Market();

        Character[] resources = {'P', 'Y', 'G', 'B', 'W'};
        Random mixer = new Random();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++) {
                int mix = mixer.nextInt(5);
                market.getMatrix()[i][j] = resources[mix];
            }

        int mix = mixer.nextInt(5);
        market.setExtraball(resources[mix]);

        MarketView marketview = new MarketView(market);

        marketview.Print();
    }
}