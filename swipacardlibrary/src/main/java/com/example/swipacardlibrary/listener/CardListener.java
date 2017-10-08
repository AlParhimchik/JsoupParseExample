package com.example.swipacardlibrary.listener;

import com.example.swipacardlibrary.model.WordInterface;

/**
 * Created by sashok on 28.9.17.
 */

public interface CardListener {
    public void onSwipedLeft();
    public void onSwipedRight();
    public void onCardClicked();
    public void onFlipAnimEnded();
    public void onDataSetChanged();
    public void onPositiveButtonClicked();
    public void onNegativeButtonClicked();
}
