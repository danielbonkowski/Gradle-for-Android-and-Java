package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import gradle.udacity.displaylibrary.DisplayActivity;



public class MainActivity extends AppCompatActivity{

    private final static String EXTRAS_JOKE = "joke";
    private static ProgressBar mProgressBar;
    private static String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.main_progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view){

        try{
            new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Daniel"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    public static class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        private static MyApi myApiService = null;
        private static Context mContext;

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {


            if(myApiService == null){
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                request.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }

            mContext = params[0].first;
            String name = params[0].second;

            try{
                return myApiService.sayHi(name).execute().getData();
            }catch (IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mProgressBar.setVisibility(View.GONE);

            Intent intent = new Intent(mContext, DisplayActivity.class);
            intent.putExtra(EXTRAS_JOKE, result);
            mContext.startActivity(intent);
        }
    }
}
