package tda367.myapplication.Model;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class User {

    private static User user;

    private String profilePicture;
    private String userName;
    private String userPassword;

    private Statistics userStatistics;

    //TODO kolla ifall statistic fungerar att spara
    public User(String userName, String userPassword, String profilePicture) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.profilePicture = profilePicture;
        userStatistics = new Statistics();
    }

    //Updates a user
    public void updateUser(String newUserName, String newUserPassword, String newProfilePictureUrl) {
        if (userName != newUserName) {
            userName = newUserName;
        }
        if (userPassword != newUserPassword) {
            userPassword = newUserPassword;
        }
        if (profilePicture != newProfilePictureUrl) {
            profilePicture = newProfilePictureUrl;
        }
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
    
}