package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.List;


public class Storage extends ArrayList<Character> {


    private ArrayList<Character> panel = new ArrayList <Character> (List.of('N','N','N','N','N','N')); // arraylist di 6 spazi di base
    private ResourceStructure extrapanel = new ResourceStructure();
    private char typeExtrapanel= 'Z';  //carattere che per noi significa che il pannello extra non è attivo, non so se si può inzializzare così


    public void setPanel(ResourceStructure panel) {
        this.panel = panel;
    }

    public void printPanel () {
        for (int i = 0; i < 6; i ++) {
            System.out.println(this.panel.get(i));
        }
        System.out.println("\n");
    }


    public  ArrayList <Character> getExtrapanel(){return extrapanel;}

    public ArrayList <Character> getPanel() {
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
            while(i!=this.panel.size()) {
                if (neededRes == this.panel.get(i))
                    counterS++;
                i++;
            }

            i=0;
                 /** while(i!=this.extrapanel.size()){
                    if(neededRes==this.extrapanel.get(i))
                        counterS++;
                    i++;  } */


        return counterS;
    }

    //metodo che restituisce la quantità totale di risorse nel magazzino


    /**
     * Counts how many resources are available inside the storage.
     * It also prints quantities of each resource
     * @return sum: how many resources are available inside the storage
     */
//Secondo me(Juan), può essere rimpiazzato dal metodo sopra
 public int getTotResourceStorage() {
     int counterB = 0;
     int counterP = 0;
     int counterY = 0;
     //int counterR = 0;
     int counterG = 0;
     //int counterW = 0;
     int sum = 0;

     for (int i = 5; i >= 0; i--) {

         switch (this.panel.get(i)) {

             case 'B': {
                 counterB++;
                 break;
             }

             /*case 'W': {
                 counterW++;
                 break;
             }*/

             case 'P': {
                 counterP++;
                 break;
             }

             case 'Y': {
                 counterY++;
                 break;
             }

             /*case 'R': {
                 counterR++;
                 break;
             }*/

             case 'G': {
                 counterG++;
                 break;
             }
         }
     }

     System.out.println( " B : " + counterB);
     System.out.println( " G : " + counterG);
     System.out.println( " Y : " + counterY);
     System.out.println( " P : " + counterP);
     //System.out.println( " R : " + counterR);
     //System.out.println( " W : " + counterW);


     sum = counterB + counterG  + counterY + counterP ;

     return sum;
 }


    // classe int getResource che restituisce la quantità di un certo tipo di risorsa

    /**
     * returns the quantity of a certain type of resource
     */

    public int returnQuantity (int quantity, Character type) {
        int counter = 0;
        for (int i=0; i < 6; i ++) {
            if (this.panel.get(i) == type) {
                counter++; }
        } return counter; }

    //classe deleteresources che cancella una quantità di un tipo di risorsa

    /**
     * returns the quantity of a certain type of resource
     */
    public void deleteResources (int quantity, Character type) {
        int counter = 0;
        int i = 5;
        while (i >= 0 && counter < quantity) {
            if (this.panel.get(i) == type) {
           this.panel.set(i, 'N');
           counter ++;
            }
            i--;
        }
    }


        // controlla se sono presenti nel panel tot risorse di uno specifico tipo

    /**
     * check if there are any resources of a specific type in the panel
     */

        public boolean getinStorage (int quantity, Character type) {                    // OK!!!!!!
           int counter = 0;
           for (int i=0; i < 6; i ++) {
               if (this.panel.get(i) == type) {
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
            this.panel.set(position, type);
        }


    /**
     * Check if the arraylist respects the rules of his impostationhow many resources are available inside the storage
     * @return true if it's correcltly implemented, false if it's there are errors
     */

        public boolean checkStorage () {


            if (   ( this.panel.get(0) != this.panel.get(1) ||
                        this.panel.get(0) == 'N' ||
                        this.panel.get(1) == 'N' )

                    &&

                    ( this.panel.get(1) != this.panel.get(3) ||
                        this.panel.get(1) == 'N' ||
                        this.panel.get(3) == 'N')
                    &&

                    ( this.panel.get(0) != this.panel.get(3) ||
                        this.panel.get(0) == 'N' ||
                        this.panel.get(3) == 'N')

                    &&

                    ( this.panel.get(1) == this.panel.get(2) ||
                        this.panel.get(1) == 'N' ||
                        this.panel.get(2) == 'N')
                    &&

                    (this.panel.get(3) == this.panel.get(4) ||
                        this.panel.get(1) == 'N' ||
                        this.panel.get(2) == 'N'
                    ) &&

                    ( this.panel.get(4) == this.panel.get(5) ||
                    this.panel.get(4) == 'N' ||
                    this.panel.get(5) == 'N')

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