package tda367.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Hanna Carlsson
 * Responisibility: Handles the storing of the statistics data
 * Used by: User, StatisticsFragment, ActivityInfo, LevelActivity, PassedLevel, QuestionMultiChoice, WriteCode, FillInTheBlanks
 */

public class Statistics implements Serializable {

    //The names of the levels
    private final String[] titleNames = {"category11", "category12", "category13", "category14", "category15",
            "category21", "category22", "category23", "category24", "category25", "category31", "category32", "category33",
            "category34", "category35", "category41", "category42", "category43", "category44", "category45",
            };

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
        if (statisticsHint.size() <= levelIndex) {
            statisticsHint.add(isHintUsed);
        } else {
            statisticsHint.remove(levelIndex);
            statisticsHint.add(levelIndex, isHintUsed);
        }
    }

    //Stores if the user has to see key to complete the assignment
    public void saveStatisticsKey(String level, Boolean isKeyUsed) {
        int levelIndex = findIndex(level);
        if (statisticsKey.size() <= levelIndex) {
            statisticsKey.add(isKeyUsed);
        } else {
            statisticsKey.remove(levelIndex);
            statisticsKey.add(levelIndex, isKeyUsed);
        }
    }

    //Stores how long time it takes to complete each assignment
    public void saveStatisticsTime(String level) {
        int levelIndex = findIndex(level);
        if (statisticsTime.size() <= levelIndex) {
            statisticsTime.add(totalTime / 1000);
        } else {
            statisticsTime.remove(levelIndex);
            statisticsTime.add(levelIndex, totalTime / 1000);
        }
    }

    //Returns the index where the statistics should be saved
    public int findIndex(String level) {
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