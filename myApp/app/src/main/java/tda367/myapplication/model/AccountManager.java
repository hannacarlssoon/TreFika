package tda367.myapplication.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.service.UserFileReader;

/**
 * Created by hannacarlsson on 2017-05-03.
 */

public class AccountManager implements Serializable {
    private static AccountManager instance;

    private Map<String, User> users = new HashMap<>();

    private User activeUser;

    private static final long serialVersionUID =7116880369697727491L;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        System.out.println("In getInstance");
        System.out.println(instance.equals(null));
        System.out.println(instance.getUsers().equals(null));
        return instance;
    }

    public static void initInstance(UserFileReader userFileReader, Context context) {
        System.out.println("Almost there");
        if (instance == null) {
            try {
                System.out.println("In here yoo");
                instance = (AccountManager) userFileReader.loadObject(context).readObject();
            } catch (FileNotFoundException e) {
                System.out.println("oh no 1");
                instance = new AccountManager();
            } catch (ClassNotFoundException e) {
                System.out.println("Oh no 2");
                instance = new AccountManager();
                e.printStackTrace();
            }catch (IOException e) {
                System.out.println("oh no 3");
                instance = new AccountManager();
                e.printStackTrace();
            }
        }
    }

    //Adds a new user
    public void addUser(String userName, String userPassword) {
        System.out.println("In addUser");
        if (!users.containsKey(userName)) {
            System.out.println("In if");
            users.put(userName, new User(userName, userPassword));
            logIn(userName, userPassword);
        }
        else {
            System.out.println("Username already exists");
        }
    }

    //Method check username to password and if it matches logs in
    public void logIn(String userName, String userPassword) {
         try {
             activeUser = users.get(userName);
             activeUser.setProfilePicture(ImageHandler.loadImage(userName));
             System.out.println("You logged in");
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