package com.bitmain.hale.springmvc.application;

import android.app.Application;

import com.bitmain.hale.androidmvc.config.Configuration;
import com.bitmain.hale.androidmvc.utils.AndroidSpringMvc;
import com.bitmain.hale.springmvc.controller.DoController;
import com.bitmain.hale.springmvc.controller.LoginController;
import com.bitmain.hale.springmvc.dao.impl.AccountDaoImpl;
import com.bitmain.hale.springmvc.service.impl.BusinessServiceImplA;
import com.bitmain.hale.springmvc.service.impl.BusinessServiceImplB;
import com.bitmain.hale.springmvc.service.impl.LoginServiceImpl;

/**
 * Created by harry.ding on 2018/8/24.
 */

public class AndroidSpringMvcApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Class classArray[] = new Class[]{
                //controller
                DoController.class,
                LoginController.class,

                //dao
                AccountDaoImpl.class,

                //Service
                BusinessServiceImplA.class,
                BusinessServiceImplB.class,
                LoginServiceImpl.class
        };
        Configuration.Builder builder = new Configuration.Builder(this).setBeanClasses(classArray);
//        Configuration.Builder builder = new Configuration.Builder(this);
        AndroidSpringMvc.init(builder.build());
    }
}
