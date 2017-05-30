package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.seba.learnenglish.R;
import com.example.seba.learnenglish.TodoDbAdapter;

/**
 * Created by Seba on 26.05.2017.
 */
public class RepeatingActivity extends Activity{

    private TodoDbAdapter todoDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);

        fillListViewData();

        //Log.d("msg-random", " " + todoDbAdapter.getRowRandom());
    }

    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
    }
}
