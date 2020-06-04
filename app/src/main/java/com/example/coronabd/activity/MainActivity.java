package com.example.coronabd.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.coronabd.R;
import com.example.coronabd.fragment.EmergencyFragment;
import com.example.coronabd.fragment.HomeFragment;
import com.example.coronabd.fragment.StatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "HomeActivity"; //Organizing Log
    //variables
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomNavigation = findViewById(R.id.bottomNavViewBar);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnnavigationItemSelectedListener);
        //openFragment(HomeFragment.newInstance("", ""));
        //setupBottomNavigationView();
        loadFragment(new HomeFragment());
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //BottomNavigationView Setup
    private void setupBottomNavigationView()
    {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");


    }
   private  BottomNavigationView.OnNavigationItemSelectedListener mOnnavigationItemSelectedListener
           = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment fragment;

           int id = item.getItemId();

           if (id == R.id.menu_home) {

               MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance()).addToBackStack(null).commit();
           }
           if (id == R.id.menu_search) {

               MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container, StatFragment.newInstance()).addToBackStack(null).commit();
           }
           if (id == R.id.menu_dashboard) {

               MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container, EmergencyFragment.newInstance()).addToBackStack(null).commit();
           }
           return true;
       }
   };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
