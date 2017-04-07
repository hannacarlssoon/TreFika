package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tda367.myapplication.Model.UserDatabase;

import static org.junit.Assert.*;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserDatabaseTest {

    @Test
    public void addUserTest() {
        UserDatabase db = new UserDatabase();
        db.addUser("Hanna", "password", "url");
        Map<String, List<String>> x = db.getUsers();
        assertTrue(x.containsKey("Hanna"));
        List<String> y = new ArrayList<>();
        y.add("password");
        y.add("url");
        assertTrue(x.containsValue(y));
    }

}
