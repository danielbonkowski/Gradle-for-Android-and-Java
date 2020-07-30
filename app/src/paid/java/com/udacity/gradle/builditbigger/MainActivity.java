package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

import com.udacity.gradle.builditbigger.IdlingResources.MyIdlingResource;

import javax.annotation.Nullable;

import gradle.udacity.displaylibrary.DisplayActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.ShowResultListener{

    private final String HIDE_PROGRESS_BAR = "hide_progress_bar";

    private final static String EXTRAS_JOKE = "joke";
    private ProgressBar mProgressBar;
    private TextView mLabel;
    private Button mJokeButton;
    private boolean mHideProgressBar = true;
    @Nullable private MyIdlingResource mIdlingResource;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(HIDE_PROGRESS_BAR, mHideProgressBar);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.main_progress_bar);
        mLabel = findViewById(R.id.instructions_text_view);
        mJokeButton = findViewById(R.id.joke_button);

        mJokeButton = findViewById(R.id.joke_button);

        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJoke();
            }
        });

        if(savedInstanceState != null){
            mHideProgressBar = savedInstanceState.getBoolean(HIDE_PROGRESS_BAR);
        }

        if(!mHideProgressBar){
            mProgressBar.setVisibility(View.VISIBLE);
            mLabel.setText(R.string.loading_label);
            mJokeButton.setEnabled(false);
        }
    }


    public void loadJoke() {

        mJokeButton.setEnabled(false);
        mLabel.setText(R.string.loading_label);

        if(mIdlingResource != null){
            mIdlingResource.setIdleState(false);
        }


        mProgressBar.setVisibility(View.VISIBLE);
        mHideProgressBar = false;


        try{
            new EndpointsAsyncTask().execute(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void displayJoke(String result) {
        mHideProgressBar = true;

        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra(EXTRAS_JOKE, result);
        startActivity(intent);

        if(mIdlingResource != null){
            mIdlingResource.setIdleState(true);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(mHideProgressBar){
            mJokeButton.setEnabled(true);
            mLabel.setText(R.string.instructions);
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource(){
        if(mIdlingResource == null){
            mIdlingResource = new MyIdlingResource();
        }
        return mIdlingResource;
    }
}
