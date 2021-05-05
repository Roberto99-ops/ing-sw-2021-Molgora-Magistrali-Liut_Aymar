package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Player;
import org.junit.Test;

import java.io.FileNotFoundException;

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
        Playerboard playerboard = new Playerboard(player);
        playerboard.Print();
    }
}