package tda367.myapplication;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;

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
