package it.polimi.ingsw.model;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;


public class Player implements Serializable {

    //cose che appartengono solo al player:
    //Nome, Numero, PV, Posizione tracciato fede, Plancia, LeaderCards(2)
    //Carte Sviluppo acquistate

    public Player()

    {
        name = new String();
        PV=0;
        storage = new Storage();
        strongBox = new StrongBox();
        DSpace = new DevelopementSpace();
        leadercards = new LeaderDeck();
        skill1 = 0;
        skill2 = 0;
        developementquantity = 0;
        faithTrack = new FaithTrack();
    }

    //Nome
    private String name;
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
    private int PV;
    public  int getPv() {
        return PV;
    }
    public  void setPv(int pv) {
        PV = pv;
    }


    //Plancia
    private  Storage storage;
    private StrongBox strongBox;
    private  DevelopementSpace DSpace;
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setStrongBox(StrongBox strongBox) {
        this.strongBox = strongBox;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }
    /*public void setStrongBox(StrongBox strongBox) {
        this.strongBox = strongBox;
    }
     */

    public DevelopementSpace getDSpace() {
        return DSpace;
    }
    public void setDSpace(DevelopementSpace DSpace) {
        this.DSpace = DSpace;
    }


    //LeaderCards(2)
    private LeaderDeck leadercards;
    public void setLeadercards(LeaderDeck leadercards){this.leadercards=leadercards;}
    public LeaderDeck getLeadercards() { return leadercards; }
    private int skill1;
    private int skill2;

    public int getSkill1(){
        return  skill1;
    }

    public int getSkill2(){
        return skill2;
    }

    public void setSkill1(int skill){
        skill1=skill;
    }

    public void setSkill2(int skill){
        skill2=skill;
    }


    //carte sviluppo in DevelopementSpace
    private DevelopeDecks minideck1;
    private DevelopeDecks minideck2;
    private DevelopeDecks minideck3;

    public DevelopeDecks getMinideck1() {
        return minideck1;
    }

    public void setMinideck1(DevelopeDecks minideck1) {
        this.minideck1 = minideck1;
    }

    public DevelopeDecks getMinideck2() {
        return minideck2;
    }

    public void setMinideck2(DevelopeDecks minideck2) {
        this.minideck2 = minideck2;
    }

    public DevelopeDecks getMinideck3() {
        return minideck3;
    }

    public void setMinideck3(DevelopeDecks minideck3) {
        this.minideck3 = minideck3;
    }

    private DevelopeCard minideck1Top;
    private DevelopeCard minideck2Top;
    private DevelopeCard minideck3Top;
    private DevelopeDecks TopCardsOnBoard;

    public DevelopeDecks getTopCardsOnBoard() {
        return TopCardsOnBoard;
    }

    private int developementquantity;
    public int getDevelopementquantity() {
        return developementquantity;
    }
    /**
     * Adds 1 when the player buys a new DevelopementCard
     */
    public void increaseDevelopQuantity() { this.developementquantity = developementquantity+1; }
    public void setDevelopementquantity(int developementquantity) {
        this.developementquantity = developementquantity;
    }


    //FaithTrack
    private FaithTrack faithTrack;
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
    private int FTposition=0;
    public  int getTrackposition() { return FTposition; }
    public void setTrackposition(int num) { FTposition=num; }
    public void increaseTrackposition() {
        this.FTposition = FTposition + 1;
    }



    /**
     * Checks if the quantity of needed resources are available in the storage and/or in the strongbox
     * @param vectorResources : arraylist of needed resources
     * @return a flag
     */
    public int checkResources(ArrayList<Character> vectorResources) {
        //flag per sapere se non possiedo tali risorse (0) o possiedo in storage (1) o in strongbox-storage (2)
        int ableTo = 0;

        ArrayList<Character> vector = new ArrayList<Character>();
        Character[] resources = {'B', 'Y', 'G', 'P'};
        for (int i=0; i<4; i++){
            for(int c=0; c<vectorResources.size();c++){
                if(vectorResources.get(c).equals(resources[i]))
                vector.add(vectorResources.get(c));
            }
        }
        for (int i=0; i<vector.size(); i++){
            System.out.println(vector.get(i));
        }

        //la prima risorsa che considero è l'ultima della arraylist (che si suppone ordinato)
        int i = vectorResources.size()-1;
        char typeResource;

        while (i >= 0) {
            int countType=0;
            //vedo qual'è la risorsa i-esima che sto considerando
            typeResource = vectorResources.get(i);
            //conto quanto c'è bisogno di una determinata risorsa nel vettore dato
            while ( i >= 0) {
                if (vectorResources.get(i) == typeResource) countType++;
                i--;
            }
            //int countType = (int) storage.getPanel().getVector().stream().count();
            //una volta finito di contare le risorse dello stesso tipo
            //confronto quantità richiesta con quantità presente o in storage o in strongbox e storage
            if (countType > storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                //risorse insufficienti
                System.out.println("Not enough resources.");
                return 0;

            } else if (countType <= storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                ableTo = 2;
                if (countType <= storage.countTypeS(typeResource)) {
                    ableTo = 1;
                }
            }
            //ricomincio il while
            i = i - countType;
        }
        if (ableTo == 1) {
            System.out.println("You have the needed quantity of resources. These will be removed from your storage");
            //rimuovo tutte le risorse di cui ho bisogno, dallo storage
            for(i = vectorResources.size() - 1;i>=0 ;i--){
                removeResourceStorage(vectorResources.get(i));
            }

        } else if (ableTo == 2) {
            System.out.println("You have the needed quantity of resources. These will be removed from your storage and strongbox");
            //rimuovo tutte le risorse di cui ho bisogno, dallo storage
            for(i = vectorResources.size() - 1;i>=0 ;i--) {
                if (removeResourceStorage(vectorResources.get(i))) {
                    vectorResources.remove(i);//rimuovo l'elemento che viene trovato e rimosso dallo storage
                }
            }
            //ottengo così il vettore aggiornato con le risorse da cercare nello strongbox
            for(i= vectorResources.size()-1;i>=0;i--){
                strongBox.getStructure().remove(vectorResources.get(i)); //elimino la risorsa voluta dallo strongbox
            }
        }
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
        minideck1Top=DSpace.getMinideck1().getStructure().get(0);
        TopCardsOnBoard.getStructure().add(minideck1Top);
        minideck2Top=DSpace.getMinideck2().getStructure().get(0);
        TopCardsOnBoard.getStructure().add(minideck2Top);
        minideck3Top=DSpace.getMinideck3().getStructure().get(0);
        TopCardsOnBoard.getStructure().add(minideck3Top);
        //ho creato un arraylist con le carte in cima ai minideck
        return TopCardsOnBoard ;//ritorna l'arraylist;
    }

    /**
     * this method add if possible a deelopecard to the player DSpace
     * @param card: card to add
     * @return: the action response
     */
    public boolean addDevelopCard(DevelopeCard card)
    {
        char color = card.getColour();
        int minideck = DSpace.colorPresent(color);

        if(minideck == 4)   return false;
        if(minideck != 0)
        {
            if(DSpace.setCard(card, minideck)) return true;
            return false;
        }

        if(DSpace.getMinideck3().getStructure().size() == 0)   minideck=3;
        if(DSpace.getMinideck2().getStructure().size() == 0)   minideck=2;
        if(DSpace.getMinideck1().getStructure().size() == 0)   minideck=1;
        if(DSpace.setCard(card, minideck)) return true;
        return false;
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
     * Removes one resource from the storage.
     * @param resource: the resource the player wants to delete
     */
    public boolean removeResourceStorage(char resource) {
        int i;
        ArrayList<Character> vector = new ArrayList<Character>();
        //aggiungo gli elementi dello storage nel vettore
        for (int c=0;c<6;c++){
            vector.add(storage.getPanel().get(c));
        }
        //aggiungo gli elemento dello strongbox
        for (int c=0; c<2; c++){
            vector.add(storage.getExtrapanel().getVector().get(c));
        }
        //caso in cui l'abilità del piano aggiuntivo sia abilitata:
        /*if (leadercards.getStructure().get(0).getSkill().equals("StorageSkill") || leadercards.getStructure().get(1).getSkill().equals("StorageSkill")){
            vector.addAll(storage.getExtrapanel().getVector()); //aggiungo tutti gli elementi nel pannello extra nel vettore fittizio
        }*/
        if (vector.contains(resource)) {
            for (i = vector.size() - 1; i >= 0; i--) {
                //mi sposto nella struttura finchè non ottengo indice della risorsa che voglio eliminare
                if (resource == vector.get(i)) {
                    break;
                }
            }
            System.out.println("Resource "+vector.get(i)+" has been removed.");
            if (i > 5) {
                //l'elemento si trova nel pannello extra
                i=i-5;
                storage.getExtrapanel().set(i,'N');
            } else {
                //l'elemento si trova nello storage normale
                storage.getPanel().set(i,'N');//rimuovo la risorsa i-esima
            }
            return true;
        } else {
            //caso in cui non c'è la risorsa richiesta da eliminare
            System.out.println("The asked resource is not in the storage.");
            return false;
        }
    }

    //Similar method is NOT needed in StrongBox since its space is unlimited.

    /**
     * Adds a single specified resource inside the Storage
     * @param resource : the resource the player will put in Storage
     */
    public boolean addResourceStorage(char resource) {
        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure, va inserita dentro il magazzino
        (se viene dal mercato)
        */
        //caso in cui il magazzino sia pieno (sia extrapanel sia panel)
        if (storage.countTypeS('N')==0) {
            System.out.println("No more space available in Storage.");
            return false;
        }

        //caso in cui il magazzino abbia uno o + spazi vuoti

        int i = 5;
        int countExtraN = 0;
        for (int c = 1; c >= 0; c--) {
            if (storage.getExtrapanel().getVector().get(c) == 'N') countExtraN++;
        }
        //controllo se extrapanel sia dello stesso tipo e se ha degli spazi liberi
        if (storage.getTypeExtrapanel() == resource && countExtraN > 0) {
            if (storage.getExtrapanel().getVector().get(0) == 'N') {
                storage.getExtrapanel().getVector().set(0, resource);
            } else if (storage.getExtrapanel().getVector().get(1) == 'N') {
                storage.getExtrapanel().getVector().set(1, resource);
                return true;
            }
        } //else if (storage.getTypeExtrapanel()!=resource && countExtraN== storage.countTypeS(resource))
        //System.out.println("No more space available in Storage.");

        //controllo panel:
        //-se c'è già una presente vedo se aggiungerne un'altra
        if (storage.getPanel().contains(resource)) {
            while (storage.getPanel().get(i) != resource && i >= 0) {
                i--;
            }
            if (i == 0 || i == 2 || i == 5) {
                // i==0 : una risorsa di quel tipo presente in cima -> elimino la risorsa
                // i==2 : il secondo piano ha risorse di quel tipo -> elimino la risorsa
                // i==5 : il terzo piano ha risorse di quel tipo -> elimino la risorsa
                System.out.println(resource + " deleted. It already exists");
            }
            // in tutti gli altri casi in cui un posto nei piani è libero
            switch (i) {
                case 1:
                    storage.getPanel().set(2, resource); //posto i==2 ora occupato
                break;
                case 3:
                    storage.getPanel().set(4, resource); //posto i==4 ora occupato
                break;
                case 4:
                    storage.getPanel().set(5, resource); //posto i==5 ora occupato
                break;
            }
            return true;
        } else {
            //se la risorsa non c'è:
            if (storage.getPanel().get(0) == 'N') {
                i = 0;
            } else if ((storage.getPanel().get(1) == 'N') && (storage.getPanel().get(2) == 'N')) {
                i = 1;
            } else if ((storage.getPanel().get(3) == 'N') && (storage.getPanel().get(4) == 'N') && (storage.getPanel().get(5) == 'N')) {
                i = 3;
            }
            storage.getPanel().set(i, resource); //aggiungo la risorsa nel primo piano (i==0) o nel secondo (i==1) o nel
            // terzo (i==3)
            return true;
        }
        //conto il numero di N in extrapanel

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


    //mostra a video la quantità totale di risorse disponibili;


    /*
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
