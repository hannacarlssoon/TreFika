package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.Controller.FillInTheBlanks;
import tda367.myapplication.Model.ModelFillBlanks;

/**
 * Created by Sara on 2017-04-07.
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
