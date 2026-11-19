package com.xiaomi.dspvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class VolumeListenerReceiver extends BroadcastReceiver {
    private static final String TAG = "DSPVolumeSynchronizer";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }

        if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == AudioManager.STREAM_MUSIC) {
            AudioManager audioManager = context.getSystemService(AudioManager.class);
            if (audioManager == null) {
                return;
            }
            int current = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
            audioManager.setParameters("volume_change=" + current + ";flags=8");
            Log.d(TAG, "onReceive VOLUME_CHANGED STREAM_MUSIC volume=" + current);
        }
    }
}
