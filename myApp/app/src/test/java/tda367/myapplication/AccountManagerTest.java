package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tda367.myapplication.Model.AccountManager;
import tda367.myapplication.Model.User;

import static org.junit.Assert.assertTrue;

/**
 * Created by hannacarlsson on 2017-05-05.
 */

public class AccountManagerTest {

    @Test
    public void addUserTest() {
        AccountManager am  = AccountManager.getInstance();
        assertTrue(am.addUser("Hanna", "Password", "url"));
        assertTrue(am.getUsers().containsKey("Hanna"));
        assertTrue(!am.addUser("Hanna", "nått", "nått"));
    }

    @Test
    public void logInTest() {
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Password", "url");
        assertTrue(am.logIn("Hanna", "Password"));
        assertTrue(!am.logIn("Hanna", "password"));
    }
}
