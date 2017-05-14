package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.seba.learnenglish.R;
import com.example.seba.learnenglish.TodoDbAdapter;

public class FindWordActivity extends Activity {

    private RadioButton radioButtonPL;
    private TodoDbAdapter todoDbAdapter;
    private EditText searchWord;
    private TextView searchedWord;
    private TextView foundWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_find_word);

        radioButtonPL = (RadioButton) findViewById(R.id.radioButtonPL);
        searchWord = (EditText) findViewById(R.id.search_word);
        searchedWord = (TextView) findViewById(R.id.search);
        foundWord = (TextView) findViewById(R.id.foundWord);
        fillListViewData();

    }
    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
    }

    public void searchWord(View view){
        searchedWord.setVisibility(View.VISIBLE);
        foundWord.setVisibility(View.VISIBLE);
        
        String word = searchWord.getText().toString();
        if(todoDbAdapter.checkIfExist(word)) {
            String found = todoDbAdapter.getW(word).getTranslated();
            foundWord.setText(found);
            searchedWord.setText(word);
        } else {
            foundWord.setText(R.string.no_found);
            searchedWord.setVisibility(View.GONE);
        }



    }
}
