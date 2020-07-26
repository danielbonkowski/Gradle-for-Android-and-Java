package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static ProgressBar mProgressBar;
    private Button mJokeButton;
    private Button mIncreaseButton;
    private TextView mIncreaseTextView;
    private int mCount = 0;
    private InterstitialAd mInterstitialAd;
    private ButtonClickListener mButtonClickListener;

    public MainActivityFragment() {
    }

    public interface ButtonClickListener{
        public void loadJoke();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mButtonClickListener = (ButtonClickListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = (ProgressBar) root.findViewById(R.id.main_progress_bar);
        final Context context = getContext();

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mIncreaseTextView = root.findViewById(R.id.increase_text_view);
        mIncreaseButton = root.findViewById(R.id.increase_button);

        mIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCount;
                mIncreaseTextView.setText(String.valueOf(mCount));
            }
        });

        mJokeButton = root.findViewById(R.id.joke_button);
        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }

                final Context context = (Context) getActivity();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        mButtonClickListener.loadJoke();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        mButtonClickListener.loadJoke();
                    }
                });
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }
}
