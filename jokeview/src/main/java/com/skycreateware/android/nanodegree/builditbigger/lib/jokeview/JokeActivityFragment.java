package com.skycreateware.android.nanodegree.builditbigger.lib.jokeview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public static String LOG_TAG = JokeActivityFragment.class.getSimpleName();

    private TextView mJokeView;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        mJokeView = (TextView) rootView.findViewById(R.id.jokeView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();

        if (intent.hasExtra(JokeActivity.JOKE_EXTRA)) {
            mJokeView.setText(intent.getStringExtra(JokeActivity.JOKE_EXTRA));
        } else {
            Log.e(LOG_TAG, "No joke provided");
            getActivity().finish();
        }
    }
}
