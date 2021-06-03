package it.polimi.ingsw.view.cli;

public enum Simbol {
    CALAMAIO("\u0238"), // u0444 u0460  u03A6
    CINQUE("\u168E"),
    MIRINO("\u1AA0"), //?
    IX("\u1CC1"),  //?
    GRECHINA("\u2021"),
    ARZIGOGOLO("\u2318"),
    BITCOIN("\u20BF"),
    FRECCIA_SU("\u2191"),
    FRECCIA_GIU("\u2193"),
    FRECCIA_DX("\u2192"),
    FRECCIA_SX("\u2190"),
    CLESSIDRA1("\u23F3"),
    CLESSIDRA2("\u231B"),
    OROLOGIO("\u231A"),
    TASTIERA("\u2328"),
    FACCIA_FELICE("\u263B"),
    FACCIA_TRISTE("\u2639"),
    SEGNALINO_PAPALE("\u2657"),  //265D
    PALLINO("\u25CF"),
    //CROCE("\u271E"),
    CROCE("✞"),
    QUADRATO("■");
    //SMALL_SPACE("\u2006");
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
