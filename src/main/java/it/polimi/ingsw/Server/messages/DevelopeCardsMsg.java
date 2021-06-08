package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.DevelopeDecks;

public class DevelopeCardsMsg extends NetworkMessage{

    private DevelopeDecks developeCards;

    public DevelopeCardsMsg(DevelopeDecks cards)
    {
        developeCards = cards;
    }

    public DevelopeDecks getCards()
        {
            return developeCards;
        }
}
