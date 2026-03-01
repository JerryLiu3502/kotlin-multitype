package com.multitype.core

/**
 * Provider for creating and managing adapters.
 * 
 * This is similar to Drakeet's MultiTypePool.
 */
class MultiTypePool {
    
    private val pools = mutableMapOf<String, MultiTypeRecyclerViewAdapter>()
    
    /**
     * Get or create an adapter with the given name.
     */
    fun getAdapter(name: String): MultiTypeRecyclerViewAdapter {
        return pools.getOrPut(name) { MultiTypeRecyclerViewAdapter() }
    }
    
    /**
     * Remove an adapter by name.
     */
    fun removeAdapter(name: String): Boolean {
        return pools.remove(name) != null
    }
    
    /**
     * Clear all adapters.
     */
    fun clear() {
        pools.clear()
    }
    
    /**
     * Get all adapter names.
     */
    fun getAdapterNames(): Set<String> = pools.keys
    
    /**
     * Get the number of adapters.
     */
    fun size(): Int = pools.size
}

/**
 * Global singleton for MultiTypePool.
 */
object GlobalMultiTypePool {
    
    private val pool = MultiTypePool()
    
    /**
     * Get the global pool instance.
     */
    fun getInstance(): MultiTypePool = pool
    
    /**
     * Get or create an adapter from the global pool.
     */
    fun getAdapter(name: String): MultiTypeRecyclerViewAdapter {
        return pool.getAdapter(name)
    }
    
    /**
     * Clear the global pool.
     */
    fun clear() {
        pool.clear()
    }
}
