package com.example.shreyansh.layouts;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * This class was part of other application. Basically this class was calling date service by using
 messenger service to get the date.
 */

public class MessengerActivity extends Activity {

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.d("hi", "in handler");
            //Toast.makeText(getApplication(), "in handler", Toast.LENGTH_SHORT).show();
            super.handleMessage(msg);
            Bundle b = msg.getData();
            Log.d("hi",b.getString("Date"));
            Toast.makeText(getApplicationContext(), b.getString("Date"), Toast.LENGTH_SHORT).show();
        }
    }

    private Messenger messenger = null;
    private boolean isBound = false;
    private Messenger myMessenger = new Messenger(new MyHandler());

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBound = true;
            messenger = new Messenger(service);
            Log.d("hi", "service connected");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            messenger = null;
            Log.d("hi","service disconnected");
            //Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    public void onClickButton(View v){
        try {
            Message msg = new Message();
            msg.replyTo = myMessenger;
            messenger.send(msg);
            Log.d("hi", "msg send");
            //Toast.makeText(getApplication(), "message send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("hi","oncreate");
        //Toast.makeText(getApplicationContext(), "oncreate", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Intent myIntent = new Intent();
        myIntent.setClassName("com.example.shreyansh.myapplication", "com.example.shreyansh.myapplication.DateService");
        //myIntent.setAction(Intent.ACTION_SEND);
        bindService(myIntent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onStop() {
        /*if (isBound) {
         */   unbindService(connection);
       /*     isBound = false;
        }*/
        Toast.makeText(getApplicationContext(), "Bye Bye !!", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
}