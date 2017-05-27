package tda367.myapplication.model;

import java.io.Serializable;

/**
 * @author Hanna Carlsson
 * Responsibility: Is the User object, handles changing of user information,
 * and saving of statistics specific for the user
 * Uses: Statistics
 * Used by: AccountManager, MyPageFragment, PlayFragment, SignInFragment, StatisticsFragment,
 * UpdateUserFragment, MainActivity, ActivityInfo, LevelActivity, PassedLevel, QuestionMultiChoice
 */

public class User implements Serializable {

    private String userName;
    private String userPassword;

    //Id needed for the serilizable interface
    private static final long serialVersionUID =8733042957659618282L;

    private Statistics userStatistics;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        userStatistics = new Statistics();
    }

    //Updates a user
    public void updateUser(String newUserName, String newUserPassword) {
        if (!userName.equals(newUserName)) {
            userName = newUserName;
        }
        if (!userPassword.equals(newUserPassword)) {
            userPassword = newUserPassword;
        }
    }

    //Method is called whenever a level is completed and saves the statistics from the level
    public void saveStatistics(String level, boolean isKeyUsed, boolean isHintUsed) {
        userStatistics.saveStatisticsTime(level);
        userStatistics.saveStatisticsKey(level, isKeyUsed);
        userStatistics.saveStatisticsHint(level, isHintUsed);
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