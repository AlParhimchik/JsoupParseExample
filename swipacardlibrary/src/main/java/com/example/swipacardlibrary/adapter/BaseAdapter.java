package com.example.swipacardlibrary.adapter;


import com.example.swipacardlibrary.listener.BaseCardAdapterListener;
import com.example.swipacardlibrary.listener.DataSetObserver;

/**
 * Created by sashok on 28.9.17.
 */

public abstract class BaseAdapter<T> implements BaseCardAdapterListener<T> {
    protected T currentItem;
    protected int position;
    protected DataSetObserver observer;

    public T getRandomItem() {
        if (getItemCount() == 0) return null;
        currentItem = getItem(position % getItemCount());
        return currentItem;
    }

    protected BaseAdapter() {
        position = 0;
    }

    public T getCurrentItem() {
        return currentItem;
    }

    public void registerDataObserver(DataSetObserver observer) {
        this.observer = observer;
    }

    public void unregisterDataObserver() {
        this.observer = null;
    }

    public void notifyDataSetChanged() {
        if (observer != null) observer.onDataSetChanged();
    }


    public T getNextItem() {
        if (getItemCount() == 0) return null;
        position = (++position) % getItemCount();
        currentItem = getItem(position);
        return currentItem;
    }

    public T getPrevItem() {
        if (getItemCount() == 0) return null;
        position--;
        if (position < 0) position = getItemCount() - 1;
        currentItem = getItem(position);
        return currentItem;
    }


}
