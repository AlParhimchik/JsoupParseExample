package com.example.sashok.jsoupparserapp.interfaces;

import com.example.sashok.jsoupparserapp.model.Folder;

import java.util.List;

/**
 * Created by sashok on 8.10.17.
 */

public interface FileFolderInterface extends FolderDownLoadInterface {
    public void saveFoldersOnFile( List<Folder> folders);
    public void deleteFile();
    public  void saveFolder(Folder folder);
}
