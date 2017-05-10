package tda367.myapplication.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.ImageModel;
import tda367.myapplication.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView wrongUsername;
    private ImageView profilePicture;
    private ImageButton upload;
    private int RESULT_LOAD_IMG = 1;
    private Uri selectedImage;
    private ImageModel imageModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        wrongUsername = (TextView) findViewById(R.id.wrongUsername);
        profilePicture = (ImageView) findViewById(R.id.imageView);
        upload = (ImageButton) findViewById(R.id.imageButton);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        Button signUp = (Button) findViewById(R.id.SignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO profile picture
                if (!checkIfUserExists(username.getText().toString())) {
                    wrongUsername.setText("");
                    AccountManager.getInstance().addUser(username.getText().toString(), password.getText().toString());
                    startActivity(new Intent(SignUpActivity.this, PlayFragment.class));
                } else {
                    wrongUsername.setText("The username already exists");
                }
            }
        });

    }

    private boolean checkIfUserExists(String username) {
        return AccountManager.getInstance().getUsers().containsKey(username);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        imageModel.saveImage(requestCode, resultCode, data, this, username.getText().toString());

    }



}
