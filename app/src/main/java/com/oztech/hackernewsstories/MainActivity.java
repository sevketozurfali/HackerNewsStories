package com.oztech.hackernewsstories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.oztech.hackernewsstories.Fragments.AskStoriesFragment;
import com.oztech.hackernewsstories.Fragments.BestStoriesFragment;
import com.oztech.hackernewsstories.Fragments.JobStoriesFragment;
import com.oztech.hackernewsstories.Fragments.TopStoriesFragment;




public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    //ui components
    ProgressBar progressBar;
    //bottom navigation
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //progressBar
        progressBar = findViewById(R.id.main_progressBar);

        bottomNavigationView.setSelectedItemId(R.id.navigation_bestStories);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()){
            case R.id.navigation_bestStories:
                loadFragment(new BestStoriesFragment());
                return true;

            case R.id.navigation_topStories:
                loadFragment(new TopStoriesFragment());
                return true;

            case R.id.navigation_askStories:
                loadFragment(new AskStoriesFragment());
                return true;

            case R.id.navigation_jobStories:
                loadFragment(new JobStoriesFragment());
                return true;
        }

        return false;
    }

    private void loadFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.commit();

    }

    public void show_progressBar(){
        Log.i(TAG, "show_progressBar: progress bar showing.");
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hide_progressBar(){
        Log.i(TAG, "hide_progressBar: ");
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void closeFragment(Fragment fragment){

        Log.i(TAG, "closeFragment: fragment : ");
    }
}
