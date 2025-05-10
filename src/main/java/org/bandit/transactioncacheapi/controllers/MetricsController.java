package org.bandit.transactioncacheapi.controllers;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
    private final CacheManager cacheManager;

    public MetricsController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/cache")
    public Map<String, Object> getCacheMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        Cache transactionsCache = cacheManager.getCache("transactions");
        if (transactionsCache instanceof CaffeineCache caffeineCache) {
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
            CacheStats cacheStats = nativeCache.stats();
            metrics.put("hitCount", cacheStats.hitCount());
            metrics.put("missCount", cacheStats.missCount());
            metrics.put("hitRate", cacheStats.hitRate());
            metrics.put("missRate", cacheStats.missRate());
            metrics.put("evictionCount", cacheStats.evictionCount());
            metrics.put("size", nativeCache.estimatedSize());
        } else {
            metrics.put("error", "Cache is not an instance of CaffeineCache");
        }
        return metrics;
    }
}
