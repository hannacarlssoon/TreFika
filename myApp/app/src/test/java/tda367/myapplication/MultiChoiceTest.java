package tda367.myapplication;

import org.junit.Test;
import tda367.myapplication.model.MultiChoice;

import static junit.framework.Assert.assertEquals;

/**
 * Created by madeleine on 2017-05-13.
 */

public class MultiChoiceTest {

    @Test
    public void testCheckAnswer(){
        MultiChoice mc = new MultiChoice("fråga", "svar", "alternativ");
        boolean check1 = mc.checkAnswer("svar");
        boolean check2 = mc.checkAnswer("felsvar");
        assertEquals(check1, true);
        assertEquals(check2, false);
    }
}
