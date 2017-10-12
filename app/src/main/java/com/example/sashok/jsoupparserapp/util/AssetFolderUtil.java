package com.example.sashok.jsoupparserapp.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.sashok.jsoupparserapp.model.Folder;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by sashok on 8.10.17.
 */

public class AssetFolderUtil extends FolderGetter {
    protected Context context;
    public  AssetFolderUtil(Context mContext){
        this.context=mContext;
    }

    @Override
    public ArrayList<Folder> fetchFolders() {
        AssetManager am = context.getAssets();
        ObjectInputStream ois = null;
        try {
            InputStream str = am.open("hello.txt");
            ois = new ObjectInputStream(str);
            ArrayList<Folder> folders = (ArrayList<Folder>) ois.readObject();
            ois.close();
            return folders;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (Exception e) {
                    Log.e("RAG", "Error in closing stream while reading records" + e.getMessage());
                }
        }
        return null;
    }
}
