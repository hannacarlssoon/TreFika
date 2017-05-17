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
        s.saveStatisticsHint("Level11", true);
        assertTrue(s.getStatisticsHint().size() == 1);
        assertTrue(s.getStatisticsHint().contains(true));
        assertTrue(!s.getStatisticsHint().contains(false));
        try {
            Boolean b = s.getStatisticsHint().get(2);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void saveStatisticsKeyTest() {
        Statistics s = new Statistics();
        s.saveStatisticsKey("Level11", false);
        assertTrue(s.getStatisticsKey().size() == 1);
        assertTrue(s.getStatisticsKey().contains(false));
        assertTrue(!s.getStatisticsKey().contains(true));
        s.saveStatisticsKey("Level12", false);
        assertTrue(s.getStatisticsKey().contains(false));
    }

    @Test
    public void saveStatisticsTimeTest() {
        Statistics s = new Statistics();
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime("Level11");
        assertTrue(s.getStatisticsTime().size() == 1);
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime("Level12");
        assertTrue(s.getStatisticsTime().get(0) == s.getStatisticsTime().get(1));
        assertTrue(s.getStatisticsTime().size() == 2);
        assertTrue(!s.getStatisticsTime().contains(null));
    }

    @Test
    public void findIndexTest() {
        Statistics s = new Statistics();
        assertTrue(s.findIndex("Level12") == 1);
        assertTrue(s.findIndex("Level21") == 5);
        assertTrue(s.findIndex("Level22") == 6);
    }

    @Test
    public void initDataTest() {

    }

}
