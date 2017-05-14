package com.example.seba.learnenglish.Activity;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.seba.learnenglish.PagerAdapter;
import com.example.seba.learnenglish.R;

public class LearnBaseFragmentActivity extends FragmentActivity {

    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn_base_fragment);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }
}
