package com.example.sashok.jsoupparserapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sashok.jsoupparserapp.helper.FolderUtil;
import com.example.sashok.jsoupparserapp.helper.FragmentBuilder;
import com.example.sashok.jsoupparserapp.helper.ShowCardBuilder;
import com.example.sashok.jsoupparserapp.helper.ShowFolderBuilder;
import com.example.sashok.jsoupparserapp.interfaces.Folderlistener;
import com.example.sashok.jsoupparserapp.model.Folder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Folderlistener {
    public static final String BUNDLE_FOLDER_ID = "folder_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new ShowFolderBuilder().build());
        //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FolderUtil.saveFoldersOnFile(MainActivity.this, folders);
//            }
//        });
    }

    public void deleteFile() {

        FolderUtil.deleteFile(this);


    }


    public void readFromFile() {
        List<Folder> new_folders;
        new_folders = FolderUtil.readFoldersFromFile(this);
        if (new_folders != null) Log.i("TAG", "has elements");
        else
            Log.i("TAG", "no elems");
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.add(R.id.frame_layout, fragment, fragment.getTag());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onFolderClicked(Folder folder) {
        FragmentBuilder builder = new ShowCardBuilder();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_FOLDER_ID, folder.getID());
        Fragment fragment = builder.setArgs(bundle).build();
        replaceFragment(fragment);
    }

}
