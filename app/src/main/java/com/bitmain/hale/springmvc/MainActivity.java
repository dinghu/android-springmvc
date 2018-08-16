package com.bitmain.hale.springmvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bitmain.hale.springmvc.controller.DoController;
import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.utils.AndroidSpringMvc;

public class MainActivity extends AppCompatActivity {

    @Autowired(name = "b")
    DoController doController;
    @Autowired
    Modle modle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("mvc","11111");
        AndroidSpringMvc.inject(this);
        Log.i("mvc","22222");
        doController.doSomething();
        Log.i("mvc",modle.name);
    }
}
