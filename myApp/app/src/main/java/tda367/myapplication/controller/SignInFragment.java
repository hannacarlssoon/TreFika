package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.R;

/**
 * Handles the sign in of the user
 */

public class SignInFragment extends Fragment implements View.OnClickListener {

    private Button signIn;
    private Button signUp;
    private EditText userName;
    private EditText password;
    public static boolean isInSignUp;

    public SignInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //Assigns each component, that is needed to reach, with an id
        signIn = (Button) view.findViewById(R.id.signIn);
        signUp = (Button) view.findViewById(R.id.signUp);
        userName = (EditText) view.findViewById(R.id.userName);
        password = (EditText) view.findViewById(R.id.Password);

        System.out.println("Hej");
                //Sets listeners to the buttons
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);

        System.out.print(AccountManager.getInstance().getActiveUser() == null);
        System.out.print("Ouututt");

        if (AccountManager.getInstance().getActiveUser() != null) {
            setMyPage();
        }

        return view;

    }

    private void setMyPage() {
        MyPageFragment myPageFragment = new MyPageFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), myPageFragment, myPageFragment.getTag()).commit();
        MainActivity.setUserInformation(AccountManager.getInstance().getActiveUser().getUserName());
    }

    //Method needed to be overriden when you have onClickListners, handles each buttons actions when clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn:
                if (isPasswordCorrect(userName.getText().toString(),
                        password.getText().toString())) {
                    AccountManager.getInstance().logIn(userName.getText().toString(),
                            password.getText().toString());
                    MainActivity.setUserInformation(userName.getText().toString());
                    setPlayPage();
                } else {
                    Toast.makeText(getContext(), "Wrong username or password",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.signUp:
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
                isInSignUp = true;
                break;
        }
    }

    //Returns wheather the password is correct or not
    private boolean isPasswordCorrect(String username, String password) {
        try {
            return AccountManager.getInstance().getUsers().get(username).getUserPassword().equals(password);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Sets the view to myPage
    private void setPlayPage() {
        PlayFragment playFragment = new PlayFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), playFragment, playFragment.getTag()).commit();
    }

}
