package com.skycreateware.android.nanodegree.builditbigger;

/**
 * Created by lalo on 22/12/15.
 */

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.skycreateware.android.nanodegree.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Async Task used to retreive jokes from our endpoint.
 */
class EndpointAsyncTask extends AsyncTask<EndpointAsyncTask.OnJokeReceived, Void, String> {
    private static MyApi myApiService = null;
    private OnJokeReceived mListener;

    public interface OnJokeReceived {
        public void onJokeReceived(String joke);
    }

    @Override
    protected String doInBackground(EndpointAsyncTask.OnJokeReceived... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                // Setting up local server IP. Set your local server or local server if in
                // emulator.
                // - 10.0.2.2 is localhost-s IP address in Android emulator.
                .setRootUrl("http://192.168.1.65:8080/_ah/api/")
                // Disable gzip compression on devappserver
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        request.setDisableGZipContent(true);
                    }
                });
                // end options for devappserver

            myApiService = builder.build();
        }

        mListener = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mListener.onJokeReceived(s);
    }
}
