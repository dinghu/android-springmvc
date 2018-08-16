package com.bitmain.hale.springmvc.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by harry.ding on 2018/8/13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    //是否加载为单例，默认单例
    boolean singleInstance() default true;

    String name() default "";
}
