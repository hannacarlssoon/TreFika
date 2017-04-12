package tda367.myapplication;

import org.junit.Test;

import tda367.myapplication.Model.Statistics;

import static org.junit.Assert.*;

/**
 * Created by hannacarlsson on 2017-04-12.
 */

public class StatisticsTest {

    @Test
    public void saveStatisticsHintTest() {
        Statistics s = new Statistics();
        s.saveStatisticsHint(0, 2);
        assertTrue(s.getStatisticsHint().size() == 1);
        assertTrue(s.getStatisticsHint().contains(2));
        assertTrue(!s.getStatisticsHint().contains(0));
    }

    @Test
    public void saveStatisticsKeyHint() {
        Statistics s = new Statistics();
        s.saveStatisticsKey(0, true);
        assertTrue(s.getStatisticsKey().size() == 1);
        assertTrue(s.getStatisticsKey().contains(true));
        assertTrue(!s.getStatisticsKey().contains(false));
        s.saveStatisticsKey(1, false);
        assertTrue(s.getStatisticsKey().contains(false));
    }

}
