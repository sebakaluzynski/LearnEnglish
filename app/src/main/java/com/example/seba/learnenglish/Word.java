package com.example.seba.learnenglish;

/**
 * Created by Seba on 15.04.2017.
 */

public class Word {

    private long id;
    private String word;
    private String translated;

    public Word(long id, String description, String completed) {
        this.id = id;
        this.word = description;
        this.translated = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String word) {
        this.translated = translated;
    }
}
