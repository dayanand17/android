package com.example.dayanandakishore.trial1;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.app.AlarmManager;
import android.widget.Button;



import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by dayananda kishore on 23-Dec-17.
 */

public class myAlarm extends BroadcastReceiver {


    private static final String TAG = "AlarmService" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Receiver has been called");
        Intent intent1 = new Intent(context,PlaybackService.class);
        context.startService(intent1);


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        if(v != null) {
            v.vibrate(2000);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        Notification notif = new Notification.Builder(context)
                .setTicker("Notification from Trial 1")
                .setContentTitle("Alarm")
                .setContentText("Your class starts at 9.00 AM")
                .setSmallIcon(R.mipmap.timetable_logo)
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher_round,"Cancel the alarm",pendingIntent).build();


        notif.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager noti = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (noti != null) {
            noti.notify(0, notif);
        }
        Intent in = new Intent(context, MyAlertDialog.class);
        in.putExtra("uri" , alarmUri);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Do you want to stop the Alarm")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog a = alert.create();
        a.setTitle("Alert From Alarm !!");
        a.show();
        // Build the dialog*/
    }
}
