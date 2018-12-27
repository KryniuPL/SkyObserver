package com.skyobserver.config;


import com.skyobserver.model.CachedPrice;
import org.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;

@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return newCacheManagerBuilder()
                .withCache("cachedFlightPrices", newCacheConfigurationBuilder(String.class, CachedPrice.class, heap(10)))
                .build(true);
    }
}
