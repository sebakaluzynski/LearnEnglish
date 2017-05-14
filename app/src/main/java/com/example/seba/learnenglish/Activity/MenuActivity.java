package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.seba.learnenglish.R;

import java.util.ArrayList;


public class MenuActivity extends Activity {

    final Context context = this;
    ArrayList<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
    }


    public void addWord(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


    public void displayWords(View view){
        Intent intent = new Intent(this, MyListActivity.class);
        startActivity(intent);
    }

    public void findWord(View view){
        Intent intent = new Intent(this, FindWordActivity.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void learnTenses(View view){
        Intent intent = new Intent(this, LearnBaseFragmentActivity.class);
        startActivity(intent);
    }

}
