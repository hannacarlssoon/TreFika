package tda367.myapplication;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.model.LevelModel;
import tda367.myapplication.model.Query;

import static junit.framework.Assert.assertEquals;

/**
 * Created by madeleine on 2017-05-16.
 */

public class LevelModelTest {

    @Test
    public void testInit(){
        LevelModel lj = new LevelModel();
        HashMap<String, Query[]> hash = new HashMap<>();
        boolean beforeInit = lj.getHasInit();
        lj.init(hash);
        boolean hasInit = lj.getHasInit();
        assertEquals(hasInit, true);
        assertEquals(beforeInit, false);
    }
}
