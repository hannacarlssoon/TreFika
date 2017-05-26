package tda367.myapplication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hannacarlsson
 * Responsibility: Handles the log in, log out and add new users methods
 * Uses: User
 * Used by: MyPageFragment, PlayFragment, SignInFragment, SignUpActivity, StatisticsFragment
 * UpdateUserFragment, MainActivity, ActivityInfo, LevelActivity, PassedLevel
 */

public class AccountManager implements Serializable {

    private static AccountManager instance;
    private Map<String, User> users = new HashMap<>();
    private User activeUser;

    //An id, needed when implementing the Serilizable interface
    private static final long serialVersionUID =7116880369697727491L;

    private AccountManager() {}

    public static AccountManager getInstance() {
        return instance;
    }

    //Initialises the AccountManager, loads it from file, only called when App starts
    public static void initInstance(AccountManager manager) {
        if (instance == null) {
            if (manager != null) {
                instance = manager;
            } else {
                instance = new AccountManager();
            }
        }
    }

    //Adds a new user
    public void addUser(String userName, String userPassword) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName, userPassword));
            setUser(userName);
        }
    }

    public void updateUser(String newUserName, String newPassword) {
        users.remove(activeUser.getUserName());
        activeUser.updateUser(newUserName, newPassword);
        users.put(newUserName, activeUser);
        setUser(newUserName);
    }

    //Sets the active user
    public void setUser(String userName) {
        activeUser = users.get(userName);
    }

    //Logs the user out
    public void logOut() {
        activeUser = null;
    }

    //Returns the active user
    public User getActiveUser() {
        return activeUser;
    }

    //Returns the users hashmap
    public Map<String, User> getUsers() {
        return users;
    }
}