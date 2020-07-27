package gradle.udacity.displaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private static final String EXTRAS_JOKE = "joke";
    TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String joke = "There is no joke";
        if(intent != null){
            joke = intent.getStringExtra(EXTRAS_JOKE);
        }

       mJokeTextView = findViewById(R.id.joke_text_view);
       mJokeTextView.setText(joke);
    }
}