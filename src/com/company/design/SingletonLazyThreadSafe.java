package com.company.design;

/**
 * 双重检查线程安全的懒汉单例
 * @author suhe17
 * @since 2025/3/6
 */
public class SingletonLazyThreadSafe {

    private static volatile SingletonLazyThreadSafe instance;

    private SingletonLazyThreadSafe(){}

    public static SingletonLazyThreadSafe getInstance(){
        // 第一次检查
        if (instance == null){
            synchronized (SingletonLazyThreadSafe.class){
                // 第二次检查
                if (instance == null){
                    instance = new SingletonLazyThreadSafe();
                }
            }
        }
        return instance;
    }
}
