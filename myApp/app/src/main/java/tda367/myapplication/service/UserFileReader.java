package tda367.myapplication.service;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tda367.myapplication.model.AccountManager;

/**
 * Created by hannacarlsson on 2017-05-10.
 */

public class UserFileReader {

    private static UserFileReader instance = new UserFileReader();

    private UserFileReader() {}

    private File myPath;

    public static UserFileReader getInstance() {
        return instance;
    }

    public ObjectInputStream loadObject(Context context) throws IOException {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("dataDir", Context.MODE_PRIVATE);
        myPath = new File(directory, "users");
        return new ObjectInputStream(new FileInputStream(myPath));
    }

    public void saveObject(Context context, Object manager) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("dataDir", Context.MODE_PRIVATE);
        myPath = new File(directory, "users");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(myPath));
            objectOutputStream.writeObject(manager);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            //TODO what to do
        }
    }

}
