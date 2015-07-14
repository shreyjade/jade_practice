package com.example.shreyansh.layouts;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TellAppService extends Service {

    private TellAppNameThread mThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.log("start command service");
        if(mThread == null) {
            mThread = new TellAppNameThread();
            mThread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mThread.terminate();
        Logger.log("destroy service");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class TellAppNameThread extends Thread {

        private boolean run;

        @Override
        public void run() {
            run = true;

            while (run) {
                ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                ActivityManager.RunningTaskInfo runningTaskInfo = activityManager.getRunningTasks(1).get(0);
                Logger.log("\n"+runningTaskInfo.baseActivity.toShortString());
                try {
                    Thread.sleep(10000);
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
