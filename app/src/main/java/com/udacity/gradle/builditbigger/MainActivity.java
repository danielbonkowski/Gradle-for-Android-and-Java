package com.udacity.gradle.builditbigger;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import gradle.udacity.displaylibrary.DisplayActivity;



public class MainActivity extends AppCompatActivity implements MainActivityFragment.ButtonClickListener,
EndpointsAsyncTask.ShowResultListener{

    private final static String EXTRAS_JOKE = "joke";
    private ProgressBar mProgressBar;
    private boolean hideProgressBar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.main_progress_bar);
    }

    @Override
    public void loadJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        hideProgressBar = false;

        try{
            new EndpointsAsyncTask().execute(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void displayJoke(String result) {
        hideProgressBar = true;

        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra(EXTRAS_JOKE, result);
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(hideProgressBar){
            mProgressBar.setVisibility(View.GONE);
        }

    }
}
