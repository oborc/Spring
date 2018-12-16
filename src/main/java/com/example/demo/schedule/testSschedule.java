package com.example.demo.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

@Component
public class testSschedule {

    @Resource
    private CacheManager myCacheManager;

    private final Logger LOG = LoggerFactory.getLogger(testSschedule.class);

    @Scheduled(cron = "0 */10 * * * *")
    public void deletCacheByMinutes(){
        LOG.info("entering deleting schedule...");
        ConcurrentMapCache tableCache = (ConcurrentMapCache)myCacheManager.getCache("userCache");
        ConcurrentMap<String,List> cacheMap =(ConcurrentMap) tableCache.getNativeCache();
        if (cacheMap!=null)
        {
            Collection<String> keySet = cacheMap.keySet();
            for(String key: keySet)
            {
                Date date = new Date();
                date.setMinutes(date.getMinutes()-1);
                if(((Date) cacheMap.get(key).get(1)).before(date))
                {
                    LOG.info("deleting "+key+"'s "+"value"+cacheMap.get(key));
                    cacheMap.remove(key);
                }
            }
        }

    }
}
