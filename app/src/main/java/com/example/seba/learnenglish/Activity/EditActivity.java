package com.example.seba.learnenglish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seba.learnenglish.R;
import com.example.seba.learnenglish.TodoDbAdapter;

public class EditActivity extends Activity {
    private EditText english;
    private EditText polish;
    private TextView idword;
    private TodoDbAdapter todoDbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit);

        idword = (TextView) findViewById(R.id.id_word);
        english = (EditText) findViewById(R.id.word_eng);
        polish = (EditText) findViewById(R.id.word_pl);
        fillListViewData();
        prepareEditText();
    }

    public void prepareEditText(){
        Intent editIntent = getIntent();
        long idWord = editIntent.getLongExtra("id_word", 1L);
        String editPL = editIntent.getStringExtra("polish_word");
        String editEng = editIntent.getStringExtra("english_word");

        Log.d("msg","getWordTest " + todoDbAdapter.getW(editPL).getTranslated());

//        Log.d("msg","id: " + idWord);
//        Log.d("msg","pl: " + editPL);
//        Log.d("msg","eng: " + editEng);


        idword.setText(String.valueOf(idWord));
        polish.setText(editPL);
        english.setText(editEng);
    }

    public void sendEditedWord(View view){
        long id = Long.parseLong(idword.getText().toString());
        String editPL = polish.getText().toString();
        String editEng = english.getText().toString();


        //Log.d("msg","getWordTest " + todoDbAdapter.getWord(id).getWord());

        Log.d("msg","id1: " + id);
        Log.d("msg","pl1: " + editPL);
        Log.d("msg","eng1: " + editEng);

        if(todoDbAdapter == null){
            Log.d("error","error");
        }
        todoDbAdapter.updateTodo(id,editPL,editEng);

//        Intent editIntent = new Intent(this, MyListActivity.class);
//        editIntent.putExtra("id", (long)id);
//        editIntent.putExtra("editpl", editPL);
//        editIntent.putExtra("editeng", editEng);
//        startActivity(editIntent);
//        finish();

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
    }
}
