package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ButtonClickListener mButtonClickListener;
    private Button mJokeButton;

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

        mJokeButton = root.findViewById(R.id.joke_button);
        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonClickListener.loadJoke();
            }
        });

        return root;
    }
}
