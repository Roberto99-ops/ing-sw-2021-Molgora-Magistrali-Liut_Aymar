package it.polimi.ingsw.model;
import java.lang.String;
import java.util.ArrayList;
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
    public  int getPv() {
        return PV;
    }
    public  void setPv(int pv) {
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
    private int skill1=0;
    private int skill2=0;

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
    private DevelopeCard minideck1Top;
    private DevelopeCard minideck2Top;
    private DevelopeCard minideck3Top;
    DevelopeDecks TopCardsOnBoard = new DevelopeDecks();

    private int developementquantity=0;
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
    private int FTposition=0;
    public  int getTrackposition() { return FTposition; }
    public void increaseTrackposition() {
        this.FTposition = FTposition + 1;
    }



    /**
     * Checks if the quantity of needed resources are available in the storage and/or in the strongbox
     * @param vectorResources : arraylist of needed resources
     * @return a flag
     */
    public int CheckResources(ArrayList<Character> vectorResources) {
        //flag per sapere se non possiedo tali risorse (0) o possiedo in storage (1) o in strongbox-storage (2)
        int ableTo = 0;

        //la prima risorsa che considero è l'ultima della arraylist (che si suppone ordinato)
        int i = vectorResources.size() - 1;
        char typeResource;
        int countNeed = 1; //dato che l'arraylist non sarà vuoto, di sicuro ci vorrà almeno una risorsa del primo tipo che si considera
        while (i >= 0) {
            typeResource = vectorResources.get(i);
            //conto quanto è richiesto di questa risorsa: se la risorsa successiva è == a quella precedente, allora aumento il contatore
            while (vectorResources.get(i) == vectorResources.get(i - 1)) {
                countNeed++;
                i--;
            }
            //una volta finito di contare le risorse dello stesso tipo
            //confronto quantità richiesta con quantità presente o in storage o in strongbox e storage
            if (countNeed > storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                //risorse insufficienti
                System.out.println("Not enough resources.");
                return 0;

            } else if (countNeed <= storage.countTypeS(typeResource) + strongBox.countTypeSB(typeResource)) {
                ableTo = 2;
                if (countNeed < storage.countTypeS(typeResource)) {
                    ableTo = 1;
                }
            }
            //ricomincio il while
        }
        //una volta finito il controllo, dico al giocatore da dove si possono eliminare le risorse
        if (ableTo == 1) {
            System.out.println("You have the needed quantity of resources. These will be removed from your storage");
            //rimuovo tutte le risorse di cui ho bisogno, dallo storage
            for(i = vectorResources.size() - 1;i>=0 ;i--){
                removeResource(vectorResources.get(i));
            }

        } else if (ableTo == 2) {
            System.out.println("You have the needed quantity of resources. These will be removed from your storage and strongbox");
            //rimuovo tutte le risorse di cui ho bisogno, dallo storage
            for(i = vectorResources.size() - 1;i>=0 ;i--) {
                if (removeResource(vectorResources.get(i))) {
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
     * Removes one resource from the storage.
     * @param resource: the resource the player wants to delete
     */
    public boolean removeResource(char resource) {
        int i;
        ArrayList<Character> vector = storage.getPanel();
        //caso in cui l'abilità del piano aggiuntivo sia abilitata:
        if (leadercards.getStructure().get(0).getSkill().equals("StorageSkill") || leadercards.getStructure().get(1).getSkill().equals("StorageSkill")){
            vector.addAll(storage.getExtrapanel()); //aggiungo tutti gli elementi nel pannello extra nel vettore fittizio
        }
        for (i=vector.size()-1; i>=-1 ;i--) {
            //mi sposto nella struttura finchè non ottengo indice della risorsa che voglio eliminare
            if (resource==vector.get(i) || i==-1){
                break;
            }

        } //salto con l'indice i che mi indica la posizione
        if (i==-1){
            //caso in cui non c'è la risorsa richiesta da eliminare
            System.out.println("The asked resource is not in the storage.");
            return false;
        }
        System.out.println("Resource "+vector.get(i)+" has been removed.");
        if (i > 5) {
            //l'elemento si trova nel pannello extra
            i=i-5;
            storage.getExtrapanel().remove(i);
        } else {
            //l'elemento si trova nello storage normale
            storage.remove(i);//rimuovo la risorsa i-esima
        }
        return true;
    }

    //Similar method is NOT needed in StrongBox since its space is unlimited.

    /**
     * Adds a single specified resource inside the Storage
     * @param resource : the resource the player will put in Storage
     */
    public void addResourceStorage(char resource) {
        char choice;
        Scanner scan = new Scanner(System.in);

        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure, va inserita dentro il magazzino
        (se viene dal mercato)
        */
        //caso in cui il magazzino sia pieno
        if (storage.size() >= 6) {
            System.out.println("No more space available in Storage.");
            //la risorsa non può essere aggiunta e allora la elimino

            /* CASO PRECEDENTE (sbagliato)
            Do you want to delete" + resource + "(A) or something in your storage(B)?
            //il player vede il suo magazzino e sceglie se non aggiungere la char resource oppure se vuole eliminarne
            // qualcuna e quale risorsa eliminare
            if (scan.nextLine().equals("A")) {
                System.out.println(resource + " deleted.");
                return;
            } else if (scan.nextLine().equals("B")) {
                System.out.println("Select the resource in your storage you want to delete:");
                choice = scan.next().charAt(0);
                removeResource(choice);
            }
            return;
            //caso in cui il magazzino abbia uno o + spazi vuoti
             */
        } else {
            int i = 5;
            //controllo se il magazzino contiene la risorsa già da qualche parte
            //se c'è già:
            if (storage.getPanel().contains(resource)) {
                while ((char) storage.getPanel().get(i) != resource && i >= 0) {
                    i--;
                }
                if (i == 0 || i == 2 || i == 5) {
                    // i==0 : una risorsa di quel tipo presente in cima -> elimino la risorsa
                    // i==2 : il secondo piano ha risorse di quel tipo -> elimino la risorsa
                    // i==5 : il terzo piano ha risorse di quel tipo -> elimino la risorsa
                    System.out.println(resource + " deleted. It already exists");
                    return;
                }
                // in tutti gli altri casi in cui un posto nei piani è libero
                switch (i) {
                    case 1:
                        storage.getPanel().add(2, resource); //posto i==2 ora occupato
                        break;
                    case 3:
                        storage.getPanel().add(4, resource); //posto i==4 ora occupato
                        break;
                    case 4:
                        storage.getPanel().add(5, resource); //posto i==2 ora occupato
                        break;
                }
                return;
            }
            //se la risorsa non c'è:
            while (storage.getPanel().get(i) != null && i >= 0) {
                if (i == 0 || i == 1 || i == 3) {
                    break;
                }
                i--;
            }
            storage.getPanel().add(i, resource); //aggiungo la risorsa nel primo piano (i==0) o nel secondo (i==1) o nel
            // terzo (i==3)
        }
        return;
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
