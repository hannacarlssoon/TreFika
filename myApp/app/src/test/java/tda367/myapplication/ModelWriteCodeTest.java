package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.model.ModelWriteCode;


/**
 * @author Sara Kitzing
 */

public class ModelWriteCodeTest {

    //Tests the CheckAnswer-method in ModelWriteCode
    @Test
    public void testCheckAnswer() {
       ModelWriteCode test = new ModelWriteCode("fråga", "svar");
        boolean check = test.checkAnswer("Svar");
        boolean check2 = test.checkAnswer("svar");
        boolean check3 = test.checkAnswer("savr");
        assertEquals(check, true);
        assertEquals(check2, true);
        assertEquals(check3, false);

    }



}
