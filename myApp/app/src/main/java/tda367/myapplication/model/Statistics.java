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

    private HashMap<String, List<Boolean>> keyHashMap;
    private HashMap<String, List<Boolean>> hintHashMap;
    private HashMap<String, List<Long>> timeHashMap;

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
        keyHashMap = new HashMap<>();
        hintHashMap = new HashMap<>();
        timeHashMap = new HashMap<>();
        keyHashMap.put("category1", new ArrayList<Boolean>());
        keyHashMap.put("category2", new ArrayList<Boolean>());
        keyHashMap.put("category3", new ArrayList<Boolean>());
        hintHashMap.put("category1", new ArrayList<Boolean>());
        hintHashMap.put("category2", new ArrayList<Boolean>());
        hintHashMap.put("category3", new ArrayList<Boolean>());
        timeHashMap.put("category1", new ArrayList<Long>());
        timeHashMap.put("category2", new ArrayList<Long>());
        timeHashMap.put("category3", new ArrayList<Long>());
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
    public int findIndex(String level) {
        for (int i = 0; i < titles.size(); i++) {
            return titles.indexOf(level);
        }
        return 0;
    }

    public void initData() {
        for (int i = 0; i < statisticsKey.size(); i++) {
            if (i < 5) {
                hintHashMap.put("category1", makeCategory1List(statisticsHint, 5));
                keyHashMap.put("category1", makeCategory1List(statisticsKey, 5));
                timeHashMap.put("category1", makeCategory1List(statisticsTime, 5));
            } else if (i < 10) {
                hintHashMap.put("category2", makeCategory2List(statisticsHint, 10));
                keyHashMap.put("category2", makeCategory2List(statisticsKey, 10));
                timeHashMap.put("category2", makeCategory2List(statisticsTime, 10));
            } else if (i < 15) {
                hintHashMap.put("category3", makeCategory3List(statisticsHint, 15));
                keyHashMap.put("category3", makeCategory3List(statisticsKey, 15));
                timeHashMap.put("category3", makeCategory3List(statisticsTime, 15));
            } else if (i < 20) {
                hintHashMap.put("category3", makeCategory4List(statisticsHint));
                keyHashMap.put("category3", makeCategory4List(statisticsKey));
                timeHashMap.put("category3", makeCategory4List(statisticsTime));
            }
        }
    }

    private List makeCategory1List(List list, int stop) {
        List<Object> tempHint = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            if (j >= stop) {
                break;
            }
            try {
                tempHint.add(j, list.get(j));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return tempHint;
    }

    private List makeCategory2List(List list, int stop) {
        List<Object> tempHint = new ArrayList<>();
        for (int j = 5; j < list.size() - 5; j++) {
            if (j >= stop) {
                break;
            }
            try {
                tempHint.add(j, list.get(j));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return tempHint;
    }

    private List makeCategory3List(List list, int stop) {
        List<Object> tempHint = new ArrayList<>();
        for (int j = 10; j < list.size() - 10; j++) {
            if (j >= stop)
            try {
                tempHint.add(j, list.get(j));
            } catch (IndexOutOfBoundsException e) {
                break;
            }

        }
        return tempHint;
    }

    private List makeCategory4List(List list) {
        List<Object> tempHint = new ArrayList<>();
        for (int j = 15; j < list.size() - 15; j++) {
            try {
                tempHint.add(j, list.get(j));
            } catch (IndexOutOfBoundsException e) {
                break;
            }

        }
        return tempHint;
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

    public HashMap<String, List<Boolean>> getKeyHashMap() {
        return keyHashMap;
    }

    public HashMap<String, List<Boolean>> getHintHashMap() {
        return hintHashMap;
    }

    public HashMap<String, List<Long>> getTimeHashMap() {
        return timeHashMap;
    }
}