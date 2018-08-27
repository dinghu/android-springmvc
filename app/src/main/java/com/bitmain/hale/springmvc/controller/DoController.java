package com.bitmain.hale.springmvc.controller;

import com.bitmain.hale.androidmvc.di.Autowired;
import com.bitmain.hale.androidmvc.di.Controller;
import com.bitmain.hale.springmvc.service.BusinessService;

/**
 * Created by harry.ding on 2018/8/15.
 */
@Controller
public class DoController {
    @Autowired
    BusinessService businessService;

    public void doSomething() {
        businessService.doSomething();
    }
}
