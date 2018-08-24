package com.bitmain.hale.springmvc.service.impl;

import android.util.Log;

import com.bitmain.hale.springmvc.dao.DataDAo;
import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.di.Service;
import com.bitmain.hale.springmvc.service.BusinessService;

/**
 * Created by harry.ding on 2018/8/15.
 */
@Service(name = "a")
public class BusinessServiceImplA implements BusinessService{

    @Autowired
    DataDAo dataDAo;

    @Override
    public void doSomething() {
        dataDAo.insert();
    }
}
