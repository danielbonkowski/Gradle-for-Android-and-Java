package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

/*import com.udacity.gradle.builditbigger.IdlingResources.MyIdlingResource;*/

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.IdlingResources.MyIdlingResource;
import com.udacity.gradle.builditbigger.MainActivityFragment;
import com.udacity.gradle.builditbigger.R;

import javax.annotation.Nullable;

import gradle.udacity.displaylibrary.DisplayActivity;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.ButtonClickListener,
        EndpointsAsyncTask.ShowResultListener {

    private final String HIDE_PROGRESS_BAR = "hide_progress_bar";

    private final static String EXTRAS_JOKE = "joke";
    private ProgressBar mProgressBar;
    private TextView mLabel;
    private Button mJokeButton;
    private boolean mHideProgressBar = true;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;


    @Override
    public void onBackPressed() {
        MyIdlingResource.increment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(HIDE_PROGRESS_BAR, mHideProgressBar);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAdView.resume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdOpened() {
                MyIdlingResource.decrement();

            }


            @Override
            public void onAdClosed() {
                MyIdlingResource.increment();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                loadJoke();

            }

            @Override
            public void onAdFailedToLoad(int i) {
                loadJoke();
            }
        });

        mProgressBar = findViewById(R.id.main_progress_bar);
        mLabel = findViewById(R.id.instructions_text_view);
        mJokeButton = findViewById(R.id.joke_button);

        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIdlingResource.increment();
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }
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



    @Override
    public void loadJoke() {

        mJokeButton.setEnabled(false);
        mLabel.setText(R.string.loading_label);



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
        MyIdlingResource.decrement();
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra(EXTRAS_JOKE, result);
        startActivity(intent);

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


    /*@VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource(){
        if(mIdlingResource == null){
            mIdlingResource = new MyIdlingResource();
        }
        return mIdlingResource;
    }*/
}
