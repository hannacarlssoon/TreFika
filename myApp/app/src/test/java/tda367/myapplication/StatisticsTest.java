package tda367.myapplication;

import org.junit.Test;

import java.util.List;

import tda367.myapplication.model.Statistics;

import static org.junit.Assert.*;

/**
 * Created by hannacarlsson on 2017-04-12.
 */

public class StatisticsTest {
/*
    @Test
    public void saveStatisticsHintTest() {
        Statistics s = new Statistics();
        s.saveStatisticsHint("category11", true);
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
        s.saveStatisticsKey("category11", false);
        assertTrue(s.getStatisticsKey().size() == 1);
        assertTrue(s.getStatisticsKey().contains(false));
        assertTrue(!s.getStatisticsKey().contains(true));
        s.saveStatisticsKey("category12", false);
        assertTrue(s.getStatisticsKey().contains(false));
    }

    @Test
    public void saveStatisticsTimeTest() {
        Statistics s = new Statistics();
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime("category11");
        assertTrue(s.getStatisticsTime().size() == 1);
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime("category12");
        assertTrue(s.getStatisticsTime().get(0) == s.getStatisticsTime().get(1));
        assertTrue(s.getStatisticsTime().size() == 2);
        assertTrue(!s.getStatisticsTime().contains(null));
    }

    @Test
    public void findIndexTest() {
        Statistics s = new Statistics();
        assertTrue(s.findIndex("category12") == 1);
        assertTrue(s.findIndex("category21") == 5);
        assertTrue(s.findIndex("category22") == 6);
    }

    @Test
    public void initDataTest() {
        Statistics s = new Statistics();
        s.startTimer();
        s.stopTimer();
        s.saveStatisticsTime("category11");
        s.saveStatisticsKey("category11", false);
        s.saveStatisticsHint("category11", true);
        s.initData();
        assertTrue(s.getHintHashMap().containsKey("category1"));
        assertTrue(s.getTimeHashMap().containsKey("category2"));
    }
    */

}
