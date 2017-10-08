package com.example.sashok.jsoupparserapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 1.10.17.
 */

public class Folder implements Serializable{
    private int ID;

    private List<String> en_names;
    private List<String> rus_names;
    private String folderURL;

    public Folder() {
        rus_names = new ArrayList<>();
        en_names = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void set_rus_name(String name) {
        rus_names.add(name);
    }

    public List<String> get_rus_names() {
        return rus_names;
    }

    public void set_rus_names(List<String> names) {
        rus_names.addAll(names);
    }


    public void set_eng_names(List<String> eng_names) {
        this.en_names.addAll(eng_names);
    }

    public void set_eng_name(String name) {
        this.en_names.add(name);
    }

    public List<String> get_eng_names() {
        return en_names;
    }

    public String getFolderURL() {
        return folderURL;
    }

    public void setFolderURL(String folderURL) {
        this.folderURL = folderURL;
    }
}
