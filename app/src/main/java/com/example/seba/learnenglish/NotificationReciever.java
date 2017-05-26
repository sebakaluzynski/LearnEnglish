package com.example.seba.learnenglish;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.seba.learnenglish.Activity.RepeatingActivity;

/**
 * Created by Seba on 26.05.2017.
 */
public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating = new Intent(context, RepeatingActivity.class);
        repeating.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity
                (context,100,repeating,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.alert_light_frame)
                .setContentTitle("Notfication title")
                .setContentText("Notification text")
                .setAutoCancel(true);
        notificationManager.notify(100, builder.build());
    }
}
