package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import tda367.myapplication.model.ModelWriteCode;


/**
 * @author Sara Kitzing
 */

public class ModelWriteCodeTest {

    //Tests the CheckAnswer-method in ModelWriteCode
    @Test
    public void testCheckAnswer() {
        List<String> list = new ArrayList<>();
        list.add("fråga");
        list.add("svar");
        list.add("test");
        list.add("tets");
        list.add("test");
        list.add("tets");
       ModelWriteCode test = new ModelWriteCode(list);
        boolean check = test.checkAnswer("Svar");
        boolean check2 = test.checkAnswer("svar");
        boolean check3 = test.checkAnswer("savr");
        assertEquals(check, true);
        assertEquals(check2, true);
        assertEquals(check3, false);

    }



}
