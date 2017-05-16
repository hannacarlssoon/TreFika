package tda367.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Handles the storing of the statistics data
 * Created by hannacarlsson on 2017-04-07.
 */

public class Statistics implements Serializable {

    //The names of the levels
    private final String[] titleNames = {"Level11", "Level12", "Level13", "Level14", "Level15",
            "Level21", "Level22", "Level23", "Level24", "Level25", "Level31", "Level32", "Level33",
            "Level34", "Level35", "Level41", "Level42", "Level43", "Level44", "Level45", "Level51",
            "Level52", "Level53", "Level54", "Level55",};

    private final List<String> titles = new ArrayList<>(Arrays.asList(titleNames));

    //Lists of the statistics
    private List<Boolean> statisticsHint;
    private List<Long> statisticsTime;
    private List<Boolean> statisticsKey;

    //Variables which keep track of the time
    private long startTime;
    private long totalTime;

    //Initializes the statistics lists
    public Statistics() {
        statisticsHint = new ArrayList<>();
        statisticsTime = new ArrayList<>();
        statisticsKey = new ArrayList<>();
    }

    //Stores how many hints you need to complete the assignment
    public void saveStatisticsHint(String level, Boolean isHintUsed) {
        int levelIndex = findIndex(level);
        statisticsHint.add(levelIndex, isHintUsed);
    }

    //Stores if the user has to see key to complete the assignment
    public void saveStatisticsKey(String level, Boolean isKeyUsed) {
        int levelIndex = findIndex(level);
        statisticsKey.add(levelIndex, isKeyUsed);
    }

    //Stores how long time it takes to complete each assignment
    public void saveStatisticsTime(String level) {
        int levelIndex = findIndex(level);
        statisticsTime.add(levelIndex, totalTime/1000);
    }

    //Returns the index where the statistics should be saved
    private int findIndex(String level) {
        for (int i = 0; i < titles.size(); i++) {
            return titles.indexOf(level);
        }
        return 0;
    }

    //Starts the timer
    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    //Calculates how long time to took to complete assignment
    public void stopTimer() {
        totalTime = System.currentTimeMillis() - startTime;
    }

    //Gets the statisticsHint list
    public List<Boolean> getStatisticsHint() {
        return statisticsHint;
    }

    //Gets the statisticsKey list
    public List<Boolean> getStatisticsKey() {
        return statisticsKey;
    }

    //Gets the statisticsTime list
    public List<Long> getStatisticsTime() {
        return statisticsTime;
    }
}