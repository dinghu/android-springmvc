package com.bitmain.hale.springmvc.application;

import android.app.Application;
import android.util.Log;

import com.bitmain.hale.springmvc.utils.AndroidSpringMvc;

/**
 * Created by harry.ding on 2018/8/24.
 */

public class AndroidSpringMvcApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("mvc", "------aaaaa-----");
        AndroidSpringMvc.init(this);
        Log.i("mvc", "------00000-----");
    }
}
