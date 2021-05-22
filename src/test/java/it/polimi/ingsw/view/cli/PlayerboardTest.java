package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.DevelopeCard;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ResourceStructure;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

public class PlayerboardTest {

    @Test
    public void print() throws FileNotFoundException {
        Player player = new Player();
        player.setName("Gianfrancioschio");
        player.setTrackposition(8);
        LeaderCard card1 = new LeaderCard();
        LeaderCard card2 = new LeaderCard();
        card1.setCard(10);
        player.getLeadercards().getStructure().add(card1);
        card2.setCard(15);
        player.getLeadercards().getStructure().add(card2);

        Character[] resources = {'P', 'Y', 'G', 'B'};
        Random mixer = new Random();
        for (int i = 0; i < 300; i++) {
            int mix = mixer.nextInt(4);
            player.addResourceStrongBox(resources[mix]);
        }

        ResourceStructure panel = new ResourceStructure();
        panel.add('P');
        panel.add('Y');
        panel.add('G');
        panel.add('B');
        panel.add('Y');
        panel.add('B');
        player.getStorage().setPanel(panel);

        player.getStorage().setTypeExtrapanel('Y');
        player.getStorage().getExtrapanel().add('P');
        player.getStorage().getExtrapanel().add('Y');

        for (int i = 0; i < 3; i++) {
            DevelopeCard Dcard1 = new DevelopeCard();
            DevelopeCard Dcard2 = new DevelopeCard();
            DevelopeCard Dcard3 = new DevelopeCard();
            Dcard1.setCard(i);
            Dcard2.setCard(i + 10);
            Dcard3.setCard(i + 20);
            player.getDSpace().getMinideck1().getStructure().add(Dcard1);
            player.getDSpace().getMinideck2().getStructure().add(Dcard2);
            player.getDSpace().getMinideck3().getStructure().add(Dcard3);
        }
        Playerboard playerboard = new Playerboard(player);
        playerboard.Print();

    }
}