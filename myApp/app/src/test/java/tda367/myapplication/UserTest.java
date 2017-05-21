package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.model.AccountManager;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserTest {

    @Test
    public void updateUserTest() {
        AccountManager.initInstance(null);
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Carlsson");
        am.getActiveUser().updateUser("Sigge", "Carlsson");
        assertTrue(am.getActiveUser().getUserName() == "Sigge");
        assertTrue(am.getActiveUser().getUserPassword() == "Carlsson");
        assertTrue(am.getActiveUser().getUserName() != "Hanna");
    }

    @Test
    public void saveStatisticsTest() {
        AccountManager.initInstance(null);
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Carlsson");
        am.getActiveUser().saveStatistics("category11", true, true);
        int levelIndex = am.getActiveUser().getUserStatistics().findIndex("category11");
        assertTrue(am.getActiveUser().getUserStatistics().getStatisticsHint().get(levelIndex));
        assertTrue(am.getActiveUser().getUserStatistics().getStatisticsKey().size() == 1);
        try {
            assertTrue(am.getActiveUser().getUserStatistics().getStatisticsKey().get(1) == null);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

}
