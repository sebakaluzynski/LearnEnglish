package com.example.seba.learnenglish.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import java.sql.Time;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.seba.learnenglish.NotificationReciever;
import com.example.seba.learnenglish.R;

public class SettingsActivity extends Activity {

    private Button notificationButton;
    private TextView timeClock;

    TimePickerDialog timePickerDialog;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        notificationButton = (Button) findViewById(R.id.buttonTest);
        timeClock = (TextView)findViewById(R.id.timeClock);
        calendar = Calendar.getInstance();

        notificationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openTimePickerDialog(false);
            }});

    }

    private void openTimePickerDialog(boolean is24r){
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(
                this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);
            if(calSet.compareTo(calNow) <= 0){
                calSet.add(Calendar.DATE, 1);
            }
            setAlarm(calSet);
        }};


    private void setAlarm(Calendar targetCal){
        Intent intent = new Intent(getBaseContext(), NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 100, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

    }
}
