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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);
        Logger.log("Application started !!");
        myCounter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_async_task_example, menu);
        return true;
    }

    public void onClickButton(View view) {
        if (myCounter == null) {
            myCounter = new AsyncCounter();
            myCounter.execute();
        }
    }

    public void onButtonStop(View view) {
        if (myCounter != null) {
            myCounter.cancel(true);
            myCounter = null;
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

    private class AsyncCounter extends AsyncTask<Void, Void, Integer> {

        int i;

        @Override
        protected void onPreExecute() {
            Logger.log("before starting AsyncTask !!");
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            for (i = 0; i < 1000000000l && !(isCancelled()); i++) ;
            return i;
        }

        @Override
        protected void onPostExecute(Integer in) {
            Logger.log("after finishing AsyncTask !!");
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText(in.toString());
            super.onPostExecute(in);
        }

        @Override
        protected void onCancelled() {
            Logger.log("ASYNCTASK CANCELLED !!" + i);
            TextView tv = (TextView) findViewById(R.id.tv);
            Integer in = i;
            tv.setText(in.toString());
            super.onCancelled();
        }
    }
}
