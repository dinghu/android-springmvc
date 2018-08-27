package com.bitmain.hale.androidmvc.config;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;


import com.bitmain.hale.androidmvc.di.Controller;
import com.bitmain.hale.androidmvc.di.Dao;
import com.bitmain.hale.androidmvc.di.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
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


    @SuppressWarnings("unchecked")
    public static <T> T getMetaData(Context context, String name) {
        try {
            final ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);

            if (ai.metaData != null) {
                return (T) ai.metaData.get(name);
            }
        }
        catch (Exception e) {
            Log.i("mvc","Couldn't find meta-data: " + name);
        }

        return null;
    }


    public static void init(Context context) throws IOException {
        String scanPackageList = getMetaData(context,"component-scan");
        if (scanPackageList != null) {
            String pakageList[] = scanPackageList.split(",");
            if (!isLoadBean) {
                scanForModel(context,pakageList);
                isLoadBean = true;
            }
        }else {
            throw new RuntimeException("cant load component-scan config");
        }

    }

    private static void scanForModel(Context context,String pakageList[]) throws IOException {
        String packageName = context.getPackageName();
        String sourcePath = context.getApplicationInfo().sourceDir;
        List<String> paths = new ArrayList<String>();

        if (sourcePath != null && !(new File(sourcePath).isDirectory())) {
            DexFile dexfile = new DexFile(sourcePath);
            Enumeration<String> entries = dexfile.entries();

            while (entries.hasMoreElements()) {
                String path = entries.nextElement();
                for (int i = 0; i < pakageList.length; i++) {
                    if (path.contains(pakageList[i])) {
                        paths.add(path);
                        break;
                    }
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
                    for (int i = 0; i < pakageList.length; i++) {
                        if (path.contains(pakageList[i])) {
                            paths.add(path);
                            break;
                        }
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
