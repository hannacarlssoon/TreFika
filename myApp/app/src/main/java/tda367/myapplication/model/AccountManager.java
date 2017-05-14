package tda367.myapplication.model;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.service.UserFileReader;

/**
 * Created by hannacarlsson on 2017-05-03.
 * Handles the log in and add new users methods
 */

public class AccountManager implements Serializable {

    private static AccountManager instance;
    private Map<String, User> users = new HashMap<>();
    private User activeUser;

    //Needed when implementing the Serilizable interface
    private static final long serialVersionUID =7116880369697727491L;

    private AccountManager() {}

    public static AccountManager getInstance() {
        return instance;
    }

    //Initialises the AccountManager, loads it from file, only called when App starts
    public static void initInstance(UserFileReader userFileReader, Context context) {
        if (instance == null) {
            try {
                instance = (AccountManager) userFileReader.loadObject(context).readObject();
            } catch (Exception e) {
                e.printStackTrace();
                instance = new AccountManager();
            }
        }
    }

    //Adds a new user
    public void addUser(String userName, String userPassword) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName, userPassword));
            logIn(userName, userPassword);
        }
    }

    //Method check username to password and if it matches logs in
    public void logIn(String userName, String userPassword) {
         try {
             activeUser = users.get(userName);
             activeUser.setProfilePicture(ImageHandler.loadImage(userName));
         } catch(NullPointerException e) {
            e.printStackTrace();
         }
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