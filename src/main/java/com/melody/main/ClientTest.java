package com.melody.main;

/**
 * Created by xiaobai on 15-11-10.
 */

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;
import com.melody.Vo.TestBean;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    public static void main(String[] args) {
        ICacheManager<IMemcachedCache> manager;
        manager = CacheUtil.getCacheManager(IMemcachedCache.class,
                MemcachedCacheManager.class.getName());
        manager.setConfigFile("memcached.xml");
        manager.start();
        try {
            IMemcachedCache cache = manager.getCache("mclient_0");
            TestBean bean = new TestBean();
            bean.setAge(12);
            bean.setName("lily");
            cache.put("bean", bean);
            TestBean beanClient = (TestBean) cache.get("bean");
            System.out.println(beanClient.getName());

            List<TestBean> lists = new ArrayList<>();
            lists.add(bean);
            cache.put("beanList", lists);
            List<TestBean> listClient = (List<TestBean>) cache.get("beanList");
            if (listClient.size() > 0) {
                TestBean bean4List = listClient.get(0);
                System.out.println(bean4List.getName());
            }
        } finally {
            manager.stop();
        }
    }
}

