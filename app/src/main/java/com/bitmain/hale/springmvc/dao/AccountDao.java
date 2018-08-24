package com.bitmain.hale.springmvc.dao;

import android.util.Log;

import com.bitmain.hale.springmvc.di.Dao;

/**
 * Created by harry.ding on 2018/8/16.
 */
public interface AccountDao {
    public void insert(String account, String password);
}
