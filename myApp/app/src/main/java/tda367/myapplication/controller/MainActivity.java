package tda367.myapplication.controller;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.R;
import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.service.UserFileReader;

import static android.R.drawable.sym_def_app_icon;

/**
 * @author Hanna Carlsson, Revised by: Tobias Lindgren
 * Responsible for starting the app and creating and managing the navigation drawer
 * Uses: AccountManager, User, AboutAppenFragment, PlayFragment, ReadMoreFragment, SettingsFragment,
 * SignInFragment, StatisticsFragment, ImageHandler, UserFileReader, activity_navigation_drawer.xml,
 * navigation_drawer.xml, constraintlayout_for_fragment.xml, content_navigation_drawer.xml
 * Used by: UpdateUserFragment
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static ImageView profilePicture;
    private static TextView navUserName;
    private static MenuItem titleSignIn;
    public static MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeAccountManager();

        //Sets content values and toolbar ids
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sets navigationview id and listner
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setMenuID(navigationView);

        setInformationNavigationDrawer(navigationView);

        setFirstView(savedInstanceState);

        setDrawerNavigation(toolbar);

    }

    //Sets the first view, when not logged in to signInFragment and when logged in to playFragment
    private void setFirstView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (AccountManager.getInstance().getActiveUser() == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintlayout_for_fragment,
                        new SignInFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintlayout_for_fragment,
                        new PlayFragment()).commit();
                setUserInformation(AccountManager.getInstance().getActiveUser().getUserName());
            }
        }
    }

    //Sets the information in the navigation drawer to the users information
    private void setInformationNavigationDrawer(NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        profilePicture = (ImageView) headerView.findViewById(R.id.imageView);
        navUserName = (TextView) headerView.findViewById(R.id.navUsername);
    }

    //Sets the menu id
    private void setMenuID(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        titleSignIn = menu.findItem(R.id.nav_signin);
    }

    //Sets the navigation drawer id and sets it to toggle
    private void setDrawerNavigation(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Gets the Accountmanagers stored object if there is once, otherwise loads new instance
    private void initializeAccountManager() {
        if (AccountManager.getInstance() == null) {
            AccountManager manager = null;
            try {
                manager = (AccountManager) UserFileReader.getInstance().loadObject(getApplicationContext()).readObject();
                AccountManager.initInstance(manager);
            } catch (IOException e) {
                AccountManager.initInstance(manager);
            } catch (ClassNotFoundException e) {
                AccountManager.initInstance(manager);
            }
        }
    }

    //Handles the back navigation in the navigation drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Inflates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    //Sets the clicked menu item view
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_play) {
            PlayFragment playFragment = new PlayFragment();
            setView(playFragment);
        } else if (id == R.id.nav_signin) {
            SignInFragment signInFragment = new SignInFragment();
            setView(signInFragment);
        } else if (id == R.id.nav_statistics) {
            StatisticsFragment statisticsFragment = new StatisticsFragment();
            setView(statisticsFragment);
        } else if (id == R.id.nav_readmore) {
            ReadMoreFragment readMoreFragment = new ReadMoreFragment();
            setView(readMoreFragment);
        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            setView(settingsFragment);
        } else if (id == R.id.nav_aboutappen) {
            AboutAppenFragment aboutAppenFragment = new AboutAppenFragment();
            setView(aboutAppenFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Sets the clicked view in the navigation drawer
    public void setView(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.constraintlayout_for_fragment,
                fragment,
                fragment.getTag()).commit();
    }

    //Sets the user information in the navigation drawer, when logged in
    public static void setUserInformation(String imageName) {
       if (imageName == null) {
           navUserName.setText("Logga in för att se användarnamn");
           titleSignIn.setTitle("Logga in");
           profilePicture.setImageResource(R.mipmap.smurf_icon);
       } else {
           profilePicture.setImageDrawable(ImageHandler.loadImage(imageName));
           navUserName.setText(imageName);
           titleSignIn.setTitle("Min sida");
           if (profilePicture.getDrawable() == null) {
               profilePicture.setImageResource(R.mipmap.smurf_icon);
           }
       }
    }

    //Overriden method, called when app is closing down, saves AccountManager instance
    @Override
    public void onDestroy() {
        super.onDestroy();
        UserFileReader.getInstance().saveObject(getApplicationContext(), AccountManager.getInstance());
    }
}
