package tda367.myapplication.Model;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.security.auth.callback.Callback;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class Statistics {

    private static final String[] titleNames = {"Level11", "Level12", "Level13", "Level14", "Level15", "Level21",
            "Level22", "Level23", "Level24", "Level25", "Level31", "Level32", "Level33", "Level34",
            "Level35", "Level41", "Level42", "Level43", "Level44", "Level45", "Level51", "Level52",
            "Level53", "Level54", "Level55",};

    private static final List<String> titles = new ArrayList<>(Arrays.asList(titleNames));

    private List<Integer> statisticsHint;
    private List<Long> statisticsTime;
    private List<Boolean> statisticsKey;

    //Initializes the statistics lists
    public Statistics() {
        statisticsHint = new ArrayList<Integer>();
        statisticsTime = new ArrayList<Long>();
        statisticsKey = new ArrayList<Boolean>();
    }

    //Stores how many hints you need to complete the assignment
    public void saveStatisticsHint(int levelIndex, Integer nHints) {
        statisticsHint.add(levelIndex, nHints);
    }

    //Stores if the user has to see key to complete the assignment
    public void saveStatisticsKey(int levelIndex, Boolean isKeyUsed) {
        statisticsKey.add(levelIndex, isKeyUsed);
    }

    //Stores how long time it takes to complete each assignment
    public void saveStatisticsTime() {
        //Make a timer
    }

    //Gets the statisticsHint list
    public List<Integer> getStatisticsHint() {
        return statisticsHint;
    }

    //Gets the statisticsKey list
    public List<Boolean> getStatisticsKey() {
        return statisticsKey;
    }

}
