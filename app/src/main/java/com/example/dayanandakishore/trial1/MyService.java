package com.example.dayanandakishore.trial1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 Created by Dayananda kishore on 05-Jan-18.
 */

public class MyService extends Service {



    boolean flag = false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        onTaskRemoved(intent);
        new alarmButton().execute();
        return START_STICKY;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 10, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmService != null) {
            alarmService.set(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() ,
                    restartServicePendingIntent);

            super.onTaskRemoved(rootIntent);
        }
    }



   /*private Timer mTimer;

   TimerTask timerTask = new TimerTask() {

       @Override
       public void run() {
           Log.e("Log", "Running");
       }
   };

   @Override
   public void onCreate() {
       super.onCreate();
       mTimer = new Timer();
       mTimer.schedule(timerTask, 2000, 2 * 1000);

   }
   /*public void onDestroy() {
       try {
           mTimer.cancel();
           timerTask.cancel();
       } catch (Exception e) {
           e.printStackTrace();
       }
       Intent intent = new Intent("com.example.dayanandakishore.trial1.ServiceBroadcast");

       sendBroadcast(intent);

   }*/






    public   class alarmButton extends AsyncTask<Void, Void, Void> {

        String p7 = Integer.toString(0);
        @Override
        public Void doInBackground(Void... params) {

          /*
           Log.d(TAG,"This is running background endlessly " + iter++);
            */

            if (flag) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int[] a = { 22,22,14,3,3,4};
            int[] b = { 8,30,7,25,46,47};
            SharedPreferences s;
            AlarmManager alarmManager;
            PendingIntent pendingIntent;
            PendingIntent pending;
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String n = s.getString("boolean", "false");
            long time, time2;
            //Toast.makeText(MyService.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            int i=0,j=0;
            while(i<6 && j<6)
            {

                if (n.equals("true")) {
                    Intent intent = new Intent(MyService.this, myAlarm.class);
                    pendingIntent = PendingIntent.getBroadcast(MyService.this, 1, intent,0);


                    String p1 = s.getString("spinner", p7);
                    int k1  = Integer.parseInt(p1);
                    int c, d;
                    int z = k1 * 5 + 5;
                    if (b[i] >= z) {
                        c = b[i] - z;
                        d = a[i];
                    } else {
                        c = 60 + b[i] - z;
                        d = a[i] - 1;
                    }

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, d);
                    calendar.set(Calendar.MINUTE, c);
                    calendar.set(Calendar.SECOND, 0);

                    time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));


                    //Log.d("daya", "ALarm will be triggered at" + d + c);
                    if (System.currentTimeMillis() > time) {
                        time = time + (1000 * 60 * 60 * 24);
                        i = i+1;
                    }

                    if (alarmManager != null) {
                        alarmManager.set(AlarmManager.RTC, time, pendingIntent);
                    }
                } else {
                    if (alarmManager != null) {
                        Intent intent = new Intent(getApplicationContext(), myAlarm.class);
                        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, intent,0);
                        alarmManager.cancel(pendingIntent);
                    }
                }
                String p = s.getString("boolean2", "false");
                if (p.equals("true")) {
                    Intent in = new Intent(getApplicationContext(), myNotifications.class);
                    pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 4, in, 0);


                    String p3 = s.getString("spinner", p7);
                    int k3  = Integer.parseInt(p3);
                    int c, t;
                    int z = k3 * 5 + 5;
                    if (b[j] >= z) {
                        c = b[j] - z;
                        t = a[j];
                    } else {
                        c = 60 + b[j] - z;
                        t = a[j] - 1;
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, t);
                    calendar.set(Calendar.MINUTE, c);
                    calendar.set(Calendar.SECOND, 0);


                    time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));

                    //Log.d("daya", "Notifications will be triggered at" + t + c);

                    if (System.currentTimeMillis() > time) {
                        time = time + (1000 * 60 * 60 * 24);
                        j++;
               /*if the given time is in past it will add 24 hours to the given time*/

                    }
                    if (alarmManager != null) {

                        alarmManager.set(AlarmManager.RTC_WAKEUP, time,  pendingIntent);
           /*Change the intervalMillis in order to get the repetitive notifications*/
                    }
                } else {
                    if (alarmManager != null) {
                        Intent in = new Intent(MyService.this, myNotifications.class);
                        pending = PendingIntent.getBroadcast(MyService.this, 3, in, 0);
                        alarmManager.cancel(pending);


                    }
                }

            }





            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            flag = true;
            super.onPostExecute(result);
            new alarmButton().execute();

        }
    }
}

