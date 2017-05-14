package tda367.myapplication.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class User implements Serializable {

    private static User user;
    private String userName;
    private String userPassword;

    private static final long serialVersionUID =8733042957659618282L;

    private Statistics userStatistics;

    //TODO kolla ifall statistic fungerar att spara
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        userStatistics = new Statistics();
    }

    //Updates a user
    public void updateUser(String newUserName, String newUserPassword, Drawable newProfilePictureUrl) {
        if (userName != newUserName) {
            userName = newUserName;
        }
        if (userPassword != newUserPassword) {
            userPassword = newUserPassword;
        }
        /*if (profilePicture.equals(newProfilePictureUrl)) {
            profilePicture = newProfilePictureUrl;
        }*/
    }

    //Method is called whenever a level is completed and saves the statistics from the level
    public void saveStatistics(int levelIndex, boolean isKeyUsed, Integer nHints) {
        userStatistics.saveStatisticsTime(levelIndex);
        userStatistics.saveStatisticsKey(levelIndex, isKeyUsed);
        userStatistics.saveStatisticsHint(levelIndex, nHints);
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

    //Sets the profile piture when it's chosen
    public void setProfilePicture(Drawable profilePicture) {
        /*this.profilePicture = profilePicture;*/
    }
    
}