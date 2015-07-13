package com.example.shreyansh.layouts;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService extends Service {

    /**
     * This function finds current time stamp and return's it.
     * @return currentTimeStamp
     */
    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStamp = dateFormat.format(new Date());
        Logger.log(currentTimeStamp);
        return currentTimeStamp;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Message responseMsg = new Message();
            String currentTimeStamp = getDate();
            Bundle msgData = new Bundle();
            msgData.putString("Date", currentTimeStamp);
            responseMsg.setData(msgData);
            Messenger rcvr = msg.replyTo;

            try {
                rcvr.send(responseMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Messenger messenger = new Messenger(new MyHandler());
    private MyThread thread;

    @Override
    public void onCreate() {
        Logger.log("create service");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.log("start command service");
        thread = new MyThread();
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        thread.terminate();
        Logger.log("destroy service");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    class MyThread extends Thread {

        private boolean run;

        @Override
        public void run() {
            run = true;

            while (run) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTimeStamp = dateFormat.format(new Date());
                Logger.log(currentTimeStamp);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Logger.log("Thread stopped !!");
        }

        public void terminate() {
            run = false;
        }
    }
}