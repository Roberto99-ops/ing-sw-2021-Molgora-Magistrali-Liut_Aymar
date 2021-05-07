package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ResourceStructure;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

public class PlayerboardTest {

    @Test
    public void print() throws FileNotFoundException {
        Player player = new Player();
        LeaderCard card1 = new LeaderCard();
        LeaderCard card2 = new LeaderCard();
        card1.setCard(10);
        player.getLeadercards().getStructure().add(card1);
        card2.setCard(15);
        player.getLeadercards().getStructure().add(card2);

        Character[] resources = {'P', 'Y', 'G', 'B'};
        Random mixer = new Random();
        for (int i = 0; i < 290; i++) {
            int mix = mixer.nextInt(4);
            player.addResourceStrongBox(resources[mix]);
        }
        ResourceStructure boh = player.getStrongBox().getStructure();
        Playerboard playerboard = new Playerboard(player);
        playerboard.Print();
        card2.setCard(9);
        //playerboard = new Playerboard(player);
        //playerboard.Print();
    }
}