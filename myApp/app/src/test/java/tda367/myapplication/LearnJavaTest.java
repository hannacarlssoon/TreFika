package tda367.myapplication;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;

import static junit.framework.Assert.assertEquals;

/**
 * Created by madeleine on 2017-05-16.
 */

public class LearnJavaTest {

    @Test
    public void testInit(){
        LearnJava lj = new LearnJava();
        HashMap<String, LevelModel[]> hash = new HashMap<>();
        boolean beforeInit = lj.getHasInit();
        lj.init(hash);
        boolean hasInit = lj.getHasInit();
        assertEquals(hasInit, true);
        assertEquals(beforeInit, false);
    }
}
