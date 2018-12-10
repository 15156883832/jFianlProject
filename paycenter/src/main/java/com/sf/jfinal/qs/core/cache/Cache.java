package com.sf.jfinal.qs.core.cache;


import net.sf.ehcache.Element;

import java.io.Serializable;

public class Cache<K extends Serializable, V> {

    private final net.sf.ehcache.Cache cache;

    public Cache(net.sf.ehcache.Cache cache) {
        this.cache = cache;
    }

    public void put(K key, V value) {
        cache.put(new Element(key, value));
    }

    public void get(K key) {
        cache.get(key);
    }
}
