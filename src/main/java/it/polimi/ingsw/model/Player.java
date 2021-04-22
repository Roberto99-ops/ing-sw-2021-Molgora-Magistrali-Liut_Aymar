package it.polimi.ingsw.model;
import java.lang.String;

public class Player {

    //ha bisogno di istanze di tutta la plancia
    private Storage storage = new Storage();
    private StrongBox SBox = new StrongBox();
    private DevelopementSpace DSpace = new DevelopementSpace();


    private String name;  //forse va istanziato
    private int number;
    private int developementquantity;
    private int pv;
    private int trackposition;
    private LeaderDeck leadercards;  //e pure questo
    //ogni player deve avere come attributi anche gli elementi della plancia credo

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public StrongBox getSBox() {
        return SBox;
    }

    public void setSBox(StrongBox SBox) {
        this.SBox = SBox;
    }
    public DevelopementSpace getDSpace() {
        return DSpace;
    }

    public void setDSpace(DevelopementSpace DSpace) {
        this.DSpace = DSpace;
    }

    public int getTrackposition() { return trackposition; }

    public LeaderDeck getLeadercards() { return leadercards; }

    public int getDevelopementquantity() {
        return developementquantity;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Counts the quantity of developement cards that this player owns
     */
    public void increaseDevelopQuantity() { this.developementquantity = developementquantity+1; }

    public DevelopeDecks getDevelopecards()
    {//deve "intreccarsi" con la plancia in modo da restituire
        //la lista delle carte sviluppo in cima ai mazzetti, forse servono solo quelli
        //della prima fila in quanto la produzioni si fanno solo
        //con le carte in cima ai mazzetti
        return null;
    }

    public ResourceStructure getResources()
    {
        //stesso discorso della plancia anche qui,
        //deve restituire le risorse totali, servono per attivare
        //le produzioni
        return null;
    }

    public ResourceStructure removeResource(char resource)
    {
        //deve rimuovere quest risorsa dalla plancia, quindi probabilmente
        //dovrebbero esserci dei metodi dentro agli elementi della plancia
        //se non ci sono già
        return null;
    }

    public ResourceStructure addResource(char resource)
    {
        //deve aggiungere questa risorsa alla plancia, stesso discorso
        return null;
    }
    //quindi servono tutti i getter getStrongbox ecc...
}
