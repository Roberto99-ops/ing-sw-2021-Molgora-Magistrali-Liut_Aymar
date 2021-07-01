package it.polimi.ingsw.model;
import it.polimi.ingsw.Server.ClientHandler;
import it.polimi.ingsw.controller.GameManager;

import java.io.IOException;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;


public class Player implements Serializable {

    //All these attributes are the things that belong to the player: name, player number, PV, FaithTrack and his/her
    //position on it, PlayerBoard, LeaderCards(2), bought DevelopCards
    private static final long serialVersionUID = 6721229633581248101L;

    public Player() {
        name = new String();
        PV = 0;
        storage = new Storage();
        strongBox = new StrongBox();
        DSpace = new DevelopmentSpace();
        leadercards = new LeaderDeck();
        skill1 = 0;
        skill2 = 0;
        developmentQuantity = 0;
        faithTrack = new FaithTrack();


    }

    //Name
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Player Number
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


    //PV
    private int PV;

    public int getPv() {
        return PV;
    }

    public void setPv(int pv) {
        PV = pv;
    }

    public void increasePV(int num) {
        PV += num;
    }

    public void decreasePV(int num) {
        PV -= num;
    }


    //PlayerBoard
    private Storage storage;
    private StrongBox strongBox;
    private DevelopmentSpace DSpace;

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

    public DevelopmentSpace getDSpace() {
        return DSpace;
    }

    public void setDSpace(DevelopmentSpace DSpace) {
        this.DSpace = DSpace;
    }


    //LeaderCards(2)
    private LeaderDeck leadercards;

    public void setLeadercards(LeaderDeck leadercards) {
        this.leadercards = leadercards;
    }

    public LeaderDeck getLeadercards() {
        return leadercards;
    }

    private int skill1;
    private int skill2;

    public int getSkill1() {
        return skill1;
    }

    public int getSkill2() {
        return skill2;
    }

    public void setSkill1(int skill) {
        this.skill1 = skill;
    }

    public void setSkill2(int skill) {
        this.skill2 = skill;
    }

    private int developmentQuantity;

    public int getDevelopmentQuantity() {
        return developmentQuantity;
    }

    /**
     * Adds 1 when the player buys a new DevelopmentCard
     */

    public void increaseDevelopQuantity() {
        this.developmentQuantity = developmentQuantity + 1;
    }

    public void setDevelopmentQuantity(int developmentQuantity) {
        this.developmentQuantity = developmentQuantity;
    }


    //FaithTrack
    private FaithTrack faithTrack;

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    private int FtPosition = 0;

    public int getTrackPosition() {
        return FtPosition;
    }

    public void setTrackPosition(int num) {
        FtPosition = num;
    }

    public void increaseTrackPosition() {

        this.FtPosition = FtPosition + 1;

        switch(this.FtPosition){
            case 3: increasePV(1);
            break;
            case 6: increasePV(2);
            break;
            case 9: increasePV(4);
            break;
            case 12: increasePV(6);
            break;
            case 15: increasePV(9);
            break;
            case 18: increasePV(12);
            break;
            case 21: increasePV(16);
            break;
            case 24: increasePV(20);
            break;
        }

    }


    private int resourcesQuantity = 0;

    private void setResourcesQuantity(int resourcesQuantity) {
        this.resourcesQuantity = resourcesQuantity;
    }


    /**
     * Checks if the quantity of needed resources are available in the storage and/or in the strongbox
     * @param vectorResources : arraylist of needed resources
     * @return a flag ("ableTo") in order to know if I can use the needed resources and where they are: 0) I don't have them
     * 1) they are in the storage  2) they are in the storage and in the strongbox
     */


    public int checkResources (ArrayList<Character> vectorResources) {
        int ableTo = 0;
        boolean notStorage = false;

        for (int i = 0; i < vectorResources.size(); i++) {
            int countType = 0;
            char typeResource = vectorResources.get(i);

            //the Game counts how much I need of a specific resource, using the vector of Resources given as parameter...
            for (int j = 0; j < vectorResources.size(); j++)
                if (vectorResources.get(j) == typeResource) countType++;

            //... once the Game has finished counting, it checks if that specific is available and where it is
            // This operation is done for every type of Resource inside the "vectorResources"
            int storageCount = storage.countTypeS(typeResource);
            int strongboxCount = strongBox.countTypeSB(typeResource);
            //If the player doesn't have the needed resources
            if (countType > (storageCount + strongboxCount)) {
                //Not enough resources
                return 0;
            }

            if (countType <= storageCount + strongboxCount) {
                ableTo = 2;
                //The needed quantity of resources is in the storage + strongbox

                if (!notStorage && countType <= storageCount) {
                    ableTo = 1;
                    //The needed quantity of resources is in the storage
                }
                else
                    notStorage = true;

            }

        }
        return ableTo;
    }





    /**
     * Deletes the resources I need from the storage or the strongbox
     * @param ableTo: where I have to delete it (1->storage, 2->storage+strongbox)
     * @param vector: vector of resources
     */

    public void deleteResources(int ableTo, ArrayList<Character> vector) {
        //removes from storage
        if (ableTo == 1) {
            for (int i = vector.size() - 1; i >= 0; i--)
                removeResourceStorage(vector.get(i));
            modifyPVforResources();
            return;
        }
        //removes from storage and strongbox
        if (ableTo == 2) {
            for (int i = vector.size() - 1; i >= 0; i--) {
                if (removeResourceStorage(vector.get(i))) {
                    vector.remove(i);
                }
            }
            for (int i = vector.size() - 1; i >= 0; i--)
                strongBox.getStructure().getVector().remove(vector.get(i));
        }

        modifyPVforResources();
    }




    /**
     * Removes one resource from the Storage, after having added all the resources on the storage's panels in one vector
     * @param resource: the resource the player wants to delete
     */

    public boolean removeResourceStorage(char resource) {
        int i;
        ArrayList<Character> vector = new ArrayList<Character>();
        for (int c=0;c<6;c++){
            vector.add(storage.getPanel().get(c));
        }
        for (int c=0; c<2; c++){
            vector.add(storage.getExtraPanel().getVector().get(c));
        }
        if (vector.contains(resource)) {
            //the Game moves to the index where the wanted resource is
            for (i = vector.size() - 1; i >= 0; i--) {
                if (resource == vector.get(i)) {
                    break;
                }
            }
            //The index "i" tells me in which panel the wanted resource is
            if (i > 5) {
                //The wanted resource is inside the extraPanel
                i=i-5;
                //The resource is changed with a letter 'N' (=null) to symbolize that this space is free
                storage.getExtraPanel().getVector().set(i,'N');
            } else {
                //The wanted resource is inside the Storage's common panels
                storage.getPanel().set(i,'N');
            }
            return true;
        } else {
            return false;
        }
    }




    /**
     * Adds a single specified resource inside the Storage
     * @param resource : the resource the player will put in Storage
     */

    public boolean addResourceStorage(char resource, Game game) throws IOException {

        ArrayList<ClientHandler> clients = GameManager.getClientList();

        if (storage.countTypeS('N') == 0) {

            if (!game.getClass().equals(SingleGame.class)) {
                for (int i = 0; i < clients.size(); i++) {
                    if (!this.equals(clients.get(i).getPlayer()))
                        clients.get(i).getPlayer().increaseTrackPosition();
                }
            } else SingleGame.getLorenzo().forwardOne();
            return false;
        }

        //I check if the extraPanel's type is the same as the resource I want to add to the Storage and if the ExtraPanel
        //has some space left
        if (storage.getTypeExtraPanel() != 'Z' && storage.getTypeExtraPanel() == resource) {
            //CountExtraN: it counts how many empty spaces I have in the storage
            int countExtraN = 0;
            for (int c = 1; c >= 0; c--) {
                if (storage.getExtraPanel().getVector().get(c) == 'N') countExtraN++;
            }

            if (storage.getTypeExtraPanel() == resource && countExtraN > 0) {
                if (storage.getExtraPanel().getVector().get(0) == 'N')
                    storage.getExtraPanel().getVector().set(0, resource);
                else if (storage.getExtraPanel().getVector().get(1) == 'N')
                    storage.getExtraPanel().getVector().set(1, resource);

                modifyPVforResources();
                return true;

            }
        }

        //check inside the storage's common panels:
        //- if there's already a resource of that type
        int i = 5;

        if (storage.getPanel().contains(resource)) {
            while (storage.getPanel().get(i) != resource && i >= 0) {
                i--;
            }


            if (i == 0) {

                if (storage.switchResources(resource, 0)) {
                    modifyPVforResources();
                    return true;
                } else {

                    if (!game.getClass().equals(SingleGame.class)) {
                        for (int c = 0; c < clients.size(); c++) {
                            if (!this.equals(clients.get(c).getPlayer()))
                                clients.get(c).getPlayer().increasePV(1);
                        }
                    } else SingleGame.getLorenzo().forwardOne();

                    return false;

                }

            }

            if (i == 2) {

                if (storage.switchResources(resource, 2)) {
                    modifyPVforResources();
                    return true;
                } else  {

                    if (!game.getClass().equals(SingleGame.class)) {
                        for (int c = 0; c < clients.size(); c++) {
                            if (!this.equals(clients.get(c).getPlayer()))
                                clients.get(c).getPlayer().increaseTrackPosition();
                        }
                    } else SingleGame.getLorenzo().forwardOne();
                    return false;
                }

            }

            if (i == 5) {

                    if (!game.getClass().equals(SingleGame.class)) {
                        for (int c = 0; c < clients.size(); c++) {
                            if (!this.equals(clients.get(c).getPlayer()))
                                clients.get(c).getPlayer().increaseTrackPosition();
                        }
                    } else SingleGame.getLorenzo().forwardOne();

                return false;
            }

            //-if other panels are free
            if (i == 1) {
                storage.getPanel().set(2, resource);
                modifyPVforResources();
                return true;
            }

            if (i == 3) {
                storage.getPanel().set(4, resource);
                modifyPVforResources();
                return true;
            }

            if (i == 4) {
                storage.getPanel().set(5, resource);
                modifyPVforResources();
                return true;
            }

        } else {

            //if the resource is not already available in the panel
            if (storage.getPanel().get(0) == 'N') {
                i = 0;
            } else if ((storage.getPanel().get(1) == 'N') && (storage.getPanel().get(2) == 'N')) {
                i = 1;
            } else if ((storage.getPanel().get(3) == 'N') && (storage.getPanel().get(4) == 'N') && (storage.getPanel().get(5) == 'N')) {
                i = 3;
            }

            if (i < 4) {
                //To avoid the case which we have a fourth type of resource, so is not contained in the storage yet, but we don't have to put it inside
                storage.getPanel().set(i, resource);
                modifyPVforResources();
                return true;
            }


                if (!game.getClass().equals(SingleGame.class)) {

            for (int j = 0; j < clients.size(); j++) {
                if (!this.equals(clients.get(j).getPlayer()))
                    clients.get(j).getPlayer().increaseTrackPosition(); }
            } else SingleGame.getLorenzo().forwardOne();


            return false;

        }

    return false;

    }


    /**
     * This method checks if the player owns enough DevelopCards to activate a LeaderCard
     * @param level: minimum level of the DevelopCards
     * @param colours: color of the cards
     * @return a flag that tells the Game if the Player owns enough DevelopCards to activate a LeaderCard
     */

    public boolean checkCards(int level, ArrayList<Character> colours)
    {
        int cont = 0; //at the end if cont is = colours.size it means i have al the cards I need
        DevelopDecks deck = new DevelopDecks();
        for (int i = 0; i < this.DSpace.getMiniDeck1().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMiniDeck1().getStructure().get(i));
        for (int i = 0; i < this.DSpace.getMiniDeck2().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMiniDeck2().getStructure().get(i));
        for (int i = 0; i < this.DSpace.getMiniDeck3().getStructure().size(); i++)
            deck.getStructure().add(this.DSpace.getMiniDeck3().getStructure().get(i));

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
     * This method modifies the pv in case we have lost or gained some resources.
     * According to the rules, for every 5 resources we gain 1 PV.
     */

    private void modifyPVforResources() {
        int newQuantity = countTotalResources();
        int oldPV = resourcesQuantity/5;
        int newPV = newQuantity/5;
        int diff = newPV - oldPV;

        setResourcesQuantity(newQuantity);

        increasePV(diff);
    }


    /**
     * This method counts all the resources of the player
     * @return the total amount of resources the Player owns
     */
    private int countTotalResources() {
        int total = 0;

        for (int i = 0; i < 6; i++)
            if(!storage.getPanel().get(i).equals('N'))
                total++;

        total+=strongBox.getStructure().getVector().size();

        for (int i = 0; i < 2; i++)
            if(!storage.getExtraPanel().getVector().get(i).equals('N'))
                total++;

        return total;
    }


    /**
     * Adds a single specified resource inside the StrongBox
     * @param resource : the resource the player will put in strongbox
     */
    public void addResourceStrongBox(char resource)
    {
        //if the resource's type is 'R', the Game changes it with 1 PV and doesn't add it to the strongbox
        if (resource == 'R') {
            PV ++;
            increaseTrackPosition();
            return;
        }
        this.strongBox.getStructure().getVector().add(resource);
        modifyPVforResources();

    }

}
