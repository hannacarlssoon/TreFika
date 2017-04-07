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
        ModelFillBlanks f = new ModelFillBlanks("hej", "jag,heter,kitzing");
        boolean check = f.checkAnswer("jag,heter,kitzing");
        boolean check2 = f.checkAnswer("hej,heter,kitzing");
        assertEquals(check, true);
        assertEquals(check2,false);
    }



}
