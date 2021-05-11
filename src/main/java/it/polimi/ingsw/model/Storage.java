package it.polimi.ingsw.model;
import java.util.ArrayList;

public class Storage extends ArrayList<Character> {

    private ResourceStructure panel = new ResourceStructure(); // arraylist di 6 spazi di base
    private ResourceStructure extrapanel = new ResourceStructure();
    private char typeExtrapanel= 'Z';  //carattere che per noi significa che il pannello extra non è attivo, non so se si può inzializzare così

    public void setPanel(ResourceStructure panel) {
        this.panel = panel;
    }

    public  ResourceStructure getExtrapanel(){return extrapanel;}

    public ResourceStructure getPanel() {
        return panel;
    }

    public char getTypeExtrapanel() {
        return typeExtrapanel;
    }

    public void setTypeExtrapanel(char typeExtrapanel) {
        this.typeExtrapanel = typeExtrapanel;
    }

    /**
     * Counts the amount of one kind of resource in panel and extrapanel
     * @param neededRes: type of resource the player needs to pay/activate something
     * @return counterS: the amount of that resource in Storage
     */

    //metodo che conta le risorse per verificare se è possibile usarle per acquistare una carta sviluppo

    public int countTypeS(char neededRes) {
        int i=0, counterS=0;
            while(i!=panel.getVector().size()) {
                if (neededRes == panel.getVector().get(i))
                    counterS++;
                i++;
            }

            i=0;
                while(i!=extrapanel.getVector().size()){
                    if(neededRes==extrapanel.getVector().get(i))
                        counterS++;
                    i++;
                }


        return counterS;
    }

    //metodo che restituisce la quantità totale di risorse nel magazzino


    /**
     * Counts how many resources are available inside the storage.
     * It also prints quantities of each resource
     * @return sum: how many resources are available inside the storage
     */

 public int getTotResourceStorage() {
        int i, t, counter;
                int sum = 0;
        char[] types = {'W', 'R', 'B', 'G', 'P', 'Y'};
        for (t = 0; t < 6; t++) { //controllo che la lettera #t...
            counter = 0;
            for (i = 0; i < panel.size(); i++) { //...sia presente dentro l'array
                if (types[t] ==  panel.getVector().get(i)) {
                    counter++;
                }
            }
            System.out.println(types[t] + ":" + counter); //stampo a video le quantità distinte
            sum += counter;
        }
            return sum; //ritorno il tot di risorse nel magazzino
    }



    // classe int getResource che restituisce la quantità di un certo tipo di risorsa

    /**
     * returns the quantity of a certain type of resource
     */

    public int returnQuantity (int quantity, Character type) {
        int counter = 0;
        for (int i=0; i < 6; i ++) {
            if (this.panel.getVector().get(i) == type) {
                counter++; }
        } return counter; }

    //classe deleteresources che cancella una quantità di un tipo di risorsa

    /**
     * returns the quantity of a certain type of resource
     */
    public void deleteResources (int quantity, Character type) {
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            if (this.panel.getVector().get(i) == type) {
                // this.panel.getVector().get(i) = 'B'; why is error?
            }
        }
    }


        // controlla se sono presenti nel panel tot risorse di uno specifico tipo

    /**
     * check if there are any resources of a specific type in the panel
     */

        public boolean getinStorage (int quantity, Character type) {                    // OK!!!!!!
           int counter = 0;
           for (int i=0; i < 6; i ++) {
               if (this.panel.getVector().get(i) == type) {
               counter++;
                                                 }
           }
           if (counter == quantity && this.checkStorage() == true) {
               return true;
           } else return false;
        }



        // inserisce nel panel una data risorsa (se si può)

    /**
     * inserts a given resource in the panel (if possible)
     */

        public void setinStorage (char type, int position) {

            if (getinStorage(1, type) == true) {
                this.panel.getVector().set(position, type);
            } else this.extrapanel.getVector().add(type);
        }


    /**
     * Check if the arraylist respects the rules of his impostationhow many resources are available inside the storage
     * @return true if it's correcltly implemented, false if it's there are errors
     */

        public boolean checkStorage () {

            if(this.panel.getVector().size()==0) return true;

            else if (   ( this.panel.getVector().get(0) != this.panel.getVector().get(1) ||
                        this.panel.getVector().get(0) == 'B' ||
                        this.panel.getVector().get(1) == 'B' )

                    &&

                    ( this.panel.getVector().get(1) != this.panel.getVector().get(3) ||
                        this.panel.getVector().get(1) == 'B' ||
                        this.panel.getVector().get(3) == 'B')
                    &&

                    ( this.panel.getVector().get(0) != this.panel.getVector().get(3) ||
                        this.panel.getVector().get(0) == 'B' ||
                        this.panel.getVector().get(3) == 'B')

                    &&

                    ( this.panel.getVector().get(1) == this.panel.getVector().get(2) ||
                        this.panel.getVector().get(1) == 'B' ||
                        this.panel.getVector().get(2) == 'B')
                    &&

                    (this.panel.getVector().get(3) == this.panel.getVector().get(4) ||
                        this.panel.getVector().get(1) == 'B' ||
                        this.panel.getVector().get(2) == 'B'
                    ) &&

                    ( this.panel.getVector().get(4) == this.panel.getVector().get(5) ||
                    this.panel.getVector().get(4) == 'B' ||
                    this.panel.getVector().get(5) == 'B')

                ) {
                return true; }

            else {
                return false; }
        }

        // controlla le risorse extra che vanno ad incrementare i puntifede degli altri giocatori

    /**
     * check the extra resources that increase the faith points of the other players
     */

            public int returnMoves () {
                    return this.extrapanel.getVector().size();
                }
        }