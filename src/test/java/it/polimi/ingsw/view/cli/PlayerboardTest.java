package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Server.GameHandler;
import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;

public class PlayerboardTest {

    @Test
    public void print() throws FileNotFoundException {
        GameHandler player = new GameHandler();
        player.setName("Gianfrancioschio");
        player.setTrackPosition(8);
        LeaderCard card1 = new LeaderCard();
        LeaderCard card2 = new LeaderCard();
        card1.setCard(10);
        player.getLeadercards().getStructure().add(card1);
        card2.setCard(15);
        player.getLeadercards().getStructure().add(card2);

        Character[] resources = {'P', 'Y', 'G', 'B'};
        Random mixer = new Random();
        for (int i = 0; i < 200; i++) {
            int mix = mixer.nextInt(4);
            player.getStrongBox().getStructure().addResource(1, resources[mix]);
        }

        ResourceStructure panel = new ResourceStructure();
        panel.add('P');
        panel.add('Y');
        panel.add('G');
        panel.add('B');
        panel.add('Y');
        panel.add('B');
        player.getStorage().setPanel(panel);

        player.getStorage().setTypeExtraPanel('Y');
        player.getStorage().getExtraPanel().getVector().set(0, 'P');
        player.getStorage().getExtraPanel().getVector().set(1, 'Y');

        for (int i = 0; i < 3; i++) {
            DevelopCard Dcard1 = new DevelopCard();
            DevelopCard Dcard2 = new DevelopCard();
            DevelopCard Dcard3 = new DevelopCard();
            Dcard1.setCard(i);
            Dcard2.setCard(i + 10);
            Dcard3.setCard(i + 20);
            player.getDSpace().getMiniDeck1().getStructure().add(Dcard1);
            player.getDSpace().getMiniDeck2().getStructure().add(Dcard2);
            player.getDSpace().getMiniDeck3().getStructure().add(Dcard3);
        }
        SingleGame game = new SingleGame();
        SingleGame.getLorenzo().setNumber(10);
        for (int i = 0; i < 4; i++) {
            GameHandler person = new GameHandler();
            person.setName("RICARDOMARTINEZ");
            person.setPv(100);
            game.getPlayers().add(person);
        }

        SingleGame.setVR(1);
        player.setTrackPosition(4);
        SingleGame.getActionStructure().shuffleSignal();
        Playerboard playerboard = new Playerboard(player, game);
        playerboard.Print();

        SingleGame.setVR(2);
        player.setTrackPosition(15);
        Playerboard rick = new Playerboard(player, game);
        rick.Print();

        SingleGame.setVR(3);
        player.setTrackPosition(19);
        Playerboard morty = new Playerboard(player, game);
        morty.Print();

    }
}