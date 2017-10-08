package com.example.swipacardlibrary.model;

import java.util.AbstractList;

/**
 * Created by sashok on 28.9.17.
 */
public interface WordInterface {
    public String getEnWord();

    public AbstractList getTranslations();

    public String getTranslation();

    public String getTranscription();

    public String getExample();

    public String getExampleTrans();


}