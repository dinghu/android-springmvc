package com.bitmain.hale.springmvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bitmain.hale.androidmvc.di.Autowired;
import com.bitmain.hale.androidmvc.utils.AndroidSpringMvc;
import com.bitmain.hale.springmvc.controller.LoginController;

public class MainActivity extends AppCompatActivity {
    @Autowired
    LoginController loginController;
    @Autowired
    LoginController x;
    @Autowired
    LoginController loginController2;
    @Autowired
    LoginController loginController3;
    @Autowired
    LoginController loginController4;
    @Autowired
    LoginController loginController5;
    @Autowired
    LoginController loginController6;
    @Autowired
    LoginController loginController7;
    @Autowired
    LoginController loginController8;
    @Autowired
    LoginController loginController9;
    @Autowired
    LoginController loginController10;
    @Autowired
    LoginController loginController11;
    @Autowired
    LoginController loginController12;
    @Autowired
    LoginController loginController13;
    @Autowired
    LoginController loginController14;
    @Autowired
    LoginController loginController15;
    @Autowired
    LoginController loginController16;
    @Autowired
    LoginController loginController17;
    @Autowired
    LoginController loginController18;
    @Autowired
    LoginController loginController19;
    @Autowired
    LoginController loginController20;
    @Autowired
    LoginController loginController21;
    @Autowired
    LoginController loginController22;
    @Autowired
    LoginController loginController23;
    @Autowired
    LoginController loginController24;
    @Autowired
    LoginController loginController71;
    @Autowired
    LoginController loginController81;
    @Autowired
    LoginController loginController91;
    @Autowired
    LoginController loginController101;
    @Autowired
    LoginController loginController211;
    @Autowired
    LoginController loginController311;
    @Autowired
    LoginController loginController411;
    @Autowired
    LoginController loginController511;
    @Autowired
    LoginController loginController611;
    @Autowired
    LoginController loginController711;
    @Autowired
    LoginController loginController811;
    @Autowired
    LoginController loginController911;
    @Autowired
    LoginController loginController1011;
    @Autowired
    LoginController loginController2111;
    @Autowired
    LoginController loginController3111;
    @Autowired
    LoginController loginController4111;
    @Autowired
    LoginController loginController5111;
    @Autowired
    LoginController loginController6111;
    @Autowired
    LoginController loginController7111;
    @Autowired
    LoginController loginController8111;
    @Autowired
    LoginController loginController9111;
    @Autowired
    LoginController loginController10111;
    @Autowired
    LoginController loginController21111;
    @Autowired
    LoginController loginController31111;
    @Autowired
    LoginController loginController41111;
    @Autowired
    LoginController loginController51111;
    @Autowired
    LoginController loginController61111;
    @Autowired
    LoginController loginController71111;
    @Autowired
    LoginController loginController81111;
    @Autowired
    LoginController loginController91111;
    @Autowired
    LoginController loginController101111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("mvc","1111");

        AndroidSpringMvc.inject(this);

        Log.i("mvc","2222");
        loginController.login("aaaa", "bbbbbb");
    }
}
