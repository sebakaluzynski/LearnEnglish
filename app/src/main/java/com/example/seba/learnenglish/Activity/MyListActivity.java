package com.example.seba.learnenglish.Activity;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seba.learnenglish.R;
import com.example.seba.learnenglish.TodoDbAdapter;
import com.example.seba.learnenglish.Word;
import com.example.seba.learnenglish.WordAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;



public class MyListActivity extends Activity {

    private EditText polish;
    private EditText english;
    private ListView listView;
    private TextView textView;
    private TodoDbAdapter todoDbAdapter;
    private Cursor todoCursor;
    private List<Word> words;
    private WordAdapter listAdapter;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(android.R.id.list);


        initListView();
        editedWord(); //Chyba trzeba sprawdzać czy było zmieniane
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

//                String word = words.get(position).getWord();
//
//                todoDbAdapter.deleteWord(word);
//                updateListViewData();
//                listAdapter.notifyDataSetChanged();
//                finish();
                //String word = words.get(position).getWord();
                Log.d("msg", "wlazłem");

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        new ContextThemeWrapper(MyListActivity.this, R.style.myDialog));
                builder.setMessage("Czy na pewno usunąć: " + words.get(position).getWord()
                        + " - " + words.get(position).getTranslated() + " ?");
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String word = words.get(position).getWord();

                        todoDbAdapter.deleteWord(word);
                        updateListViewData();
                        finish();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                builder.create();
                builder.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Log.v("long clicked","pos: " + pos);

                long idWord = words.get(pos).getId();
                String wordPl = words.get(pos).getWord();
                String wordEng = words.get(pos).getTranslated();

                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("id_word", (long)idWord);
                intent.putExtra("polish_word", wordPl);
                intent.putExtra("english_word", wordEng);

                startActivity(intent);
                return true;
            }
        });
    }



    private void initListView() {
        fillListViewData();
        initListViewOnItemClick();
    }

    private void fillListViewData() {
        todoDbAdapter = new TodoDbAdapter(getApplicationContext());
        todoDbAdapter.open();
        getAllWords();
        listAdapter = new WordAdapter(this, words);
        listView.setAdapter(listAdapter);
    }

    private void getAllWords() {
        words = new ArrayList<Word>();
        todoCursor = getAllEntriesFromDb();
        updateTaskList();
    }

    private Cursor getAllEntriesFromDb() {
        todoCursor = todoDbAdapter.getAllWord();
        if(todoCursor != null) {
            startManagingCursor(todoCursor);
            todoCursor.moveToFirst();
        }
        return todoCursor;
    }

    private void updateTaskList() {
        if(todoCursor != null && todoCursor.moveToFirst()) {
            do {
                long id = todoCursor.getLong(TodoDbAdapter.ID_COLUMN);
                String word = todoCursor.getString(TodoDbAdapter.WORDS_COLUMN);
                String translate = todoCursor.getString(TodoDbAdapter.TRANSLATE_COLUMN);
                words.add(new Word(id, word, translate));
            } while(todoCursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        if(todoDbAdapter != null)
            todoDbAdapter.close();
        super.onDestroy();
    }

    private void initListViewOnItemClick() {
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                Word word = words.get(position);

                updateListViewData();
            }
        });
    }

    private void updateListViewData() {
        todoCursor.requery();
        words.clear();
        updateTaskList();
        listAdapter.notifyDataSetChanged();
    }

    public void editedWord() {

        Intent editedWordIntent = getIntent();
        long idWord = editedWordIntent.getLongExtra("id", 1L);
        String editedPolishWord = editedWordIntent.getStringExtra("editpl");
        String editedEnglishWord = editedWordIntent.getStringExtra("editeng");

        todoDbAdapter.updateTodo(0, editedPolishWord, editedEnglishWord);
        Log.d("MyListActivity", "id2: " + idWord);
        Log.d("MyListActivity", "pl2: " + editedPolishWord);
        Log.d("MyListActivity", "eng2: " + editedEnglishWord);
        updateListViewData();

    }
}