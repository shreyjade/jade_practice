package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * This class creates a counter which counts values in AsyncTask and stop the counter if user leaves
 * activity.
 */
public class AsyncTaskExample extends Activity {

    private static final int TOTAL_COUNT = 15;
    private boolean mIsCounterOn;
    private AsyncCounter mMyCounter;
    private String mCounterState = "Counter State";
    private String mCounterValue = "Counter Value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);
        mMyCounter = null;
        mIsCounterOn = false;

        if (savedInstanceState != null) {
            /*
             If the counter was on and the activity was destroyed, then on recreating the activity,
             counter is initialized to the previous value and the AsyncTask is started again.
             */
            mIsCounterOn = savedInstanceState.getBoolean(mCounterState);
            if (mIsCounterOn) {
                int initialCounter = Integer.parseInt(savedInstanceState.getString(mCounterValue));
                // initialCounter is incremented by one because the AsyncTask while exiting, completed the count
                // but the value is not updated as it was in different thread.
                initialCounter++;

                mMyCounter = new AsyncCounter((TextView) findViewById(R.id.tv));
                mMyCounter.execute(initialCounter, TOTAL_COUNT);
            }
        } else {
            Logger.log("Application started !!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_async_task_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * If the counter wasn't running, then start it with 0 value or else do nothing.
     * We don't want to start counter if the user accidentally tapped the start button twice.
     */
    public void onStart(View view) {
        if (mMyCounter == null) {
            mMyCounter = new AsyncCounter((TextView) findViewById(R.id.tv));
            mMyCounter.execute(0, TOTAL_COUNT);
            mIsCounterOn = true;
        }
    }

    /**
     * Stop the counter if the counter was on or else do nothing.
     */
    public void onButtonStop(View view) {
        stopCounter();
    }

    /**
     * Save the counter value if counter was on before user leaves the application.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String count = ((TextView) findViewById(R.id.tv)).getText().toString();
        Logger.log("inside save instance state : " + count);
        if (count.equals("Counter completed !!")) {
            mIsCounterOn = false;
        } else {
            outState.putString(mCounterValue, count);
        }
        outState.putBoolean(mCounterState, mIsCounterOn);
        super.onSaveInstanceState(outState);
    }

    /**
     * Stop the counter if the activity get's destroyed as we are displaying the value on the activity,
     * If activity is destroyed then the counter(AsyncTask) should not run.
     */
    @Override
    protected void onDestroy() {
        stopCounter();
        super.onDestroy();
    }

    /**
     * Stop the counter.
     */
    private void stopCounter() {
        if (mMyCounter != null) {
            mMyCounter.cancel(true);
            mMyCounter = null;
        }
    }
}