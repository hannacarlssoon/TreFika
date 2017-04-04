package tda367.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hannacarlsson on 2017-04-03.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    //Database Info
    private static final String DATABASE_NAME = "userDatabase.db";
    private static final int DATABASE_VERSION = 1;

    //Table name
    public static final String TABLE_USERS = "user";

    //User table columns
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_PROFIL_PICTURE_URL = "profilPictureUrl";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query_user_table = "CREATE TABLE " + TABLE_USERS +
                "(" + KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PROFIL_PICTURE_URL + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(query_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(sqLiteDatabase);
        }
    }
}
