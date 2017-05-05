package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tda367.myapplication.Model.User;

import static org.junit.Assert.*;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserTest {

    @Test
    public void addUserTest() {
        User db = new User();
        db.addUser("Hanna", "password", "url");
        Map<String, List<String>> x = db.getUsers();
        assertTrue(x.containsKey("Hanna"));
        List<String> y = new ArrayList<>();
        y.add("password");
        y.add("url");
        assertTrue(x.containsValue(y));
    }

    @Test
    public void updateUserTest() {
        User db = new User();
        db.addUser("Hanna", "password", "url");
        db.updateUser("Kitzing", "password", "url");
        Map<String, List<String>> x = db.getUsers();
        assertTrue(x.containsKey("Kitzing"));
        List<String> y = new ArrayList<>();
        y.add("password");
        y.add("url");
        assertTrue(x.containsValue(y));
    }

    @Test
    public void logInTest() {
        User db = new User();
        db.addUser("Hanna", "password", "url");
        assertTrue(db.logIn("Hanna", "password"));
    }

}
