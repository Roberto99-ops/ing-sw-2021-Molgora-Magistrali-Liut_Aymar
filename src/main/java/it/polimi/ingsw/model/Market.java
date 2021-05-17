package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Market {

    private char[][] matrix = new char[3][4];
    private char extraball;

    /**
     this method randomize market
     */

    public void randomizeMarket () {
        ArrayList<Character> obj = new ArrayList<>();
            obj.add('B');
            obj.add('B');
            obj.add('G');
            obj.add('G');
            obj.add('P');
            obj.add('P');
            obj.add('R');
            obj.add('Y');
            obj.add('Y');
            obj.add('W');
            obj.add('W');
            obj.add('W');
            obj.add('W');
            Collections.shuffle(obj);
            this.extraball = obj.get(0);
            int i = 1;

            while (i < 13) {
                for (int j = 0; j < 3; j ++) {
                    for (int k = 0; k < 4; k++) {
                        this.matrix [j][k] = obj.get(i);
                        i ++;
                    }
                }
            }
    }


    /**
     inserts the extraball into the matrix + new extraball
     check tells me if it is column / row from above / below and return
     */


    public  ArrayList<Character> doMarket(int check, int number) {
        char temporaryball = this.extraball;
        char[][] equalmatrix = new char[3][4];


        for (int j = 0; j < 3; j ++) {
            for (int k = 0; k < 4; k++) {
                equalmatrix [j][k] = this.matrix [j][k];
            }
        }

        ArrayList<Character> vector = new ArrayList<Character>();

        if (check == 2) {
            for (int i = 0; i < 2; i++) {
                this.matrix[1-i][number] = equalmatrix[2-i][number];
                vector.add(equalmatrix[2-i][number]);
            }
            this.matrix[2][number] = temporaryball;
            this.extraball = equalmatrix[0][number];
            vector.add(equalmatrix[0][number]);
        }

        if (check == 1) {
            for (int i = 0; i < 3; i++) {
                this.matrix[number][2-i] = equalmatrix[number][3-i];
                vector.add(equalmatrix[number][3-i]);
            }
            this.matrix[number][3] = temporaryball;
            this.extraball = equalmatrix[number][0];
            vector.add(equalmatrix[number][0]);
        }

        return vector; }



    public void printMatrix () {

            for (int j = 0; j < 3; j ++) {
                for (int k = 0; k < 4; k++) {
                    System.out.println (this.matrix [j][k]);
                }
                System.out.println ("\n");
            }
        }



    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public char getExtraball() {
        return extraball;
    }

    public void setExtraball(char extraball) {
        this.extraball = extraball;
    }


}


