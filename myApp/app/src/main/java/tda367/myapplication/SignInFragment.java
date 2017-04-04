package tda367.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static tda367.myapplication.R.id.view;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
        LoginButton login_button;
        CallbackManager callbackManager;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
       FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext());
      initializeControls(view);
        loginWithFB();
        // Inflate the layout for this fragment
        // ------------------------------------------> change return to view if this works!
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    //Initializing facebook controls
    private void initializeControls(View view){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton) view.findViewById(R.id.login_button);
        login_button.setReadPermissions("email");
        login_button.setFragment(this);
    }

    private void loginWithFB(){
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
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    */

}
