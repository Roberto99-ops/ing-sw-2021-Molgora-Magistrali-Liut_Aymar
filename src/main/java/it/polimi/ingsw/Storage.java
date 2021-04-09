package it.polimi.ingsw;

public class Storage {
            private ResourceStructure panel; // arraylist di 6 spazi di base
            private ResourceStructure extrapanel;



        // controlla se sono presenti nel panel tot risorse di uno specifico tipo
        public boolean getinStorage (int quantity, Character type) {                    // OK!!!!!!
           int counter = 0;
           for (int i=0; i < 6; i ++) {
               if (this.panel.getVector().get(i) == type) {
               counter ++;
                                                 }
           }
           if (counter == quantity && this.checkStorage() == true) {
               return true;
           } else return false;
        }



        // inserisce nel panel una data risorsa (se si può)
        public void setinStorage (char type, int position) {

            if (getinStorage(1, type) == true) {
                this.panel.getVector().set(position, type);
            } else this.extrapanel.getVector().add(type);
        }


        // se viene lanciata un'exeption in setinstorage oppure in getinstorage si attiva e modifica arraylist
        public void repairStorage () {


        }

        // controlla se i sei spazi del panel rispettano le regole prefissate del gioco se non le rispetta chiama repairstorage
        public boolean checkStorage () {
            //if(this.panel.getVector().size()==0) return true;
            if (    this.panel.getVector().get(0) != this.panel.getVector().get(1) &&
                    this.panel.getVector().get(1) != this.panel.getVector().get(3) &&
                    this.panel.getVector().get(0) != this.panel.getVector().get(3) &&
                    this.panel.getVector().get(1) == this.panel.getVector().get(2) &&
                    this.panel.getVector().get(3) == this.panel.getVector().get(4) &&
                    this.panel.getVector().get(4) == this.panel.getVector().get(5)         ) // però possono essere anche vuoti! come posso eprimerlo?
            { return true; } else {
                                this.repairStorage();
                                return false; }
        }

        // controlla le risorse extra che vanno ad incrementare i puntifede degli altri giocatori
        public int returnmoves () {
            return this.extrapanel.getVector().size();
        }
}