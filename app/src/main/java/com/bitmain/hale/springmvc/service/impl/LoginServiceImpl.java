package com.bitmain.hale.springmvc.service.impl;

import android.util.Log;

import com.bitmain.hale.springmvc.dao.AccountDao;
import com.bitmain.hale.springmvc.di.Autowired;
import com.bitmain.hale.springmvc.di.Service;
import com.bitmain.hale.springmvc.service.LoginService;

/**
 * Created by harry.ding on 2018/8/16.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountDao accountDao;

    public void login(String account, String password) {
        accountDao.insert(account, password);
    }
}
