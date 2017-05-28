package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import tda367.myapplication.model.MultiChoice;

import static junit.framework.Assert.assertEquals;

/**
 * Created by madeleine on 2017-05-13.
 */

public class MultiChoiceTest {

    @Test
    public void testCheckAnswer(){
        List<String> list = new ArrayList<>();
        list.add("fråga");
        list.add("svar");
        list.add("test");
        list.add("tets");
        list.add("test");
        list.add("tets");
        MultiChoice mc = new MultiChoice(list);
        boolean check1 = mc.checkAnswer("svar");
        boolean check2 = mc.checkAnswer("felsvar");
        assertEquals(check1, true);
        assertEquals(check2, false);
    }
}
