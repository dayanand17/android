package com.example.dayanandakishore.trial1;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
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
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by dayananda kishore on 07-Jan-18.
 */

public class MyAlertDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /*Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();
*/

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        final MediaPlayer mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource( getBaseContext(), alarmUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        if(audioManager != null) {
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(true);
                try {
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.start();
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Alarm Alert")
                .setMessage("Do you want to cancel the alarm?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        /*Toast.makeText(getApplicationContext(),"Fuck Off You Cant stop the Alarm",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MyAlertDialog.this, myAlarm.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        Log.d("MyAlertDialog.this","NOT WORKINGGggg   ");
                        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                        if(am != null) {

                            am.cancel(pendingIntent);
                            pendingIntent.cancel();*/
                            mMediaPlayer.stop();

                        }

                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}

