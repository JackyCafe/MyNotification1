package com.ian.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Jacky";
    private NotificationManager mgr;
    private NotificationCompat.Builder builder;
    private int notifyId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

    }

    public void btnClick(View view) {
//        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,0,push,0);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.flashlight)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("蛀牙檢查"))
                .setContentTitle("檢查口腔")
                .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.morning))
                .setAutoCancel(true)
               
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(CHANNEL_ID);
        mgr.notify(notifyId,builder.build());
    }


    private void createNotificationChannel() {
        Log.v("Jacky", ""+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            mgr.createNotificationChannel(channel);
        }

    }

}