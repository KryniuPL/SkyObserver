package com.skyobserver.config;


import com.skyobserver.model.Airline;
import com.skyobserver.model.CachedPrice;
import org.ehcache.CacheManager;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.concurrent.TimeUnit;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;


public class CacheConfiguration {

    public static CacheManager pricesCacheManager() {
        return newCacheManagerBuilder()
                .withCache("cachedFlightPrices", newCacheConfigurationBuilder(String.class, CachedPrice.class, heap(10))
                        .withExpiry(Expirations.timeToIdleExpiration(Duration.of(6, TimeUnit.HOURS)))
                        .withSizeOfMaxObjectSize(400, MemoryUnit.MB))
                .build(true);
    }

    public static CacheManager airlinesCacheManager() {
        return newCacheManagerBuilder()
                .withCache("cachedAirlines", newCacheConfigurationBuilder(String.class, Airline.class, heap(10))
                        .withExpiry(Expirations.timeToIdleExpiration(Duration.of(6, TimeUnit.DAYS)))
                        .withSizeOfMaxObjectSize(1, MemoryUnit.GB))
                .build(true);
    }

}
