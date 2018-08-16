package com.bitmain.hale.springmvc.config;


import com.bitmain.hale.springmvc.controller.DoController;
import com.bitmain.hale.springmvc.controller.LoginController;
import com.bitmain.hale.springmvc.dao.AccountDao;
import com.bitmain.hale.springmvc.dao.DataDAo;
import com.bitmain.hale.springmvc.service.impl.BusinessServiceImplA;
import com.bitmain.hale.springmvc.service.impl.BusinessServiceImplB;
import com.bitmain.hale.springmvc.service.impl.LoginServiceImpl;

import java.util.ArrayList;

/**
 * Created by harry.ding on 2018/8/13.
 */

public class BeanManager {
    private static ArrayList<Class<?>> beans = new ArrayList<>();

    public static ArrayList<Class<?>> getBeans() {
        return beans;
    }

    //配置需要加载的impl实现类
    static {
        beans.add(BusinessServiceImplA.class);
        beans.add(BusinessServiceImplB.class);
        beans.add(LoginServiceImpl.class);
        beans.add(DoController.class);
        beans.add(LoginController.class);
        beans.add(DataDAo.class);
        beans.add(AccountDao.class);
    }
}
