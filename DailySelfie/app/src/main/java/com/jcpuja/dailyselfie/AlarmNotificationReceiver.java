package com.jcpuja.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmNotificationReceiver extends BroadcastReceiver {


    public AlarmNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent openSelfieAppIntent = new Intent(context, PhotoGridActivity.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(context, 0, openSelfieAppIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification.Builder notificationBuilder = new Notification.Builder(context) //
                .setSmallIcon(android.R.drawable.ic_menu_camera) //
                .setAutoCancel(true) //
                .setContentTitle(context.getString(R.string.notification_title)) //
                .setContentText(context.getString(R.string.notification_text)) //
                .setContentIntent(notificationPendingIntent);

        // Get the NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Pass the Notification to the NotificationManager:
        mNotificationManager.notify(0, notificationBuilder.build());
    }
}
