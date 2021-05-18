package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

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

           pre.add('Y');
           pre.add('Y');
           pre.add('R');
           pre.add('P');



       assertEquals( pre , bowl.doMarket(1, 1));

        ArrayList<Character> dec = new ArrayList<>();

        dec.add('W');
        dec.add('R');
        dec.add('G');


        // assertEquals( dec , bowl.doMarket(2, 1));

    }


}

