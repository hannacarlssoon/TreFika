package tda367.myapplication.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;

import tda367.myapplication.Model.User;
import tda367.myapplication.R;

import static tda367.myapplication.R.id.view;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
       //LoginButton login_button;
    //private CallbackManager callbackManager;

    private Button signIn;
    private Button signUp;
    private EditText userName;
    private EditText password;
    private User user;


    public SignInFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
       //FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
      //initializeControls(view);
        //loginWithFB();
        // Inflate the layout for this fragment
        // ------------------------------------------> change return to return view if this works!

        signIn = (Button) view.findViewById(R.id.signIn);
        signUp = (Button) view.findViewById(R.id.signUp);
        userName = (EditText) view.findViewById(R.id.userName);
        password = (EditText) view.findViewById(R.id.Password);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);


        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn:
                if (userName.getText() != null && password.getText() != null) {
                    //user.logIn(userName.getText().toString(), password.getText().toString());
                }
                break;
            case R.id.signUp:
                Intent intent = new Intent(getActivity(), SignUp.class);
                startActivity(intent);
                break;
        }
    }


    //Initializing facebook controls
    /*private void initializeControls(View view){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton) view.findViewById(R.id.login_button);
        login_button.setReadPermissions("email");
        login_button.setFragment(this);
    }*/


    /*private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Write code on what to happen when login successfully
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }*/



   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
       // Fragment fragment = getFragmentManager().findFragmentById(R.id.nav_signin);
       // fragment.onActivityResult(requestCode, resultCode, data);
    }*/


}
