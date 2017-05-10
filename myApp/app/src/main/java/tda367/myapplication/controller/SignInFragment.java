package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        System.out.println("bajs");
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
        System.out.println("Tjenis");
        signIn = (Button) view.findViewById(R.id.signIn);
        signUp = (Button) view.findViewById(R.id.signUp);
        userName = (EditText) view.findViewById(R.id.userName);
        password = (EditText) view.findViewById(R.id.Password);

        signIn.setOnClickListener(new Button.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          System.out.println("Hemm");
                                      }
                                  });
                signUp.setOnClickListener(this);
        System.out.println(signIn.hasOnClickListeners());


        System.out.println("After");
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onClick(View view) {
        System.out.println("You're in!");
        switch (view.getId()) {
            case R.id.signIn:
                System.out.println("Case 1");
                if (isPasswordCorrect(userName.getText().toString(), password.getText().toString())) {
                    AccountManager.getInstance().logIn(userName.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getContext(), "Wrong username or password",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.signUp:
                System.out.println("Case 2");
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean isPasswordCorrect(String username, String password) {
        return AccountManager.getInstance().getUsers().get(username).getUserPassword().equals(password);
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
