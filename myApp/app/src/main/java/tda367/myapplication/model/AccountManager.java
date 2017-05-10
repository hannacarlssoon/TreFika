package tda367.myapplication.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import tda367.myapplication.service.ImageHandler;

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
    public void addUser(String userName, String userPassword) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName, userPassword));
            logIn(userName, userPassword);
        }
        else {
            System.out.println("Username already exists");
        }
    }

    //Method check username to password and if it matches logs in
    //TODO exceptions
    public void logIn(String userName, String userPassword) {
         try {
             activeUser = users.get(userName);
             activeUser.setProfilePicture(ImageHandler.loadImage(userName));
         } catch(NullPointerException e) {
             System.out.println("User doesn't exist");
            e.printStackTrace();
        }

    }

    public User getActiveUser() {
        return activeUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}