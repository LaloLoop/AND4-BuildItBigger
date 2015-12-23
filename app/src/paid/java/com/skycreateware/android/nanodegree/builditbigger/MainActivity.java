package com.skycreateware.android.nanodegree.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.skycreateware.android.nanodegree.builditbigger.lib.jokeview.JokeActivity;

public class MainActivity extends AppCompatActivity implements EndpointAsyncTask.OnJokeReceived,
    OnFragmentViewReady{
    private Button mTellJokeButton;
    private ProgressBar mProgressBar;

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
        showLoadingView(true);
        new EndpointAsyncTask().execute(this);
    }

    /**
     * Enables / Disables the loading view.
     * @param show  True if the loading view should be presented.
     */
    private void showLoadingView(boolean show) {
        mTellJokeButton.setEnabled(!show);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
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
        showLoadingView(false);
        launchJokeActivity(joke);
    }

    @Override
    public void onFragmentViewReady() {
        mTellJokeButton = (Button) findViewById(R.id.tellJokeButton);
        mProgressBar = (ProgressBar) findViewById(R.id.loadingJokeBar);
    }
}
