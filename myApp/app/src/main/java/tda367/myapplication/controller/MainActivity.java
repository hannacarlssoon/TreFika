package tda367.myapplication.controller;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import tda367.myapplication.model.AccountManager;

import tda367.myapplication.R;
import tda367.myapplication.service.BackgroundMusicService;
import tda367.myapplication.service.ImageHandler;
import tda367.myapplication.service.UserFileReader;

import static android.R.drawable.sym_def_app_icon;

/*
@author Hanna Carlsson
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static ImageView profilePicture;
    private static TextView navUserName;
    private NavigationView navigationView;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AccountManager.initInstance(UserFileReader.getInstance(), getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent svc = new Intent(this, BackgroundMusicService.class);
        startService(svc);
        if (savedInstanceState == null) {
            if (AccountManager.getInstance().getActiveUser() == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintlayout_for_fragment,
                        new SignInFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintlayout_for_fragment,
                        new PlayFragment()).commit();
            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.getHeaderView(0);
        profilePicture = (ImageView) headerView.findViewById(R.id.imageView);
        navUserName = (TextView) headerView.findViewById(R.id.navUsername);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
        } else if (id == R.id.nav_achievements) {
            AcheivementsFragment acheivementsFragment = new AcheivementsFragment();
            setView(acheivementsFragment);
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

    public void setView(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.constraintlayout_for_fragment,
                fragment,
                fragment.getTag()).commit();
    }

    public void nextButtonClicked() {
        Intent myIntent = new Intent(MainActivity.this, LevelActivity.class);
        myIntent.putExtra("key", true); //Optional parameters
        MainActivity.this.startActivity(myIntent);

    }

   public static void setUserInformation(String imageName) {
       if (imageName == null) {
           profilePicture.setImageResource(sym_def_app_icon);
           navUserName.setText("Log in to see username");
       } else {
           profilePicture.setImageDrawable(ImageHandler.loadImage(imageName));
           System.out.println(imageName);
           navUserName.setText(imageName);
       }
   }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserFileReader.getInstance().saveObject(getApplicationContext());
    }
}
