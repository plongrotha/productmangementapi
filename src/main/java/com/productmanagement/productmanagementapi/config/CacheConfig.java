package com.productmanagement.productmanagementapi.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("employee", Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.HOURS)
                .maximumSize(500)
                .expireAfterAccess(Duration.ofMinutes(5))
                .build());
        cacheManager.registerCustomCache("product", Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.HOURS)
                .maximumSize(500)
                .expireAfterAccess(Duration.ofMinutes(10))
                .build());
        cacheManager.registerCustomCache("category", Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.HOURS)
                .maximumSize(500)
                .expireAfterAccess(Duration.ofMinutes(10))
                .build());
        // return back when it called
        return cacheManager;
    }

}
