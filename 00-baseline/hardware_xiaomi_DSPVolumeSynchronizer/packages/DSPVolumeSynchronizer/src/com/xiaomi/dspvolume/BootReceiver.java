package com.xiaomi.dspvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "DSPVolumeSynchronizer";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (context == null) {
            return;
        }
        Log.d(TAG, "BootReceiver onReceive action=" + (intent != null ? intent.getAction() : "null"));
        context.startService(new Intent(context, VolumeListenerService.class));
    }
}
