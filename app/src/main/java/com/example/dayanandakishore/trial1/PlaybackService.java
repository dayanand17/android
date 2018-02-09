package com.example.dayanandakishore.trial1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class PlaybackService extends Service {

    private static final String TAG = "AlarmService";
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    public PlaybackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v(TAG, "Ringtone started");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);


        try {
            mMediaPlayer.setDataSource( getBaseContext(), alarmUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        if(audioManager != null) {
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(false);
                try {
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.start();
            }
        }


        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mMediaPlayer.stop();
    }


}
