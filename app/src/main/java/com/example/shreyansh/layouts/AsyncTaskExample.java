package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AsyncTaskExample extends Activity {

    AsyncCounter myCounter;
    Integer initialCounter, totalCount;
    boolean isCounterOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);
        totalCount = 15;
        initialCounter = 0;
        myCounter = null;
        isCounterOn = false;

        if (savedInstanceState == null) {
            Logger.log("Application started !!");
        } else {
            isCounterOn = savedInstanceState.getBoolean("isCounterOn");
            if(isCounterOn) {
                String count = savedInstanceState.getString("counterValue");
                initialCounter = Integer.parseInt(count);
                initialCounter++;
                myCounter = new AsyncCounter();
                myCounter.execute(initialCounter, totalCount);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_async_task_example, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView tv = (TextView) findViewById(R.id.tv);
        String count = tv.getText().toString();
        Logger.log("inside save instance state : " + count);
        if (count.equals("Counter completed !!"))
            isCounterOn = false;
        else
            outState.putString("counterValue", count);
        outState.putBoolean("isCounterOn", isCounterOn);
        super.onSaveInstanceState(outState);
    }

    public void onClickButton(View view) {
        if (myCounter == null) {
            myCounter = new AsyncCounter();
            myCounter.execute(initialCounter, totalCount);
            isCounterOn = true;
        }
    }

    public void onButtonStop(View view) {
        if (myCounter != null) {
            myCounter.cancel(true);
            myCounter = null;
            isCounterOn = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (myCounter != null) {
            myCounter.cancel(true);
            myCounter = null;
        }
        super.onDestroy();
    }

    private class AsyncCounter extends AsyncTask<Integer, Integer, Integer> {

        int i;

        @Override
        protected void onPreExecute() {
            Logger.log("before starting AsyncTask !!");
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            for (i = params[0]; i < params[1]; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(5000, 0);
                    if (isCancelled()) break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return i;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Logger.log("Inside progress update !!");
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText(values[0].toString());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer in) {
            Logger.log("after finishing AsyncTask !!");
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText("Counter completed !!");
            super.onPostExecute(in);
        }

        @Override
        protected void onCancelled() {
            Logger.log("ASYNCTASK CANCELLED at value " + i);
            super.onCancelled();
        }
    }
}
