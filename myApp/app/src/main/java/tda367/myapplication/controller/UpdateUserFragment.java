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

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.service.ImageHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateUserFragment extends Fragment {

    private final static int RESULT_LOAD_IMG = 1;
    private EditText username;


    public UpdateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_user, container, false);

        username = (EditText) view.findViewById(R.id.updateUsername);
        final EditText password = (EditText) view.findViewById(R.id.updatePassword);
        Button updateInformation = (Button) view.findViewById(R.id.updateInformation);
        ImageButton upload = (ImageButton) view.findViewById(R.id.imageButton2);

        username.setText(AccountManager.getInstance().getActiveUser().getUserName());
        password.setText(AccountManager.getInstance().getActiveUser().getUserPassword());

        updateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManager.getInstance().getActiveUser().updateUser(username.getText().toString(),
                        password.getText().toString());
                setMyPage();
                MainActivity.setUserInformation(username.getText().toString());

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        return view;
    }

    private void setMyPage() {
        MyPageFragment myPageFragment = new MyPageFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(getId(), myPageFragment, myPageFragment.getTag()).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ImageHandler.saveImage(requestCode, resultCode, data, getActivity(), username.getText().toString(), getContext());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
