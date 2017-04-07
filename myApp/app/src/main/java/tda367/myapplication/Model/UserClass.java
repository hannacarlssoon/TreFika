package tda367.myapplication.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserClass implements User {

    private String profilePicture;
    private String userName;
    private String userPassword;

    private Map<String, String> users;
    private static final String[] titleNames = {"Level11", "Level12", "Level13", "Level14", "Level15", "Level21",
            "Level22", "Level23", "Level24", "Level25", "Level31", "Level32", "Level33", "Level34",
            "Level35", "Level41", "Level42", "Level43", "Level44", "Level45", "Level51", "Level52",
            "Level53", "Level54", "Level55",};
    private static final List<String> titles = new ArrayList<>(Arrays.asList(titleNames));
    //Statistics
    List<String> statisticsHint;
    List<String> statisticsTime;



    public UserClass() {

    }

    @Override
    public void addUser(String userName, String userPassword, String profilePictureUrl) {

            if (!users.containsKey(userName)) {
                users.put(userName, userPassword);
                profilePicture = profilePictureUrl;
            }
            else {
                System.out.println("Username already exists");
            }
    }

    @Override
    public void updateUser(String newUserName, String newUserPassword, String newProfilePictureUrl) {
        if (userName != newUserName) {
            String value = users.remove(userName);
            users.put(newUserName, value);
        }
    }

    @Override
    public User getUser(String userName) {
        return this;
    }

    @Override
    public boolean LogIn(String userName, String userPassword) {
        if (users.get(userName).equals(userPassword)) {

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void saveStatistics() {

    }
}
