package it.polimi.ingsw.view.cli;

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[92m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[95m"),
    GRAY("\u001B[37m"),
    LIGHT_BLUE("\u001B[96m"),
    BLACK("\u001B[0m\u001B[97m\u001B[047m"), //scelta iniziale se usare una cli che riposa gli occhi oppure no?
    WHITE("\u001B[30m");

    static final String RESET = "\u001B[0m";


    private String escape;


    Color(String escape)
    {
        this.escape = escape;
    }


    public String getEscape()
    {
        return escape;
    }


    @Override
    public String toString()
    {
        return escape;
    }
}
