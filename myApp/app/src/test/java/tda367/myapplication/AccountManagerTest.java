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
        //AccountManager.initInstance(null, null);
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Password");
        assertTrue(am.getUsers().containsKey("Hanna"));
        assertTrue(am.getActiveUser().getUserPassword() == "Password");
    }

    @Test
    public void logInTest() {
        //AccountManager.initInstance(null, null);
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Password");
        am.logOut();
        am.logIn("Hanna", "Password");
        assertTrue(am.getActiveUser() != null);
        assertTrue(am.getActiveUser().getUserName() == "Hanna");

    }

    @Test
    public void logOutTest() {
        //AccountManager.initInstance(null, null);
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Password");
        am.logOut();
        assertTrue(am.getActiveUser() == null);
        am.logIn("Hanna", "Password");
        assertTrue(am.getActiveUser() != null);
        am.logOut();
        assertTrue(am.getActiveUser() == null);
        try {
            String name = am.getActiveUser().getUserName();
            assertTrue(false);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

}
