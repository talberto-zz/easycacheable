package com.github.talberto.easycacheable.api;

import static com.google.common.base.Preconditions.*;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Default CacheProvider used if no other is available. Uses a HashMap
 * 
 * @author Tomás Rodríguez (rstomasalberto@gmail.com)
 *
 */
public class DefaultCacheProvider extends CacheProvider {

  Map<String, Cache<Object, Object>> namedCaches = Maps.newHashMap();
  Cache<Object, Object> defaultCache = new DefaultCache<Object, Object>();
  
  @SuppressWarnings("unchecked")
  @Override
  public <K, V> Cache<K, V> getCache() {
    return (Cache<K, V>) defaultCache;
  }

  @Override
  public <K, V> Cache<K, V> getCache(String cacheName) {
    @SuppressWarnings("unchecked")
    Cache<K, V> cache = (Cache<K, V>) namedCaches.get(cacheName);
    checkNotNull(cache, "The named cache [%s] is null", cacheName);
    return cache;
  }
}
