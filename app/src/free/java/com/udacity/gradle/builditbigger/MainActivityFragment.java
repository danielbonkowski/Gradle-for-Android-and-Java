package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private Button mJokeButton;

    private ButtonClickListener mButtonClickListener;
    private Button mIncreaseButton;
    private TextView mIncreaseTextView;
    private int mCounter;


    public MainActivityFragment() {
    }

    public interface ButtonClickListener{
        void loadJoke();
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
        mIncreaseButton = root.findViewById(R.id.increase_button);
        mIncreaseTextView = root.findViewById(R.id.increase_text_view);


        mIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++mCounter;
                mIncreaseTextView.setText(String.valueOf(mCounter));
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
