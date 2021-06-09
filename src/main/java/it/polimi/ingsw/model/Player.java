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
    public void increasePV(int num) { PV+=num;}
    public void decreasePV(int num) { PV-=num;}


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
        this.skill1=skill;
    }

    public void setSkill2(int skill){
        this.skill2=skill;
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
        increasePV(1);
    }



    /**
     * Checks if the quantity of needed resources are available in the storage and/or in the strongbox
     * @param vectorResources : arraylist of needed resources
     * @return a flag
     */
    public int checkResources(ArrayList<Character> vectorResources) {
        //flag per sapere se non possiedo tali risorse (0) o possiedo in storage (1) o in strongbox-storage (2)
        int ableTo = 0;

        for (int i = 0; i < vectorResources.size(); i++) {
            int countType=0;
            char typeResource = vectorResources.get(i);

            //conto quanto c'è bisogno di una determinata risorsa nel vettore dato
            for (int j = 0; j < vectorResources.size(); j++)
                if (vectorResources.get(j).equals(typeResource)) countType++;

            //una volta finito di contare le risorse dello stesso tipo
            //confronto quantità richiesta con quantità presente o in storage o in strongbox e storage
            int storageCount = storage.countTypeS(typeResource);
            int strongboxCount = strongBox.countTypeSB(typeResource);
            if (countType > (storageCount + strongboxCount)) {
                //risorse insufficienti
                System.out.println("Not enough resources.");
                return 0;
            }

            //ableTo!=2 because if once we had ableTo=2 ->it's impossible that we have all the resources in the storage
            if (countType <= storageCount && ableTo != 2) {
                ableTo = 1;
                System.out.println("You have the needed quantity of resources in the storage.");
            }
            else {
                ableTo = 2;
                System.out.println("You have the needed quantity of resources in storage + strongbox");
            }
        }

        return ableTo;
    }

    /**
     * deletes the resources i need from the storage or the strongbox
     * @param ableTo: where i have to delete it (1->storage, 2->storage+strongbox)
     * @param vector: vector of resources
     */
    public void deleteResources(int ableTo, ArrayList<Character> vector) {
        ArrayList<Character> vectorResources = vector;
        //removes from storage
        if (ableTo == 1) {
            for (int i = vectorResources.size() - 1; i >= 0; i--)
                removeResourceStorage(vectorResources.get(i));
            return;
        }

        //removes from storage and strongbox
        if (ableTo == 2) {
            for (int i = vectorResources.size() - 1; i >= 0; i--) {
                if (removeResourceStorage(vectorResources.get(i))) {
                    vectorResources.remove(i);
                }
            }
            for (int i = vectorResources.size() - 1; i >= 0; i--)
                strongBox.getStructure().getVector().remove(vectorResources.get(i));
        }

        return;
    }

    /**
     * Gets card on the top of each minideck in the DevelopementSpace
     * @return arraylist of minidecks' topcards
     */
    //deve "intreccarsi" con la plancia in modo da restituire
    //la lista delle carte sviluppo in cima ai mazzetti, forse servono solo quelli
    //della prima fila in quanto la produzioni si fanno solo
    //con le carte in cima ai mazzetti
    public DevelopeDecks getTopCards() throws Exception {
        DevelopeDecks TopCardsOnBoard = new DevelopeDecks();
        DevelopeCard card;
        int size1 = DSpace.getMinideck1().getStructure().size();
        int size2 = DSpace.getMinideck2().getStructure().size();
        int size3 = DSpace.getMinideck3().getStructure().size();

        if(size1 > 0) {
            card = DSpace.getMinideck1().getStructure().get(size1-1);
            TopCardsOnBoard.getStructure().add(card);
        }

        if(size2 > 0) {
            card = DSpace.getMinideck2().getStructure().get(size2-1);
            TopCardsOnBoard.getStructure().add(card);
        }

        if(size3 > 0) {
            card = DSpace.getMinideck3().getStructure().get(size3-1);
            TopCardsOnBoard.getStructure().add(card);
        }

        return TopCardsOnBoard ;
    }

    /**
     * this method adds if possible a developecard to the player DSpace
     * @param card: card to add
     * @return: the action response
     */
    public boolean addDevelopCard(DevelopeCard card)
    {
        char color = card.getColour();
        int minideck = DSpace.colorPresent(color);

        //if(minideck == 4)   return false;
        if(minideck != 0)
        {
            if(DSpace.setCard(card, minideck)) {
                increasePV(card.getPv());
                return true;
            }
        }

        //if(DSpace.getMinideck3().getStructure().size() == 0)   minideck=3;
        //if(DSpace.getMinideck2().getStructure().size() == 0)   minideck=2;
        //if(DSpace.getMinideck1().getStructure().size() == 0)   minideck=1;
        if(DSpace.setCard(card, 1)) {
            increasePV(card.getPv());
            return true;
        }
        if(DSpace.setCard(card, 2)) {
            increasePV(card.getPv());
            return true;
        }
        if(DSpace.setCard(card, 3)) {
            increasePV(card.getPv());
            return true;
        }
        return false;
    }

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
        //aggiungo gli elemento dell'extrapanel
        for (int c=0; c<2; c++){
            vector.add(storage.getExtrapanel().getVector().get(c));
        }
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


    /**
     * Adds a single specified resource inside the Storage
     * @param resource : the resource the player will put in Storage
     */
    public boolean addResourceStorage(char resource) {
        /*IDEA: scelgo una risorsa e questa , che si trova dentro il resourcestructure,
        va inserita dentro il magazzino
        (se viene dal mercato)
        */
        //caso in cui il magazzino sia pieno (sia extrapanel sia panel)

        if (storage.countTypeS('N') == 0) {
            System.out.println("No more space available in Storage.");
            return false;
        }


        //caso in cui il magazzino abbia uno o + spazi vuoti


        //controllo se extrapanel sia dello stesso tipo e se ha degli spazi liberi
        int countExtraN = 0;
        for (int c = 1; c >= 0; c--) {
            if (storage.getExtrapanel().getVector().get(c).equals('N')) countExtraN++;
        }

        if (storage.getTypeExtrapanel() == resource && countExtraN > 0) {
            if (storage.getExtrapanel().getVector().get(0) == 'N')
                storage.getExtrapanel().getVector().set(0, resource);
            else if (storage.getExtrapanel().getVector().get(1) == 'N')
                storage.getExtrapanel().getVector().set(1, resource);

            return true;

        } else if (storage.getTypeExtrapanel() != resource && countExtraN == storage.countTypeS(resource))
            System.out.println("No more space available in Storage");

        //controllo panel:


        //-se c'è già una presente vedo se aggiungerne un'altra

        int i = 5;

        if (storage.getPanel().contains(resource)) {
            while (storage.getPanel().get(i) != resource && i >= 0) {
                i--;
            }

            if (i == 0 || i == 2) {

                // i==0 : provo a vedere se riesco a sostituire i piani

                if (storage.switchresources(resource, 0) == true) {
                    System.out.println(resource + " switched using best case");
                    return true;
                } else {
                    System.out.println(resource + " can't be added because of is present in panel and panels can't be switched ");
                }

                // i==2 : provo a vedere se riesco a sostituire i piani

                if (storage.switchresources(resource, 2) == true) {
                    System.out.println(resource + " switched using best case");
                    return true;
                } else System.out.println(resource + " can't be added because of is present in panel and panels can't be switched ");


            }

            // i==5 : il terzo piano ha risorse di quel tipo -> elimino la risorsa
            if (i == 5) {
                System.out.println(resource + " deleted. It already exists");
                return false;
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

            //se la risorsa non c'è

            if (storage.getPanel().get(0) == 'N') {
                i = 0;
            } else if ((storage.getPanel().get(1) == 'N') && (storage.getPanel().get(2) == 'N')) {
                i = 1;
            } else if ((storage.getPanel().get(3) == 'N') && (storage.getPanel().get(4) == 'N') && (storage.getPanel().get(5) == 'N')) {
                i = 3;
            }

            if (i < 4) {
                //this if is here to avoid the case which we have a fourth type of resource, so is not contained in the storage yet, but we don't have to put it inside
                storage.getPanel().set(i, resource); //aggiungo la risorsa nel primo piano (i==0) o nel secondo (i==1) o nel terzo (i==3)
                return true;
            } else {
                System.out.println(resource + " deleted. It can't be put in the storage");
                return false;
            }
        }
    }

    /**
     * this method checks if the player own enough developecards to activate a leadercard
     * @param level: minimum level of the developecards
     * @param colours: color of the cards
     * @return
     */
    public boolean checkCards(int level, ArrayList<Character> colours)
    {
        int cont = 0; //at the end if cont is = colours.size it means i have al the cards I need
        DevelopeDecks deck = new DevelopeDecks();
        for (int i = 0; i < this.DSpace.getMinideck1().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMinideck1().getStructure().get(i));
        for (int i = 0; i < this.DSpace.getMinideck2().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMinideck2().getStructure().get(i));
        for (int i = 0; i < this.DSpace.getMinideck3().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMinideck3().getStructure().get(i));

        for (int i = 0; i < colours.size(); i++) {
            for (int j = 0; j < deck.getStructure().size(); j++) {
                if (colours.get(i).equals(deck.getStructure().get(j).getColour()) && deck.getStructure().get(j).getLevel() >= level)
                {
                    deck.getStructure().remove(j);
                    cont++;
                }
            }
        }

        if(cont == colours.size()) return true;

        return false;
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

}
