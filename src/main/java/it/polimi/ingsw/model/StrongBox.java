package it.polimi.ingsw.model;

public class StrongBox {

    private ResourceStructure structure;
    private int TotalResources;

    /**
     * Counts the amount of one kind of resource
     * @param neededRes: type of resource the player needs to pay/activate something
     * @return counterSB: the amount of that resource in StrongBox
     */

    //metodo che conta le risorse per verificare se è possibile usarle per acquistare una carta sviluppo
    public int countTypeSB(char neededRes){
        int i=0, counterSB=0;
        if (structure.contains(neededRes)){
            while(i!= structure.size()){
                if(neededRes==(char)structure.get(i))
                        counterSB++;
                i++;
            }
            return counterSB;
        }else{
            return 0;
        }
    }

    /*//metodo che conta e stampa tot risorse e anche distinte
    public void getTotResourceSB() {
        int i, t, counter;
                //int sum = 0;
        char[] types = {'W', 'R', 'B', 'G', 'P', 'Y'};
        for (t = 0; t < 6; t++) { //controllo che la lettera #t...
            counter = 0;
            for (i = 0; i < structure.size(); i--) { //...sia presente dentro l'array
                if (types[t] == (char) structure.get(i)) {
                    counter++;
                }
            }
            System.out.println(types[t] + ":" + counter); //stampo a video le quantità distinte
            //sum += counter;
        }
        //return sum; //ritorno il tot di risorse nel magazzino
    }
    */

    //classe int getResource che restituisce la quantità di un certo tipo di risorsa
    //classe deleteresources che cancella una cquantità di un tipo di risorsa
    //metodo che restituisce proprio la struttura.

    public boolean getResource(int number, char withdraw) {
        int counter = 0;
        for (int i = 0; i <= number; i++) {
            if (this.structure.getVector().get(i) == withdraw) {
                counter++;
            }
        }

        if (counter == number) {
            return true;
        } else {
            return false;
        }
    }

    public void setResource(int number, char deposit) {
        for (int i = 0; i <= number; i++) {
            this.structure.getVector().add(deposit);
        }
    }

    public int counterResource() {
        return this.structure.getVector().size();
    }
}
