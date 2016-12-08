package com.ruzhi.demo.test.test;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by chunlong.wchl on 2015/5/30.
 * 当把 MySingleton对象(通过getInstance方法获得的那个单例对象)序列化到cache后再从内存中读出时,
 * 就有一个全新但跟原来一样的MySingleton对象存在了. 那怎么来维护单例模式呢?这就要用到readResolve方法了. 如下所示:
 * 这样当JVM从内存中反序列化地"组装"一个新对象时,就会自动调用这个 readResolve方法来返回我们指定好的对象了, 单例规则也就得到了保证
 */
public final class MySingleton implements Serializable {
    private MySingleton() {
    }

    private static final MySingleton INSTANCE = new MySingleton();

    public static MySingleton getInstance() {
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException {
        // instead of the object we're on,
        // return the class variable INSTANCE
        return INSTANCE;
    }
}