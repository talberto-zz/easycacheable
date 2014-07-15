package com.github.talberto.easycacheable.api;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Kindly borrowed from org.infinispan.commons.api.BasicCache
 * 
 * @author Tomás Rodríguez (rstomasalberto@gmail.com)
 * 
 * @param <K>
 * @param <V>
 */
public interface Cache<K, V> extends ConcurrentMap<K, V> {
  /**
   * Retrieves the name of the cache
   * 
   * @return the name of the cache
   */
  String getName();

  /**
   * {@inheritDoc}
   * 
   * If the return value of this operation will be ignored by the application,
   * the user is strongly encouraged to use the
   * {@link org.infinispan.context.Flag#IGNORE_RETURN_VALUES} flag when invoking
   * this method in order to make it behave as efficiently as possible (i.e.
   * avoiding needless remote or network calls).
   */
  @Override
  V put(K key, V value);

  /**
   * An overloaded form of {@link #put(Object, Object)}, which takes in lifespan
   * parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param unit
   *          unit of measurement for the lifespan
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V put(K key, V value, long lifespan, TimeUnit unit);

  /**
   * An overloaded form of {@link #putIfAbsent(Object, Object)}, which takes in
   * lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param unit
   *          unit of measurement for the lifespan
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V putIfAbsent(K key, V value, long lifespan, TimeUnit unit);

  /**
   * An overloaded form of {@link #putAll(Map)}, which takes in lifespan
   * parameters. Note that the lifespan is applied to all mappings in the map
   * passed in.
   * 
   * @param map
   *          map containing mappings to enter
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param unit
   *          unit of measurement for the lifespan
   */
  void putAll(Map<? extends K, ? extends V> map, long lifespan, TimeUnit unit);

  /**
   * An overloaded form of {@link #replace(Object, Object)}, which takes in
   * lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param unit
   *          unit of measurement for the lifespan
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V replace(K key, V value, long lifespan, TimeUnit unit);

  /**
   * An overloaded form of {@link #replace(Object, Object, Object)}, which takes
   * in lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param oldValue
   *          value to replace
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param unit
   *          unit of measurement for the lifespan
   * @return true if the value was replaced, false otherwise
   */
  boolean replace(K key, V oldValue, V value, long lifespan, TimeUnit unit);

  /**
   * An overloaded form of {@link #put(Object, Object)}, which takes in lifespan
   * parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param lifespanUnit
   *          time unit for lifespan
   * @param maxIdleTime
   *          the maximum amount of time this key is allowed to be idle for
   *          before it is considered as expired
   * @param maxIdleTimeUnit
   *          time unit for max idle time
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V put(K key, V value, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit);

  /**
   * An overloaded form of {@link #putIfAbsent(Object, Object)}, which takes in
   * lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param lifespanUnit
   *          time unit for lifespan
   * @param maxIdleTime
   *          the maximum amount of time this key is allowed to be idle for
   *          before it is considered as expired
   * @param maxIdleTimeUnit
   *          time unit for max idle time
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V putIfAbsent(K key, V value, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit);

  /**
   * An overloaded form of {@link #putAll(Map)}, which takes in lifespan
   * parameters. Note that the lifespan is applied to all mappings in the map
   * passed in.
   * 
   * @param map
   *          map containing mappings to enter
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param lifespanUnit
   *          time unit for lifespan
   * @param maxIdleTime
   *          the maximum amount of time this key is allowed to be idle for
   *          before it is considered as expired
   * @param maxIdleTimeUnit
   *          time unit for max idle time
   */
  void putAll(Map<? extends K, ? extends V> map, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit);

  /**
   * An overloaded form of {@link #replace(Object, Object)}, which takes in
   * lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param lifespanUnit
   *          time unit for lifespan
   * @param maxIdleTime
   *          the maximum amount of time this key is allowed to be idle for
   *          before it is considered as expired
   * @param maxIdleTimeUnit
   *          time unit for max idle time
   * @return the value being replaced, or null if nothing is being replaced.
   */
  V replace(K key, V value, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit);

  /**
   * An overloaded form of {@link #replace(Object, Object, Object)}, which takes
   * in lifespan parameters.
   * 
   * @param key
   *          key to use
   * @param oldValue
   *          value to replace
   * @param value
   *          value to store
   * @param lifespan
   *          lifespan of the entry. Negative values are interpreted as
   *          unlimited lifespan.
   * @param lifespanUnit
   *          time unit for lifespan
   * @param maxIdleTime
   *          the maximum amount of time this key is allowed to be idle for
   *          before it is considered as expired
   * @param maxIdleTimeUnit
   *          time unit for max idle time
   * @return true if the value was replaced, false otherwise
   */
  boolean replace(K key, V oldValue, V value, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit);

  /**
   * {@inheritDoc}
   * 
   * If the return value of this operation will be ignored by the application,
   * the user is strongly encouraged to use the
   * {@link org.infinispan.context.Flag#IGNORE_RETURN_VALUES} flag when invoking
   * this method in order to make it behave as efficiently as possible (i.e.
   * avoiding needless remote or network calls).
   */
  @Override
  V remove(Object key);
}
