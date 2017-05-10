package tda367.myapplication;

import org.junit.Test;

import tda367.myapplication.model.AccountManager;

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
