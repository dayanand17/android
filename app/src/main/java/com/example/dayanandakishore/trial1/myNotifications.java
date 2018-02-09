package com.example.dayanandakishore.trial1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by dayananda kishore on 25-Dec-17.
 */

public class  myNotifications extends BroadcastReceiver{

    @Override

        /* Dont forget to mention in man*/
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "Notifications from cepstrum app", Toast.LENGTH_LONG).show();
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        /*Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(2000);*/

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 4, intent, 0);
        Notification notification = new Notification.Builder(context)
                .setTicker("Notification from Cepstrum app")
                .setContentTitle("Notification")
                .setContentText("Your class starts at 10 AM as per the given timetable")
                .setSmallIcon(R.mipmap.timetable_logo)
                .setSound(alarmSound)
                .setContentIntent(pendingIntent).getNotification();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(4, notification);
        }
    }
}
