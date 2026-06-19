package com.spring;

public class RightApplicationContext {

    private Class configClass;

    public RightApplicationContext(Class configClass){
        this.configClass = configClass;


    }

    public Object getBean(String beanName){
        return null;
    }
}
