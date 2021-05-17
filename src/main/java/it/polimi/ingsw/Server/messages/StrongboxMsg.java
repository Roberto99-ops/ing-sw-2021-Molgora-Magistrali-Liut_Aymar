package it.polimi.ingsw.Server.messages;

import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.model.ResourceStructure;
import it.polimi.ingsw.model.StrongBox;

import java.io.*;
import java.util.ArrayList;

/**
 * this class pass trough the network ONLY the strongbox data
 * so the java.io.Serializable interface has no difficulty serializing it.
 */
public class StrongboxMsg extends NetworkMessage {
    private ResourceStructure strongBox;

    public StrongboxMsg(ResourceStructure strong)
    {
        this.strongBox=strong;
    }

    public ResourceStructure getStrongBox() {
        return strongBox;
    }

   /* private void readObject(ObjectInputStream serialized) throws ClassNotFoundException, IOException
    {
        serialized.defaultReadObject();
        // After this, you can handle transient fields or
        // special initialization that happens in the constructor
    }*/
}
