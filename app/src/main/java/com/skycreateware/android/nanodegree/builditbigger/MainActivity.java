package com.skycreateware.android.nanodegree.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.skycreateware.android.nanodegree.builditbigger.backend.myApi.MyApi;
import com.skycreateware.android.nanodegree.builditbigger.lib.jokeview.JokeActivity;

public class MainActivity extends AppCompatActivity implements EndpointAsyncTask.OnJokeReceived{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    /**
     * Click handler to process "Tell Joke" button.
     */
    public void tellJoke(View view) {
        new EndpointAsyncTask().execute(this);
    }

    /**
     * Called to show an activity passing a joke as an extra.
     * @param joke  Joke to show on activity.
     */
    public void launchJokeActivity (String joke) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);

        startActivity(intent);
    }

    @Override
    public void onJokeReceived(String joke) {
        launchJokeActivity(joke);
    }
}
