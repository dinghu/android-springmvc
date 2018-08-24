package com.bitmain.hale.springmvc.dao.impl;

import android.util.Log;

import com.bitmain.hale.springmvc.dao.AccountDao;
import com.bitmain.hale.springmvc.di.Dao;

/**
 * Created by harry.ding on 2018/8/24.
 */
@Dao
public class AccountDaoImpl implements AccountDao {
    public void insert(String account, String password) {
        Log.i("mvc", "login .........account=" + account + ",password=" + password);
    }
}
