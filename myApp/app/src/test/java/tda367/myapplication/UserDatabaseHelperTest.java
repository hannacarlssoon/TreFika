package tda367.myapplication;

import android.content.Context;

import org.junit.Test;

import tda367.myapplication.Model.UserDatabaseHelper;

/**
 * Created by hannacarlsson on 2017-04-05.
 */

public class UserDatabaseHelperTest {

    @Test
    public void onAddUser(Context context) {
        UserDatabaseHelper db = new UserDatabaseHelper(context);
        db.addUser("Name", "Password", "Picture");
    }

}
