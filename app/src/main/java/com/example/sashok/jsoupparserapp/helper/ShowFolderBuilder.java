package com.example.sashok.jsoupparserapp.helper;

import android.support.v4.app.Fragment;

import com.example.sashok.jsoupparserapp.fragment.ShowFoldersFragment;

/**
 * Created by sashok on 2.10.17.
 */

public class ShowFolderBuilder extends FragmentBuilder {
    @Override
    public Fragment build() {
        return ShowFoldersFragment.newInstance(args);
    }
}
