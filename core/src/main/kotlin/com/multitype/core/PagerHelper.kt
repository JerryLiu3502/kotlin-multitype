package com.multitype.core

/**
 * Helper for ViewPager integration.
 * 
 * This is similar to Drakeet's PagerHelper.
 */
class PagerHelper {
    
    private val items = mutableListOf<Any>()
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = items[position]
    
    /**
     * Get current position.
     */
    fun getCurrentPosition(): Int = currentPosition
    
    /**
     * Set current position.
     */
    fun setCurrentPosition(position: Int) {
        currentPosition = position
    }
    
    /**
     * Get item count.
     */
    fun getCount(): Int = items.size
    
    /**
     * Get all items.
     */
    fun getItems(): List<Any> = items.toList()
    
    /**
     * Set items.
     */
    fun setItems(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
    }
    
    /**
     * Add item.
     */
    fun addItem(item: Any) {
        items.add(item)
    }
    
    /**
     * Remove item.
     */
    fun removeItem(item: Any): Boolean {
        return items.remove(item)
    }
    
    /**
     * Clear all items.
     */
    fun clear() {
        items.clear()
        currentPosition = 0
    }
    
    private var currentPosition = 0
}

/**
 * Builder for PagerHelper.
 */
object PagerHelperBuilder {
    
    /**
     * Create a PagerHelper.
     */
    fun create(): PagerHelper {
        return PagerHelper()
    }
    
    /**
     * Create a PagerHelper with items.
     */
    fun create(items: List<Any>): PagerHelper {
        val helper = PagerHelper()
        helper.setItems(items)
        return helper
    }
}
