package com.example.dayanandakishore.trial1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AlarmService extends Service {

    private final static String TAG = "AlarmService";
    private SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND,30);
        Intent alarmIntent = new Intent(getApplicationContext(),myAlarm.class);
        alarmIntent.setAction("com.example.dayanandakishore.trail1.MyBroadcastReceiver");
        alarmIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        alarmIntent.setFlags(Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 12345, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            Log.d(TAG,"Alarm is set for " + dtf.format(now.getTime()));
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), pendingIntent);
        }
        return START_STICKY;
    }

    private class backgroundAlarmService extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE,1);
            int a = now.get(Calendar.HOUR);
            int b = now.get(Calendar.MINUTE);
            Intent intent = new Intent(getApplicationContext(),myAlarm.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 12345, intent,0);;
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                Log.d(TAG,"Alarm is set for " + dtf.format(now.getTime()));
                alarmManager.set(AlarmManager.RTC, now.getTimeInMillis(), pendingIntent);
            }
            return null;
        }
    }
}
