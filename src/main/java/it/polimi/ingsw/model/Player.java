package it.polimi.ingsw.model;
import java.lang.String;
import java.util.ArrayList;

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
    private StrongBox strongBox = new StrongBox();
    private DevelopementSpace DSpace = new DevelopementSpace();
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }
    public void setStrongBox(StrongBox StrongBox) {
        this.strongBox = StrongBox;
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

    private int developementquantity=0;
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

    /**
     * Checks if the quantity of needed resources are available in the storage and/or in the strongbox
     * @param ResourceStructure : arraylist of needed resources
     * @return a flag
     */
    public int CheckResources(ArrayList ResourceStructure) {
        //flag per sapere se non elimino (0) o elimino in storage (1) o in strongbox-storage (2)
        int ableTo = 0;

        //prendo la prima risorsa richiesta
        int i = ResourceStructure.size() - 1;
        char typeResource;
        int countNeed = 1;
        while (i >= 0) {
            typeResource = (char) ResourceStructure.get(i);
            //conto quanto è richiesto di questa risorsa
            while ((char) ResourceStructure.get(i) == (char) ResourceStructure.get(i - 1)) {
                countNeed++;
                i--;
            }
            //confronto quantità richiesta con quantità presente o in storage o in strongbox e storage
            if (countNeed > storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                ableTo = 0;
                System.out.println("Not enough resources.");
                return 0;

            } else if (countNeed <= storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                //ableTo = 2;
                if (countNeed < storage.countTypeS(typeResource)) {
                    ableTo = 1;
                }

            }
        }
        System.out.println("You have the needed quantity of resources. These will be removed from your storage and/or strongbox");
        return 1;
    }

    /**
     * Gets the first card of each minideck in the DevelopementSpace
     * @return arraylist of minidecks' topcards
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
     NON FARE
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
    public ResourceStructure addResourceStorage(char resource)
    {
        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure, va inserita dentro il magazzino
        (se viene dal mercato)
        */

        //deve aggiungere questa risorsa alla plancia, stesso discorso
        return null;
    }

    /**
     * Adds a single specified resource inside the StrongBox
     * @param resource : the resource the player will put in strongbox
     */
    public void addResourceStrongBox(char resource)
    {
        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure, va inserita dentro il magazzino
        (se viene dal mercato)
        */
        this.strongBox.getStructure().add(resource);
    }


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
