package com.programacion.config;

import com.programacion.db.Singer;
import jakarta.enterprise.context.ApplicationScoped;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.enterprise.inject.Produces;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CacheManager {
    @Produces
    @ApplicationScoped
    public Cache<Integer, Singer> albumCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }
}
