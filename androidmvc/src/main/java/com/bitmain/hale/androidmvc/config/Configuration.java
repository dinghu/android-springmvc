package com.bitmain.hale.androidmvc.config;

import android.content.Context;

import com.bitmain.hale.androidmvc.di.Controller;
import com.bitmain.hale.androidmvc.di.Dao;
import com.bitmain.hale.androidmvc.di.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by harry.ding on 2018/8/27.
 */

public class Configuration {
    private List<Class<?>> mBeanClasses;
    private Context mContext;

    private Configuration(Builder builder) {
        mBeanClasses = builder.mBeanClasses;
        mContext = builder.mContext;
    }

    public List<Class<?>> getBeanClasses() {
        return mBeanClasses;
    }

    public Context getContext() {
        return mContext;
    }

    public static final class Builder {
        private List<Class<?>> mBeanClasses = new ArrayList<>();
        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setBeanClasses(Class<?>... beanClasses) {
            for (int i = 0; i < beanClasses.length; i++) {
                Class<?> classBean = beanClasses[i];
                Service serviceAnnotation = classBean.getAnnotation(Service.class);
                Controller controllerAnnotation = classBean.getAnnotation(Controller.class);
                Dao daoAnnotation = classBean.getAnnotation(Dao.class);
                if (serviceAnnotation != null || controllerAnnotation != null || daoAnnotation != null) {
                    if (!mBeanClasses.contains(classBean)) {
                        mBeanClasses.add(classBean);
                    }
                }
            }
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
