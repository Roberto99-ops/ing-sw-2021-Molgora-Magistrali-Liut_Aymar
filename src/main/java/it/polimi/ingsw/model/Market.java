package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Market implements Serializable {

    //Matrix: Market's structure
    private char[][] matrix = new char[3][4];
    private char extraBall;


    /**
     * This method initialize and randomize market
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
            this.extraBall = obj.get(0);
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
     * ONLY USED FOR TESTING
     * Prints all resources in strongbox
     */

    public void printMatrix () {

        for (int j = 0; j < 3; j ++) {
            for (int k = 0; k < 4; k++) {
                System.out.println (this.matrix [j][k]);
            }
            System.out.println ("\n");
        }
    }


    /**
     * Getter and setter
     */

    public char[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
    public char getExtraBall() {
        return extraBall;
    }
    public void setExtraBall(char extraBall) {
        this.extraBall = extraBall;
    }


    /**
     * This method inserts a resource in market (the resource added is the extraball)
     * @param row in which insert the resource
     * @param column in which insert the resource
     * @param input resource (char) that have to be inserted
     */

    public void setResourceInMarket(int row, int column, char input) {
        this.matrix[row][column] = input;
    }


    /**
     * This method inserts the extraBall inside the matrix: doing so, the extraBall changes its type of resource
     * "Check" helps the Game to understand where I want to put the extraBall: in a column / row, from above / below.
     * @param check tells if it is column / row
     * @param number tells if it is from above / below
     * @return ArrayList<Character> that replace the row/column I've chosen at the beginning
     */


    public ArrayList<Character> doMarket(int check, int number) {
        char temporaryBall = this.extraBall;
        char[][] equalMatrix = new char[3][4];


        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 4; k++) {
                equalMatrix[j][k] = this.matrix[j][k];
            }
        }

        ArrayList<Character> vector = new ArrayList<Character>();



        if (check == 2) {

            for (int i = 1; i < 3; i++) {
                this.matrix[2 - i][number] = equalMatrix[3 - i][number];
                vector.add(equalMatrix[3-i][number]);
            }

            vector.add(equalMatrix[0][number]);
            this.matrix[2][number] = temporaryBall;
            this.extraBall = equalMatrix[0][number];
        }


            if (check == 1) {


                for (int i = 1; i < 4; i++) {
                    this.matrix[number][3-i] = equalMatrix[number][4-i];
                    vector.add(equalMatrix[number][4-i]);
                }

                vector.add(equalMatrix[number][0]);
                this.matrix[number][3] = temporaryBall;
                this.extraBall = equalMatrix[number][0];

            }

            return vector;
        }


}


