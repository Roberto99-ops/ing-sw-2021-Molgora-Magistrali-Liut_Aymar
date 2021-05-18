package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.ResourceStructure;
import it.polimi.ingsw.model.Storage;

public class StorageMessage extends NetworkMessage {

    private Storage storage;

    public void StorageMsg (Storage stor) {  this.storage = stor; }

    public Storage getStorage() {
        return storage;
    }

    /*
    implementation
     */

}
