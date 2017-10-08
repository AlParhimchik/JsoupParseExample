package com.example.sashok.jsoupparserapp.model;

import com.example.swipacardlibrary.model.WordInterface;

import java.util.AbstractList;

/**
 * Created by sashok on 2.10.17.
 */

public class Word  implements WordInterface{
    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String getEnWord() {
        return getOriginal();
    }

    @Override
    public AbstractList getTranslations() {
        return null;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String getTranscription() {
        return transcription;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setTranscription(String transcriprion) {
        this.transcription = transcriprion;
    }

    private String original;
    private  String translation;
    private String transcription;
    private String example;

    public String getExample() {
        return example;
    }

    @Override
    public String getExampleTrans() {
        return example_translate;
    }

    public void setExample(String example) {
        this.example = example;
    }

    private String example_translate;

    public String getExapmleTranslate(){
        return example_translate;
    }
    public void setExapmleTranslate(String tr){
        example_translate=tr;
    }
}
