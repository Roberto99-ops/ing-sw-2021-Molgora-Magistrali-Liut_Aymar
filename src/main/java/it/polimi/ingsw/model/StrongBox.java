package it.polimi.ingsw.model;

public class StrongBox {

    private ResourceStructure structure;

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
