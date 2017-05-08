package tda367.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tda367.myapplication.Model.AccountManager;
import tda367.myapplication.Model.User;

import static org.junit.Assert.*;

/**
 * Created by hannacarlsson on 2017-04-07.
 */

public class UserTest {

    @Test
    public void updateUserTest() {
        AccountManager am = AccountManager.getInstance();
        am.addUser("Hanna", "Password", "url");

    }



}
