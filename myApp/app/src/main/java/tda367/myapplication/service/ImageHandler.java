package tda367.myapplication.service;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * @author Hanna Carlsson based on:
 * https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
 *
 * Responsibility: Handles the storing and loading of images from directory where the images are
 * saved with username as name of the image in storage.
 *
 * Used by: SignUpActivity, UpdateUserFragment, MainActivity
 */

public class ImageHandler {

    //Saves the image to a directory
    public static void saveImage(int requestCode, int resultCode, Intent data, Activity activity, String username, Context context) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File myPath = new File(directory, username);
            FileOutputStream fileOutputStream = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImage);
                fileOutputStream = new FileOutputStream(myPath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                //TODO add catch
            } catch (IOException e) {
                //TODO add catch
            }
        }
    }

    //Loads and returns the image stored, the String path in the parameter will be the username
    public static Drawable loadImage(String path) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            try {
                File f = new File(directory, path);
                return Drawable.createFromStream(new FileInputStream(f), path);
            } catch (FileNotFoundException e) {
                //TODO add catch
            }
        return null;
    }
}
