package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.seba.learnenglish.NotificationReciever;
import com.example.seba.learnenglish.R;

public class SettingsActivity extends Activity {

    private Button notificationButton;
    private ToggleButton alarmToggle;
    private AlarmManager alarmManager;
    private static SettingsActivity inst;

    public static SettingsActivity instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        alarmToggle = (ToggleButton) findViewById(R.id.toggleButton);
        notificationButton = (Button) findViewById(R.id.buttonTest);
    }

    public void setNotifications(View view) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,20);
        calendar.set(Calendar.SECOND,30);

        Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);git
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
}
