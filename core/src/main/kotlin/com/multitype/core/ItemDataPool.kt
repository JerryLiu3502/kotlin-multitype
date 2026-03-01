package com.multitype.core

/**
 * Data class for holding item information.
 */
data class ItemData(
    val item: Any,
    val position: Int,
    val viewType: Int
)

/**
 * ItemDataPool - a pool for managing item data.
 * 
 * This is useful for caching and reusing item data.
 */
class ItemDataPool(
    private val initialCapacity: Int = 10
) {
    private val pool = mutableListOf<ItemData>()
    
    /**
     * Add an item to the pool.
     */
    fun add(itemData: ItemData) {
        pool.add(itemData)
    }
    
    /**
     * Get an item from the pool.
     */
    fun get(index: Int): ItemData? {
        return if (index in pool.indices) pool[index] else null
    }
    
    /**
     * Get all items from the pool.
     */
    fun getAll(): List<ItemData> = pool.toList()
    
    /**
     * Clear the pool.
     */
    fun clear() {
        pool.clear()
    }
    
    /**
     * Get the size of the pool.
     */
    fun size(): Int = pool.size
    
    /**
     * Check if the pool is empty.
     */
    fun isEmpty(): Boolean = pool.isEmpty()
    
    /**
     * Check if the pool is not empty.
     */
    fun isNotEmpty(): Boolean = pool.isNotEmpty()
}

/**
 * Builder for ItemDataPool.
 */
object ItemDataPoolBuilder {
    /**
     * Create an ItemDataPool with the given initial capacity.
     */
    fun create(capacity: Int = 10): ItemDataPool {
        return ItemDataPool(capacity)
    }
    
    /**
     * Create an ItemDataPool from a list of items and their types.
     */
    fun from(adapter: MultiTypeRecyclerViewAdapter): ItemDataPool {
        val pool = ItemDataPool(adapter.getItemCount())
        for (i in 0 until adapter.getItemCount()) {
            pool.add(ItemData(
                item = adapter.getItem(i),
                position = i,
                viewType = adapter.getItemViewType(i)
            ))
        }
        return pool
    }
}
