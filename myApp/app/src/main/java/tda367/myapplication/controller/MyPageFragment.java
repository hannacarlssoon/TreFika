package tda367.myapplication.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    private ImageView myPageProfilePicture;
    private TextView myPageUserName;

    public MyPageFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        myPageProfilePicture = (ImageView) view.findViewById(R.id.myPageProfilePicture);
        myPageUserName = (TextView) view.findViewById(R.id.myPageUserName);
        Button myPageUpdate = (Button) view.findViewById(R.id.myPageUpdate);
        Button myPageLogOut = (Button) view.findViewById(R.id.myPageLogOut);

        myPageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdatePage();
            }
        });

        myPageLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInFragment.isLoggedIn = false;
                AccountManager.getInstance().logOut();
                MainActivity.setUserInformation(null);
                setSignInPage();
                SignInFragment.isLoggedIn = false;
            }
        });

        setInformationView(AccountManager.getInstance().getActiveUser().getUserName());

        return view;
    }

    private void setInformationView(String imageName) {
        myPageProfilePicture.setImageDrawable(ImageHandler.loadImage(imageName));
        myPageUserName.setText(imageName);
    }

    private void setSignInPage() {
        SignInFragment signInFragment = new SignInFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), signInFragment, signInFragment.getTag()).commit();
    }

    private void setUpdatePage() {
        UpdateUserFragment updateUserFragment = new UpdateUserFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), updateUserFragment, updateUserFragment.getTag()).commit();
    }
}
