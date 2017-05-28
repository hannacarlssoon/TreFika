package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import tda367.myapplication.model.ModelFillBlanks;

/**
 * @author Sara Kitzing
 */

public class ModelFillBlanksTest {

    //Tests the CheckAnswer-method in ModelFillBlanks
    @Test
    public void testCheckAnswer() {
        List<String> list = new ArrayList<>();
        list.add("häst");
        list.add("häst,hund,ko");
        list.add("ko");
        list.add("test");
        list.add("tets");
        list.add("test");
        ModelFillBlanks f = new ModelFillBlanks(list);
        boolean check = f.checkAnswer("häst,hund,ko");
        boolean check2 = f.checkAnswer("hej,hund,ko");
        boolean check3 = f.checkAnswer("Häst,hund,KO");
        assertEquals(check, true);
        assertEquals(check2,false);
        assertEquals(check3, true);
    }



}
