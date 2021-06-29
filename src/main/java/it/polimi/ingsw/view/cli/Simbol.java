package it.polimi.ingsw.view.cli;

public enum Simbol {
    POPE_SYMBOL("\u2657"),  //265D
    CIRCLE("\u25CF"),
    CROSS("✞"),
    VATICAN_CARD("*");
    private String form;

    Simbol(String form)
    {
        this.form = form;
    }


    public String getForm()
    {
        return form;
    }

    @Override
    public String toString()
    {
        return form;
    }
}
