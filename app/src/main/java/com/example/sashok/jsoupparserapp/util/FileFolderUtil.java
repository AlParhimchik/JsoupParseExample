package com.example.sashok.jsoupparserapp.util;

import android.content.Context;
import android.util.Log;

import com.example.sashok.jsoupparserapp.interfaces.FileFolderInterface;
import com.example.sashok.jsoupparserapp.model.Folder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 8.10.17.
 */

public class FileFolderUtil extends FolderGetter implements FileFolderInterface {
    private static String FILE_NAME = "folders.txt";
    private Context mContext;
    public FileFolderUtil(Context context){
        this.mContext=context;
    }
    @Override
    public ArrayList<Folder> fetchFolders() {
        File root = android.os.Environment.getExternalStorageDirectory();
        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;

        File file = new File(file_name);
        File storageDir = null;
        storageDir = mContext.getExternalFilesDir(null);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fin;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);
            ArrayList<Folder> folders = (ArrayList<Folder>) ois.readObject();
            ois.close();
            Log.v("TAG", "Records read successfully");
            return folders;
        } catch (Exception e) {
            Log.e("TAG", "Cant read saved records" + e.getMessage());
            return null;
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (Exception e) {
                    Log.e("RAG", "Error in closing stream while reading records" + e.getMessage());
                }
        }
    }
    @Override
    public  void saveFoldersOnFile( List<Folder> folders) {
        FileOutputStream outputStream;
        ObjectOutputStream oos = null;
        File root = android.os.Environment.getExternalStorageDirectory();
        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;
        File file = new File(file_name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            outputStream = new FileOutputStream(file);
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(folders);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    @Override
    public  void deleteFile() {
        File root = android.os.Environment.getExternalStorageDirectory();

        String file_name = root.getAbsolutePath() + "/Download/" + FILE_NAME;

        File file = new File(file_name);
        if (file.exists()) {
            file.delete();
        }
    }
    @Override
    public  void saveFolder( Folder folder) {
        List<Folder> folders = new ArrayList<Folder>();
        folders.add(folder);
        saveFoldersOnFile(folders);
    }

}
