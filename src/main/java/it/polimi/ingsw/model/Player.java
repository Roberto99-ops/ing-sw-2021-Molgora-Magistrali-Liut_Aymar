package it.polimi.ingsw.model;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

public class Player {
    //cose che appartengono solo al player:
    //Nome, Numero, PV, Posizione tracciato fede, Plancia, LeaderCards(2)
    //Carte Sviluppo acquistate

    //Nome
    private String name = new String();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    //Numero
    private int number;
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }


    //PV
    private int PV=0;
    public int getPv() {
        return PV;
    }
    public void setPv(int pv) {
        PV = pv;
    }


    //Plancia
    private Storage storage = new Storage();
    private StrongBox StrongBox = new StrongBox();
    private DevelopementSpace DSpace = new DevelopementSpace();
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public StrongBox getStrongBox() {
        return StrongBox;
    }
    public void setStrongBox(StrongBox StrongBox) {
        this.StrongBox = StrongBox;
    }

    public DevelopementSpace getDSpace() {
        return DSpace;
    }
    public void setDSpace(DevelopementSpace DSpace) {
        this.DSpace = DSpace;
    }


    //LeaderCards(2)
    private LeaderDeck leadercards = new LeaderDeck();
    public LeaderDeck getLeadercards() { return leadercards; }


    //carte sviluppo in DevelopementSpace
    private DevelopeCard minideck1Top;
    private DevelopeCard minideck2Top;
    private DevelopeCard minideck3Top;
    DevelopeDecks TopCardsOnBoard = new DevelopeDecks();

    private int developementquantity;
    public int getDevelopementquantity() {
        return developementquantity;
    }


    //FaithTrack
    private int FTposition=0;
    public int getTrackposition() { return FTposition; }


    /**
     * Adds 1 when the player buys a new DevelopementCard
     */
    public void increaseDevelopQuantity() { this.developementquantity = developementquantity+1; }

    //questo metodo controlla che ho determinate risorse dentro a , in ordine, storage e strongbox-storage.
    //Bisogna controllare costo produzione/carta
    //restituisce un int
    public void CheckResources(char ResourceStructure){

    }

    /**
     * Gets the first card of each minideck in the DevelopementSpace
     * @return
     */
    //deve "intreccarsi" con la plancia in modo da restituire
    //la lista delle carte sviluppo in cima ai mazzetti, forse servono solo quelli
    //della prima fila in quanto la produzioni si fanno solo
    //con le carte in cima ai mazzetti
    public DevelopeDecks getDevelopecards() throws Exception {
        minideck1Top=this.DSpace.getCard(1);
        TopCardsOnBoard.getStructure().add(minideck1Top);
        minideck2Top=this.DSpace.getCard(2);
        TopCardsOnBoard.getStructure().add(minideck2Top);
        minideck3Top=this.DSpace.getCard(3);
        TopCardsOnBoard.getStructure().add(minideck3Top);
        //ho creato un arraylist con le carte in cima ai minideck
        return TopCardsOnBoard ;//ritorna l'arraylist;
    }

    /*
    public ResourceStructure getResourcesStorage()
    {
        //stesso discorso della plancia anche qui,
        //deve restituire le risorse totali, servono per attivare
        //le produzioni
        return this.storage.getPanel();
    }


    public ResourceStructure getResourcesStrongBox()
    {
        //stesso discorso della plancia anche qui,
        //deve restituire le risorse totali, servono per attivare
        //le produzioni
        return this.SBox.getStructure();
    }
*/


    //chiedo quale risorsa vuole eliminare
    //System.out.println ("Which type of resource would you like to remove from your Storage:? W,R,B,G,P,Y");
    //try {choice = (char)System.in.read();} catch (IOException e) {
    //    System.out.println("Choice not available");
    //}

    /**
     * Removes one resource from the storage
     * @param resource: da eliminare
     */
    //da dividere per ambienti
    public void removeResource(char resource) {
        int i;
        //char choice = 'W';

        for (i=this.storage.size()-1; i>=-1 ;i--) {
            if (resource==(char)this.storage.get(i) || i==-1){
                break;
            }
        } //salto con l'indice i che mi indica la posizione
        if (i==-1){
            //caso in cui non c'è la risorsa richiesta da eliminare
            System.out.println("La risorsa richiesta non c'è nel magazzino.");
            return ;
        }
        this.storage.remove(i);//rimuovo la risorsa i-esima
    }

    //da dividere
    public ResourceStructure addResource(char resource)
    {
        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure, va inserita dentro il magazzino
        (se viene dal mercato)
        */

        //deve aggiungere questa risorsa alla plancia, stesso discorso
        return null;
    }
    //quindi servono tutti i getter getStrongbox ecc...

    //mostra a video la uantità totale di risorse disponibili;


    ///**
     //* Counts all the available resources in Storage and in StrongBox
    // */
    /*
    public void TotAvailableResources (){
        int tot=0;
        tot+= storage.getTotResourceStorage(); //registro risorse nel magazzino
        tot+= SBox.getTotResourceSB();//registro risorse nel SB
        System.out.println("Available Resources: "+ tot);
    }
    */
}
