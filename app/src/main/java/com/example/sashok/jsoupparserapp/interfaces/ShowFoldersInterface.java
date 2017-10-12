package com.example.sashok.jsoupparserapp.interfaces;

import com.example.sashok.jsoupparserapp.model.Folder;
import com.example.sashok.jsoupparserapp.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 2.10.17.
 */

public interface ShowFoldersInterface {
    public void onFoldersFetches();
    public void getFolders();
    public void onFolderClicked(Folder folder);
}
