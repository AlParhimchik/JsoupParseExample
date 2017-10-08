package com.example.sashok.jsoupparserapp.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by sashok on 2.10.17.
 */

public  abstract class FragmentBuilder{
    protected Bundle args;
    public FragmentBuilder setArgs(Bundle args){
        this.args=args;
        return  this;

    }
    public abstract Fragment build();


}


