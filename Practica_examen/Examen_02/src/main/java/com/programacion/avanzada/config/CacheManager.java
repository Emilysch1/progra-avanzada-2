package com.programacion.avanzada.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.programacion.avanzada.db.Singer;

import java.util.concurrent.TimeUnit;

public class CacheManager {

    public Cache<Integer, Singer> albumCache() {
        return Caffeine.newBuilder().
                expireAfterWrite(5, TimeUnit.MINUTES).
                maximumSize(100).build();
    }
}