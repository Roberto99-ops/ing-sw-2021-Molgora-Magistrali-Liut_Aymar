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
    private StrongBox strongBox = new StrongBox();

    public StrongboxMsg(StrongBox strong)
    {
        this.strongBox = strong;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }

    public String available() {
        return "Available resources in your strongbox:\nRocks(G): "+strongBox.countTypeSB('G')+"\nShields(B): "+strongBox.countTypeSB('B')+"\nServants(P): "+strongBox.countTypeSB('P')+"\nCoins(Y): "+strongBox.countTypeSB('Y')+"\n";
    }

   /* private void readObject(ObjectInputStream serialized) throws ClassNotFoundException, IOException
    {
        serialized.defaultReadObject();
        // After this, you can handle transient fields or
        // special initialization that happens in the constructor
    }*/


}
