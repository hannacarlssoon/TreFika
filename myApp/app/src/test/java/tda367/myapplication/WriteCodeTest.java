package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.Model.WriteCode;

/**
 * Created by sarakitzing on 2017-05-09.
 */

public class WriteCodeTest {

    @Test
    public void testCheckAnswer() {
        WriteCode c = new WriteCode("hej", "1 2 3");
        boolean check = c.checkAnswer("for(int i = 1; i<4; i++){ print(i); }");
        boolean check2 = c.checkAnswer("for(int i = 0; i<4; i++){ print(i); }");
        boolean check3 = c.checkAnswer("for(int i = 1; i<4; i++{ print(i); }");
        assertEquals(check, true);
        assertEquals(check2, false);
        assertEquals(check3, false);

    }

}
