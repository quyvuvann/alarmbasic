package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(context,Music.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent1);
        } else {

            context.startService(intent1);
        }
        intent.putExtra("extra","on");
        context.startService(intent1);
    }
}


