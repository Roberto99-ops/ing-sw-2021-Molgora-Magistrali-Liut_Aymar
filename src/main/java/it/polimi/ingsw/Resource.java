package it.polimi.ingsw;

public class Resource {
    private enum type {G, B, P, Y, R, W};
    private type colour;

    public type getColour() {
        return colour;
    }
}
