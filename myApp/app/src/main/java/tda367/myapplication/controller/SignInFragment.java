package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.R;


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

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn:
                if (isPasswordCorrect(userName.getText().toString(), password.getText().toString())) {
                    AccountManager.getInstance().logIn(userName.getText().toString(), password.getText().toString());
                    MainActivity.setUserInformation(userName.getText().toString());
                    System.out.println("holaaa");
                } else {
                    Toast.makeText(getContext(), "Wrong username or password",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.signUp:
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isPasswordCorrect(String username, String password) {
        try {
            return AccountManager.getInstance().getUsers().get(username).getUserPassword().equals(password);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
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
