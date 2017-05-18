package tda367.myapplication;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;

/**
 * Created by madeleine on 2017-05-16.
 */

public class LearnJavaTest {

    @Test
    public void testInit(){
        LearnJava lj = new LearnJava();
        HashMap<String, LevelModel[]> hash = new HashMap<>();
        lj.init(hash);

    }
}
