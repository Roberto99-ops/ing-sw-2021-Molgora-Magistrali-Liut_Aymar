package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Market {
    private char[][] matrix = new char[3][4];
    private char extraball;


    // metodo che randomizza la matrix?

    /*
    inserisce la extraball nella matrice + nuova extraball
    check mi dice se è colonna/riga da sopra/sotto
    il metodo ritorna arraylist con le
     */


    public  ArrayList<Character> doMarket(int check, int number) {
        char temporaryball = this.extraball;
        char[][] equalmatrix = new char[3][4];
        equalmatrix = this.matrix;
        ArrayList<Character> vector = new ArrayList<Character>();

        if (check == 2) {
            for (int i = 0; i < 2; i++) {
                this.matrix[2-i][number] = equalmatrix[2-i][number];
                i++;
                vector.add(equalmatrix[2-i][number]);
            }
            this.matrix[2][number] = temporaryball;
            this.extraball = equalmatrix[0][number];
            vector.add(equalmatrix[0][number]);
        }

        if (check == 1) {
            for (int i = 0; i < 3; i++) {
                this.matrix[number][3-i] = equalmatrix[number][3-i];
                i++;
                vector.add(equalmatrix[number][3-i]);
            }
            this.matrix[number][3] = temporaryball;
            this.extraball = equalmatrix[number][0];
            vector.add(equalmatrix[number][0]);
        }

    return vector;}

}