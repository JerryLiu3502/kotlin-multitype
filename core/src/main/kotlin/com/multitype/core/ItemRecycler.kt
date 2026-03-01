package com.multitype.core

/**
 * Item recycler for recycling items.
 */
class ItemRecycler(private val maxSize: Int = 50) {
    private val recycledItems = mutableListOf<Any>()
    
    /**
     * Recycle an item.
     */
    fun recycle(item: Any) {
        if (recycledItems.size >= maxSize) {
            recycledItems.removeAt(0)
        }
        recycledItems.add(item)
    }
    
    /**
     * Get a recycled item or null.
     */
    fun obtain(): Any? {
        return if (recycledItems.isNotEmpty()) {
            recycledItems.removeAt(recycledItems.size - 1)
        } else null
    }
    
    /**
     * Get recycled item count.
     */
    fun getRecycledCount(): Int = recycledItems.size
    
    /**
     * Clear all recycled items.
     */
    fun clear() = recycledItems.clear()
    
    /**
     * Check if has recycled items.
     */
    fun hasRecycled(): Boolean = recycledItems.isNotEmpty()
}

/**
 * Builder for ItemRecycler.
 */
object ItemRecyclerBuilder {
    fun create(maxSize: Int = 50): ItemRecycler = ItemRecycler(maxSize)
}
