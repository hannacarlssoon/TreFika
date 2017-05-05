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

    public User(String userName, String userPassword, String profilePicture) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.profilePicture = profilePicture;
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
    public void saveStatistics() {

    }

    //Returns the username
    public String getUserName() {
        return userName;
    }

    //Returns the password
    public String getUserPassword() {
        return userPassword;
    }
    
}