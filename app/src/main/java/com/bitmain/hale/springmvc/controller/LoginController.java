package com.bitmain.hale.springmvc.controller;

import android.util.Log;

import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.di.Controller;
import com.bitmain.hale.springmvc.service.LoginService;

/**
 * Created by harry.ding on 2018/8/16.
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;


    public void login(String account, String password) {
        loginService.login(account,password);
    }
}
