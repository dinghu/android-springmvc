package com.bitmain.hale.springmvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bitmain.hale.springmvc.controller.DoController;
import com.bitmain.hale.springmvc.controller.LoginController;
import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.utils.AndroidSpringMvc;

public class MainActivity extends AppCompatActivity {

    @Autowired(name = "b")
    DoController doController;

    @Autowired
    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("mvc","11111");
        AndroidSpringMvc.inject(this);
        Log.i("mvc","22222");
        doController.doSomething();

        loginController.login("aaaa","bbbbbb");
    }
}
