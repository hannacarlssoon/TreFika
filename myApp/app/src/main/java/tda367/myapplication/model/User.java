package tda367.myapplication.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class User implements Serializable {

    private String userName;
    private String userPassword;

    private static final long serialVersionUID =8733042957659618282L;

    private Statistics userStatistics;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        userStatistics = new Statistics();
    }

    //Updates a user
    public void updateUser(String newUserName, String newUserPassword) {
        if (userName != newUserName) {
            userName = newUserName;
        }
        if (userPassword != newUserPassword) {
            userPassword = newUserPassword;
        }
    }

    //Method is called whenever a level is completed and saves the statistics from the level
    public void saveStatistics(String level, boolean isKeyUsed, boolean isHintUsed) {
        userStatistics.saveStatisticsTime(level);
        userStatistics.saveStatisticsKey(level, isKeyUsed);
        userStatistics.saveStatisticsHint(level, isHintUsed);
        userStatistics.initData();
    }

    //Returns the username
    public String getUserName() {
        return userName;
    }

    //Returns the password
    public String getUserPassword() {
        return userPassword;
    }

    //Returns the statistics
    public Statistics getUserStatistics() {
        return userStatistics;
    }

}