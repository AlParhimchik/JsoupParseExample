package com.example.sashok.jsoupparserapp.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.sashok.jsoupparserapp.fragment.ShowCardWithWordsFragment;

/**
 * Created by sashok on 2.10.17.
 */

public class ShowCardBuilder extends FragmentBuilder {
    public ShowCardBuilder(){

    }

    @Override
    public Fragment build() {
        return ShowCardWithWordsFragment.newInstance(args);
    }
}
