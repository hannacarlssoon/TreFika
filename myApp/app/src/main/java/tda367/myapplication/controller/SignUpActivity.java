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
import android.widget.Toast;

import java.io.FileNotFoundException;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageButton upload;
    private int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
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
                if (!checkIfUserExists(username.getText().toString())) {
                    AccountManager.getInstance().addUser(username.getText().toString(), password.getText().toString());
                    MainActivity.setUserInformation(username.getText().toString());
                    //startActivity(new Intent(SignUpActivity.this, PlayFragment.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkIfUserExists(String username) {
        try {
            return AccountManager.getInstance().getUsers().containsKey(username);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("fuuuuuuckkkkk");
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ImageHandler.saveImage(requestCode, resultCode, data, this, username.getText().toString(), getApplicationContext());
        } catch (NullPointerException e) {
            System.out.println("Whaat");
        }
    }
}
