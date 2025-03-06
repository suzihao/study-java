package com.company.design;

/**
 * 单例模式 饿汉
 * @author suhe17
 * @since 2025/3/6
 */
public class SingletonEager {

    /**
     * 单例模式
     */
    private static final SingletonEager INSTANCE = new SingletonEager();

    private SingletonEager(){}

    public static SingletonEager getInstance(){
        return INSTANCE;
    }

}
