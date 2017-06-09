package com.example.seba.learnenglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Seba on 15.04.2017.
 */

public class TodoDbAdapter {

    private static final String DEBUG_TAG = "SqLiteTodoManager";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "w.db";
//    private static final String DB_NAME = "word.db";
    private static final String DB_TODO_TABLE = "WORD";

    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;
    public static final String KEY_WORDS = "WORDS";
    public static final String WORDS_OPTIONS = "TEXT NOT NULL";
    public static final int WORDS_COLUMN = 1;
    public static final String KEY_TRANSLATE = "TRANSLATE";
    public static final String TRANSLATE_OPTIONS = "TEXT NOT NULL";
    public static final int TRANSLATE_COLUMN = 2;

    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE " + DB_TODO_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_WORDS + " " + WORDS_OPTIONS + ", " +
                    KEY_TRANSLATE + " " + TRANSLATE_OPTIONS +
                    ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + DB_TODO_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_TODO_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_TODO_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TODO_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_TODO_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    public TodoDbAdapter(Context context) {
        this.context = context;
    }

    public TodoDbAdapter open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertWord(String word, String translate){

        Log.d("msg", "insertData");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_WORDS, word);
        contentValues.put(KEY_TRANSLATE, translate);


        Log.d("msg", "wordPL: " +word);
        Log.d("msg", "wordENG: " +translate);
        return db.insert(DB_TODO_TABLE,null,contentValues);
    }

    public boolean updateTodo(Word w) {
        long id = w.getId();
        String word = w.getWord();
        String translate = w.getTranslated();
        return updateTodo(id, word, translate);
    }

    public boolean updateTodo(long id, String word, String translate) {
        String where = KEY_ID + "=" + id;
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put(KEY_WORDS, word);
        updateTodoValues.put(KEY_TRANSLATE, translate);
        db.update(DB_TODO_TABLE, updateTodoValues, where, null);
        return true;
    }

    public void deleteWord(String word){
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DB_TODO_TABLE + " WHERE " + KEY_WORDS + "= '" + word + "'");
        db.close();
    }

    public Cursor getAllWord() {
        String[] columns = {KEY_ID, KEY_WORDS, KEY_TRANSLATE};
        return db.query(DB_TODO_TABLE, columns, null, null, null, null, null);
    }

    public Word getWord(long id) {
        String[] columns = {KEY_ID, KEY_WORDS, KEY_TRANSLATE};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_TODO_TABLE, columns, where, null, null, null, null);
        Word word = null;
        if(cursor != null && cursor.moveToFirst()) {
            String words = cursor.getString(WORDS_COLUMN);
            String translate = cursor.getString(TRANSLATE_COLUMN);
            word = new Word(id, words, translate);
        }
        return word;
    }


    public Word getW(String word) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DB_TODO_TABLE, new String[] { KEY_ID,
                        KEY_WORDS, KEY_TRANSLATE }, KEY_WORDS + "=?",
                new String[] { word }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Word words = new Word(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        Log.d("msg", "getword: " + words);

        return words;
    }

    public boolean checkIfExist(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DB_TODO_TABLE, new String[] { KEY_ID,
                        KEY_WORDS, KEY_TRANSLATE }, KEY_WORDS + "=?",
                new String[] { name }, null, null, null, null);


        if (cursor.getCount() > 0){
            Log.d("msg", "znaleziono");
            return true;
        } else {
            Log.d("msg", "znaleziono");
            return false;
        }
    }


    public Cursor getRowRandom() {
        Cursor cursor = this.db.query(DB_TODO_TABLE + " Order BY RANDOM() LIMIT 1",
                new String[] { "*" }, null, null, null, null, null);
        return cursor;
    }


}
