package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.model.WriteCode;


/**
 * @author Sara Kitzing
 */

public class WriteCodeTest {

    @Test
    public void testCheckAnswer() {
       WriteCode test = new WriteCode("fråga", "svar");
        Boolean check = test.checkAnswer("Svar");
        assertEquals(check, true);
    }



}
