package it.polimi.ingsw.view.cli;

/**
 * These are all the colors are used for the CLI
 */

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[92m"),
    YELLOW("\u001B[93m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[95m"),
    GRAY("\u001B[37m"),
    LIGHT_BLUE("\u001B[96m"),
    WHITEonBLACK("\u001B[0m\u001B[97m\u001B[040m"),

    BACKGROUND_YELLOW("\u001B[0m\u001B[0m\u001B[103m"),
    BACKGROUND_CYAN("\u001B[0m\u001B[30m\u001B[106m"),
    BACKGROUND_GRAY("\u001B[0m\u001B[30m\u001B[047m"),
    STRONGBOX_COLOR("\u001B[0m\u001B[90m\u001B[104m"),
    BACKGROUND_GREEN("\u001B[0m\u001B[97m\u001B[042m"),
    BACKGROUND_BLUE("\u001B[0m\u001B[0m\u001B[044m"),
    BACKGROUND_RED("\u001B[0m\u001B[0m\u001B[041m"),
    BACKGROUND_WHITE("\u001B[0m\u001B[0m\u001B[107m"),
    BACKGROUND_PURPLE("\u001B[0m\u001B[97m\u001B[105m");

    static final String RESET = "\u001B[0m";
    static final String CLEAR = "\033[H\033[2J";


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
