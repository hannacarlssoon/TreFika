package tda367.myapplication.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;

/**
 * @author Hanna Carlsson
 * Responsibilty: Makes the My page, sets the profile picture and username
 * Uses: fragment_my_page.xml, AccountManager, User, SignInFragment, UpdateUserFragment
 * Used by: PlayFragment, SignInFragment, UpdateUserFragment
 */
public class MyPageFragment extends Fragment {

    private ImageView myPageProfilePicture;
    private TextView myPageUserName;

    public MyPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        //Finds the imageView, textView and buttons by id
        myPageProfilePicture = (ImageView) view.findViewById(R.id.myPageProfilePicture);
        myPageUserName = (TextView) view.findViewById(R.id.myPageUserName);
        Button myPageUpdate = (Button) view.findViewById(R.id.myPageUpdate);
        Button myPageLogOut = (Button) view.findViewById(R.id.myPageLogOut);

        updateUserButtonClicked(myPageUpdate);

        logOutButtonClicked(myPageLogOut);

        setInformationView(AccountManager.getInstance().getActiveUser().getUserName());

        return view;
    }

    //Sets onClickListners on update button, when clicked sets the update user page
    private void updateUserButtonClicked(Button myPageUpdate) {
        myPageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdatePage();
            }
        });
    }

    //Sets onClickListners on log out button, when clicked logs out and sets sign in page
    private void logOutButtonClicked(Button myPageLogOut) {
        myPageLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManager.getInstance().logOut();
                MainActivity.setUserInformation(null);
                setSignInPage();
            }
        });
    }

    //Displays the users profile picture and user name on the view
    private void setInformationView(String imageName) {
        myPageProfilePicture.setImageDrawable(ImageHandler.loadImage(imageName));
        myPageUserName.setText(imageName);
    }

    //Sets the page showing to the sign in page
    private void setSignInPage() {
        SignInFragment signInFragment = new SignInFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), signInFragment, signInFragment.getTag()).commit();
    }

    //Sets the page showing to the update user page
    private void setUpdatePage() {
        UpdateUserFragment updateUserFragment = new UpdateUserFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), updateUserFragment, updateUserFragment.getTag()).commit();
    }
}
