package com.example.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Music extends Service {
    MediaPlayer mediaStore;
    int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AAA","bao thuc");
//        String nhankey = intent.getStringExtra("extra");
//        if(nhankey.equals("on")){
//            id = 1;
//        }else if (nhankey.equals("off")){
//            id = 0;
//
//        }
//        if(id == 1){
            mediaStore = MediaPlayer.create(this,R.raw.tim_lai_bau_troi);
            mediaStore.start();
            //id = 0;
//        }else if (id == 0){
//            mediaStore.stop();
//            mediaStore.reset();
//        }
        return START_NOT_STICKY;
    }
}
