package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;

/**
 * @author Hanna Carlsson
 * Responsibility: Sets the update user view
 * Uses: AccountManager, User, MainActivity, MyPageFragment, ImageHandler, fragment_update_user.xml
 * Used by:
 */
public class UpdateUserFragment extends Fragment {

    private final static int RESULT_LOAD_IMG = 1;
    private EditText username;

    public UpdateUserFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_user, container, false);

        //Sets the id:s
        final EditText password = (EditText) view.findViewById(R.id.updatePassword);
        username = (EditText) view.findViewById(R.id.updateUsername);
        Button updateInformation = (Button) view.findViewById(R.id.updateInformation);
        ImageButton upload = (ImageButton) view.findViewById(R.id.imageButton2);

        //Sets the textfields to username and password
        username.setText(AccountManager.getInstance().getActiveUser().getUserName());
        password.setText(AccountManager.getInstance().getActiveUser().getUserPassword());

        onUpdateClicked(password, updateInformation);

        onUploadClicked(upload);

        return view;
    }

    //Sets onClickListner on upload, when clicked launches gallery app
    private void onUploadClicked(ImageButton upload) {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
    }

    //Sets onClickListners to update, when clicked updates the users information
    private void onUpdateClicked(final EditText password, Button updateInformation) {
        updateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManager.getInstance().getActiveUser().updateUser(username.getText().toString(),
                        password.getText().toString());
                setMyPage();
                MainActivity.setUserInformation(username.getText().toString());

            }
        });
    }

    //Sets the page showing to My Page
    private void setMyPage() {
        MyPageFragment myPageFragment = new MyPageFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), myPageFragment, myPageFragment.getTag()).commit();
    }

    //Saves the image choosen to the user object
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ImageHandler.saveImage(requestCode, resultCode, data, getActivity(), username.getText().toString(), getContext());
        } catch (NullPointerException e) {
            Toast.makeText(getContext(), "Something went wrong, try again", Toast.LENGTH_SHORT);
        }
    }
}
