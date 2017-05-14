package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ToggleButton;

import com.example.seba.learnenglish.R;

public class SettingsActivity extends Activity {

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
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        inst = this;
//    }
}
