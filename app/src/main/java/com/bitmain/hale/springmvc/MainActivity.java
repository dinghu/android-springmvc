package com.bitmain.hale.springmvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bitmain.hale.androidmvc.di.Autowired;
import com.bitmain.hale.androidmvc.utils.AndroidSpringMvc;
import com.bitmain.hale.springmvc.controller.LoginController;

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
