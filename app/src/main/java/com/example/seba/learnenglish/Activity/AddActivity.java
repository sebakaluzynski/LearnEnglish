package com.example.seba.learnenglish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seba.learnenglish.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seba.learnenglish.R;
import com.example.seba.learnenglish.TodoDbAdapter;
import com.example.seba.learnenglish.WordAdapter;

public class AddActivity extends Activity {

    private Button addButton;
    private EditText englishWord;
    private EditText polishWord;
    private TodoDbAdapter todoDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_add);
        fillListViewData();
        englishWord = (EditText) findViewById(R.id.english_word);
        polishWord = (EditText) findViewById(R.id.polish_word);

    }

    public void addWordToDatabase(View view){
        String wordPL = polishWord.getText().toString();
        String wordENG = englishWord.getText().toString();
//
        Log.d("msg", "wordPL: " +wordPL);
//        Log.d("msg", "wordENG: " +wordENG);
//        Log.d("msg", "wordENG: " + wordPL.replaceAll("a", ""));


        if(wordPL.equals("") || wordENG.equals("")){
            Toast.makeText(getApplicationContext(), "Uzupełnij pola", Toast.LENGTH_SHORT).show();
        } else if(!todoDbAdapter.checkIfExist(wordPL)) {
            todoDbAdapter.insertWord(wordPL, wordENG);
            Toast.makeText(getApplicationContext(), "Dodano słowo", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Takie słowo zostało dodane wcześniej", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
    }


}
