package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.model.ModelFillBlanks;

/**
 * @author Sara Kitzing
 */

public class FillBlanksTest {

    @Test
    public void testCheckAnswer() {
        ModelFillBlanks f = new ModelFillBlanks("hej", "häst,hund,ko");
        boolean check = f.checkAnswer("häst,hund,ko");
        boolean check2 = f.checkAnswer("hej,hund,ko");
        assertEquals(check, true);
        assertEquals(check2,false);
    }



}
