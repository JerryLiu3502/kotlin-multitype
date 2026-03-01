package com.multitype.core

/**
 * Item cache for caching items.
 */
class ItemCache(private val maxSize: Int = 100) {
    private val cache = mutableMapOf<Any, Any>()
    
    /**
     * Put an item in cache.
     */
    fun put(key: Any, value: Any) {
        if (cache.size >= maxSize) {
            // Remove oldest entry
            val oldest = cache.keys.first()
            cache.remove(oldest)
        }
        cache[key] = value
    }
    
    /**
     * Get an item from cache.
     */
    fun get(key: Any): Any? = cache[key]
    
    /**
     * Check if cache contains key.
     */
    fun contains(key: Any): Boolean = cache.containsKey(key)
    
    /**
     * Remove an item from cache.
     */
    fun remove(key: Any): Any? = cache.remove(key)
    
    /**
     * Clear the cache.
     */
    fun clear() = cache.clear()
    
    /**
     * Get cache size.
     */
    fun size(): Int = cache.size
    
    /**
     * Get all keys.
     */
    fun keys(): Set<Any> = cache.keys.toSet()
    
    /**
     * Get all values.
     */
    fun values(): Collection<Any> = cache.values.toList()
}

/**
 * Builder for ItemCache.
 */
object ItemCacheBuilder {
    fun create(maxSize: Int = 100): ItemCache = ItemCache(maxSize)
}
