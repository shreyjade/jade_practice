package com.example.shreyansh.layouts;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * This class creates AsyncTask which increase the counter value after 5 seconds.
 */
public class AsyncCounter extends AsyncTask<Integer, Integer, Integer> {

    private TextView mTextView;

    AsyncCounter(TextView paramTextView) {
        mTextView = paramTextView;
    }

    @Override
    protected void onPreExecute() {
        Logger.log("before starting AsyncTask !!");
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        int i;
        for (i = params[0]; i < params[1] && !isCancelled(); i++) {
            try {
                Thread.sleep(5000, 0);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Logger.log("Inside progress update !!");
        if(!TextUtils.isEmpty(values[0].toString())){
            mTextView.setText(values[0].toString());
        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer in) {
        Logger.log("after finishing AsyncTask !!");
        mTextView.setText("Counter completed !!");
        super.onPostExecute(in);
    }

    @Override
    protected void onCancelled() {
        //Logger.log("ASYNCTASK CANCELLED at value " + i);
        super.onCancelled();
    }
}