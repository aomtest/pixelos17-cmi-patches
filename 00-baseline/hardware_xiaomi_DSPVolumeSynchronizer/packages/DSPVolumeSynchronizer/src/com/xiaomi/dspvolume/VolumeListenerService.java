package com.xiaomi.dspvolume;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class VolumeListenerService extends Service {
    private static final String TAG = "DSPVolumeSynchronizer";
    private VolumeListenerReceiver mReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new VolumeListenerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(mReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        Log.d(TAG, "onCreate registered VolumeListenerReceiver");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AudioManager audioManager = getSystemService(AudioManager.class);
        if (audioManager != null) {
            int current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            audioManager.setParameters("volume_change=" + current + ";flags=8");
            Log.d(TAG, "onStartCommand synced STREAM_MUSIC volume=" + current);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
            Log.d(TAG, "onDestroy unregistered VolumeListenerReceiver");
        }
        super.onDestroy();
    }
}
