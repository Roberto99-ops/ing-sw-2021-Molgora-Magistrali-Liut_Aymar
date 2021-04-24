package it.polimi.ingsw.model;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

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



    //chiedo quale risorsa vuole eliminare
    //System.out.println ("Which type of resource would you like to remove from your Storage:? W,R,B,G,P,Y");
    //try {choice = (char)System.in.read();} catch (IOException e) {
    //    System.out.println("Choice not available");
    //}

    /**
     * Removes one resource from the storage
     * @param resource: da eliminare
     */
    public void removeResource(char resource) {
        int i;
        //char choice = 'W';

        for (i=storage.size()-1; i>=-1 ;i--) {
            if (resource==(char)storage.get(i) || i==-1){
                break;
            }
        } //salto con l'indice i che mi indica la posizione
        if (i==-1){
            //caso in cui non c'è la risorsa richiesta da eliminare
            System.out.println("La risorsa richiesta non c'è nel magazzino.");
            return ;
        }
        storage.remove(i);//rimuovo la risorsa i-esima
    }

    public ResourceStructure addResource(char resource)
    {
        //deve aggiungere questa risorsa alla plancia, stesso discorso
        return null;
    }
    //quindi servono tutti i getter getStrongbox ecc...

    //mostra a video la uantità totale di risorse disponibili;

    /**
     * Counts all the available resources in Storage and in StrongBox
     */
    public void TotAvailableResources (){
        int tot=0;
        tot+= storage.getTotResourceStorage(); //registro risorse nel magazzino
        tot+= SBox.getTotResourceSB();//registro risorse nel SB
        System.out.println("Available Resources: "+ tot);
    }
}
