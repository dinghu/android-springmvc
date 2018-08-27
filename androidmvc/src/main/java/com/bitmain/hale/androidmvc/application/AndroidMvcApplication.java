package com.bitmain.hale.androidmvc.application;

import android.app.Application;
import android.util.Log;

import com.bitmain.hale.androidmvc.utils.AndroidSpringMvc;


/**
 * Created by harry.ding on 2018/8/24.
 */

public class AndroidMvcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("mvc", "------aaaaa-----");
        AndroidSpringMvc.init(this);
        Log.i("mvc", "------00000-----");
    }
}
