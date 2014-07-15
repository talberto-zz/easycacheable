package com.github.talberto.easycacheable.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Dummy implementation of a Cache.
 * 
 * @author Tomás Rodríguez (rstomasalberto@gmail.com)
 * 
 * @param <K>
 * @param <V>
 */
public class DefaultCache<K, V> extends ConcurrentHashMap<K, V> implements Cache<K, V> {

  /**
   * 
   */
  private static final long serialVersionUID = 2676738106898852576L;

  @Override
  public String getName() {
    throw new UnsupportedOperationException();
  }

  @Override
  public V put(K pKey, V pValue, long pLifespan, TimeUnit pUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V putIfAbsent(K pKey, V pValue, long pLifespan, TimeUnit pUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> pMap, long pLifespan, TimeUnit pUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V replace(K pKey, V pValue, long pLifespan, TimeUnit pUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean replace(K pKey, V pOldValue, V pValue, long pLifespan, TimeUnit pUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V put(K pKey, V pValue, long pLifespan, TimeUnit pLifespanUnit, long pMaxIdleTime, TimeUnit pMaxIdleTimeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V putIfAbsent(K pKey, V pValue, long pLifespan, TimeUnit pLifespanUnit, long pMaxIdleTime, TimeUnit pMaxIdleTimeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> pMap, long pLifespan, TimeUnit pLifespanUnit, long pMaxIdleTime,
      TimeUnit pMaxIdleTimeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V replace(K pKey, V pValue, long pLifespan, TimeUnit pLifespanUnit, long pMaxIdleTime, TimeUnit pMaxIdleTimeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean replace(K pKey, V pOldValue, V pValue, long pLifespan, TimeUnit pLifespanUnit, long pMaxIdleTime, TimeUnit pMaxIdleTimeUnit) {
    throw new UnsupportedOperationException();
  }
}
