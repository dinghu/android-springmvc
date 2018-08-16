package com.bitmain.hale.springmvc.dao;

import android.util.Log;

import com.bitmain.hale.springmvc.di.Dao;

/**
 * Created by harry.ding on 2018/8/16.
 */
@Dao
public class AccountDao {
    public void insert(String account, String password) {
        Log.i("mvc", "login .........account=" + account + ",password=" + password);
    }
}
