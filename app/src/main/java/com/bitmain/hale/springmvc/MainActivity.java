package com.bitmain.hale.springmvc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bitmain.hale.springmvc.config.BeanManager;
import com.bitmain.hale.springmvc.controller.DoController;
import com.bitmain.hale.springmvc.controller.LoginController;
import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.utils.AndroidSpringMvc;

public class MainActivity extends AppCompatActivity {
    @Autowired
    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidSpringMvc.inject(this);

        loginController.login("aaaa", "bbbbbb");
    }
}
