package com.bitmain.hale.springmvc.controller;

import android.util.Log;

import com.bitmain.hale.androidmvc.di.Autowired;
import com.bitmain.hale.androidmvc.di.Controller;
import com.bitmain.hale.springmvc.service.BusinessService;
import com.bitmain.hale.springmvc.service.LoginService;

/**
 * Created by harry.ding on 2018/8/16.
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    BusinessService businessService;


    public void login(String account, String password) {
        loginService.login(account,password);
        businessService.doSomething();
    }
}
