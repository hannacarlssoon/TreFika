package tda367.myapplication.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserDatabase {

    private static UserDatabase user;

    private String profilePicture;
    private String userName;
    private String userPassword;

    private Statistics userStatistics;

    public UserDatabase(String userName, String userPassword, String profilePicture) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.profilePicture = profilePicture;
    }



    public void updateUser(String newUserName, String newUserPassword, String newProfilePictureUrl) {
        if (userName != newUserName) {
            userName = newUserName;
        }
        if (userPassword != newUserPassword || profilePicture != newProfilePictureUrl) {
            if (userPassword != newUserPassword && profilePicture != newProfilePictureUrl) {
                userPassword = newUserPassword;
                profilePicture = newProfilePictureUrl;
            }
            else if (userPassword != newUserPassword && profilePicture == newProfilePictureUrl) {
                userPassword = newUserPassword;
            }
            else {
                profilePicture = newProfilePictureUrl;
            }
        }
    }

    public void saveStatistics() {

    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
    
}