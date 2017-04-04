package tda367.myapplication;

import android.content.ContentValues;
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
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PROFIL_PICTURE_URL = "profilePictureUrl";

    //Constructor which calls the SQLiteOpenHelpers constructor with the database name and version
    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //The method is called whenever a new database is created
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query_user_table = "CREATE TABLE " + TABLE_USERS +
                "(" + USER_ID + " INTEGER PRIMARY KEY," +
                USER_NAME + " TEXT," +
                USER_PASSWORD + " TEXT," +
                USER_EMAIL + " TEXT, " +
                USER_PROFIL_PICTURE_URL + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(query_user_table);
    }

    //The method is called whenever the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(sqLiteDatabase);
        }
    }

    //Adds a new user to the database (a new row)
    public void addUser(String userName, String userPassword, String profilePictureUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PASSWORD, userPassword);
        values.put(USER_PROFIL_PICTURE_URL, profilePictureUrl);
        db.insert(TABLE_USERS, null, values);
    }

    //Deletes a users information
    public Integer deleteUser(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS, USER_ID + " = ? ", new String[] {Integer.toString(id)});
    }
}
