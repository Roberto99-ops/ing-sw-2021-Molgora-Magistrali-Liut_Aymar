package it.polimi.ingsw;

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
     */

    public  ArrayList<Character> doMarket(int check, int number) {
        char temporaryball = this.extraball;
        char[][] equalmatrix = new char[3][4];
        equalmatrix = this.matrix;
        ArrayList<Character> vector = new ArrayList<Character>();

        if (check == 1) {
            for (int i = 0; i < 2; i++) {
                this.matrix[i + 1][number] = equalmatrix[i][number];
                i++;
                vector.add(equalmatrix[i][number]);
            }
            this.matrix[0][number] = temporaryball;
            this.extraball = equalmatrix[2][number];
            vector.add(equalmatrix[2][number]);
        }

        if (check == 1) {
            for (int i = 0; i < 2; i++) {
                this.matrix[2-i][number] = equalmatrix[2-i][number];
                i++;
                vector.add(equalmatrix[2-i][number]);
            }
            this.matrix[2][number] = temporaryball;
            this.extraball = equalmatrix[0][number];
            vector.add(equalmatrix[0][number]);
        }

        if (check == 3) {
            for (int i = 0; i < 3; i++) {
                this.matrix[number][i + 1] = equalmatrix[number][i];
                i++;
                vector.add(equalmatrix[number][i]);
            }
            this.matrix[number][0] = temporaryball;
            this.extraball = equalmatrix[number][3];
            vector.add(equalmatrix[number][3]);
        }

        if (check == 4) {
            for (int i = 0; i < 3; i++) {
                this.matrix[number][3-i] = equalmatrix[number][3-i];
                i++;
                vector.add(equalmatrix[number][3-i]);
            }
            this.matrix[number][3] = temporaryball;
            this.extraball = equalmatrix[number][0];
            vector.add(equalmatrix[number][0]);
        }

    }

    return vector;

}