package com.isidoreofseville.swine;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BroadcastService extends Service {
    private final static String TAG = "BroadcastService";

    //we need to add 2000 so that we can see that our timer starts from 15 seconds up to 0
    private static final long secondsToCount = 15000 + 2000;
    public static final String COUNTDOWN_BR = "com.isidoreofseville.swine";
    public static int secondsRemaining = (int) (secondsToCount / 1000) - 1;
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

        cdt = new CountDownTimer(secondsToCount, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                secondsRemaining = (int) (millisUntilFinished / 1000) - 1;
                Log.i(TAG, "Countdown seconds remaining: " + secondsRemaining);
                bi.putExtra("countdown", (long)(secondsRemaining * 1000));
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        secondsRemaining = (int) (secondsToCount / 1000) - 1;
        cdt.cancel();
        Log.i(TAG, "BroadcastService: Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        secondsRemaining = (int) (secondsToCount / 1000) - 1;
        cdt.cancel();
        stopSelf();
        Log.i(TAG, "BroadcastService onTaskRemoved: Timer cancelled");
    }
}
