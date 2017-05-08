package tda367.myapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tda367.myapplication.Model.AccountManager;
import tda367.myapplication.Model.User;
import tda367.myapplication.R;

public class SignUp extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText userName = (EditText) findViewById(R.id.Username);
        final EditText password = (EditText) findViewById(R.id.Password);

        Button signUp = (Button) findViewById(R.id.SignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Fix profile picture
                //TODO Make addUser and logIn methods static
                //user.addUser(userName.getText().toString(), password.getText().toString(), null);
            }
        });

    }


}
