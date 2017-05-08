package tda367.myapplication.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hannacarlsson on 2017-05-03.
 */

public class AccountManager {
    private static AccountManager instance;

    private Map<String, User> users = new HashMap<>();

    private User activeUser;

    public static final String FILENAME = "users.bin";

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME));
                instance = (AccountManager) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                instance = new AccountManager();
            } catch (ClassNotFoundException e) {
                instance = new AccountManager();
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    //Adds a new user
    public boolean addUser(String userName, String userPassword, String profilePictureUrl) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName, userPassword, profilePictureUrl));
            logIn(userName, userPassword);
            return true;
        }
        else {
            System.out.println("Username already exists");
            return false;
        }
    }

    //Method check username to password and if it matches logs in
    public boolean logIn(String userName, String userPassword) {
        if (users.get(userName).getUserPassword().equals(userPassword)) {
            activeUser = users.get(userName);
            return true;
        } else {
            System.out.println("User name or password is incorrect");
            return false;
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}