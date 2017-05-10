package tda367.myapplication.service;

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

    public static UserFileReader getInstance() {
        return instance;
    }

    public ObjectInputStream loadObject() throws IOException {
        return new ObjectInputStream(new FileInputStream(AccountManager.FILENAME));
    }

    public void saveObject() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(AccountManager.FILENAME));
            objectOutputStream.writeObject(AccountManager.getInstance());
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
