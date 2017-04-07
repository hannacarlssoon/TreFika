package tda367.myapplication.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserDatabase implements User {

    private String profilePicture;
    private String userName;
    private String userPassword;

    private Map<String, List<String>> users = new HashMap<>();

    private Statistics userStatistics;

    @Override
    public void addUser(String userName, String userPassword, String profilePictureUrl) {

            if (!users.containsKey(userName)) {
                List<String> userInfo = new ArrayList<>();
                userInfo.add(userPassword);
                userInfo.add(profilePictureUrl);
                users.put(userName, userInfo);
                logIn(userName, userPassword);
            }
            else {
                System.out.println("Username already exists");
            }
    }

    @Override
    public void updateUser(String newUserName, String newUserPassword, String newProfilePictureUrl) {
        if (userName != newUserName) {
            List<String> value = users.remove(userName);
            users.put(newUserName, value);
            userName = newUserName;
        }
        if (userPassword != newUserPassword || profilePicture != newProfilePictureUrl) {
            if (userPassword != newUserPassword && profilePicture != newProfilePictureUrl) {
                List<String> userInfo = new ArrayList<>();
                userInfo.add(newUserPassword);
                userInfo.add(newProfilePictureUrl);
                users.put(userName, userInfo);
                userPassword = newUserPassword;
                profilePicture = newProfilePictureUrl;
            }
            else if (userPassword != newUserPassword && profilePicture == newProfilePictureUrl) {
                List<String> userInfo = new ArrayList<>();
                userInfo.add(newUserPassword);
                userInfo.add(profilePicture);
                users.put(userName, userInfo);
                userPassword = newUserPassword;
            }
            else {
                List<String> userInfo = new ArrayList<>();
                userInfo.add(userPassword);
                userInfo.add(newProfilePictureUrl);
                users.put(userName, userInfo);
                profilePicture = newProfilePictureUrl;
            }
        }
    }

    @Override
    public User getUser(String userName) {
        return this;
    }

    @Override
    public boolean logIn(String userName, String userPassword) {
        if (users.get(userName).get(0).equals(userPassword)) {
            this.userName = userName;
            this.userPassword = userPassword;
            this.profilePicture = users.get(userName).get(1);
            return true;
        } else {
            System.out.println("User name or password is incorrect");
            return false;
        }
    }

    @Override
    public void saveStatistics() {

    }

    public Map<String, List<String>> getUsers () {
        return users;
    }
    
}