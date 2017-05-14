package com.example.seba.learnenglish;

import android.app.Activity;
import android.provider.UserDictionary;

import com.example.seba.learnenglish.R;

/**
 * Created by Seba on 15.04.2017.
 */
import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private Activity context;
    private List<Word> words;

    public WordAdapter(Activity context, List<Word> words) {
        super(context, R.layout.one_item, words);
        this.context = context;
        this.words = words;
    }

    static class WordHolder {
        TextView t_id, t_word, t_translate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WordHolder wordHolder;
        View row = convertView;
        if (row == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.one_item, null, true);

            wordHolder = new WordHolder();
            wordHolder.t_id = (TextView) row.findViewById(R.id.id);
            wordHolder.t_word = (TextView) row.findViewById(R.id.word);
            wordHolder.t_translate = (TextView) row.findViewById(R.id.translate);
            row.setTag(wordHolder);
            } else {
            wordHolder = (WordHolder) row.getTag();
        }
        Word word = words.get(position);
        wordHolder.t_id.setText(String.valueOf(word.getId()));
        wordHolder.t_word.setText(word.getWord());
        wordHolder.t_translate.setText(word.getTranslated());

        return row;
        }
}
