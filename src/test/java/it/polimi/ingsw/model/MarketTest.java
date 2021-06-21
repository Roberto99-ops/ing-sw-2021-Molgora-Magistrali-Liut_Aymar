package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MarketTest {

    @Test

    public void checkDoMarket() throws FileNotFoundException {

        Market bowl = new Market();
        char [][] pimpa = new char[3][4];

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

        // bggp pryy wwww

        int i = 1;

       bowl.setExtraball(obj.get(0));


            for (int j = 0; j < 3; j ++) {
                for (int k = 0; k < 4; k++) {
                    pimpa [j][k] = obj.get(i);
                    i ++;
            }
        }


        bowl.setMatrix(pimpa);
        bowl.printMatrix();



        assertEquals(bowl.getExtraball(), 'B');
        assertEquals(bowl.getMatrix(), pimpa);

        ArrayList<Character> pre = new ArrayList<>();

        pre.add('W');
        pre.add('P');
        pre.add('B');

        assertEquals (pre, bowl.doMarket(2, 0));
        bowl.printMatrix();

        ArrayList<Character> dec = new ArrayList<>();

        dec.add('P');
        dec.add('G');
        dec.add('G');
        dec.add('P');

        bowl.printMatrix();

        assertEquals( dec , bowl.doMarket(1, 0));


        bowl.setResourceinMarket(1,3,'P');
        bowl.setExtraball('Y');

        ArrayList<Character> red = new ArrayList<>();

        red.add('P');
        red.add('Y');
        red.add('R');
        red.add('W');

        assertEquals( red, bowl.doMarket(1, 1));

        bowl.printMatrix();

    }


}


