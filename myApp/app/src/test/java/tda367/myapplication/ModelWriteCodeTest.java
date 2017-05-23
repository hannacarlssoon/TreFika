package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.model.ModelWriteCode;


/**
 * @author Sara Kitzing
 */

public class ModelWriteCodeTest {

    @Test
    public void testCheckAnswer() {
       ModelWriteCode test = new ModelWriteCode("fråga", "svar");
        Boolean check = test.checkAnswer("Svar");
        assertEquals(check, true);
    }



}
