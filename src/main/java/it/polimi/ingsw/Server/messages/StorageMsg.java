package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.model.Storage;

public class StorageMsg extends NetworkMessage{
    private Storage storage= new Storage();

    public StorageMsg(Storage storage){
        this.storage=storage;
    }

    public Storage getStorage() {
        return storage;
    }

    //secondo me (Juan), il metodo getTotResource può essere sostituito semplicemente da una somma dei valori di ritorno
    //di countTypeS

    /**
     * It sends a message with all the available resources
     * @return message as a string with all the available resources written
     */
    public String available() {
        return "Available resources in your storage:\nRocks(G): "+storage.countTypeS('G')+"\nShields(B): "+storage.countTypeS('B')+"\nServants(P): "+storage.countTypeS('P')+"\nCoins(Y): "+storage.countTypeS('Y')+"\n";
    }


}
