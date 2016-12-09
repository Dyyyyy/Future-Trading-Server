package com.wave.staticsetting;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Json on 2016/11/14.
 */
public class ContextUtil implements ApplicationContextAware{
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
    static public Object getBean(String beanname){
        return context.getBean(beanname);
    }
}
