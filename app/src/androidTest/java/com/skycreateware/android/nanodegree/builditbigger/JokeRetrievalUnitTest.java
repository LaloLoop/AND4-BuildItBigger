package com.skycreateware.android.nanodegree.builditbigger;

import android.test.ActivityUnitTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for async task joke retrieval.
 * With information from:
 * http://stackoverflow.com/questions/9774792/asynctask-onpostexecute-not-called-in-unit-test-case
 * Created by lalo on 22/12/15.
 */
public class JokeRetrievalUnitTest extends ActivityUnitTestCase<MainActivity> implements EndpointAsyncTask.OnJokeReceived{
    private static final String LOG_TAG = JokeRetrievalUnitTest.class.getSimpleName();
    final CountDownLatch signal = new CountDownLatch(1);

    public JokeRetrievalUnitTest() {
        super(MainActivity.class);
    }

    /**
     * Test async task
     * @throws Throwable when timeout occurs on waiting for response from devappserver.
     */
    public void testJokeRetrieval () throws Throwable {

        // Async task must be run on a different thread.
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointAsyncTask().execute(JokeRetrievalUnitTest.this);
            }
        });

        // Wait 10 seconds for callback.
        try {
            signal.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();
        }
    }

    // Callback with joke from GCE.
    @Override
    public void onJokeReceived(String joke) {
        Log.i(LOG_TAG, "Joke received: " + joke);

        assertNotNull("Joke is null", joke);
        assertTrue("Joke is empty", joke.replace(" ", "").length() > 0);
        // Notify callback assertions finished.
        signal.countDown();
    }
}
