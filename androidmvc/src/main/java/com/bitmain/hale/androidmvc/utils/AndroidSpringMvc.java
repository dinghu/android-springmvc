package com.bitmain.hale.androidmvc.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.bitmain.hale.androidmvc.config.BeanContainer;
import com.bitmain.hale.androidmvc.config.Configuration;
import com.bitmain.hale.androidmvc.di.Autowired;
import com.bitmain.hale.androidmvc.di.Controller;
import com.bitmain.hale.androidmvc.di.Dao;
import com.bitmain.hale.androidmvc.di.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by harry.ding on 2018/8/15.
 */

public class AndroidSpringMvc {
    private static AndroidSpringMvc instance = new AndroidSpringMvc();
    private HashMap<Class<?>, Object> beanMap = new HashMap();

    public static void init(Configuration configuration) {
        try {
            BeanContainer.init(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Object injectAsSingleInstance(Class<?> cls) throws Exception {
        if (instance.beanMap.get(cls) == null) {
            instance.beanMap.put(cls, injectAsNewInstance(cls));
        }
        return instance.beanMap.get(cls);
    }

    private Object injectAsNewInstance(Class<?> cls) throws Exception {
        Object injectInstance = cls.newInstance();
        //注入Autowired标记的对象
        injectObject(injectInstance);
        return injectInstance;
    }

    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        int extendCount = 0;
        int maxExtendCount = 3;
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            extendCount++;
            clazz = clazz.getSuperclass();
            if (clazz != null) {
                extendCount++;
                if (clazz.getName().startsWith("android.") || clazz.getName().startsWith("java.")) {
                    break;
                }

                if (extendCount >= maxExtendCount) {
                    break;
                }
            }

        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }


    private void injectObject(Object object) throws Exception {
//        Class<? extends Object> clazz = object.getClass();
        // 查询类上是否存在注解
        Field[] fields = getAllFields(object);//clazz.getDeclaredFields();
        for (Field field : fields) {
            Autowired fieldAnnotation = field.getAnnotation(Autowired.class);
            if (fieldAnnotation != null) {
                Class<?> fieldType = field.getType();
                Object injectInstance = null;
                String autowiredAnnotationName = fieldAnnotation.name();

                String beanName = fieldType.getName();
                if (!TextUtils.isEmpty(autowiredAnnotationName)) {
                    beanName = fieldType.getName() + "_" + autowiredAnnotationName;
                }

                Class<?> beanCls = BeanContainer.getBean(beanName);
                if (beanCls == null) {
                    //遍历实体配置
                    ArrayList<Class<?>> classArrayList = BeanContainer.getAllBeans();
                    for (int i = 0; i < classArrayList.size(); i++) {
                        if (fieldType.isAssignableFrom(classArrayList.get(i))) {//找到实现类
                            Class<?> cls = classArrayList.get(i);
                            Service serviceAnnotation = cls.getAnnotation(Service.class);
                            Controller controllerAnnotation = cls.getAnnotation(Controller.class);
                            Dao daoAnnotation = cls.getAnnotation(Dao.class);
                            if (cls.getAnnotation(Service.class) != null) {//注入Service
                                String serviceAnnotationName = serviceAnnotation.name();
                                //可以注入的条件是名字相同 或者 autowiredAnnotationName无标记
                                if ((!TextUtils.isEmpty(autowiredAnnotationName) && autowiredAnnotationName.equals(serviceAnnotationName)
                                        || (TextUtils.isEmpty(autowiredAnnotationName)))) {
                                    beanCls = cls;
                                    break;
                                }
                            } else if (controllerAnnotation != null) {//注入Controller
                                beanCls = cls;
                                break;
                            } else if (daoAnnotation != null) {//注入Dao
                                beanCls = cls;
                                break;
                            }
                        }
                    }

                    if (beanCls != null) {
                        BeanContainer.addBean(beanName, beanCls);
                    }
                }

                if (beanCls != null) {
                    Service serviceAnnotation = beanCls.getAnnotation(Service.class);
                    Controller controllerAnnotation = beanCls.getAnnotation(Controller.class);
                    Dao daoAnnotation = beanCls.getAnnotation(Dao.class);
                    if (serviceAnnotation != null) {//注入Service
                        String serviceAnnotationName = serviceAnnotation.name();
                        //可以注入的条件是名字相同 或者 autowiredAnnotationName无标记
                        if ((!TextUtils.isEmpty(autowiredAnnotationName) && autowiredAnnotationName.equals(serviceAnnotationName)
                                || (TextUtils.isEmpty(autowiredAnnotationName)))) {
                            injectInstance = serviceAnnotation.singleInstance() ? injectAsSingleInstance(beanCls) : injectAsNewInstance(beanCls);
                            field.setAccessible(true);
                            field.set(object, injectInstance);
                        }
                    } else if (controllerAnnotation != null) {//注入Controller
                        injectInstance = controllerAnnotation.singleInstance() ? injectAsSingleInstance(beanCls) : injectAsNewInstance(beanCls);
                        field.setAccessible(true);
                        field.set(object, injectInstance);
                    } else if (daoAnnotation != null) {//注入Dao
                        injectInstance = daoAnnotation.singleInstance() ? injectAsSingleInstance(beanCls) : injectAsNewInstance(beanCls);
                        field.setAccessible(true);
                        field.set(object, injectInstance);
                    }
                }

                if (injectInstance == null) {
                    try {
                        injectInstance = fieldType.newInstance();
                        field.setAccessible(true);
                        field.set(object, injectInstance);
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(fieldType + " cannot Autowired inject");
                    }
                }
            }
        }

    }

    public static void inject(Object object) {
        try {
            instance.injectObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
