package tda367.myapplication;

import org.junit.Test;

import tda367.myapplication.model.Statistics;

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
    public void saveStatisticsKeyTest() {
        Statistics s = new Statistics();
        s.saveStatisticsKey(0, true);
        assertTrue(s.getStatisticsKey().size() == 1);
        assertTrue(s.getStatisticsKey().contains(true));
        assertTrue(!s.getStatisticsKey().contains(false));
        s.saveStatisticsKey(1, false);
        assertTrue(s.getStatisticsKey().contains(false));
    }

    @Test
    public void saveStatisticsTimeTest() {
        Statistics s = new Statistics();
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime(0);
        assertTrue(s.getStatisticsTime().size() == 1);
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime(1);
        assertTrue(s.getStatisticsTime().get(0) == s.getStatisticsTime().get(1));
        assertTrue(s.getStatisticsTime().size() == 2);
        assertTrue(!s.getStatisticsTime().contains(null));
    }

}
