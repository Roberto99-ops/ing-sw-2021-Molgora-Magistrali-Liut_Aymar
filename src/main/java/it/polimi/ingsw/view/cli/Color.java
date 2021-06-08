package it.polimi.ingsw.view.cli;

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[92m"),
    YELLOW("\u001B[93m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[95m"),
    GRAY("\u001B[37m"),
    LIGHT_BLUE("\u001B[96m"),
    BLACK("\u001B[0m\u001B[97m\u001B[040m"), //scelta iniziale se usare una cli che riposa gli occhi oppure no?
    WHITE("\u001B[30m"),

    BACKGROUND_YELLOW("\u001B[0m\u001B[0m\u001B[103m"),
    BACKGROUND_CYAN("\u001B[0m\u001B[97m\u001B[106m"),
    BACKGROUND_GRAY("\u001B[0m\u001B[30m\u001B[047m"),
    STRONGBOX_COLOR("\u001B[0m\u001B[90m\u001B[104m"),
    BACKGROUND_GREEN("\u001B[0m\u001B[0m\u001B[042m"),
    BACKGROUND_BLUE("\u001B[0m\u001B[0m\u001B[044m"),
    BACKGROUND_RED("\u001B[0m\u001B[0m\u001B[041m"),
    BACKGROUND_PURPLE("\u001B[0m\u001B[97m\u001B[105m");

    static final String RESET = "\u001B[0m";
    static final String CLEAR = "\033[H\033[2J";

    static final void CLEAR()
    {
        for(int i=0; i<30; i++) {
            System.out.println("\n");
        }
    }


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
