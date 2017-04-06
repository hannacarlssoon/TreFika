package tda367.myapplication;

/**
 * Created by hannacarlsson on 2017-04-06.
 */

public interface User {

    //Adds a new user
    void addUser(String userName, String userPassword, String profilePictureUrl);

    //Updates a user
    void updateUser(String userName, String userPassword, String profilePictureUrl);

    //Gets the user information
    User getUser(String userName);

    //Method check username to password and if it matches logs in
    boolean LogIn(String userName, String userPassword);

    //Method is called whenever a level is completed and saves the statistics from the level
    void saveStatistics();

}
