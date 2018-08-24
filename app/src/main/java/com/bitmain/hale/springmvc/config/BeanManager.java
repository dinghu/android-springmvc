package com.bitmain.hale.springmvc.config;


import android.content.Context;
import android.util.Log;

import com.bitmain.hale.springmvc.di.Controller;
import com.bitmain.hale.springmvc.di.Dao;
import com.bitmain.hale.springmvc.di.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by harry.ding on 2018/8/13.
 */

public class BeanManager {


    static boolean isLoadBean = false;

//    private static HashMap<Class<? extends Annotation>, List<Class<?>>> beanMap = new HashMap();

    //    static {
//        beanMap.put(Service.class, new ArrayList<Class<?>>());
//        beanMap.put(Dao.class, new ArrayList<Class<?>>());
//        beanMap.put(Controller.class, new ArrayList<Class<?>>());
//    }
//    public static List<Class<?>> getBeans(Class<? extends Annotation> annotationClass) {
//        return beanMap.get(annotationClass);
//    }
    private static ArrayList<Class<?>> beans = new ArrayList<>();

    public static ArrayList<Class<?>> getBeans() {
        return beans;
    }


    public static void init(Context context) throws IOException {
        if (!isLoadBean) {
            scanForModel(context);
            isLoadBean = true;
        }

    }

    private static void scanForModel(Context context) throws IOException {
        String packageName = context.getPackageName();
        String sourcePath = context.getApplicationInfo().sourceDir;
        List<String> paths = new ArrayList<String>();

        if (sourcePath != null && !(new File(sourcePath).isDirectory())) {
            DexFile dexfile = new DexFile(sourcePath);
            Enumeration<String> entries = dexfile.entries();

            while (entries.hasMoreElements()) {
                String path = entries.nextElement();
                if (path.contains("com.bitmain.hale.springmvc.service.impl")
                        || path.contains("com.bitmain.hale.springmvc.controller")
                        || path.contains("com.bitmain.hale.springmvc.dao")) {
                    paths.add(path);
                }
            }
        }
        // Robolectric fallback
        else {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources("");

            while (resources.hasMoreElements()) {
                String path = resources.nextElement().getFile();
                if (path.contains("bin") || path.contains("classes")) {
                    if (path.contains("com.bitmain.hale.springmvc.service.impl")
                            || path.contains("com.bitmain.hale.springmvc.controller")
                            || path.contains("com.bitmain.hale.springmvc.dao")) {
                        paths.add(path);
                    }
                }
            }
        }

        for (String path : paths) {
            File file = new File(path);
            scanForModelClasses(file, packageName, context.getClassLoader());
        }
    }

    private static void scanForModelClasses(File path, String packageName, ClassLoader classLoader) {
        if (path.isDirectory()) {
            for (File file : path.listFiles()) {
                scanForModelClasses(file, packageName, classLoader);
            }
        } else {
            String className = path.getName();

            if (!path.getPath().equals(className)) {
                className = path.getPath();

                if (className.endsWith(".class")) {
                    className = className.substring(0, className.length() - 6);
                } else {
                    return;
                }

                className = className.replace(System.getProperty("file.separator"), ".");

                int packageNameIndex = className.lastIndexOf(packageName);
                if (packageNameIndex < 0) {
                    return;
                }

                className = className.substring(packageNameIndex);
            }

            try {
                Class<?> discoveredClass = Class.forName(className, false, classLoader);
                Service serviceAnnotation = discoveredClass.getAnnotation(Service.class);
                Controller controllerAnnotation = discoveredClass.getAnnotation(Controller.class);
                Dao daoAnnotation = discoveredClass.getAnnotation(Dao.class);
                if (serviceAnnotation != null || controllerAnnotation != null || daoAnnotation != null) {
                    beans.add(discoveredClass);
                }
            } catch (ClassNotFoundException e) {
                Log.e("Couldn't create class.", e.getMessage());
            }
        }
    }
}
