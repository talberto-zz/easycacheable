package com.github.talberto.easycacheable.api;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.basic.AccessibleInstantiator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Tomás Rodríguez (rstomasalberto@gmail.com)
 *
 */
public abstract class CacheProvider {

  protected static Logger sLogger = LoggerFactory.getLogger(CacheProvider.class);
  
  /**
   * 
   * @throws IllegalArgumentException
   * @return
   */
  public static CacheProvider newInstance() {
    // Try with system properties first
    sLogger.debug("Searching a CacheProvider in system property [com.github.talberto.easycacheable.api.CacheProvider]");
    String cacheProviderClassname = System.getProperty("com.github.talberto.easycacheable.api.CacheProvider");
       
    if(cacheProviderClassname != null) {
      sLogger.debug("System property com.github.talberto.easycacheable.api.CacheProvider is defined with value [{}]. Will try to load an instantiate the class", cacheProviderClassname);
      Class<?> cacheProviderClass;
      try {
        cacheProviderClass = Class.forName(cacheProviderClassname);
      } catch (ClassNotFoundException e) {
        sLogger.error("Couldn't load the class [{}]", cacheProviderClassname);
        throw new IllegalArgumentException(String.format("The class [%s] couldn't be loaded", cacheProviderClassname), e);
      }
      @SuppressWarnings({ "rawtypes", "unchecked" })
      ObjectInstantiator<CacheProvider> instantiator = new AccessibleInstantiator(cacheProviderClass);
      CacheProvider instance;
      try {
        instance = instantiator.newInstance();
      } catch (Exception e) {
        sLogger.error("Couldn't instantiate the class [{}]", cacheProviderClassname);
        throw new IllegalArgumentException(String.format("The class [%s] couldn't be instantiated", cacheProviderClassname), e);
      }
      return instance;
    }
    
    // If the system property isn't defined, try with the ServiceLoader
    sLogger.debug("The system property com.github.talberto.easycacheable.api.CacheProvider is null, will try using ServiceLoader");
    ServiceLoader<CacheProvider> serviceLoader = ServiceLoader.load(CacheProvider.class);
    // Pick the first available
    Iterator<CacheProvider> it = serviceLoader.iterator();
    if(it.hasNext()) {
      CacheProvider instance = it.next();
      sLogger.debug("ServiceLoader found at least one implementing class [{}]", instance.getClass().getSimpleName());
      return instance;
    }
    
    // Instantiate the default cache provider
    sLogger.debug("Neither the system property com.github.talberto.easycacheable.api.CacheProvider, nor the ServiceLoader provided a CacheProvider. Instantiating a DefaultCacheProvider");
    return new DefaultCacheProvider();
  }
  
  /**
   * Retrieves the default cache associated with this cache container.
   * <p/>
   * As such, this method is always guaranteed to return the default cache.
   * <p />
   * <b>NB:</b> Shared caches are supported (and in fact encouraged) but if they are used it's the users responsibility to
   * ensure that <i>at least one</i> but <i>only one</i> caller calls stop() on the cache, and it does so with the awareness
   * that others may be using the cache.
   *
   * @return the default cache.
   */
  public abstract <K, V> Cache<K, V> getCache();

  /**
   * Retrieves a named cache from the system.  If the cache has been previously created with the same name, the running
   * cache instance is returned.  Otherwise, this method attempts to create the cache first.
   * <p/>
   * In the case of a {@link org.infinispan.manager.EmbeddedCacheManager}: when creating a new cache, this method will
   * use the configuration passed in to the EmbeddedCacheManager on construction, as a template, and then optionally
   * apply any overrides previously defined for the named cache using the {@link EmbeddedCacheManager#defineConfiguration(String, org.infinispan.config.Configuration)}
   * or {@link EmbeddedCacheManager#defineConfiguration(String, String, org.infinispan.config.Configuration)}
   * methods, or declared in the configuration file.
   * <p />
   * <b>NB:</b> Shared caches are supported (and in fact encouraged) but if they are used it's the users responsibility to
   * ensure that <i>at least one</i> but <i>only one</i> caller calls stop() on the cache, and it does so with the awareness
   * that others may be using the cache.
   *
   * @param cacheName name of cache to retrieve
   * @return a cache instance identified by cacheName
   */
  public abstract <K, V> Cache<K, V> getCache(String cacheName);
}
