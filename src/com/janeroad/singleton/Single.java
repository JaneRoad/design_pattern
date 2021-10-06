package com.janeroad.singleton;

import java.util.Objects;

public class Single {
    private volatile static Single instance;

    private Single(){
        System.out.println("创建了Single对象");
    }

    public static synchronized Single getInstance(){
        if (Objects.isNull(instance)){
            synchronized (Single.class){
                instance = new Single();
            }
        }
        return instance;
    }

}
