package tda367.myapplication.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-04-06.
 */

public interface User {

    //Adds a new user
    void addUser(String userName, String userPassword, String profilePictureUrl);

    //Updates a user
    void updateUser(String newUserName, String newUserPassword, String newProfilePictureUrl);

    //Method check username to password and if it matches logs in
    boolean logIn(String userName, String userPassword);

    //Method is called whenever a level is completed and saves the statistics from the level
    void saveStatistics();

    //Gets the users name
    String getUserName();

    //Gets all the users "database"
    Map<String, List<String>> getUsers();

}
