package com.github.talberto.easycacheable.aop;

import com.github.talberto.easycacheable.api.CacheProvider;
import com.github.talberto.easycacheable.api.Cacheable;
import com.github.talberto.easycacheable.api.Cache;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.List;

aspect CacheableAspect {

  private interface HasCache {};
  private Cache HasCache.cache;
  
  public Cache HasCache.getCache() {
    if(cache == null) {
      CacheProvider provider = CacheProvider.newInstance();
      cache = provider.getCache();
    }
    return cache;
  }
  
  declare parents: (@Cacheable *) implements HasCache;
  
  declare error: execution(@Cacheable * *(..)) && !within(@Cacheable *)
    : "Cacheable must be used at class level too";
  
  pointcut cacheableExecution():
    execution(@Cacheable * *(..));
  
  Object around(): cacheableExecution() {
    HasCache hasCache = (HasCache) thisJoinPoint.getThis();
    Cache cache = hasCache.getCache();
    
    // Check if we already computed the result
    List argList = Lists.newArrayList(thisJoinPoint.getArgs());
    Object result = cache.get(argList);
    System.out.println(String.format("Cached value: [%s]=%s", Joiner.on(",").join(argList), result));
    if(result == null) { // If the result wasn't computed, compute it and store it in cache
      result = proceed();
      System.out.println(String.format("Computed value: [%s]=%s", Joiner.on(",").join(thisJoinPoint.getArgs()), result));
      cache.put(argList, result);
    }
    return result;
  }
}