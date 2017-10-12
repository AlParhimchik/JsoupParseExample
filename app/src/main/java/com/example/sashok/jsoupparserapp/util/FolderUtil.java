package com.example.sashok.jsoupparserapp.util;

import com.example.sashok.jsoupparserapp.model.Folder;

import java.util.List;

/**
 * Created by sashok on 8.10.17.
 */

public  class FolderUtil {
    public static List<Folder> fetchFolders(FolderGetter method){
        return method.fetchFolders();
    }
}
