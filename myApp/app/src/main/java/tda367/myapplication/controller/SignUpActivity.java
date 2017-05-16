package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.R;

/*
 * Handles the sign up of a user
 */

public class SignUpActivity extends AppCompatActivity {

    //All components that are needed to be reached within the class
    private EditText username;
    private EditText password;

    //Variable which decieds how many images that are needed for the profile picture
    private final int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign Up");

        //Finds the id for the components
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);

        ImageButton upload = (ImageButton) findViewById(R.id.imageButton);

        //Sets onClickListner to the upload button, which sends the user to the gallery app
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        Button signUp = (Button) findViewById(R.id.SignUp);

        //Sets onClickListner to the sign up button, signs in if username is okay, else makes toast
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIfUserExists(username.getText().toString())) {
                    String userName = username.getText().toString();
                    AccountManager.getInstance().addUser(userName, password.getText().toString());
                    MainActivity.setUserInformation(userName);
                    SignInFragment.isLoggedIn = true;
                    SignInFragment.isInSignUp = false;
                    setMyPage();
                } else {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Returns wheather the user already exists or not
    private boolean checkIfUserExists(String username) {
        try {
            return AccountManager.getInstance().getUsers().containsKey(username);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Overridden method from the AppCompatActivity, saves the images from the gallery app
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ImageHandler.saveImage(requestCode, resultCode, data, this, username.getText().toString(), getApplicationContext());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //Sets the view to myPage
    private void setMyPage() {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
    }

    //Handles the back navigation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        SignInFragment.isInSignUp = false;
        return super.onOptionsItemSelected(item);
    }
}
