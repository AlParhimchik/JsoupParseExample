package com.example.swipacardlibrary.adapter;

import com.example.swipacardlibrary.listener.WordCardAdapterListener;
import com.example.swipacardlibrary.model.WordInterface;


/**
 * Created by sashok on 28.9.17.
 */

public abstract class WordAdapter<T extends WordInterface> extends BaseAdapter<T>  implements WordCardAdapterListener<T> {

    public WordAdapter(){
        position=0;
    }

}
