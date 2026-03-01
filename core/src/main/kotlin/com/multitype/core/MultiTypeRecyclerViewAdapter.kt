package com.multitype.core

/**
 * A more complete MultiTypeAdapter that integrates with RecyclerView.
 * 
 * This is similar to Drakeet's MultiTypeAdapter.
 */
class MultiTypeRecyclerViewAdapter {
    
    private val typePool = TypePool()
    private val items = mutableListOf<Any>()
    
    /**
     * Register a binder for a specific class type.
     * The type will be assigned automatically.
     */
    fun <T : ItemBinder> register(binder: T): T {
        typePool.register(binder)
        return binder
    }
    
    /**
     * Register a binder for a specific type.
     */
    fun <T : ItemBinder> register(type: Int, binder: T): T {
        typePool.register(type, binder)
        return binder
    }
    
    /**
     * Add an item to the adapter.
     */
    fun addItem(item: Any) {
        items.add(item)
    }
    
    /**
     * Add multiple items to the adapter.
     */
    fun addItems(newItems: List<Any>) {
        items.addAll(newItems)
    }
    
    /**
     * Insert an item at a specific position.
     */
    fun insertItem(position: Int, item: Any) {
        items.add(position, item)
    }
    
    /**
     * Remove an item at a specific position.
     */
    fun removeItem(position: Int): Any {
        return items.removeAt(position)
    }
    
    /**
     * Clear all items.
     */
    fun clearItems() {
        items.clear()
    }
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = items[position]
    
    /**
     * Get all items.
     */
    fun getItems(): List<Any> = items.toList()
    
    /**
     * Get item count.
     */
    fun getItemCount(): Int = items.size
    
    /**
     * Get the view type for a position.
     */
    fun getItemViewType(position: Int): Int {
        val item = items[position]
        val itemClass = item::class.java
        
        val binder = typePool.getBinder(itemClass)
            ?: throw IllegalArgumentException(
                "No binder found for item: ${itemClass.name}. " +
                "Please register a binder using register() method."
            )
        
        return typePool.getType(itemClass) 
            ?: throw IllegalStateException("Type not found for binder")
    }
    
    /**
     * Bind data to the ViewHolder.
     */
    fun onBindViewHolder(holder: Any, position: Int) {
        val item = items[position]
        val viewType = getItemViewType(position)
        val binder = typePool.getBinder(viewType) ?: return
        
        binder.bind(holder, item, position)
    }
    
    /**
     * Set items directly (replaces all items).
     */
    fun setItems(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
    }
}
