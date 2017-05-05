package tda367.myapplication.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-05-03.
 */

public class AccountManager {
    private static AccountManager instance;

    private Map<String, UserDatabase> users = new HashMap<>();

    private UserDatabase activeUser;

    public static final String FILENAME = "users.bin";

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME));
                instance = (AccountManager) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                instance = new AccountManager();
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void addUser(String userName, String userPassword, String profilePictureUrl) {
        if (!users.containsKey(userName)) {
            users.put(userName, new UserDatabase(userName, userPassword, profilePictureUrl));
            logIn(userName, userPassword);
        }
        else {
            System.out.println("Username already exists");
        }
    }

    public boolean logIn(String userName, String userPassword) {
        if (users.get(userName).getUserPassword().equals(userPassword)) {
            activeUser = users.get(userName);
            return true;
        } else {
            System.out.println("User name or password is incorrect");
            return false;
        }
    }

    public UserDatabase getActiveUser() {
        return activeUser;
    }

    public Map<String, UserDatabase> getUsers() {
        return users;
    }
}
