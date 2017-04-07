package tda367.myapplication.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class Statistics {

    private static final String[] titleNames = {"Level11", "Level12", "Level13", "Level14", "Level15", "Level21",
            "Level22", "Level23", "Level24", "Level25", "Level31", "Level32", "Level33", "Level34",
            "Level35", "Level41", "Level42", "Level43", "Level44", "Level45", "Level51", "Level52",
            "Level53", "Level54", "Level55",};

    private static final List<String> titles = new ArrayList<>(Arrays.asList(titleNames));

    List<String> statisticsHint;
    List<String> statisticsTime;

}
