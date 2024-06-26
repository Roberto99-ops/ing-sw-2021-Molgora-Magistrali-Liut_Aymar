package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.Storage;

public class MarketMsg extends NetworkMessage {

        private Market market;

    /**
     * Prepares the data the Server is going to send to the Client about the actual situation of Market
     * @param mark the data requested (Market's actual status)
     */
    public MarketMsg (Market mark) {  this.market = mark; }

    /**
     * Sends the data of the Market
     * @return the data (Market's actual status)
     */
    public Market getMarket() {
        return market;
    }

    }
