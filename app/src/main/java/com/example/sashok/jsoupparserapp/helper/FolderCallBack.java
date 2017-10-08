package com.example.sashok.jsoupparserapp.helper;

import com.example.sashok.jsoupparserapp.model.Folder;
import com.example.sashok.jsoupparserapp.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 2.10.17.
 */

public interface FolderCallBack {
    public void onFoldersGetted(List<Folder> folders);
}
