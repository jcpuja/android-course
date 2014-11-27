package com.jcpuja.dailyselfie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    public AlarmNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_SHORT).show();
    }
}
