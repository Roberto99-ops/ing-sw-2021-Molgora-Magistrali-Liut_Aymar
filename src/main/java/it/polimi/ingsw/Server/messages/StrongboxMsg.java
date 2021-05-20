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

    /**
     * Prepares the data the Server is going to send to the Client about the situation of StrongBox
     * @param strong the actual situation of StrongBox
     */
    public StrongboxMsg(ResourceStructure strong)
    {
        this.strongBox = strong;
    }

    /**
     * Sends the data of the situation of StrongBox
     * @return the data requested
     */
    public ResourceStructure getStrongBox() {
        return strongBox;
    }


}
