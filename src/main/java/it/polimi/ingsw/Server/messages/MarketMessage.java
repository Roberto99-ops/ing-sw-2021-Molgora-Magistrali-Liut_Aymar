package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.Storage;

public class MarketMessage extends NetworkMessage {

        private Market market;

        public void MarketMsg (Market mark) {  this.market = mark; }

        public Market getMarket() {
            return market;
        }

        /*

        implementation

         */


    }
