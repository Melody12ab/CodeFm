package com.melody.mem;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

/**
 * Created by xiaobai on 15-11-11.
 */
public class MemcachedCache {
    private ICacheManager<IMemcachedCache> manager;
    private IMemcachedCache cache;
    public MemcachedCache(){
        manager=CacheUtil.getCacheManager(IMemcachedCache.class,MemcachedCacheManager.class.getName());
        manager.setConfigFile("memcached.xml");
        manager.setResponseStatInterval(5*1000);
        manager.start();
        cache=manager.getCache("mclient_0");
    }

    public IMemcachedCache getCache(){
        return cache;
    }

    public void put(String key,Object object){
        cache.put(key,object);
    }

    public Object get(String key){
        return cache.get(key);
    }

}

